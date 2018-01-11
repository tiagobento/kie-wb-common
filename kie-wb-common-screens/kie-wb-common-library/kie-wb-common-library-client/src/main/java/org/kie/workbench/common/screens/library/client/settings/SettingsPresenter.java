/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.library.client.settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import elemental2.dom.HTMLElement;
import elemental2.promise.Promise;
import org.guvnor.common.services.project.client.repositories.ConflictingRepositoriesPopup;
import org.guvnor.common.services.project.context.ProjectContext;
import org.guvnor.common.services.project.service.DeploymentMode;
import org.guvnor.common.services.project.service.GAVAlreadyExistsException;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.kie.workbench.common.screens.library.client.perspective.LibraryPerspective;
import org.kie.workbench.common.screens.library.client.settings.util.ListItemPresenter;
import org.kie.workbench.common.screens.library.client.settings.util.ListPresenter;
import org.kie.workbench.common.screens.library.client.settings.util.UberElementalListItem;
import org.kie.workbench.common.screens.projecteditor.model.ProjectScreenModel;
import org.kie.workbench.common.screens.projecteditor.service.ProjectScreenService;
import org.kie.workbench.common.widgets.client.callbacks.CommandWithThrowableDrivenErrorCallback;
import org.kie.workbench.common.widgets.client.callbacks.CommandWithThrowableDrivenErrorCallback.CommandWithThrowable;
import org.uberfire.annotations.Customizable;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberElemental;
import org.uberfire.ext.editor.commons.client.file.popups.SavePopUpPresenter;
import org.uberfire.ext.widgets.common.client.callbacks.DefaultErrorCallback;
import org.uberfire.ext.widgets.common.client.common.HasBusyIndicator;
import org.uberfire.workbench.events.NotificationEvent;

import static java.util.stream.Collectors.toList;
import static org.kie.workbench.common.screens.library.client.settings.Promises.all;
import static org.kie.workbench.common.screens.library.client.settings.Promises.promisify;
import static org.kie.workbench.common.screens.library.client.settings.Promises.resolve;
import static org.kie.workbench.common.screens.library.client.settings.Promises.throwOrExecute;
import static org.uberfire.ext.widgets.common.client.common.ConcurrentChangePopup.newConcurrentUpdate;
import static org.uberfire.workbench.events.NotificationEvent.NotificationType.ERROR;
import static org.uberfire.workbench.events.NotificationEvent.NotificationType.SUCCESS;

@WorkbenchScreen(identifier = "project-settings",
        owningPerspective = LibraryPerspective.class)
public class SettingsPresenter {

    public interface View extends UberElemental<SettingsPresenter>,
                                  HasBusyIndicator {

        void showBusyIndicator();

        void setSection(final Section contentView);

        HTMLElement getMenuItemsContainer();

        String getSaveSuccessMessage();

        String getLoadErrorMessage();

        interface Section<T> extends UberElemental<T>,
                                     IsElement {

            String getTitle();
        }
    }

    private final View view;
    private final Event<NotificationEvent> notificationEvent;
    private final SettingsSections settingsSections;
    private final SavePopUpPresenter savePopUpPresenter;

    private final Caller<ProjectScreenService> projectScreenService;
    private final ProjectContext projectContext;
    private final MenuItemsListPresenter menuItemsListPresenter;
    private final ManagedInstance<ObservablePath> observablePaths;
    private final ConflictingRepositoriesPopup conflictingRepositoriesPopup;

    private ObservablePath pathToPom;
    private ObservablePath.OnConcurrentUpdateEvent concurrentPomUpdateInfo = null;

    private ProjectScreenModel model;
    private Section currentSection;
    private Map<Section, Integer> originalHashCodes;

    @Inject
    public SettingsPresenter(final View view,
                             final Event<NotificationEvent> notificationEvent,
                             final @Customizable SettingsSections settingsSections,
                             final SavePopUpPresenter savePopUpPresenter,
                             final Caller<ProjectScreenService> projectScreenService,
                             final ProjectContext projectContext,
                             final MenuItemsListPresenter menuItemsListPresenter,
                             final ManagedInstance<ObservablePath> observablePaths,
                             final ConflictingRepositoriesPopup conflictingRepositoriesPopup) {
        this.view = view;
        this.notificationEvent = notificationEvent;
        this.settingsSections = settingsSections;
        this.savePopUpPresenter = savePopUpPresenter;

        this.projectScreenService = projectScreenService;
        this.projectContext = projectContext;
        this.menuItemsListPresenter = menuItemsListPresenter;
        this.observablePaths = observablePaths;
        this.conflictingRepositoriesPopup = conflictingRepositoriesPopup;
        this.currentSection = settingsSections.getList().get(0);
    }

    @PostConstruct
    public void setup() {
        view.init(this);
        view.showBusyIndicator();

        if (pathToPom != null) {
            pathToPom.dispose();
        }

        originalHashCodes = new HashMap<>();
        concurrentPomUpdateInfo = null;

        pathToPom = observablePaths.get().wrap(projectContext.getActiveProject().getPomXMLPath());
        pathToPom.onConcurrentUpdate(info -> concurrentPomUpdateInfo = info);

        Promises.promisify(projectScreenService, s -> s.load(pathToPom)).then(model -> {
            this.model = model;
            return Promises.all(getSections(), (final Section section) -> setupSection(model, section));
        }).then(i -> {

            menuItemsListPresenter.setupWithPresenters(
                    view.getMenuItemsContainer(),
                    getSections().stream().map(Section::getMenuItem).collect(toList()),
                    (section, presenter) -> presenter.setup(section, this));

            view.hideBusyIndicator();
            return goTo(currentSection);
        }).catch_(e -> throwOrExecute(e, i -> {
            notificationEvent.fire(new NotificationEvent(view.getLoadErrorMessage(), ERROR));
            view.hideBusyIndicator();
            return resolve();
        })).catch_(this::defaultErrorResolution);
    }

    private Promise<Object> setupSection(final ProjectScreenModel model,
                                         final Section section) {

        return section.setup(model).then(i -> {
            section.getMenuItem().setup(section, this);
            resetDirtyIndicator(section);
            return resolve();
        });
    }

    public void showSaveModal() {
        Promises.reduceLazily(null, getSections(), Section::validate).then(i -> {
            savePopUpPresenter.show(this::save);
            return resolve();
        }).catch_(e -> throwOrExecute(e, (final Section section) -> {
            view.hideBusyIndicator();
            return goTo(section);
        })).catch_(this::defaultErrorResolution);
    }

    private void save(final String comment) {
        Promises.reduceLazilyChaining(null, getSavingSteps(comment), this::executeSavingStep)
                .catch_(e -> throwOrExecute(e, this::goTo))
                .catch_(this::defaultErrorResolution);
    }

    private Promise<Void> executeSavingStep(final Supplier<Promise<Void>> chain,
                                            final SavingStep savingStep) {

        return savingStep.execute(chain);
    }

    private List<SavingStep> getSavingSteps(final String comment) {

        final Stream<SavingStep> saveSectionsSteps =
                getSections().stream()
                        .map(section -> chain -> section.save(comment, chain));

        final Stream<SavingStep> commonSavingSteps =
                Stream.of(chain -> saveProjectScreenModel(comment, DeploymentMode.VALIDATED, chain),
                          chain -> all(getSections(), this::resetDirtyIndicator),
                          chain -> displaySuccessMessage());

        return Stream.concat(saveSectionsSteps, commonSavingSteps).collect(toList());
    }

    private Promise<Void> displaySuccessMessage() {
        view.hideBusyIndicator();
        notificationEvent.fire(new NotificationEvent(view.getSaveSuccessMessage(), SUCCESS));
        return resolve();
    }

    private Promise<Void> resetDirtyIndicator(final Section section) {
        originalHashCodes.put(section, section.currentHashCode());
        updateDirtyIndicator(section);
        return resolve();
    }

    private Promise<Void> saveProjectScreenModel(final String comment,
                                                 final DeploymentMode mode,
                                                 final Supplier<Promise<Void>> chain) {

        return checkConcurrentPomUpdate(comment, chain)
                .then(i -> promisify(projectScreenService,
                                     s -> s.save(pathToPom, model, comment, mode),
                                     onSaveProjectScreenModelError(comment, chain)::error));
    }

    private Promise<Void> checkConcurrentPomUpdate(final String comment,
                                                   final Supplier<Promise<Void>> chain) {

        return new Promise<>((resolve, reject) -> {
            if (this.concurrentPomUpdateInfo == null) {
                resolve.onInvoke(resolve());
            } else {
                newConcurrentUpdate(this.concurrentPomUpdateInfo.getPath(),
                                    this.concurrentPomUpdateInfo.getIdentity(),
                                    () -> forceSave(comment, chain),
                                    () -> {
                                    },
                                    this::setup).show();
                reject.onInvoke(currentSection);
            }
        });
    }

    private ErrorCallback<Message> onSaveProjectScreenModelError(final String comment,
                                                                 final Supplier<Promise<Void>> saveChain) {

        return new CommandWithThrowableDrivenErrorCallback(view, new HashMap<Class<? extends Throwable>, CommandWithThrowable>() {{
            put(GAVAlreadyExistsException.class,
                e -> {
                    view.hideBusyIndicator();
                    conflictingRepositoriesPopup.setContent(model.getPOM().getGav(),
                                                            ((GAVAlreadyExistsException) e).getRepositories(),
                                                            () -> forceSave(comment, saveChain));

                    conflictingRepositoriesPopup.show();
                });
        }});
    }

    private void forceSave(final String comment,
                           final Supplier<Promise<Void>> chain) {

        concurrentPomUpdateInfo = null;
        conflictingRepositoriesPopup.hide();
        saveProjectScreenModel(comment, DeploymentMode.FORCED, chain).then(i -> chain.get());
    }

    private Promise<Void> defaultErrorResolution(final Object e) {
        new DefaultErrorCallback().error(null, (Throwable) e);
        view.hideBusyIndicator();
        return resolve();
    }

    public void onSettingsSectionChanged(@Observes final SettingsSectionChange settingsSectionChange) {
        updateDirtyIndicator(settingsSectionChange.getSection());
    }

    private void updateDirtyIndicator(final Section changedSection) {

        final boolean isDirty = Optional.ofNullable(originalHashCodes.get(changedSection))
                .map(originalHashCode -> !originalHashCode.equals(changedSection.currentHashCode()))
                .orElse(false);

        changedSection.setDirty(isDirty);
    }

    public void reset() {
        setup();
    }

    private Promise<Object> goTo(final Section section) {
        currentSection = section;
        view.setSection(section.getView());
        return resolve();
    }

    private List<Section> getSections() {
        return settingsSections.getList();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Settings Screen";
    }

    @WorkbenchPartView
    public View getView() {
        return view;
    }

    @Dependent
    public static class MenuItemsListPresenter extends ListPresenter<Section, MenuItem> {

        @Inject
        public MenuItemsListPresenter(final ManagedInstance<MenuItem> itemPresenters) {
            super(itemPresenters);
        }
    }

    public static abstract class Section {

        private final Event<SettingsSectionChange> settingsSectionChangeEvent;
        private final SettingsPresenter.MenuItem menuItem;

        protected Section(final Event<SettingsSectionChange> settingsSectionChangeEvent,
                          final MenuItem menuItem) {

            this.settingsSectionChangeEvent = settingsSectionChangeEvent;
            this.menuItem = menuItem;
        }

        public abstract View.Section getView();

        public abstract int currentHashCode();

        public Promise<Void> save(final String comment, final Supplier<Promise<Void>> chain) {
            return resolve();
        }

        public Promise<Object> validate() {
            return resolve();
        }

        public Promise<Void> setup(final ProjectScreenModel model) {
            return resolve();
        }

        public void fireChangeEvent() {
            settingsSectionChangeEvent.fire(new SettingsSectionChange(this));
        }

        public SettingsPresenter.MenuItem getMenuItem() {
            return menuItem;
        }

        public void setDirty(final boolean dirty) {
            menuItem.markAsDirty(dirty);
        }
    }

    @Dependent
    public static class MenuItem extends ListItemPresenter<Section, SettingsPresenter, SettingsPresenter.MenuItem.View> {

        private final SettingsPresenter.MenuItem.View view;

        private Section section;
        private SettingsPresenter settingsPresenter;

        @Inject
        public MenuItem(final SettingsPresenter.MenuItem.View view) {
            super(view);
            this.view = view;
        }

        public void showSection() {
            settingsPresenter.goTo(section);
        }

        public void markAsDirty(final boolean dirty) {
            view.markAsDirty(dirty);
        }

        @Override
        public MenuItem setup(final Section section,
                              final SettingsPresenter settingsPresenter) {

            this.section = section;
            this.settingsPresenter = settingsPresenter;

            this.view.init(this);
            this.view.setLabel(section.getView().getTitle());

            return this;
        }

        @Override
        public Section getObject() {
            return section;
        }

        public interface View extends UberElementalListItem<MenuItem>,
                                      IsElement {

            void setLabel(final String label);

            void markAsDirty(final boolean dirty);
        }
    }

    @FunctionalInterface
    private interface SavingStep {

        Promise<Void> execute(final Supplier<Promise<Void>> chain);
    }
}

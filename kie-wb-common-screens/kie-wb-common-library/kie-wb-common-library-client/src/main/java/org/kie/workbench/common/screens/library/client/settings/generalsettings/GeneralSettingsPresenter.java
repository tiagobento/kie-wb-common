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

package org.kie.workbench.common.screens.library.client.settings.generalsettings;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import elemental2.promise.Promise;
import org.guvnor.common.services.project.client.preferences.ProjectScopedResolutionStrategySupplier;
import org.guvnor.common.services.project.model.POM;
import org.guvnor.common.services.project.preferences.GAVPreferences;
import org.jboss.errai.common.client.api.Caller;
import org.uberfire.client.promise.Promises;
import org.kie.workbench.common.screens.library.client.settings.SettingsPresenter;
import org.kie.workbench.common.screens.library.client.settings.SettingsSectionChange;
import org.kie.workbench.common.screens.projecteditor.model.ProjectScreenModel;
import org.kie.workbench.common.services.shared.validation.ValidationService;

public class GeneralSettingsPresenter extends SettingsPresenter.Section {

    public interface View extends SettingsPresenter.View.Section<GeneralSettingsPresenter> {

        String getName();

        String getDescription();

        String getURL();

        String getGroupId();

        String getArtifactId();

        String getVersion();

        Boolean getConflictingGAVCheckDisabled();

        Boolean getChildGavEditEnabled();

        void setName(String name);

        void setDescription(String description);

        void setURL(String url);

        void setGroupId(String groupId);

        void setArtifactId(String artifactId);

        void setVersion(String version);

        void showError(String message);

        void setConflictingGAVCheckDisabled(boolean value);

        void setChildGavEditEnabled(boolean value);

        void hideError();

        String getEmptyNameMessage();

        String getInvalidNameMessage();

        String getEmptyGroupIdMessage();

        String getInvalidGroupIdMessage();

        String getEmptyArtifactIdMessage();

        String getInvalidArtifactIdMessage();

        String getEmptyVersionMessage();

        String getInvalidVersionMessage();
    }

    private final View view;
    private final Caller<ValidationService> validationService;
    private final GAVPreferences gavPreferences;
    private final ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier;

    POM pom;

    @Inject
    public GeneralSettingsPresenter(final View view,
                                    final Promises promises,
                                    final SettingsPresenter.MenuItem menuItem,
                                    final Caller<ValidationService> validationService,
                                    final Event<SettingsSectionChange> settingsSectionChangeEvent,
                                    final GAVPreferences gavPreferences,
                                    final ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier) {

        super(settingsSectionChangeEvent, menuItem, promises);
        this.view = view;
        this.validationService = validationService;
        this.gavPreferences = gavPreferences;
        this.projectScopedResolutionStrategySupplier = projectScopedResolutionStrategySupplier;
    }

    // Save

    @Override
    public Promise<Void> setup(final ProjectScreenModel model) {

        pom = model.getPOM();

        view.init(this);
        view.setName(pom.getName());
        view.setDescription(pom.getDescription());
        view.setURL(pom.getUrl() != null ? pom.getUrl() : "");
        view.setGroupId(pom.getGav().getGroupId());
        view.setArtifactId(pom.getGav().getArtifactId());
        view.setVersion(pom.getGav().getVersion());

        return promises.create((resolve, reject) -> {
            gavPreferences.load(projectScopedResolutionStrategySupplier.get(),
                                gavPreferences -> {
                                    view.setConflictingGAVCheckDisabled(gavPreferences.isConflictingGAVCheckDisabled());
                                    view.setChildGavEditEnabled(gavPreferences.isChildGAVEditEnabled());

                                    resolve.onInvoke(promises.resolve());
                                },
                                reject::onInvoke);
        });
    }

    @Override
    public Promise<Object> validate() {
        view.hideError();

        return promises.all(

                validateStringIsNotEmpty(pom.getName(), view.getEmptyNameMessage())
                        .then(o -> executeValidation(s -> s.isProjectNameValid(pom.getName()), view.getInvalidNameMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(pom.getGav().getGroupId(), view.getEmptyGroupIdMessage())
                        .then(o -> executeValidation(s -> s.validateGroupId(pom.getGav().getGroupId()), view.getInvalidGroupIdMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(pom.getGav().getArtifactId(), view.getEmptyArtifactIdMessage())
                        .then(o -> executeValidation(s -> s.validateArtifactId(pom.getGav().getArtifactId()), view.getInvalidArtifactIdMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(pom.getGav().getVersion(), view.getEmptyVersionMessage())
                        .then(o -> executeValidation(s -> s.validateGAVVersion(pom.getGav().getVersion()), view.getInvalidVersionMessage()))
                        .catch_(this::showErrorAndReject)
        );
    }

    Promise<Object> showErrorAndReject(final Object o) {
        return promises.catchOrExecute(o, e -> {
            view.showError(e.getMessage());
            return promises.reject(this);
        }, (final String errorMessage) -> {
            view.showError(errorMessage);
            return promises.reject(this);
        });
    }

    Promise<Boolean> validateStringIsNotEmpty(final String string,
                                              final String errorMessage) {

        return promises.create((resolve, reject) -> {
            if (string == null || string.isEmpty()) {
                reject.onInvoke(errorMessage);
            } else {
                resolve.onInvoke(true);
            }
        });
    }

    Promise<Boolean> executeValidation(final Function<ValidationService, Boolean> call,
                                       final String errorMessage) {

        return promises
                .promisify(validationService, call)
                .then(valid -> valid ? promises.resolve(true) : promises.reject(errorMessage));
    }

    void setVersion(final String version) {
        pom.getGav().setVersion(version);
        fireChangeEvent();
    }

    void setArtifactId(final String artifactId) {
        pom.getGav().setArtifactId(artifactId);
        fireChangeEvent();
    }

    void setGroupId(final String groupId) {
        pom.getGav().setGroupId(groupId);
        fireChangeEvent();
    }

    void setUrl(final String url) {
        pom.setUrl(url);
        fireChangeEvent();
    }

    void setDescription(final String description) {
        pom.setDescription(description);
        fireChangeEvent();
    }

    void setName(final String name) {
        pom.setName(name);
        fireChangeEvent();
    }

    void disableGavConflictCheck(final boolean value) {
        gavPreferences.setConflictingGAVCheckDisabled(value);
        fireChangeEvent();
    }

    void allowChildGavEdition(final boolean value) {
        gavPreferences.setChildGAVEditEnabled(value);
        fireChangeEvent();
    }

    @Override
    public Promise<Void> save(final String comment,
                              final Supplier<Promise<Void>> chain) {

        return promises.create((resolve, reject) -> {
            gavPreferences.save(projectScopedResolutionStrategySupplier.get(),
                                () -> resolve.onInvoke(promises.resolve()),
                                (throwable) -> reject.onInvoke(this));
        });
    }

    @Override
    public int currentHashCode() {
        return pom.hashCode() +
                (gavPreferences.isChildGAVEditEnabled() ? 1 : 2) +
                (gavPreferences.isConflictingGAVCheckDisabled() ? 4 : 8);
    }

    @Override
    public SettingsPresenter.View.Section getView() {
        return view;
    }
}

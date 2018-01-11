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
import org.kie.workbench.common.screens.library.client.settings.Promises;
import org.kie.workbench.common.screens.library.client.settings.SettingsPresenter;
import org.kie.workbench.common.screens.library.client.settings.SettingsSectionChange;
import org.kie.workbench.common.screens.projecteditor.model.ProjectScreenModel;
import org.kie.workbench.common.services.shared.validation.ValidationService;

import static elemental2.promise.Promise.reject;
import static org.kie.workbench.common.screens.library.client.settings.Promises.resolve;
import static org.kie.workbench.common.screens.library.client.settings.Promises.throwOrExecute;

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

    private POM pom;

    @Inject
    public GeneralSettingsPresenter(final View view,
                                    final SettingsPresenter.MenuItem menuItem,
                                    final Caller<ValidationService> validationService,
                                    final Event<SettingsSectionChange> settingsSectionChangeEvent,
                                    final GAVPreferences gavPreferences,
                                    final ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier) {

        super(settingsSectionChangeEvent, menuItem);
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

        return new Promise<>((resolve, reject) -> {
            gavPreferences.load(projectScopedResolutionStrategySupplier.get(),
                                gavPreferences -> {
                                    view.setConflictingGAVCheckDisabled(gavPreferences.isConflictingGAVCheckDisabled());
                                    view.setChildGavEditEnabled(gavPreferences.isChildGAVEditEnabled());

                                    resolve.onInvoke(Promises.resolve());
                                },
                                reject::onInvoke);
        });
    }

    @Override
    public Promise<Object> validate() {
        view.hideError();

        return Promises.all(

                validateStringIsNotEmpty(view.getName(), view.getEmptyNameMessage())
                        .then(o -> executeValidation(s -> s.isProjectNameValid(view.getName()), view.getInvalidNameMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(view.getGroupId(), view.getEmptyGroupIdMessage())
                        .then(o -> executeValidation(s -> s.validateGroupId(view.getGroupId()), view.getInvalidGroupIdMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(view.getArtifactId(), view.getEmptyArtifactIdMessage())
                        .then(o -> executeValidation(s -> s.validateArtifactId(view.getArtifactId()), view.getInvalidArtifactIdMessage()))
                        .catch_(this::showErrorAndReject),

                validateStringIsNotEmpty(view.getVersion(), view.getEmptyVersionMessage())
                        .then(o -> executeValidation(s -> s.validateGAVVersion(view.getVersion()), view.getInvalidVersionMessage()))
                        .catch_(this::showErrorAndReject)
        );
    }

    private Promise<Object> showErrorAndReject(final Object e) {
        return throwOrExecute(e, (final String errorMessage) -> {
            view.showError(errorMessage);
            return reject(this);
        });
    }

    private Promise<Boolean> validateStringIsNotEmpty(final String string,
                                                      final String errorMessage) {

        return new Promise<>((resolve, reject) -> {
            if (string == null || string.isEmpty()) {
                reject.onInvoke(errorMessage);
            } else {
                resolve.onInvoke(true);
            }
        });
    }

    private Promise<Boolean> executeValidation(final Function<ValidationService, Boolean> call,
                                               final String errorMessage) {

        return Promises.promisify(validationService,
                                  call,
                                  Promises::throwException,
                                  errorMessage,
                                  isValid -> isValid);
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

        return new Promise<>((resolve, reject) -> {
            gavPreferences.save(projectScopedResolutionStrategySupplier.get(),
                                () -> resolve.onInvoke(resolve()),
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

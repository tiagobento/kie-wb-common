/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.screens.library.client.util.breadcrumb;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.workbench.common.screens.library.client.resources.i18n.LibraryConstants;
import org.kie.workbench.common.screens.library.client.util.LibraryPlaces;
import org.kie.workbench.common.screens.library.client.util.ResourceUtils;
import org.kie.workbench.common.screens.library.client.util.TranslationUtils;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.widgets.common.client.breadcrumbs.UberfireBreadcrumbs;
import org.uberfire.ext.widgets.common.client.breadcrumbs.widget.BreadcrumbPresenter;

import static org.kie.workbench.common.screens.library.client.util.LibraryPlaces.LIBRARY_PERSPECTIVE;

@Dependent
public class LibraryBreadcrumbs {

    private LibraryPlaces libraryPlaces;

    @Inject
    private UberfireBreadcrumbs breadcrumbs;

    @Inject
    private TranslationUtils translationUtils;

    @Inject
    private TranslationService ts;

    @Inject
    private ResourceUtils resourceUtils;

    @Inject
    private ManagedInstance<ProjectBranchBreadcrumb> projectBranchBreadcrumbFactory;

    public void init(final LibraryPlaces libraryPlaces) {
        this.libraryPlaces = libraryPlaces;
    }

    public void clear() {
        breadcrumbs.clearBreadcrumbs(LIBRARY_PERSPECTIVE);
    }

    //Spaces
    public void setupForSpacesScreen() {
        clear();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  translationUtils.getOrganizationalUnitAliasInPlural(),
                                  libraryPlaces::goToSpaces);
    }

    //Spaces -> {spaceName}
    public void setupForSpace(final OrganizationalUnit space) {
        setupForSpacesScreen();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  space.getName(),
                                  libraryPlaces::goToLibrary);
    }

    //Spaces -> {spaceName} -> {projectName} -> {branchName}
    public void setupForProject(final WorkspaceProject project) {

        setupForSpace(project.getOrganizationalUnit());

        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  project.getName(),
                                  libraryPlaces::goToActiveProject);

        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  projectBranchBreadcrumbFactory.get().setup(project));
    }

    //Spaces -> {spaceName} -> Try Samples
    public void setupForTrySamples(final OrganizationalUnit space) {
        setupForSpace(space);
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  ts.getTranslation(LibraryConstants.TrySamples),
                                  libraryPlaces::goToTrySamples);
    }

    //Spaces -> {spaceName} -> Import project
    public void setupForExternalImport(final OrganizationalUnit space) {
        setupForSpace(space);
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  ts.getTranslation(LibraryConstants.ImportProjects),
                                  libraryPlaces::goToImportRepositoryPopUp);
    }

    //Spaces -> {spaceName} -> {projectName} -> {branchName} -> {assetName}
    public void setupForAsset(final WorkspaceProject project, final Path path) {
        setupForProject(project);
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  resourceUtils.getBaseFileName(path),
                                  () -> libraryPlaces.goToAsset(path));
    }
}

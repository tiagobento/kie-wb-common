/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.screens.library.client.screens;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.project.client.security.ProjectController;
import org.guvnor.common.services.project.events.NewProjectEvent;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.structure.client.security.OrganizationalUnitController;
import org.guvnor.structure.events.AfterEditOrganizationalUnitEvent;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.repositories.RepositoryRemovedEvent;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.kie.workbench.common.screens.library.api.LibraryService;
import org.kie.workbench.common.screens.library.client.perspective.LibraryPerspective;
import org.kie.workbench.common.screens.library.client.screens.organizationalunit.contributors.edit.EditContributorsPopUpPresenter;
import org.kie.workbench.common.screens.library.client.screens.organizationalunit.contributors.tab.ContributorsListPresenter;
import org.kie.workbench.common.screens.library.client.screens.organizationalunit.delete.DeleteOrganizationalUnitPopUpPresenter;
import org.kie.workbench.common.screens.library.client.util.LibraryPlaces;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberElement;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.spaces.Space;

@WorkbenchScreen(identifier = LibraryPlaces.LIBRARY_SCREEN,
        owningPerspective = LibraryPerspective.class)
public class LibraryScreen {

    public interface View extends UberElement<LibraryScreen> {

        void setTitle(String title);

        void setProjectsCount(int count);

        void setContributorsCount(int count);

        void updateContent(HTMLElement content);
    }

    protected List<WorkspaceProject> projects;
    private View view;

    private ManagedInstance<EditContributorsPopUpPresenter> editContributorsPopUpPresenters;

    private ManagedInstance<DeleteOrganizationalUnitPopUpPresenter> deleteOrganizationalUnitPopUpPresenters;

    private OrganizationalUnitController organizationalUnitController;

    private WorkspaceProjectContext projectContext;
    private ProjectController projectController;

    private EmptyLibraryScreen emptyLibraryScreen;

    private PopulatedLibraryScreen populatedLibraryScreen;

    private OrgUnitsMetricsScreen orgUnitsMetricsScreen;

    private ContributorsListPresenter contributorsListPresenter;

    private Caller<LibraryService> libraryService;

    private LibraryPlaces libraryPlaces;

    @Inject
    public LibraryScreen(final View view,
                         final ManagedInstance<DeleteOrganizationalUnitPopUpPresenter> deleteOrganizationalUnitPopUpPresenters,
                         final ManagedInstance<EditContributorsPopUpPresenter> editContributorsPopUpPresenters,
                         final WorkspaceProjectContext projectContext,
                         final OrganizationalUnitController organizationalUnitController,
                         final ProjectController projectController,
                         final EmptyLibraryScreen emptyLibraryScreen,
                         final PopulatedLibraryScreen populatedLibraryScreen,
                         final OrgUnitsMetricsScreen orgUnitsMetricsScreen,
                         final ContributorsListPresenter contributorsListPresenter,
                         final Caller<LibraryService> libraryService,
                         final LibraryPlaces libraryPlaces) {
        this.view = view;
        this.deleteOrganizationalUnitPopUpPresenters = deleteOrganizationalUnitPopUpPresenters;
        this.editContributorsPopUpPresenters = editContributorsPopUpPresenters;
        this.projectContext = projectContext;
        this.organizationalUnitController = organizationalUnitController;
        this.projectController = projectController;
        this.emptyLibraryScreen = emptyLibraryScreen;
        this.populatedLibraryScreen = populatedLibraryScreen;
        this.orgUnitsMetricsScreen = orgUnitsMetricsScreen;
        this.contributorsListPresenter = contributorsListPresenter;
        this.libraryService = libraryService;
        this.libraryPlaces = libraryPlaces;
    }

    @PostConstruct
    public void init() {
        view.init(this);
        view.setTitle(projectContext.getActiveOrganizationalUnit()
                              .orElseThrow(() -> new IllegalStateException("Cannot initialize library screen without an active organizational unit.")).getName());
        showProjects();
        view.setContributorsCount(contributorsListPresenter.getContributorsCount());
    }

    public void trySamples() {
        if (userCanCreateProjects()) {
            libraryPlaces.closeAllPlacesOrNothing(() -> {
                                                      libraryPlaces.goToLibrary();
                                                      libraryPlaces.goToTrySamples();
                                                  });
        }
    }

    public void importProject() {
        if (userCanCreateProjects()) {
            libraryPlaces.closeAllPlacesOrNothing(() -> {
                                                      libraryPlaces.goToLibrary();
                                                      libraryPlaces.goToImportRepositoryPopUp();
                                                  });
        }
    }

    public void editContributors() {
        if (userCanUpdateOrganizationalUnit()) {
            final EditContributorsPopUpPresenter editContributorsPopUpPresenter = editContributorsPopUpPresenters.get();
            // Can call get here because condition passed.
            editContributorsPopUpPresenter.show(projectContext.getActiveOrganizationalUnit().get());
        }
    }

    public void delete() {
        if (userCanDeleteOrganizationalUnit()) {
            final DeleteOrganizationalUnitPopUpPresenter deleteOrganizationalUnitPopUpPresenter = deleteOrganizationalUnitPopUpPresenters.get();
            deleteOrganizationalUnitPopUpPresenter.show(projectContext.getActiveOrganizationalUnit()
                                                                .orElseThrow(() -> new IllegalStateException("Cannot delete organizational unit if none is active.")));
        }
    }

    public void showProjects() {

        libraryService.call((Boolean hasProjects) -> {
            if (hasProjects) {
                view.updateContent(populatedLibraryScreen.getView().getElement());
                view.setProjectsCount(populatedLibraryScreen.getProjectsCount());
            } else {
                view.updateContent(emptyLibraryScreen.getView().getElement());
                view.setProjectsCount(0);
            }
        }).hasProjects(projectContext.getActiveOrganizationalUnit()
                               .orElseThrow(() -> new IllegalStateException("Cannot try to query library projects without an active organizational unit.")));
    }

    public void showContributors() {
        view.updateContent(contributorsListPresenter.getView().getElement());
    }

    public void showMetrics() {
        orgUnitsMetricsScreen.refresh();
        view.updateContent(orgUnitsMetricsScreen.getView().getElement());
    }

    public boolean userCanCreateProjects() {
        return projectController.canCreateProjects();
    }

    public boolean userCanUpdateOrganizationalUnit() {
        return organizationalUnitController.canUpdateOrgUnit(projectContext.getActiveOrganizationalUnit()
                                                                     .orElseThrow(() -> new IllegalStateException("Cannot try to update an organizational unit when none is active.")));
    }

    public boolean userCanDeleteOrganizationalUnit() {
        return organizationalUnitController.canDeleteOrgUnit(projectContext.getActiveOrganizationalUnit()
                                                                     .orElseThrow(() -> new IllegalStateException("Cannot try to delete an organizational unit when none is active.")));
    }

    public void organizationalUnitEdited(@Observes final AfterEditOrganizationalUnitEvent afterEditOrganizationalUnitEvent) {
        view.setContributorsCount(afterEditOrganizationalUnitEvent.getEditedOrganizationalUnit().getContributors().size());
    }

    public void onNewProject(@Observes NewProjectEvent e) {
        projectContext.getActiveOrganizationalUnit().ifPresent(p -> {
            if (eventOnCurrentSpace(p, e.getWorkspaceProject().getSpace())) {
                showProjects();
            }
        });
    }

    public void onRepositoryRemovedEvent(@Observes RepositoryRemovedEvent e) {
        projectContext.getActiveOrganizationalUnit().ifPresent(p -> {
            if (eventOnCurrentSpace(p, e.getRepository().getSpace())) {
                showProjects();
            }
        });
    }

    boolean eventOnCurrentSpace(OrganizationalUnit p,
                                Space space) {
        return p.getSpace().getName().equalsIgnoreCase(space.getName());
    }

    @OnClose
    public void onClose() {
        orgUnitsMetricsScreen.onClose();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Library Screen";
    }

    @WorkbenchPartView
    public View getView() {
        return view;
    }
}

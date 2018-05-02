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
package org.kie.workbench.common.screens.library.client.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.ext.uberfire.social.activities.model.ExtendedTypes;
import org.ext.uberfire.social.activities.model.SocialFileSelectedEvent;
import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.project.client.preferences.ProjectScopedResolutionStrategySupplier;
import org.guvnor.common.services.project.context.WorkspaceProjectContextChangeEvent;
import org.guvnor.common.services.project.context.WorkspaceProjectContextChangeHandler;
import org.guvnor.common.services.project.events.RenameModuleEvent;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.common.services.project.service.WorkspaceProjectService;
import org.guvnor.common.services.project.social.ModuleEventType;
import org.guvnor.messageconsole.client.console.MessageConsoleScreen;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.organizationalunit.RemoveOrganizationalUnitEvent;
import org.guvnor.structure.repositories.RepositoryRemovedEvent;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.soup.commons.validation.PortablePreconditions;
import org.kie.workbench.common.screens.examples.model.ExampleProject;
import org.kie.workbench.common.screens.explorer.client.utils.Utils;
import org.kie.workbench.common.screens.library.api.LibraryService;
import org.kie.workbench.common.screens.library.api.ProjectAssetListUpdated;
import org.kie.workbench.common.screens.library.api.Remote;
import org.kie.workbench.common.screens.library.api.Routed;
import org.kie.workbench.common.screens.library.client.events.AssetDetailEvent;
import org.kie.workbench.common.screens.library.client.perspective.LibraryPerspective;
import org.kie.workbench.common.screens.library.client.resources.i18n.LibraryConstants;
import org.kie.workbench.common.screens.library.client.screens.importrepository.ImportProjectsSetupEvent;
import org.kie.workbench.common.screens.library.client.screens.importrepository.ImportRepositoryPopUpPresenter;
import org.kie.workbench.common.screens.library.client.screens.importrepository.Source;
import org.kie.workbench.common.screens.library.client.screens.project.close.CloseUnsavedProjectAssetsPopUpPresenter;
import org.kie.workbench.common.screens.library.client.widgets.library.LibraryToolbarPresenter;
import org.kie.workbench.common.services.shared.project.KieModuleService;
import org.kie.workbench.common.widgets.client.handlers.NewResourceSuccessEvent;
import org.kie.workbench.common.workbench.client.docks.AuthoringWorkbenchDocks;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.VFSService;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.client.promise.Promises;
import org.uberfire.client.workbench.events.PlaceGainFocusEvent;
import org.uberfire.ext.editor.commons.client.event.ConcurrentRenameAcceptedEvent;
import org.uberfire.ext.preferences.client.central.screen.PreferencesRootScreen;
import org.uberfire.ext.preferences.client.event.PreferencesCentralInitializationEvent;
import org.uberfire.ext.preferences.client.event.PreferencesCentralSaveEvent;
import org.uberfire.ext.preferences.client.event.PreferencesCentralUndoChangesEvent;
import org.uberfire.ext.widgets.common.client.breadcrumbs.UberfireBreadcrumbs;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.mvp.impl.PathPlaceRequest;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.events.ResourceCopiedEvent;
import org.uberfire.workbench.model.PartDefinition;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;

import static java.util.Collections.singletonList;
import static org.kie.workbench.common.screens.library.client.screens.importrepository.Source.Kind.EXTERNAL;
import static org.uberfire.workbench.events.NotificationEvent.NotificationType.DEFAULT;

@ApplicationScoped
public class LibraryPlaces implements WorkspaceProjectContextChangeHandler {

    public static final String LIBRARY_PERSPECTIVE = "LibraryPerspective";
    public static final String LIBRARY_SCREEN = "LibraryScreen";
    public static final String PROJECT_SCREEN = "ProjectScreen";
    public static final String IMPORT_PROJECTS_SCREEN = "ImportProjectsScreen";
    public static final String IMPORT_SAMPLE_PROJECTS_SCREEN = "TrySamplesScreen";
    public static final String ORG_UNITS_METRICS_SCREEN = "OrgUnitsMetricsScreen";
    public static final String PROJECT_METRICS_SCREEN = "ProjectMetricsScreen";
    public static final String ORGANIZATIONAL_UNITS_SCREEN = "LibraryOrganizationalUnitsScreen";
    public static final String PROJECT_SETTINGS = "ProjectSettings";
    public static final String PROJECT_EXPLORER = "org.kie.guvnor.explorer";
    public static final String ALERTS = MessageConsoleScreen.ALERTS;
    public static final String REPOSITORY_STRUCTURE_SCREEN = "repositoryStructureScreen";
    public static final String ADD_ASSET_SCREEN = "AddAssetsScreen";

    public static final List<String> LIBRARY_PLACES = Arrays.asList(
            LIBRARY_SCREEN,
            ORG_UNITS_METRICS_SCREEN,
            PROJECT_SCREEN,
            PROJECT_METRICS_SCREEN,
            ORGANIZATIONAL_UNITS_SCREEN,
            PROJECT_SETTINGS,
            ADD_ASSET_SCREEN,
            IMPORT_PROJECTS_SCREEN,
            IMPORT_SAMPLE_PROJECTS_SCREEN,
            PreferencesRootScreen.IDENTIFIER
    );

    private final UberfireBreadcrumbs breadcrumbs;
    private final TranslationService ts;
    private final Event<AssetDetailEvent> assetDetailEvent;
    private final ResourceUtils resourceUtils;
    private final Caller<LibraryService> libraryService;
    private final Caller<WorkspaceProjectService> projectService;
    private final Caller<KieModuleService> moduleService;
    private final PlaceManager placeManager;
    private final WorkspaceProjectContext projectContext;
    private final LibraryToolbarPresenter libraryToolbar;
    private final AuthoringWorkbenchDocks docks;
    private final Event<WorkspaceProjectContextChangeEvent> projectContextChangeEvent;
    private final Event<NotificationEvent> notificationEvent;
    private final TranslationUtils translationUtils;
    private final Caller<VFSService> vfsService;
    private final ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier;
    private final Event<PreferencesCentralInitializationEvent> preferencesCentralInitializationEvent;
    private final ManagedInstance<ImportRepositoryPopUpPresenter> importRepositoryPopUpPresenters;
    private final Event<ProjectAssetListUpdated> assetListUpdatedEvent;
    private final CloseUnsavedProjectAssetsPopUpPresenter closeUnsavedProjectAssetsPopUpPresenter;
    private final Event<ImportProjectsSetupEvent> importProjectsSetupEvent;

    private LibraryPerspective libraryPerspective;
    private boolean docksReady = false;
    private boolean docksHidden = true;
    private boolean closingLibraryPlaces = false;

    @Inject
    public LibraryPlaces(final UberfireBreadcrumbs breadcrumbs,
                         final TranslationService ts,
                         final Event<AssetDetailEvent> assetDetailEvent,
                         final ResourceUtils resourceUtils,
                         final Caller<LibraryService> libraryService,
                         final Caller<WorkspaceProjectService> projectService,
                         final Caller<KieModuleService> moduleService,
                         final PlaceManager placeManager,
                         final WorkspaceProjectContext projectContext,
                         final LibraryToolbarPresenter libraryToolbar,
                         final AuthoringWorkbenchDocks docks,
                         final Event<WorkspaceProjectContextChangeEvent> projectContextChangeEvent,
                         final Event<NotificationEvent> notificationEvent,
                         final TranslationUtils translationUtils,
                         final Caller<VFSService> vfsService,
                         final ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier,
                         final Event<PreferencesCentralInitializationEvent> preferencesCentralInitializationEvent,
                         final ManagedInstance<ImportRepositoryPopUpPresenter> importRepositoryPopUpPresenters,
                         final @Routed Event<ProjectAssetListUpdated> assetListUpdatedEvent,
                         final CloseUnsavedProjectAssetsPopUpPresenter closeUnsavedProjectAssetsPopUpPresenter,
                         final @Source(EXTERNAL) Event<ImportProjectsSetupEvent> importProjectsSetupEvent) {
        this.breadcrumbs = breadcrumbs;
        this.ts = ts;
        this.assetDetailEvent = assetDetailEvent;
        this.resourceUtils = resourceUtils;
        this.libraryService = libraryService;
        this.projectService = projectService;
        this.moduleService = moduleService;
        this.placeManager = placeManager;
        this.projectContext = projectContext;
        this.libraryToolbar = libraryToolbar;
        this.docks = docks;
        this.projectContextChangeEvent = projectContextChangeEvent;
        this.notificationEvent = notificationEvent;
        this.translationUtils = translationUtils;
        this.vfsService = vfsService;
        this.projectScopedResolutionStrategySupplier = projectScopedResolutionStrategySupplier;
        this.preferencesCentralInitializationEvent = preferencesCentralInitializationEvent;
        this.importRepositoryPopUpPresenters = importRepositoryPopUpPresenters;
        this.assetListUpdatedEvent = assetListUpdatedEvent;
        this.closeUnsavedProjectAssetsPopUpPresenter = closeUnsavedProjectAssetsPopUpPresenter;
        this.importProjectsSetupEvent = importProjectsSetupEvent;
    }

    @Inject
    private Promises promises;

    @PostConstruct
    public void setup() {
        projectContext.addChangeHandler(this);

        breadcrumbs.addToolbar(LIBRARY_PERSPECTIVE, libraryToolbar.getView().getElement());

        placeManager.registerPerspectiveCloseChain(LIBRARY_PERSPECTIVE, (chain, place) -> {
            if (LIBRARY_PERSPECTIVE.equals(place.getIdentifier())) {
                closeAllPlacesOrNothing(chain);
            } else {
                closePlace(chain, place);
            }
        });
    }

    public void init(final LibraryPerspective libraryPerspective) {
        this.libraryPerspective = libraryPerspective;
    }

    //
    //
    //Events

    public void onResourceCopiedEvent(@Observes final ResourceCopiedEvent resourceCopiedEvent) {
        if (isLibraryPerspectiveOpen()) {
            final Path path = resourceCopiedEvent.getDestinationPath();
            goToAsset(path);
            setupLibraryBreadCrumbsForAsset(path);
        }
    }

    public void onSelectPlaceEvent(@Observes final PlaceGainFocusEvent placeGainFocusEvent) {
        if (isLibraryPerspectiveOpen() && !closingLibraryPlaces) {
            final PlaceRequest place = placeGainFocusEvent.getPlace();

            if (place instanceof PathPlaceRequest) {
                setupLibraryBreadCrumbsForAsset(((PathPlaceRequest) place).getPath());
                showDocks();
            } else if (!place.getIdentifier().equals(ALERTS) && LIBRARY_PLACES.contains(place.getIdentifier())) {
                hideDocks();
                if (place.getIdentifier().equals(PROJECT_SCREEN) && projectContext.getActiveWorkspaceProject().isPresent()) {
                    setupActiveProjectBreadcrumb();
                } else if (place.getIdentifier().equals(LIBRARY_SCREEN)) {
                    setupActiveSpaceBreadcrumb();
                }
            }
        }
    }

    /*
     * Re-reroutes this event for project screen. If we tried to observe this directly from the project screen,
     * there are timing issues involved with subscribing to the event.
     */
    public void onAssetListUpdateEvent(@Observes @Remote final ProjectAssetListUpdated event) {
        assetListUpdatedEvent.fire(event);
    }

    public void hideDocks() {
        if (!docksHidden) {
            docks.hide();
            docksHidden = true;
        }
    }

    public void showDocks() {
        if (docksHidden) {
            if (!docksReady) {
                docks.setup(LIBRARY_PERSPECTIVE, new DefaultPlaceRequest(PROJECT_EXPLORER));
                docksReady = true;
            }
            docks.show();
            docksHidden = false;
        }
    }

    public void onNewResourceCreated(@Observes final NewResourceSuccessEvent newResourceSuccessEvent) {
        if (isLibraryPerspectiveOpen()) {
            assetDetailEvent.fire(new AssetDetailEvent(getActiveProject(), newResourceSuccessEvent.getPath()));
            placeManager.closePlace(ADD_ASSET_SCREEN);
        }
    }

    public void onAssetRenamedAccepted(@Observes final ConcurrentRenameAcceptedEvent concurrentRenameAcceptedEvent) {
        if (isLibraryPerspectiveOpen()) {
            final ObservablePath path = concurrentRenameAcceptedEvent.getPath();
            goToAsset(path);
            setupLibraryBreadCrumbsForAsset(path);
        }
    }

    public void onProjectDeleted(@Observes final RepositoryRemovedEvent repositoryRemovedEvent) {

        final boolean activeProjectBelongsToDeletedRepo = projectContext.getActiveWorkspaceProject()
                .filter(project -> project.getRepository().getIdentifier().equals(repositoryRemovedEvent.getRepository().getIdentifier()))
                .isPresent();

        if (isLibraryPerspectiveOpen() && activeProjectBelongsToDeletedRepo) {
            closeAllPlaces();
            projectContextChangeEvent.fire(projectContext.getActiveOrganizationalUnit()
                                                   .map(WorkspaceProjectContextChangeEvent::new)
                                                   .orElseGet(WorkspaceProjectContextChangeEvent::new));
            goToLibrary();
            notificationEvent.fire(new NotificationEvent(ts.getTranslation(LibraryConstants.ProjectDeleted), DEFAULT));
        }
    }

    private boolean isLibraryPerspectiveOpen() {
        return placeManager.getStatus(LIBRARY_PERSPECTIVE).equals(PlaceStatus.OPEN)
                || placeManager.getStatus(getLibraryPlaceRequestWithoutRefresh()).equals(PlaceStatus.OPEN);
    }

    public void onOrganizationalUnitRemoved(@Observes final RemoveOrganizationalUnitEvent removedOrganizationalUnitEvent) {
        if (isLibraryPerspectiveOpen()) {
            projectContext.getActiveOrganizationalUnit()
                    .filter(removedOrganizationalUnitEvent.getOrganizationalUnit()::equals)
                    .ifPresent(active -> projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent()));
        }
    }

    public void onProjectRenamed(@Observes final RenameModuleEvent renameModuleEvent) {
        if (isLibraryPerspectiveOpen()) {
            projectContext.getActiveWorkspaceProject()
                    .map(WorkspaceProject::getMainModule)
                    .filter(renameModuleEvent.getOldModule()::equals)
                    .ifPresent(module -> refresh(() -> {
                    }));
        }
    }

    public void onAssetSelected(@Observes final AssetDetailEvent assetDetails) {
        goToAsset(assetDetails.getPath());
    }

    public void onPreferencesSave(@Observes final PreferencesCentralSaveEvent event) {
        if (isLibraryPerspectiveOpen()) {
            goToActiveProject();
        }
    }

    public void onPreferencesCancel(@Observes final PreferencesCentralUndoChangesEvent event) {
        if (isLibraryPerspectiveOpen()) {
            goToActiveProject();
        }
    }

    public void onSocialFileSelected(@Observes final SocialFileSelectedEvent event) {
        promises.promisify(vfsService, s -> {
            return s.get(event.getUri());
        }).then((final Path path) -> promises.promisify(projectService, s -> {
            return s.resolveProject(path);
        })).then((final WorkspaceProject project) -> {

            if (!projectContext.getActiveWorkspaceProject().map(project::equals).orElse(false)) {
                projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent(project, project.getMainModule()));
            }

            if (ExtendedTypes.NEW_REPOSITORY_EVENT.name().equals(event.getEventType())) {
                placeManager.goTo(REPOSITORY_STRUCTURE_SCREEN);
            } else if (ModuleEventType.NEW_MODULE.name().equals(event.getEventType())) {
                placeManager.goTo(getLibraryPlaceRequestWithoutRefresh());
                goToActiveProject();
            } else {
                placeManager.goTo(getLibraryPlaceRequestWithoutRefresh());
                goToActiveProject();
                goToAsset(project.getRootPath());
            }
            return promises.resolve();
        });
    }

    @Override
    public void onChange(WorkspaceProjectContextChangeEvent previous,
                         WorkspaceProjectContextChangeEvent current) {

        if (current.getWorkspaceProject() != null
                && Utils.hasRepositoryChanged(previous.getWorkspaceProject(), current.getWorkspaceProject())) {

            closeAllPlacesOrNothing(this::goToActiveProject);
        }
    }

    PlaceRequest getLibraryPlaceRequestWithoutRefresh() {
        return new DefaultPlaceRequest(LIBRARY_PERSPECTIVE, new HashMap<String, String>() {{
            put("refresh", "false");
        }});
    }

    //
    //
    //Navigation

    public void refresh(final Command callback) {
        breadcrumbs.clearBreadcrumbs(LIBRARY_PERSPECTIVE);
        translationUtils.refresh(() -> libraryToolbar.init(callback));
    }

    public void goToSpaces() {
        closeAllPlacesOrNothing(() -> {
            PortablePreconditions.checkNotNull("libraryPerspective.closeAllPlacesOrNothing", libraryPerspective);
            projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent());
            placeManager.goTo(newNonSelectablePartDefinition(ORGANIZATIONAL_UNITS_SCREEN), libraryPerspective.getRootPanel());
            setupSpacesScreenBreadcrumb();
        });
    }

    public void goToLibrary() {

        if (projectContext.getActiveOrganizationalUnit().isPresent()) {
            setupLibraryPerspective();
            return;
        }

        promises.promisify(libraryService, LibraryService::getDefaultOrganizationalUnit).then((final OrganizationalUnit space) -> {
            projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent(space));
            setupLibraryPerspective();
            return promises.resolve();
        });
    }

    private void setupLibraryPerspective() {
        PortablePreconditions.checkNotNull("libraryPerspective", libraryPerspective);

        if (!projectContext.getActiveWorkspaceProject().isPresent()) {
            projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent(getActiveSpace()));
        }

        closeAllLibraryPlaces();

        placeManager.goTo(newNonSelectablePartDefinition(LIBRARY_SCREEN), libraryPerspective.getRootPanel());

        setupActiveSpaceBreadcrumb();

        hideDocks();
    }

    public void goToProject(final WorkspaceProject project) {

        if (!projectContext.getActiveWorkspaceProject().map(project::equals).orElse(true)) {
            goToActiveProject();
            return;
        }

        closeAllPlacesOrNothing(() -> {
            projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent(project, project.getMainModule()));
            goToActiveProject();
        });
    }

    void goToActiveProject() {
        setupActiveProjectBreadcrumb();
        placeManager.goTo(newNonSelectablePartDefinition(PROJECT_SCREEN), libraryPerspective.getRootPanel());
    }

    public void goToAsset(final Path path) {

        promises.promisify(moduleService, s -> {
            return s.resolvePackage(path);
        }).then((final org.guvnor.common.services.project.model.Package pkg) -> {

            projectContextChangeEvent.fire(new WorkspaceProjectContextChangeEvent(
                    projectContext.getActiveWorkspaceProject().orElse(null),
                    projectContext.getActiveModule().orElse(null),
                    pkg));

            final PathPlaceRequest placeRequest = new PathPlaceRequest(path);
            placeRequest.getPath().onRename(() -> setupLibraryBreadCrumbsForAsset(placeRequest.getPath()));

            placeManager.goTo(placeRequest);
            return promises.resolve();
        });
    }

    public void goToAddAsset() {
        placeManager.goTo(newNonSelectablePartDefinition(ADD_ASSET_SCREEN), libraryPerspective.getRootPanel());
    }

    public void goToTrySamples() {
        closeAllPlacesOrNothing(() -> {
            placeManager.goTo(newNonSelectablePartDefinition(IMPORT_SAMPLE_PROJECTS_SCREEN), libraryPerspective.getRootPanel());
            setupLibraryBreadCrumbsForTrySamples();
        });
    }

    public void goToImportRepositoryPopUp() {
        importRepositoryPopUpPresenters.get().show();
    }

    public void goToExternalImportPresenter(final Set<ExampleProject> projects) {
        closeAllPlacesOrNothing(() -> {
            // TODO add title
            placeManager.goTo(newNonSelectablePartDefinition(IMPORT_PROJECTS_SCREEN), libraryPerspective.getRootPanel());
            setupExternalImportBreadCrumbs();
            importProjectsSetupEvent.fire(new ImportProjectsSetupEvent(projects));
        });
    }

    private PartDefinition newNonSelectablePartDefinition(final String target) {
        final PartDefinition partDefinition = new PartDefinitionImpl(new DefaultPlaceRequest(target));
        partDefinition.setSelectable(false);
        return partDefinition;
    }

    public void closeAllPlacesOrNothing(final Command callback) {

        final List<PlaceRequest> uncloseablePlaces = placeManager.getUncloseablePlaces();

        if (uncloseablePlaces.isEmpty()) {
            closeAllPlaces();
            callback.execute();
            return;
        }

        showCloseUnsavedProjectAssetsPopup(uncloseablePlaces, () -> {
            forceCloseAllPlaces();
            callback.execute();
        });
    }

    private void closeAllPlaces() {
        try {
            closingLibraryPlaces = true;
            placeManager.closeAllPlaces();
        } finally {
            closingLibraryPlaces = false;
        }
    }

    private void forceCloseAllPlaces() {
        try {
            closingLibraryPlaces = true;
            placeManager.forceCloseAllPlaces();
        } finally {
            closingLibraryPlaces = false;
        }
    }

    private void closeAllLibraryPlaces() {
        try {
            closingLibraryPlaces = true;
            LIBRARY_PLACES.forEach(placeManager::closePlace);
        } finally {
            closingLibraryPlaces = false;
        }
    }

    private void closePlace(final Command callback, final PlaceRequest place) {

        if (placeManager.canClosePlace(place)) {
            callback.execute();
            return;
        }

        showCloseUnsavedProjectAssetsPopup(singletonList(place), () -> {
            placeManager.forceClosePlace(place);
            callback.execute();
        });
    }

    private void showCloseUnsavedProjectAssetsPopup(final List<PlaceRequest> uncloseablePlaces,
                                                    final Command callback) {

        closeUnsavedProjectAssetsPopUpPresenter.show(
                getActiveProject(),
                uncloseablePlaces,
                callback,
                () -> placeManager.goTo(uncloseablePlaces.get(0)));
    }

    public WorkspaceProject getActiveProject() {
        return projectContext.getActiveWorkspaceProject().orElseThrow(() -> new IllegalStateException("No active workspace project found"));
    }

    OrganizationalUnit getActiveSpace() {
        return projectContext.getActiveOrganizationalUnit().orElseThrow(() -> new IllegalStateException("No active space found"));
    }

    //
    //
    //Breadcrumb

    //Spaces
    void setupSpacesScreenBreadcrumb() {
        breadcrumbs.clearBreadcrumbs(LIBRARY_PERSPECTIVE);

        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  translationUtils.getOrganizationalUnitAliasInPlural(),
                                  this::goToSpaces,
                                  false);
    }

    //Spaces -> {spaceName}
    void setupActiveSpaceBreadcrumb() {
        setupSpacesScreenBreadcrumb();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  getActiveSpace().getName(),
                                  this::goToLibrary,
                                  false);
    }

    //Spaces -> {spaceName} -> {projectName} -> {branchName}
    void setupActiveProjectBreadcrumb() {
        setupActiveSpaceBreadcrumb();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  getActiveProject().getName(),
                                  this::goToActiveProject,
                                  false);

        //FIXME: Add branch selection view
        libraryToolbar.setUpBranches();
    }

    //Spaces -> {spaceName} -> Try Samples
    void setupLibraryBreadCrumbsForTrySamples() {
        setupActiveSpaceBreadcrumb();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  ts.getTranslation(LibraryConstants.TrySamples),
                                  this::goToTrySamples,
                                  false);
    }

    //Spaces -> {spaceName} -> Import project
    void setupExternalImportBreadCrumbs() {
        setupActiveSpaceBreadcrumb();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  ts.getTranslation(LibraryConstants.ImportProjects),
                                  this::goToImportRepositoryPopUp);
    }

    //Spaces -> {spaceName} -> {projectName} -> {branchName} -> {assetName}
    void setupLibraryBreadCrumbsForAsset(final Path path) {
        setupActiveProjectBreadcrumb();
        breadcrumbs.addBreadCrumb(LIBRARY_PERSPECTIVE,
                                  resourceUtils.getBaseFileName(path),
                                  () -> goToAsset(path),
                                  false);
    }
}

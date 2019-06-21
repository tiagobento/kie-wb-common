/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.dmn.showcase.client.editor;

import java.util.Optional;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import elemental2.dom.DomGlobal;
import elemental2.promise.Promise;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.kie.workbench.common.dmn.client.commands.general.NavigateToExpressionEditorCommand;
import org.kie.workbench.common.dmn.client.decision.DecisionNavigatorDock;
import org.kie.workbench.common.dmn.client.editors.expressions.ExpressionEditorView;
import org.kie.workbench.common.dmn.client.editors.included.IncludedModelsPage;
import org.kie.workbench.common.dmn.client.editors.included.imports.IncludedModelsPageStateProviderImpl;
import org.kie.workbench.common.dmn.client.editors.types.DataTypePageTabActiveEvent;
import org.kie.workbench.common.dmn.client.editors.types.DataTypesPage;
import org.kie.workbench.common.dmn.client.editors.types.listview.common.DataTypeEditModeToggleEvent;
import org.kie.workbench.common.dmn.client.events.EditExpressionEvent;
import org.kie.workbench.common.dmn.client.session.DMNSession;
import org.kie.workbench.common.dmn.showcase.client.menus.DMNEditorMenuSessionItems;
import org.kie.workbench.common.dmn.showcase.client.perspectives.AuthoringPerspective;
import org.kie.workbench.common.dmn.showcase.client.toolbar.ProjectToolbarStateHandler;
import org.kie.workbench.common.stunner.client.widgets.presenters.session.impl.SessionEditorPresenter;
import org.kie.workbench.common.stunner.client.widgets.presenters.session.impl.SessionViewerPresenter;
import org.kie.workbench.common.stunner.core.client.annotation.DiagramEditor;
import org.kie.workbench.common.stunner.core.client.api.SessionManager;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.CanvasHandler;
import org.kie.workbench.common.stunner.core.client.command.SessionCommandManager;
import org.kie.workbench.common.stunner.core.client.components.layout.LayoutHelper;
import org.kie.workbench.common.stunner.core.client.components.layout.OpenDiagramLayoutExecutor;
import org.kie.workbench.common.stunner.core.client.error.DiagramClientErrorHandler;
import org.kie.workbench.common.stunner.core.client.i18n.ClientTranslationService;
import org.kie.workbench.common.stunner.core.client.service.ClientRuntimeError;
import org.kie.workbench.common.stunner.core.client.service.ServiceCallback;
import org.kie.workbench.common.stunner.core.client.session.Session;
import org.kie.workbench.common.stunner.core.client.session.impl.EditorSession;
import org.kie.workbench.common.stunner.core.client.session.impl.ViewerSession;
import org.kie.workbench.common.stunner.core.diagram.Metadata;
import org.kie.workbench.common.stunner.core.documentation.DocumentationView;
import org.kie.workbench.common.stunner.forms.client.event.RefreshFormPropertiesEvent;
import org.kie.workbench.common.stunner.submarine.api.diagram.SubmarineDiagram;
import org.kie.workbench.common.stunner.submarine.client.docks.DiagramEditorPreviewAndExplorerDock;
import org.kie.workbench.common.stunner.submarine.client.docks.DiagramEditorPropertiesDock;
import org.kie.workbench.common.stunner.submarine.client.editor.AbstractDiagramEditor;
import org.kie.workbench.common.stunner.submarine.client.editor.event.OnDiagramFocusEvent;
import org.kie.workbench.common.stunner.submarine.client.service.SubmarineClientDiagramService;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.client.annotations.WorkbenchClientEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.events.ChangeTitleWidgetEvent;
import org.uberfire.client.workbench.widgets.common.ErrorPopupPresenter;
import org.uberfire.ext.editor.commons.client.menu.MenuItems;
import org.uberfire.ext.widgets.core.client.editors.texteditor.TextEditorView;
import org.uberfire.lifecycle.GetContent;
import org.uberfire.lifecycle.IsDirty;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnFocus;
import org.uberfire.lifecycle.OnLostFocus;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.lifecycle.SetContent;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.model.menu.Menus;

import static elemental2.dom.DomGlobal.setTimeout;

@ApplicationScoped
@DiagramEditor
@WorkbenchClientEditor(identifier = DMNDiagramEditor.EDITOR_ID)
//@WorkbenchScreen(identifier = DMNDiagramEditor.EDITOR_ID)
public class DMNDiagramEditor extends AbstractDiagramEditor {

    public static final String EDITOR_ID = "DMNDiagramEditor";

    private static final int DATA_TYPES_PAGE_INDEX = 1;

    private final SessionManager sessionManager;
    private final SessionCommandManager<AbstractCanvasHandler> sessionCommandManager;
    private final Event<RefreshFormPropertiesEvent> refreshFormPropertiesEvent;
    private final Event<NotificationEvent> notificationEvent;

    private final DecisionNavigatorDock decisionNavigatorDock;
    private final DiagramEditorPreviewAndExplorerDock diagramPreviewAndExplorerDock;
    private final DiagramEditorPropertiesDock diagramPropertiesDock;

    private final LayoutHelper layoutHelper;
    private final OpenDiagramLayoutExecutor openDiagramLayoutExecutor;

    private final DataTypesPage dataTypesPage;
    private final IncludedModelsPage includedModelsPage;
    private final IncludedModelsPageStateProviderImpl importsPageProvider;

    private final SubmarineClientDiagramService diagramServices;
    private final DMNDiagramSubmarineWrapper stateHolder;

    @Inject
    public DMNDiagramEditor(final View view,
                            final FileMenuBuilder fileMenuBuilder,
                            final PlaceManager placeManager,
                            final MultiPageEditorContainerView multiPageEditorContainerView,
                            final Event<ChangeTitleWidgetEvent> changeTitleNotificationEvent,
                            final Event<NotificationEvent> notificationEvent,
                            final Event<OnDiagramFocusEvent> onDiagramFocusEvent,
                            final TextEditorView xmlEditorView,
                            final ManagedInstance<SessionEditorPresenter<EditorSession>> editorSessionPresenterInstances,
                            final ManagedInstance<SessionViewerPresenter<ViewerSession>> viewerSessionPresenterInstances,
                            final DMNEditorMenuSessionItems menuSessionItems,
                            final ErrorPopupPresenter errorPopupPresenter,
                            final DiagramClientErrorHandler diagramClientErrorHandler,
                            final ClientTranslationService translationService,
                            final DocumentationView documentationView,
                            final SessionManager sessionManager,
                            final @Session SessionCommandManager<AbstractCanvasHandler> sessionCommandManager,
                            final Event<RefreshFormPropertiesEvent> refreshFormPropertiesEvent,
                            final DecisionNavigatorDock decisionNavigatorDock,
                            final DiagramEditorPreviewAndExplorerDock diagramPreviewAndExplorerDock,
                            final DiagramEditorPropertiesDock diagramPropertiesDock,
                            final LayoutHelper layoutHelper,
                            final OpenDiagramLayoutExecutor openDiagramLayoutExecutor,
                            final DataTypesPage dataTypesPage,
                            final IncludedModelsPage includedModelsPage,
                            final IncludedModelsPageStateProviderImpl importsPageProvider,
                            final SubmarineClientDiagramService diagramServices,
                            final DMNDiagramSubmarineWrapper stateHolder) {
        super(view,
              fileMenuBuilder,
              placeManager,
              multiPageEditorContainerView,
              changeTitleNotificationEvent,
              notificationEvent,
              onDiagramFocusEvent,
              xmlEditorView,
              editorSessionPresenterInstances,
              viewerSessionPresenterInstances,
              menuSessionItems,
              errorPopupPresenter,
              diagramClientErrorHandler,
              translationService,
              documentationView);
        this.sessionManager = sessionManager;
        this.sessionCommandManager = sessionCommandManager;
        this.refreshFormPropertiesEvent = refreshFormPropertiesEvent;
        this.notificationEvent = notificationEvent;

        this.decisionNavigatorDock = decisionNavigatorDock;
        this.diagramPreviewAndExplorerDock = diagramPreviewAndExplorerDock;
        this.diagramPropertiesDock = diagramPropertiesDock;

        this.layoutHelper = layoutHelper;
        this.openDiagramLayoutExecutor = openDiagramLayoutExecutor;

        this.dataTypesPage = dataTypesPage;
        this.includedModelsPage = includedModelsPage;
        this.importsPageProvider = importsPageProvider;

        this.diagramServices = diagramServices;
        this.stateHolder = stateHolder;
    }

    @OnStartup
    @SuppressWarnings("unused")
    public void onStartup(final PlaceRequest place) {
        superDoStartUp(place);

        decisionNavigatorDock.init(AuthoringPerspective.PERSPECTIVE_ID);
        diagramPreviewAndExplorerDock.init(AuthoringPerspective.PERSPECTIVE_ID);
        diagramPropertiesDock.init(AuthoringPerspective.PERSPECTIVE_ID);

        getWidget().init(this);
    }

    void superDoStartUp(final PlaceRequest place) {
        super.doStartUp(place);
    }

    @Override
    public void initialiseKieEditorForSession(final SubmarineDiagram diagram) {
        superInitialiseKieEditorForSession(diagram);

        getWidget().getMultiPage().addPage(dataTypesPage);
        getWidget().getMultiPage().addPage(includedModelsPage);
    }

    @SuppressWarnings("unused")
    public void onDataTypePageNavTabActiveEvent(final @Observes DataTypePageTabActiveEvent event) {
        getWidget().getMultiPage().selectPage(DATA_TYPES_PAGE_INDEX);
    }

    void superInitialiseKieEditorForSession(final SubmarineDiagram diagram) {
        super.initialiseKieEditorForSession(diagram);
    }

    @Override
    public void open(final SubmarineDiagram diagram) {
        this.layoutHelper.applyLayout(diagram, openDiagramLayoutExecutor);
        super.open(diagram);
    }

    @OnOpen
    @SuppressWarnings("unused")
    public void onOpen() {
        super.doOpen();
        setContent(""); //TODO: (tiago) remove this line
    }

    @OnClose
    @SuppressWarnings("unused")
    public void onClose() {
        superOnClose();

        decisionNavigatorDock.close();
        decisionNavigatorDock.resetContent();

        diagramPreviewAndExplorerDock.close();
        diagramPropertiesDock.close();

        dataTypesPage.disableShortcuts();
    }

    void superOnClose() {
        super.doClose();
    }

    @Override
    public void onDiagramLoad() {
        final Optional<CanvasHandler> canvasHandler = Optional.ofNullable(getCanvasHandler());

        canvasHandler.ifPresent(c -> {
            final Metadata metadata = c.getDiagram().getMetadata();
            metadata.setPath(makeMetadataPath(metadata.getRoot(), metadata.getTitle()));

            final ExpressionEditorView.Presenter expressionEditor = ((DMNSession) sessionManager.getCurrentSession()).getExpressionEditor();
            expressionEditor.setToolbarStateHandler(new ProjectToolbarStateHandler(getMenuSessionItems()));

            decisionNavigatorDock.setupCanvasHandler(c);
            decisionNavigatorDock.open();

            diagramPreviewAndExplorerDock.open();
            diagramPropertiesDock.open();

            dataTypesPage.reload();

            includedModelsPage.setup(importsPageProvider.withDiagram(c.getDiagram()));
        });
    }

    private Path makeMetadataPath(final Path root,
                                  final String title) {
        final String uri = root.toURI();
        return PathFactory.newPath(title, uri + "/" + title + ".dmn");
    }

    @OnFocus
    @SuppressWarnings("unused")
    public void onFocus() {
        superDoFocus();
        onDiagramLoad();
        dataTypesPage.onFocus();
        dataTypesPage.enableShortcuts();
    }

    void superDoFocus() {
        super.doFocus();
    }

    @OnLostFocus
    @SuppressWarnings("unused")
    public void onLostFocus() {
        super.doLostFocus();
        dataTypesPage.onLostFocus();
    }

    @Override
    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        return "";
    }

    @WorkbenchMenu
    public void getMenus(final Consumer<Menus> menusConsumer) {
        menusConsumer.accept(super.getMenus());
    }

    @Override
    protected void makeMenuBar() {
        if (!menuBarInitialized) {
            getFileMenuBuilder().addSave(this::doSave);
            getMenuSessionItems().populateMenu(getFileMenuBuilder());
            makeAdditionalStunnerMenus(getFileMenuBuilder());
            menuBarInitialized = true;
        }
    }

    private void doSave() {
        stateHolder.saveFile(new ServiceCallback<String>() {
            @Override
            public void onSuccess(final String xml) {
                resetContentHash();
                notificationEvent.fire(new NotificationEvent(org.uberfire.ext.editor.commons.client.resources.i18n.CommonConstants.INSTANCE.ItemSavedSuccessfully()));
                hideLoadingViews();
            }

            @Override
            public void onError(final ClientRuntimeError error) {
                onSaveError(error);
            }
        });
    }

    @Override
    @WorkbenchPartView
    public IsWidget asWidget() {
        return super.asWidget();
    }

    @OnMayClose
    @SuppressWarnings("unused")
    public boolean onMayClose() {
        return super.mayClose();
    }

    @Override
    public String getEditorIdentifier() {
        return EDITOR_ID;
    }

    public void onDataTypeEditModeToggle(final @Observes DataTypeEditModeToggleEvent event) {
        /* Delaying the 'onDataTypeEditModeToggleCallback' since external events
         * refresh the menu widget and override this change. */
        setTimeout(getOnDataTypeEditModeToggleCallback(event), 250);
    }

    DomGlobal.SetTimeoutCallbackFn getOnDataTypeEditModeToggleCallback(final DataTypeEditModeToggleEvent event) {
        return (e) -> {
            if (event.isEditModeEnabled()) {
                disableMenuItem(MenuItems.SAVE);
            } else {
                enableMenuItem(MenuItems.SAVE);
            }
        };
    }

    void onEditExpressionEvent(final @Observes EditExpressionEvent event) {
        if (isSameSession(event.getSession())) {
            final DMNSession session = sessionManager.getCurrentSession();
            final ExpressionEditorView.Presenter expressionEditor = session.getExpressionEditor();
            sessionCommandManager.execute(session.getCanvasHandler(),
                                          new NavigateToExpressionEditorCommand(expressionEditor,
                                                                                getSessionPresenter(),
                                                                                sessionManager,
                                                                                sessionCommandManager,
                                                                                refreshFormPropertiesEvent,
                                                                                event.getNodeUUID(),
                                                                                event.getHasExpression(),
                                                                                event.getHasName(),
                                                                                event.isOnlyVisualChangeAllowed()));
        }
    }

    @Override
    @GetContent
    public Promise getContent() {
        return diagramServices.transform(getEditor().getEditorProxy().getContentSupplier().get());
    }

    @Override
    @IsDirty
    public boolean isDirty() {
        return super.isDirty();
    }

    @Override
    @SetContent
    public void setContent(final String value) {
        diagramServices.transform(value,
                                  new ServiceCallback<SubmarineDiagram>() {

                                      @Override
                                      public void onSuccess(final SubmarineDiagram diagram) {
                                          getEditor().open(diagram);
                                      }

                                      @Override
                                      public void onError(final ClientRuntimeError error) {
                                          DMNDiagramEditor.this.getEditor().onLoadError(error);
                                      }
                                  });
    }

    @Override
    public void resetContentHash() {
        setOriginalContentHash(getCurrentDiagramHash());
    }
}

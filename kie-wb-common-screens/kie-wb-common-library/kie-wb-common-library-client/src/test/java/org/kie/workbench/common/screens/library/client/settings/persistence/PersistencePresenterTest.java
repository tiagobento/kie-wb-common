package org.kie.workbench.common.screens.library.client.settings.persistence;

import java.util.Arrays;

import javax.enterprise.event.Event;

import org.guvnor.common.services.project.context.ProjectContext;
import org.guvnor.common.services.project.model.Project;
import org.guvnor.common.services.shared.metadata.model.Overview;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.screens.datamodeller.model.persistence.PersistenceDescriptorEditorContent;
import org.kie.workbench.common.screens.datamodeller.model.persistence.PersistenceDescriptorModel;
import org.kie.workbench.common.screens.datamodeller.model.persistence.PersistenceUnitModel;
import org.kie.workbench.common.screens.datamodeller.model.persistence.Property;
import org.kie.workbench.common.screens.datamodeller.model.persistence.TransactionType;
import org.kie.workbench.common.screens.datamodeller.service.DataModelerService;
import org.kie.workbench.common.screens.datamodeller.service.PersistenceDescriptorEditorService;
import org.kie.workbench.common.screens.library.client.settings.SettingsPresenter;
import org.kie.workbench.common.screens.library.client.settings.SettingsSectionChange;
import org.kie.workbench.common.screens.library.client.settings.util.modal.doublevalue.AddDoubleValueModal;
import org.kie.workbench.common.screens.library.client.settings.util.modal.single.AddSingleValueModal;
import org.kie.workbench.common.screens.projecteditor.model.ProjectScreenModel;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.promise.Promises;
import org.uberfire.mocks.CallerMock;
import org.uberfire.promise.SyncPromises;
import org.uberfire.workbench.events.NotificationEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersistencePresenterTest {

    private PersistencePresenter persistencePresenter;

    @Mock
    private PersistencePresenter.View view;

    @Mock
    private ProjectContext projectContext;

    @Mock
    private SettingsPresenter.MenuItem menuItem;

    @Mock
    private Event<NotificationEvent> notificationEvent;

    @Mock
    private Event<SettingsSectionChange> settingsSectionChangeEvent;

    @Mock
    private ManagedInstance<ObservablePath> observablePaths;

    @Mock
    private AddDoubleValueModal newPropertyModal;

    @Mock
    private AddSingleValueModal newPersistableDataObjectModal;

    @Mock
    private PersistenceDescriptorEditorService editorService;

    @Mock
    private DataModelerService dataModelerService;

    @Mock
    private PersistencePresenter.PropertiesListPresenter propertiesListPresenter;

    @Mock
    private PersistencePresenter.PersistableDataObjectsListPresenter persistableDataObjectsListPresenter;

    private final Promises promises = new SyncPromises();

    @Before
    public void before() {

        final ObservablePath observablePath = mock(ObservablePath.class);
        doReturn(observablePath).when(observablePath).wrap(any());
        doReturn(observablePath).when(observablePaths).get();

        when(projectContext.getActiveProject()).thenReturn(mock(Project.class));
        when(projectContext.getActiveProject().getRootPath()).thenReturn(mock(Path.class));
        when(projectContext.getActiveProject().getRootPath().toURI()).thenReturn("root");

        persistencePresenter = spy(new PersistencePresenter(view,
                                                            projectContext,
                                                            promises,
                                                            menuItem,
                                                            notificationEvent,
                                                            settingsSectionChangeEvent,
                                                            observablePaths,
                                                            newPropertyModal,
                                                            newPersistableDataObjectModal,
                                                            new CallerMock<>(editorService),
                                                            new CallerMock<>(dataModelerService),
                                                            propertiesListPresenter,
                                                            persistableDataObjectsListPresenter));
    }

    @Test
    public void testSetup() {

        final PersistenceDescriptorEditorContent model = spy(newPersistenceDescriptorEditorContent());
        doReturn(model).when(editorService).loadContent(any(), anyBoolean());

        persistencePresenter.setup(mock(ProjectScreenModel.class)).catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(view).init(eq(persistencePresenter));
        verify(editorService).loadContent(any(), anyBoolean());
        verify(newPropertyModal).setup(any(), any(), any());

        verify(propertiesListPresenter).setup(any(), any(), any());
        verify(newPersistableDataObjectModal).setup(any(), any());
        verify(persistableDataObjectsListPresenter).setup(any(), any(), any());
        verify(view).setPersistenceUnit(model.getDescriptorModel().getPersistenceUnit().getName());
        verify(view).setPersistenceProvider(model.getDescriptorModel().getPersistenceUnit().getProvider());
        verify(view).setDataSource(eq(model.getDescriptorModel().getPersistenceUnit().getJtaDataSource()));
    }

    @Test
    public void testSetupFail() {

        final RuntimeException testException = new RuntimeException("Test exception");
        doThrow(testException).when(editorService).loadContent(any(), anyBoolean());

        persistencePresenter.setup(mock(ProjectScreenModel.class)).then(i -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        }).catch_(e -> promises.catchOrExecute(e, e1 -> {
            Assert.fail("Promise should've not thrown exception outside Caller");
            return promises.resolve();
        }, (final Promises.Error<?> e1) -> {
            Assert.assertEquals(e1.getThrowable().getCause(), testException);
            return promises.resolve();
        }));
    }

    @Test
    public void testAddProperty() {
        final Property property = new Property("Name", "Value");

        persistencePresenter.add(property);

        verify(propertiesListPresenter).add(eq(property));
        verify(persistencePresenter).fireChangeEvent();
    }

    @Test
    public void testAddPersistableDataObject() {
        final String className = "ClassName";

        persistencePresenter.add(className);

        verify(persistableDataObjectsListPresenter).add(eq(className));
        verify(persistencePresenter).fireChangeEvent();
    }

    @Test
    public void testAddAllProjectsPersistableDataObjects() {
        persistencePresenter.persistenceDescriptorEditorContent = newPersistenceDescriptorEditorContent();
        doReturn(Arrays.asList("Class1", "NewClass1", "NewClass2")).when(dataModelerService).findPersistableClasses(any());

        persistencePresenter.addAllProjectsPersistableDataObjects();

        verify(dataModelerService).findPersistableClasses(any());
        verify(persistencePresenter, never()).add(eq("Class1"));
        verify(persistencePresenter).add(eq("NewClass1"));
        verify(persistencePresenter).add(eq("NewClass1"));
    }

    @Test
    public void testShowNewPropertyModal() {
        persistencePresenter.showNewPropertyModal();
        verify(newPropertyModal).show(any());
    }

    @Test
    public void testShowNewPersistableDataObjectModal() {
        persistencePresenter.showNewPersistableDataObjectModal();
        verify(newPersistableDataObjectModal).show(any());
    }

    @Test
    public void testSetPersistenceUnit() {
        final PersistenceDescriptorEditorContent model = newPersistenceDescriptorEditorContent();
        persistencePresenter.persistenceDescriptorEditorContent = model;
        persistencePresenter.setPersistenceUnit("PersistenceUnit");

        Assert.assertEquals("PersistenceUnit", model.getDescriptorModel().getPersistenceUnit().getName());
        verify(persistencePresenter).fireChangeEvent();
    }

    @Test
    public void testSetPersistenceProvider() {
        final PersistenceDescriptorEditorContent model = newPersistenceDescriptorEditorContent();
        persistencePresenter.persistenceDescriptorEditorContent = model;
        persistencePresenter.setPersistenceProvider("PersistenceProvider");

        Assert.assertEquals("PersistenceProvider", model.getDescriptorModel().getPersistenceUnit().getProvider());
        verify(persistencePresenter).fireChangeEvent();
    }

    @Test
    public void testSetDataSource() {
        final PersistenceDescriptorEditorContent model = newPersistenceDescriptorEditorContent();
        persistencePresenter.persistenceDescriptorEditorContent = model;
        persistencePresenter.setDataSource("DataSource");

        Assert.assertEquals("DataSource", model.getDescriptorModel().getPersistenceUnit().getJtaDataSource());
        verify(persistencePresenter).fireChangeEvent();
    }

    @Test
    public void testSave() {
        doReturn(promises.resolve()).when(persistencePresenter).save(eq("Test comment"));

        persistencePresenter.save("Test comment", null).catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(persistencePresenter).save(eq("Test comment"));
        verify(persistencePresenter, never()).setup();
        verify(notificationEvent, never()).fire(any());
    }

    @Test
    public void testSaveWithConcurrentUpdate() {
        persistencePresenter.concurrentPersistenceXmlUpdateInfo = mock(ObservablePath.OnConcurrentUpdateEvent.class);
        doReturn(promises.resolve()).when(persistencePresenter).setup();

        persistencePresenter.save("Test comment", null).catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(persistencePresenter, never()).save(any());
        verify(persistencePresenter).setup();
        verify(notificationEvent).fire(any());
    }

    @Test
    public void testSaveModel() {
        persistencePresenter.persistenceDescriptorEditorContent = newPersistenceDescriptorEditorContent();
        doReturn(mock(Path.class)).when(editorService).save(any(), any(), any(), eq("Test comment"));

        persistencePresenter.save("Test comment").catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(editorService).save(any(), any(), any(), eq("Test comment"));
    }

    private PersistenceDescriptorEditorContent newPersistenceDescriptorEditorContent() {
        final PersistenceDescriptorEditorContent content = new PersistenceDescriptorEditorContent();
        final PersistenceDescriptorModel model = new PersistenceDescriptorModel();
        model.setVersion("2.0");

        final PersistenceUnitModel unitModel = new PersistenceUnitModel();
        model.setPersistenceUnit(unitModel);

        unitModel.setName("UnitName");
        unitModel.setTransactionType(TransactionType.JTA);
        unitModel.setProvider("ProviderClass");
        unitModel.setJtaDataSource("JTADataSource");
        unitModel.setClasses(Arrays.asList("Class1", "Class2"));

        content.setDescriptorModel(model);
        content.setOverview(new Overview());

        return content;
    }
}
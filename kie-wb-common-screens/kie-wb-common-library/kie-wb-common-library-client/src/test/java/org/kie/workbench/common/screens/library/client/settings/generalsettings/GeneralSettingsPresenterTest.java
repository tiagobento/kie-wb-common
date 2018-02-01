package org.kie.workbench.common.screens.library.client.settings.generalsettings;

import org.guvnor.common.services.project.client.preferences.ProjectScopedResolutionStrategySupplier;
import org.guvnor.common.services.project.model.GAV;
import org.guvnor.common.services.project.model.POM;
import org.guvnor.common.services.project.preferences.GAVPreferences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.screens.library.client.settings.SettingsPresenter;
import org.kie.workbench.common.screens.library.client.settings.SettingsSectionChange;
import org.kie.workbench.common.screens.projecteditor.model.ProjectScreenModel;
import org.kie.workbench.common.services.shared.validation.ValidationService;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.client.promise.Promises;
import org.uberfire.mocks.CallerMock;
import org.uberfire.mocks.EventSourceMock;
import org.uberfire.preferences.shared.impl.PreferenceScopeResolutionStrategyInfo;
import org.uberfire.promise.SyncPromises;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GeneralSettingsPresenterTest {

    @Mock
    private GeneralSettingsPresenter generalSettingsPresenter;

    @Mock
    private GeneralSettingsView view;

    @Mock
    private SettingsPresenter.MenuItem menuItem;

    @Mock
    private ValidationService validationService;

    @Mock
    private EventSourceMock<SettingsSectionChange> settingsSectionChangeEvent;

    @Mock
    private GAVPreferences gavPreferences;

    @Mock
    private ProjectScopedResolutionStrategySupplier projectScopedResolutionStrategySupplier;

    private Promises promises = new SyncPromises();

    @Before
    public void before() {
        generalSettingsPresenter = spy(new GeneralSettingsPresenter(view,
                                                                    promises,
                                                                    menuItem,
                                                                    new CallerMock<>(validationService),
                                                                    settingsSectionChangeEvent,
                                                                    gavPreferences,
                                                                    projectScopedResolutionStrategySupplier));
    }

    @Test
    public void testSetup() {

        final ProjectScreenModel model = mock(ProjectScreenModel.class);
        final POM pom = mock(POM.class);

        doReturn(pom).when(model).getPOM();
        doReturn(new GAV()).when(pom).getGav();

        generalSettingsPresenter.setup(model).catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(view).init(eq(generalSettingsPresenter));
        verify(view).setName(any());
        verify(view).setDescription(any());
        verify(view).setURL(any());
        verify(view).setGroupId(any());
        verify(view).setArtifactId(any());
        verify(view).setVersion(any());

        verify(gavPreferences).load(any(), any(), any());
    }

    @Test
    public void testName() {
        final POM pom = spy(new POM());
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setName("Name");

        Assert.assertEquals(pom.getName(), "Name");
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testDescription() {
        final POM pom = spy(new POM());
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setDescription("Description");

        Assert.assertEquals("Description", pom.getDescription());
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testURL() {
        final POM pom = spy(new POM());
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setUrl("URL");

        Assert.assertEquals("URL", pom.getUrl());
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testVersion() {
        final POM pom = spy(new POM());
        doReturn(new GAV()).when(pom).getGav();
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setVersion("Version");

        Assert.assertEquals("Version", pom.getGav().getVersion());
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testArtifactId() {
        final POM pom = spy(new POM());
        doReturn(new GAV()).when(pom).getGav();
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setArtifactId("ArtifactId");

        Assert.assertEquals("ArtifactId", pom.getGav().getArtifactId());
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testGroupId() {
        final POM pom = spy(new POM());
        doReturn(new GAV()).when(pom).getGav();
        generalSettingsPresenter.pom = pom;

        generalSettingsPresenter.setGroupId("GroupId");

        Assert.assertEquals("GroupId", pom.getGav().getGroupId());
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testSave() {

        generalSettingsPresenter.save("Test comment", null).catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(gavPreferences).save(any(PreferenceScopeResolutionStrategyInfo.class), any(), any());
    }

    @Test
    public void testValidate() {
        generalSettingsPresenter.pom = new POM("Name", null, new GAV("GroupId:ArtifactId:Version"), false);

        doReturn("EmptyNameMessage").when(view).getEmptyNameMessage();
        doReturn("InvalidNameMessage").when(view).getInvalidNameMessage();

        doReturn("EmptyGroupIdMessage").when(view).getEmptyGroupIdMessage();
        doReturn("InvalidGroupIdMessage").when(view).getInvalidGroupIdMessage();

        doReturn("EmptyArtifactIdMessage").when(view).getEmptyArtifactIdMessage();
        doReturn("InvalidArtifactIdMessage").when(view).getInvalidArtifactIdMessage();

        doReturn("EmptyVersionMessage").when(view).getEmptyVersionMessage();
        doReturn("InvalidVersionMessage").when(view).getInvalidVersionMessage();

        doReturn(true).when(validationService).isProjectNameValid(eq("Name"));
        doReturn(true).when(validationService).validateGroupId(eq("GroupId"));
        doReturn(true).when(validationService).validateArtifactId(eq("ArtifactId"));
        doReturn(true).when(validationService).validateGAVVersion(eq("Version"));

        generalSettingsPresenter.validate().catch_(i -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });

        verify(view).hideError();

        verify(generalSettingsPresenter).validateStringIsNotEmpty(eq("Name"), eq("EmptyNameMessage"));
        verify(generalSettingsPresenter).executeValidation(any(), eq("InvalidNameMessage"));
        verify(validationService).isProjectNameValid(eq("Name"));

        verify(generalSettingsPresenter).validateStringIsNotEmpty(eq("GroupId"), eq("EmptyGroupIdMessage"));
        verify(generalSettingsPresenter).executeValidation(any(), eq("InvalidGroupIdMessage"));
        verify(validationService).validateGroupId(eq("GroupId"));

        verify(generalSettingsPresenter).validateStringIsNotEmpty(eq("ArtifactId"), eq("EmptyArtifactIdMessage"));
        verify(generalSettingsPresenter).executeValidation(any(), eq("InvalidArtifactIdMessage"));
        verify(validationService).validateArtifactId(eq("ArtifactId"));

        verify(generalSettingsPresenter).validateStringIsNotEmpty(eq("Version"), eq("EmptyVersionMessage"));
        verify(generalSettingsPresenter).executeValidation(any(), eq("InvalidVersionMessage"));
        verify(validationService).validateGAVVersion(eq("Version"));
    }

    @Test
    public void testValidateWithOneError() {

        generalSettingsPresenter.pom = new POM("", null, null, new GAV());

        doReturn("NameMessage").when(view).getEmptyNameMessage();

        generalSettingsPresenter.validate().then(i -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        });

        verify(generalSettingsPresenter).showErrorAndReject(eq("NameMessage"));
        verify(generalSettingsPresenter).validateStringIsNotEmpty(eq(""), eq("NameMessage"));
        verify(validationService, never()).isProjectNameValid(any());
    }

    @Test
    public void testShowErrorAndRejectWithException() {
        final RuntimeException testException = new RuntimeException("Test message");

        generalSettingsPresenter.showErrorAndReject(testException).then(i -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        }).catch_(e -> {
            verify(view).showError(eq("Test message"));
            Assert.assertEquals(e, generalSettingsPresenter);
            return promises.resolve();
        });
    }

    @Test
    public void testShowErrorAndRejectWithRejection() {
        generalSettingsPresenter.showErrorAndReject("Test message").then(i -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        }).catch_(e -> {
            verify(view).showError(eq("Test message"));
            Assert.assertEquals(e, generalSettingsPresenter);
            return promises.resolve();
        });
    }

    @Test
    public void testValidateStringIsNotEmpty() {
        generalSettingsPresenter.validateStringIsNotEmpty("NotEmptyString", "Message").then(e -> {
            Assert.assertEquals(e, true);
            return promises.resolve();
        }).catch_(e -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });
    }

    @Test
    public void testValidateStringIsNotEmptyWithEmptyString() {
        generalSettingsPresenter.validateStringIsNotEmpty("", "Message").then(e -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        }).catch_(e -> {
            Assert.assertEquals(e, "Message");
            return promises.resolve();
        });
    }

    @Test
    public void testValidateStringIsNotEmptyWithNullString() {
        generalSettingsPresenter.validateStringIsNotEmpty(null, "Message").then(e -> {
            Assert.fail("Promise should've not been resolved!");
            return promises.resolve();
        }).catch_(e -> {
            Assert.assertEquals(e, "Message");
            return promises.resolve();
        });
    }

    @Test
    public void testExecuteValidation() {
        final POM pom = mock(POM.class);
        doReturn(true).when(validationService).validate(eq(pom));

        generalSettingsPresenter.executeValidation(s -> s.validate(pom), "Test message").then(valid -> {
            verify(validationService).validate(eq(pom));
            Assert.assertEquals(valid, true);
            return promises.resolve();
        }).catch_(e -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        });
    }

    @Test
    public void testExecuteValidationFailing() {
        final POM pom = mock(POM.class);
        doReturn(false).when(validationService).validate(eq(pom));

        generalSettingsPresenter.executeValidation(s -> s.validate(pom), "Test message").then(valid -> {
            Assert.fail("Promise should've been resolved!");
            return promises.resolve();
        }).catch_(e -> {
            verify(validationService).validate(eq(pom));
            Assert.assertEquals(e, "Test message");
            return promises.resolve();
        });
    }

    @Test
    public void testDisableGavConflictCheck() {
        generalSettingsPresenter.disableGavConflictCheck(true);
        verify(gavPreferences).setConflictingGAVCheckDisabled(eq(true));
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testAllowChildGavEdition() {
        generalSettingsPresenter.allowChildGavEdition(true);
        verify(gavPreferences).setChildGAVEditEnabled(eq(true));
        verify(generalSettingsPresenter).fireChangeEvent();
    }

    @Test
    public void testCurrentHashCode() {
        final POM pom = new POM("Name", "Description", new GAV("GroupId:ArtifactId:Version"), false);
        generalSettingsPresenter.pom = pom;

        int originalHashCode = generalSettingsPresenter.currentHashCode();

        doReturn(true).when(gavPreferences).isConflictingGAVCheckDisabled();
        Assert.assertNotEquals(originalHashCode, generalSettingsPresenter.currentHashCode());
        originalHashCode = generalSettingsPresenter.currentHashCode();

        doReturn(true).when(gavPreferences).isChildGAVEditEnabled();
        Assert.assertNotEquals(originalHashCode, generalSettingsPresenter.currentHashCode());
        originalHashCode = generalSettingsPresenter.currentHashCode();

        pom.setName("Name2");
        Assert.assertNotEquals(originalHashCode, generalSettingsPresenter.currentHashCode());
    }
}
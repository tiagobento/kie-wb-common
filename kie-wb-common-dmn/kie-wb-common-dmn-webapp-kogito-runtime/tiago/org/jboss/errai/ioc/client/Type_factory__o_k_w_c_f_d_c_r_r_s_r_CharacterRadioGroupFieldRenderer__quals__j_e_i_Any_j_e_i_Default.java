package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.forms.dynamic.client.config.ClientSelectorDataProviderManager;
import org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer;
import org.kie.workbench.common.forms.dynamic.client.rendering.formGroups.impl.configError.ConfigErrorDisplayer;
import org.kie.workbench.common.forms.dynamic.client.rendering.formGroups.impl.def.DefaultFormGroup;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.RequiresValueConverter;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.SelectorFieldRenderer;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.radioGroup.CharacterRadioGroupFieldRenderer;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.radioGroup.RadioGroupFieldRendererBase;
import org.kie.workbench.common.forms.dynamic.client.rendering.util.FormsElementWrapperWidgetUtil;
import org.kie.workbench.common.forms.dynamic.client.rendering.util.impl.FormsElementWrapperWidgetUtilImpl;
import org.kie.workbench.common.forms.dynamic.service.shared.BackendSelectorDataProviderService;
import org.kie.workbench.common.forms.dynamic.service.shared.SelectorDataProviderManager;

public class Type_factory__o_k_w_c_f_d_c_r_r_s_r_CharacterRadioGroupFieldRenderer__quals__j_e_i_Any_j_e_i_Default extends Factory<CharacterRadioGroupFieldRenderer> { public Type_factory__o_k_w_c_f_d_c_r_r_s_r_CharacterRadioGroupFieldRenderer__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(CharacterRadioGroupFieldRenderer.class, "Type_factory__o_k_w_c_f_d_c_r_r_s_r_CharacterRadioGroupFieldRenderer__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { CharacterRadioGroupFieldRenderer.class, RadioGroupFieldRendererBase.class, SelectorFieldRenderer.class, FieldRenderer.class, Object.class, RequiresValueConverter.class });
  }

  public CharacterRadioGroupFieldRenderer createInstance(final ContextManager contextManager) {
    final CharacterRadioGroupFieldRenderer instance = new CharacterRadioGroupFieldRenderer();
    setIncompleteInstance(instance);
    final ClientSelectorDataProviderManager SelectorFieldRenderer_clientProviderManager = (ClientSelectorDataProviderManager) contextManager.getInstance("Type_factory__o_k_w_c_f_d_c_c_ClientSelectorDataProviderManager__quals__j_e_i_Any_j_e_i_Default");
    SelectorFieldRenderer_SelectorDataProviderManager_clientProviderManager(instance, SelectorFieldRenderer_clientProviderManager);
    final Caller SelectorFieldRenderer_backendSelectorDataProviderService = (Caller) contextManager.getContextualInstance("ContextualProvider_factory__o_j_e_c_c_a_Caller__quals__Universal", new Class[] { BackendSelectorDataProviderService.class }, new Annotation[] { });
    registerDependentScopedReference(instance, SelectorFieldRenderer_backendSelectorDataProviderService);
    SelectorFieldRenderer_Caller_backendSelectorDataProviderService(instance, SelectorFieldRenderer_backendSelectorDataProviderService);
    final FormsElementWrapperWidgetUtilImpl FieldRenderer_wrapperWidgetUtil = (FormsElementWrapperWidgetUtilImpl) contextManager.getInstance("Type_factory__o_k_w_c_f_d_c_r_u_i_FormsElementWrapperWidgetUtilImpl__quals__j_e_i_Any_j_e_i_Default");
    FieldRenderer_FormsElementWrapperWidgetUtil_wrapperWidgetUtil(instance, FieldRenderer_wrapperWidgetUtil);
    final ManagedInstance FieldRenderer_formGroupsInstance = (ManagedInstance) contextManager.getContextualInstance("ContextualProvider_factory__o_j_e_i_c_a_ManagedInstance__quals__Universal", new Class[] { DefaultFormGroup.class }, new Annotation[] { });
    registerDependentScopedReference(instance, FieldRenderer_formGroupsInstance);
    FieldRenderer_ManagedInstance_formGroupsInstance(instance, FieldRenderer_formGroupsInstance);
    final ConfigErrorDisplayer FieldRenderer_errorDisplayer = (ConfigErrorDisplayer) contextManager.getInstance("Type_factory__o_k_w_c_f_d_c_r_f_i_c_ConfigErrorDisplayer__quals__j_e_i_Any_j_e_i_Default");
    registerDependentScopedReference(instance, FieldRenderer_errorDisplayer);
    FieldRenderer_ConfigErrorDisplayer_errorDisplayer(instance, FieldRenderer_errorDisplayer);
    setIncompleteInstance(null);
    return instance;
  }

  public void generatedDestroyInstance(final Object instance, final ContextManager contextManager) {
    destroyInstanceHelper((CharacterRadioGroupFieldRenderer) instance, contextManager);
  }

  public void destroyInstanceHelper(final CharacterRadioGroupFieldRenderer instance, final ContextManager contextManager) {
    instance.preDestroy();
  }

  native static Caller SelectorFieldRenderer_Caller_backendSelectorDataProviderService(SelectorFieldRenderer instance) /*-{
    return instance.@org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.SelectorFieldRenderer::backendSelectorDataProviderService;
  }-*/;

  native static void SelectorFieldRenderer_Caller_backendSelectorDataProviderService(SelectorFieldRenderer instance, Caller<BackendSelectorDataProviderService> value) /*-{
    instance.@org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.SelectorFieldRenderer::backendSelectorDataProviderService = value;
  }-*/;

  native static ManagedInstance FieldRenderer_ManagedInstance_formGroupsInstance(FieldRenderer instance) /*-{
    return instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::formGroupsInstance;
  }-*/;

  native static void FieldRenderer_ManagedInstance_formGroupsInstance(FieldRenderer instance, ManagedInstance<DefaultFormGroup> value) /*-{
    instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::formGroupsInstance = value;
  }-*/;

  native static SelectorDataProviderManager SelectorFieldRenderer_SelectorDataProviderManager_clientProviderManager(SelectorFieldRenderer instance) /*-{
    return instance.@org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.SelectorFieldRenderer::clientProviderManager;
  }-*/;

  native static void SelectorFieldRenderer_SelectorDataProviderManager_clientProviderManager(SelectorFieldRenderer instance, SelectorDataProviderManager value) /*-{
    instance.@org.kie.workbench.common.forms.dynamic.client.rendering.renderers.selectors.SelectorFieldRenderer::clientProviderManager = value;
  }-*/;

  native static FormsElementWrapperWidgetUtil FieldRenderer_FormsElementWrapperWidgetUtil_wrapperWidgetUtil(FieldRenderer instance) /*-{
    return instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::wrapperWidgetUtil;
  }-*/;

  native static void FieldRenderer_FormsElementWrapperWidgetUtil_wrapperWidgetUtil(FieldRenderer instance, FormsElementWrapperWidgetUtil value) /*-{
    instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::wrapperWidgetUtil = value;
  }-*/;

  native static ConfigErrorDisplayer FieldRenderer_ConfigErrorDisplayer_errorDisplayer(FieldRenderer instance) /*-{
    return instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::errorDisplayer;
  }-*/;

  native static void FieldRenderer_ConfigErrorDisplayer_errorDisplayer(FieldRenderer instance, ConfigErrorDisplayer value) /*-{
    instance.@org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer::errorDisplayer = value;
  }-*/;
}
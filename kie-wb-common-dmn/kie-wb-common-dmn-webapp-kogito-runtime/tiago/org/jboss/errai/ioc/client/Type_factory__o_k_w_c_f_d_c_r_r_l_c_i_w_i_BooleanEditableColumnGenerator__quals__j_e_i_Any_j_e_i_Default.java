package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.EditableColumnGenerator;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.AbstractEditableColumnGenerator;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.BooleanEditableColumnGenerator;

public class Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_BooleanEditableColumnGenerator__quals__j_e_i_Any_j_e_i_Default extends Factory<BooleanEditableColumnGenerator> { public Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_BooleanEditableColumnGenerator__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(BooleanEditableColumnGenerator.class, "Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_BooleanEditableColumnGenerator__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { BooleanEditableColumnGenerator.class, AbstractEditableColumnGenerator.class, Object.class, EditableColumnGenerator.class });
  }

  public BooleanEditableColumnGenerator createInstance(final ContextManager contextManager) {
    final TranslationService _translationService_0 = (TranslationService) contextManager.getInstance("Provider_factory__o_j_e_u_c_l_s_TranslationService__quals__j_e_i_Any_j_e_i_Default");
    final BooleanEditableColumnGenerator instance = new BooleanEditableColumnGenerator(_translationService_0);
    registerDependentScopedReference(instance, _translationService_0);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
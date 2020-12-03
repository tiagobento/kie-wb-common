package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.stunner.client.widgets.toolbar.ToolbarCommand;
import org.kie.workbench.common.stunner.client.widgets.toolbar.command.AbstractToolbarCommand;
import org.kie.workbench.common.stunner.client.widgets.toolbar.command.DeleteSelectionToolbarCommand;
import org.kie.workbench.common.stunner.core.client.i18n.ClientTranslationService;
import org.kie.workbench.common.stunner.core.client.session.command.impl.DeleteSelectionSessionCommand;
import org.kie.workbench.common.stunner.core.util.DefinitionUtils;

public class Type_factory__o_k_w_c_s_c_w_t_c_DeleteSelectionToolbarCommand__quals__j_e_i_Any_j_e_i_Default extends Factory<DeleteSelectionToolbarCommand> { public Type_factory__o_k_w_c_s_c_w_t_c_DeleteSelectionToolbarCommand__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(DeleteSelectionToolbarCommand.class, "Type_factory__o_k_w_c_s_c_w_t_c_DeleteSelectionToolbarCommand__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { DeleteSelectionToolbarCommand.class, AbstractToolbarCommand.class, Object.class, ToolbarCommand.class });
  }

  public DeleteSelectionToolbarCommand createInstance(final ContextManager contextManager) {
    final DefinitionUtils _definitionUtils_0 = (DefinitionUtils) contextManager.getInstance("Type_factory__o_k_w_c_s_c_u_DefinitionUtils__quals__j_e_i_Any_j_e_i_Default");
    final ClientTranslationService _translationService_2 = (ClientTranslationService) contextManager.getInstance("Type_factory__o_k_w_c_s_c_c_i_ClientTranslationService__quals__j_e_i_Any_j_e_i_Default");
    final ManagedInstance<DeleteSelectionSessionCommand> _command_1 = (ManagedInstance) contextManager.getContextualInstance("ContextualProvider_factory__o_j_e_i_c_a_ManagedInstance__quals__Universal", new Class[] { DeleteSelectionSessionCommand.class }, new Annotation[] { new Any() {
        public Class annotationType() {
          return Any.class;
        }
        public String toString() {
          return "@javax.enterprise.inject.Any()";
        }
    } });
    final DeleteSelectionToolbarCommand instance = new DeleteSelectionToolbarCommand(_definitionUtils_0, _command_1, _translationService_2);
    registerDependentScopedReference(instance, _command_1);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
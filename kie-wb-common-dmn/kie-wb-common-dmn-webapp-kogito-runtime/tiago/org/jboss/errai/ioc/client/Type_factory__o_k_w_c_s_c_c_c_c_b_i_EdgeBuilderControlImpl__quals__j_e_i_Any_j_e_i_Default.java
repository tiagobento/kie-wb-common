package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.stunner.client.lienzo.canvas.command.LienzoCanvasCommandFactory;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.controls.AbstractCanvasHandlerControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.CanvasControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.builder.BuilderControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.builder.EdgeBuilderControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.builder.impl.EdgeBuilderControlImpl;
import org.kie.workbench.common.stunner.core.client.command.CanvasCommandFactory;
import org.kie.workbench.common.stunner.core.client.command.RequiresCommandManager;

public class Type_factory__o_k_w_c_s_c_c_c_c_b_i_EdgeBuilderControlImpl__quals__j_e_i_Any_j_e_i_Default extends Factory<EdgeBuilderControlImpl> { public Type_factory__o_k_w_c_s_c_c_c_c_b_i_EdgeBuilderControlImpl__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(EdgeBuilderControlImpl.class, "Type_factory__o_k_w_c_s_c_c_c_c_b_i_EdgeBuilderControlImpl__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { EdgeBuilderControlImpl.class, AbstractCanvasHandlerControl.class, Object.class, CanvasControl.class, EdgeBuilderControl.class, BuilderControl.class, RequiresCommandManager.class });
  }

  public EdgeBuilderControlImpl createInstance(final ContextManager contextManager) {
    final CanvasCommandFactory<AbstractCanvasHandler> _commandFactory_0 = (LienzoCanvasCommandFactory) contextManager.getInstance("Type_factory__o_k_w_c_s_c_l_c_c_LienzoCanvasCommandFactory__quals__j_e_i_Any_j_e_i_Default");
    final EdgeBuilderControlImpl instance = new EdgeBuilderControlImpl(_commandFactory_0);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.stunner.client.lienzo.canvas.LienzoLayer;

public class Type_factory__o_k_w_c_s_c_l_c_LienzoLayer__quals__j_e_i_Any_j_e_i_Default extends Factory<LienzoLayer> { public Type_factory__o_k_w_c_s_c_l_c_LienzoLayer__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(LienzoLayer.class, "Type_factory__o_k_w_c_s_c_l_c_LienzoLayer__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { LienzoLayer.class, Object.class });
  }

  public LienzoLayer createInstance(final ContextManager contextManager) {
    final LienzoLayer instance = new LienzoLayer();
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.stunner.core.graph.processing.index.GraphIndexBuilder;
import org.kie.workbench.common.stunner.core.graph.processing.index.IndexBuilder;
import org.kie.workbench.common.stunner.core.graph.processing.index.map.MapIndexBuilder;

public class Type_factory__o_k_w_c_s_c_g_p_i_m_MapIndexBuilder__quals__j_e_i_Any_j_e_i_Default extends Factory<MapIndexBuilder> { public Type_factory__o_k_w_c_s_c_g_p_i_m_MapIndexBuilder__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(MapIndexBuilder.class, "Type_factory__o_k_w_c_s_c_g_p_i_m_MapIndexBuilder__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { MapIndexBuilder.class, Object.class, GraphIndexBuilder.class, IndexBuilder.class });
  }

  public MapIndexBuilder createInstance(final ContextManager contextManager) {
    final MapIndexBuilder instance = new MapIndexBuilder();
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
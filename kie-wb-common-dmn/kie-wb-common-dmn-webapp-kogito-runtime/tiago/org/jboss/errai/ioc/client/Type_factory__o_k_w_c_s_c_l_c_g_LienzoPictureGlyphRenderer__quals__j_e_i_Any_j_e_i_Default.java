package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.stunner.client.lienzo.components.glyph.AbstractLienzoShapeGlyphRenderer;
import org.kie.workbench.common.stunner.client.lienzo.components.glyph.LienzoGlyphRenderer;
import org.kie.workbench.common.stunner.client.lienzo.components.glyph.LienzoPictureGlyphRenderer;
import org.kie.workbench.common.stunner.core.client.components.glyph.GlyphRenderer;

public class Type_factory__o_k_w_c_s_c_l_c_g_LienzoPictureGlyphRenderer__quals__j_e_i_Any_j_e_i_Default extends Factory<LienzoPictureGlyphRenderer> { public Type_factory__o_k_w_c_s_c_l_c_g_LienzoPictureGlyphRenderer__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(LienzoPictureGlyphRenderer.class, "Type_factory__o_k_w_c_s_c_l_c_g_LienzoPictureGlyphRenderer__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { LienzoPictureGlyphRenderer.class, AbstractLienzoShapeGlyphRenderer.class, Object.class, LienzoGlyphRenderer.class, GlyphRenderer.class });
  }

  public LienzoPictureGlyphRenderer createInstance(final ContextManager contextManager) {
    final LienzoPictureGlyphRenderer instance = new LienzoPictureGlyphRenderer();
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
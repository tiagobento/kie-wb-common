package org.kie.workbench.common.stunner.client.widgets.explorer.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class TreeExplorerView_ViewBinderImpl_GenBundle_default_InlineClientBundleGenerator implements org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenBundle {
  private static TreeExplorerView_ViewBinderImpl_GenBundle_default_InlineClientBundleGenerator _instance0 = new TreeExplorerView_ViewBinderImpl_GenBundle_default_InlineClientBundleGenerator();
  private void styleInitializer() {
    style = new org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenCss_style() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "style";
      }
      public String getText() {
        return ("");
      }
    }
    ;
  }
  private static class styleInitializer {
    static {
      _instance0.styleInitializer();
    }
    static org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenCss_style get() {
      return style;
    }
  }
  public org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenCss_style style() {
    return styleInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenCss_style style;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      style(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("style", style());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'style': return this.@org.kie.workbench.common.stunner.client.widgets.explorer.tree.TreeExplorerView_ViewBinderImpl_GenBundle::style()();
    }
    return null;
  }-*/;
}

package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.uberfire.security.Resource;
import org.uberfire.security.authz.RuntimeFeatureResource;
import org.uberfire.security.authz.RuntimeResource;
import org.uberfire.workbench.model.menu.HasEnabledStateChangeListeners;
import org.uberfire.workbench.model.menu.MenuGroup;
import org.uberfire.workbench.model.menu.MenuItem;

public class JsType_factory__o_u_w_m_m_MenuGroup__quals__Universal extends Factory<MenuGroup> { public JsType_factory__o_u_w_m_m_MenuGroup__quals__Universal() {
    super(new FactoryHandleImpl(MenuGroup.class, "JsType_factory__o_u_w_m_m_MenuGroup__quals__Universal", Dependent.class, false, null, false));
    handle.setAssignableTypes(new Class[] { MenuGroup.class, MenuItem.class, RuntimeFeatureResource.class, RuntimeResource.class, Resource.class, HasEnabledStateChangeListeners.class });
    handle.setQualifiers(new Annotation[] { });
  }

  public MenuGroup createInstance(final ContextManager contextManager) {
    return (MenuGroup) WindowInjectionContextStorage.createOrGet().getBean("org.uberfire.workbench.model.menu.MenuGroup");
  }
}
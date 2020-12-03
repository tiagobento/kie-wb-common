package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.uberfire.workbench.model.Position;

public class JsType_factory__o_u_w_m_Position__quals__Universal extends Factory<Position> { public JsType_factory__o_u_w_m_Position__quals__Universal() {
    super(new FactoryHandleImpl(Position.class, "JsType_factory__o_u_w_m_Position__quals__Universal", Dependent.class, false, null, false));
    handle.setAssignableTypes(new Class[] { Position.class });
    handle.setQualifiers(new Annotation[] { });
  }

  public Position createInstance(final ContextManager contextManager) {
    return (Position) WindowInjectionContextStorage.createOrGet().getBean("org.uberfire.workbench.model.Position");
  }
}
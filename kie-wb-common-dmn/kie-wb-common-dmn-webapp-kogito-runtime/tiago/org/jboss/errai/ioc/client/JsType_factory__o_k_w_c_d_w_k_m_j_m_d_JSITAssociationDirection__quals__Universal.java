package org.jboss.errai.ioc.client;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITAssociationDirection;

public class JsType_factory__o_k_w_c_d_w_k_m_j_m_d_JSITAssociationDirection__quals__Universal extends Factory<JSITAssociationDirection> { public JsType_factory__o_k_w_c_d_w_k_m_j_m_d_JSITAssociationDirection__quals__Universal() {
    super(new FactoryHandleImpl(JSITAssociationDirection.class, "JsType_factory__o_k_w_c_d_w_k_m_j_m_d_JSITAssociationDirection__quals__Universal", Dependent.class, false, null, false));
    handle.setAssignableTypes(new Class[] { JSITAssociationDirection.class, Enum.class, Object.class, Comparable.class, Serializable.class });
    handle.setQualifiers(new Annotation[] { });
  }

  public JSITAssociationDirection createInstance(final ContextManager contextManager) {
    return (JSITAssociationDirection) WindowInjectionContextStorage.createOrGet().getBean("org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITAssociationDirection");
  }
}
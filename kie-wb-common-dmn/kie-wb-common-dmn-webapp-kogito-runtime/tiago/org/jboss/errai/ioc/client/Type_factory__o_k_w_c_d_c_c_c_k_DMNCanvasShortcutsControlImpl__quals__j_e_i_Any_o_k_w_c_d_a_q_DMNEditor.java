package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.dmn.api.qualifiers.DMNEditor;
import org.kie.workbench.common.dmn.client.canvas.controls.keyboard.DMNCanvasShortcutsControlImpl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.AbstractCanvasHandlerControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.AbstractCanvasHandlerRegistrationControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.CanvasControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.CanvasControl.SessionAware;
import org.kie.workbench.common.stunner.core.client.canvas.controls.CanvasRegistrationControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.keyboard.AbstractCanvasShortcutsControlImpl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.keyboard.KeyboardControl.KeyShortcutCallback;
import org.kie.workbench.common.stunner.core.client.canvas.controls.keyboard.shortcut.KeyboardShortcut;

public class Type_factory__o_k_w_c_d_c_c_c_k_DMNCanvasShortcutsControlImpl__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor extends Factory<DMNCanvasShortcutsControlImpl> { public Type_factory__o_k_w_c_d_c_c_c_k_DMNCanvasShortcutsControlImpl__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor() {
    super(new FactoryHandleImpl(DMNCanvasShortcutsControlImpl.class, "Type_factory__o_k_w_c_d_c_c_c_k_DMNCanvasShortcutsControlImpl__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { DMNCanvasShortcutsControlImpl.class, AbstractCanvasShortcutsControlImpl.class, AbstractCanvasHandlerRegistrationControl.class, AbstractCanvasHandlerControl.class, Object.class, CanvasControl.class, CanvasRegistrationControl.class, SessionAware.class, KeyShortcutCallback.class });
    handle.setQualifiers(new Annotation[] { QualifierUtil.ANY_ANNOTATION, new DMNEditor() {
        public Class annotationType() {
          return DMNEditor.class;
        }
        public String toString() {
          return "@org.kie.workbench.common.dmn.api.qualifiers.DMNEditor()";
        }
    } });
  }

  public DMNCanvasShortcutsControlImpl createInstance(final ContextManager contextManager) {
    final Instance<KeyboardShortcut> _implementedActions_0 = (Instance) contextManager.getContextualInstance("ContextualProvider_factory__j_e_i_Instance__quals__Universal", new Class[] { KeyboardShortcut.class }, new Annotation[] { new DMNEditor() {
        public Class annotationType() {
          return DMNEditor.class;
        }
        public String toString() {
          return "@org.kie.workbench.common.dmn.api.qualifiers.DMNEditor()";
        }
    } });
    final DMNCanvasShortcutsControlImpl instance = new DMNCanvasShortcutsControlImpl(_implementedActions_0);
    registerDependentScopedReference(instance, _implementedActions_0);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
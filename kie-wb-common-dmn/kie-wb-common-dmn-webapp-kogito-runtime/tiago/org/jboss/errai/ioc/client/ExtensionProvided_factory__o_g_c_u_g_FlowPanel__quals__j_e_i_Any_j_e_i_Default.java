package org.jboss.errai.ioc.client;

import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HasWidgets.ForIsWidget;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import javax.enterprise.context.Dependent;
import org.gwtbootstrap3.client.ui.base.HasDataSpy;
import org.gwtbootstrap3.client.ui.base.HasDataTarget;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;

public class ExtensionProvided_factory__o_g_c_u_g_FlowPanel__quals__j_e_i_Any_j_e_i_Default extends Factory<FlowPanel> { public ExtensionProvided_factory__o_g_c_u_g_FlowPanel__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(FlowPanel.class, "ExtensionProvided_factory__o_g_c_u_g_FlowPanel__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { FlowPanel.class, com.google.gwt.user.client.ui.FlowPanel.class, ComplexPanel.class, Panel.class, Widget.class, UIObject.class, Object.class, HasVisibility.class, EventListener.class, HasAttachHandlers.class, HasHandlers.class, IsWidget.class, ForIsWidget.class, HasWidgets.class, Iterable.class, com.google.gwt.user.client.ui.IndexedPanel.ForIsWidget.class, IndexedPanel.class, com.google.gwt.user.client.ui.InsertPanel.ForIsWidget.class, InsertPanel.class, HasDataSpy.class, HasId.class, HasDataTarget.class, HasResponsiveness.class });
  }

  public FlowPanel createInstance(final ContextManager contextManager) {
    return new FlowPanel();
  }
}
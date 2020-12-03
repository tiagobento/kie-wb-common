package org.jboss.errai.ioc.client;

import javax.enterprise.context.Dependent;
import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.DateTimePickerPresenter;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.DateTimePickerPresenterView;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.DateTimePickerPresenterView.Presenter;
import org.kie.workbench.common.forms.dynamic.client.rendering.renderers.lov.creator.input.widget.impl.DateTimePickerPresenterViewImpl;

public class Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_DateTimePickerPresenter__quals__j_e_i_Any_j_e_i_Default extends Factory<DateTimePickerPresenter> { public Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_DateTimePickerPresenter__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(DateTimePickerPresenter.class, "Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_DateTimePickerPresenter__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { DateTimePickerPresenter.class, Object.class, Presenter.class, IsElement.class });
  }

  public DateTimePickerPresenter createInstance(final ContextManager contextManager) {
    final DateTimePickerPresenterView _view_0 = (DateTimePickerPresenterViewImpl) contextManager.getInstance("Type_factory__o_k_w_c_f_d_c_r_r_l_c_i_w_i_DateTimePickerPresenterViewImpl__quals__j_e_i_Any_j_e_i_Default");
    final DateTimePickerPresenter instance = new DateTimePickerPresenter(_view_0);
    registerDependentScopedReference(instance, _view_0);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }
}
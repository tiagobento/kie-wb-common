package org.jboss.errai.ioc.client;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ioc.client.container.Context;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.jboss.errai.ioc.client.container.Proxy;
import org.jboss.errai.ioc.client.container.ProxyHelper;
import org.jboss.errai.ioc.client.container.ProxyHelperImpl;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.workbench.common.dmn.api.definition.HasExpression;
import org.kie.workbench.common.dmn.api.definition.model.DecisionTable;
import org.kie.workbench.common.dmn.client.api.ReadOnlyProviderImpl;
import org.kie.workbench.common.dmn.client.commands.factory.DefaultCanvasCommandFactory;
import org.kie.workbench.common.dmn.client.editors.expressions.types.BaseEditorDefinition;
import org.kie.workbench.common.dmn.client.editors.expressions.types.ExpressionEditorDefinition;
import org.kie.workbench.common.dmn.client.editors.expressions.types.ExpressionEditorModelEnricher;
import org.kie.workbench.common.dmn.client.editors.expressions.types.ExpressionType;
import org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.DecisionTableEditorDefinition;
import org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.DecisionTableEditorDefinitionEnricher;
import org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.DecisionTableGridData;
import org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.hitpolicy.HitPolicyPopoverImpl;
import org.kie.workbench.common.dmn.client.widgets.grid.controls.container.CellEditorControlsView.Presenter;
import org.kie.workbench.common.dmn.client.widgets.grid.controls.list.ListSelector;
import org.kie.workbench.common.dmn.client.widgets.grid.model.ExpressionEditorChanged;
import org.kie.workbench.common.dmn.client.widgets.grid.model.GridCellTuple;
import org.kie.workbench.common.dmn.client.widgets.layer.DMNGridLayer;
import org.kie.workbench.common.dmn.client.widgets.panel.DMNGridPanel;
import org.kie.workbench.common.stunner.core.client.ReadOnlyProvider;
import org.kie.workbench.common.stunner.core.client.api.GlobalSessionManager;
import org.kie.workbench.common.stunner.core.client.api.SessionManager;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.event.selection.DomainObjectSelectionEvent;
import org.kie.workbench.common.stunner.core.client.command.ApplicationCommandManager;
import org.kie.workbench.common.stunner.core.client.command.SessionCommandManager;
import org.kie.workbench.common.stunner.core.util.DefinitionUtils;
import org.kie.workbench.common.stunner.forms.client.event.RefreshFormPropertiesEvent;

public class Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_Default extends Factory<DecisionTableEditorDefinition> { private class Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_DefaultProxyImpl extends DecisionTableEditorDefinition implements Proxy<DecisionTableEditorDefinition> {
    private final ProxyHelper<DecisionTableEditorDefinition> proxyHelper = new ProxyHelperImpl<DecisionTableEditorDefinition>("Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_Default");
    public void initProxyProperties(final DecisionTableEditorDefinition instance) {

    }

    public DecisionTableEditorDefinition asBeanType() {
      return this;
    }

    public void setInstance(final DecisionTableEditorDefinition instance) {
      proxyHelper.setInstance(instance);
    }

    public void clearInstance() {
      proxyHelper.clearInstance();
    }

    public void setProxyContext(final Context context) {
      proxyHelper.setProxyContext(context);
    }

    public Context getProxyContext() {
      return proxyHelper.getProxyContext();
    }

    public Object unwrap() {
      return proxyHelper.getInstance(this);
    }

    public boolean equals(Object obj) {
      obj = Factory.maybeUnwrapProxy(obj);
      return proxyHelper.getInstance(this).equals(obj);
    }

    @Override public ExpressionType getType() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final ExpressionType retVal = proxiedInstance.getType();
        return retVal;
      } else {
        return super.getType();
      }
    }

    @Override public String getName() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final String retVal = proxiedInstance.getName();
        return retVal;
      } else {
        return super.getName();
      }
    }

    @Override public Optional getModelClass() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final Optional retVal = proxiedInstance.getModelClass();
        return retVal;
      } else {
        return super.getModelClass();
      }
    }

    @Override public void enrich(Optional nodeUUID, HasExpression hasExpression, Optional expression) {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        proxiedInstance.enrich(nodeUUID, hasExpression, expression);
      } else {
        super.enrich(nodeUUID, hasExpression, expression);
      }
    }

    @Override public Optional getEditor(GridCellTuple parent, Optional nodeUUID, HasExpression hasExpression, Optional hasName, boolean isOnlyVisualChangeAllowed, int nesting) {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final Optional retVal = proxiedInstance.getEditor(parent, nodeUUID, hasExpression, hasName, isOnlyVisualChangeAllowed, nesting);
        return retVal;
      } else {
        return super.getEditor(parent, nodeUUID, hasExpression, hasName, isOnlyVisualChangeAllowed, nesting);
      }
    }

    @Override protected DecisionTableGridData makeGridData(Supplier expression) {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final DecisionTableGridData retVal = DecisionTableEditorDefinition_makeGridData_Supplier(proxiedInstance, expression);
        return retVal;
      } else {
        return super.makeGridData(expression);
      }
    }

    @Override protected DMNGridPanel getGridPanel() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final DMNGridPanel retVal = BaseEditorDefinition_getGridPanel(proxiedInstance);
        return retVal;
      } else {
        return super.getGridPanel();
      }
    }

    @Override protected DMNGridLayer getGridLayer() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final DMNGridLayer retVal = BaseEditorDefinition_getGridLayer(proxiedInstance);
        return retVal;
      } else {
        return super.getGridLayer();
      }
    }

    @Override protected Presenter getCellEditorControls() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final Presenter retVal = BaseEditorDefinition_getCellEditorControls(proxiedInstance);
        return retVal;
      } else {
        return super.getCellEditorControls();
      }
    }

    @Override public int hashCode() {
      if (proxyHelper != null) {
        final DecisionTableEditorDefinition proxiedInstance = proxyHelper.getInstance(this);
        final int retVal = proxiedInstance.hashCode();
        return retVal;
      } else {
        return super.hashCode();
      }
    }
  }
  public Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(DecisionTableEditorDefinition.class, "Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_Default", ApplicationScoped.class, false, null, true));
    handle.setAssignableTypes(new Class[] { DecisionTableEditorDefinition.class, BaseEditorDefinition.class, Object.class, ExpressionEditorDefinition.class, ExpressionEditorModelEnricher.class });
  }

  public DecisionTableEditorDefinition createInstance(final ContextManager contextManager) {
    final ReadOnlyProvider _readOnlyProvider_12 = (ReadOnlyProviderImpl) contextManager.getInstance("Type_factory__o_k_w_c_d_c_a_ReadOnlyProviderImpl__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor");
    final org.kie.workbench.common.dmn.client.widgets.grid.controls.list.ListSelectorView.Presenter _listSelector_7 = (ListSelector) contextManager.getInstance("Type_factory__o_k_w_c_d_c_w_g_c_l_ListSelector__quals__j_e_i_Any_j_e_i_Default");
    final org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.hitpolicy.HitPolicyPopoverView.Presenter _hitPolicyEditor_9 = (HitPolicyPopoverImpl) contextManager.getInstance("Type_factory__o_k_w_c_d_c_e_e_t_d_h_HitPolicyPopoverImpl__quals__j_e_i_Any_j_e_i_Default");
    final Event<ExpressionEditorChanged> _editorSelectedEvent_4 = (Event) contextManager.getContextualInstance("ContextualProvider_factory__j_e_e_Event__quals__Universal", new Class[] { ExpressionEditorChanged.class }, new Annotation[] { });
    final Event<RefreshFormPropertiesEvent> _refreshFormPropertiesEvent_5 = (Event) contextManager.getContextualInstance("ContextualProvider_factory__j_e_e_Event__quals__Universal", new Class[] { RefreshFormPropertiesEvent.class }, new Annotation[] { });
    final SessionCommandManager<AbstractCanvasHandler> _sessionCommandManager_2 = (ApplicationCommandManager) contextManager.getInstance("Type_factory__o_k_w_c_s_c_c_c_ApplicationCommandManager__quals__j_e_i_Any_j_e_i_Default");
    final DefinitionUtils _definitionUtils_0 = (DefinitionUtils) contextManager.getInstance("Type_factory__o_k_w_c_s_c_u_DefinitionUtils__quals__j_e_i_Any_j_e_i_Default");
    final SessionManager _sessionManager_1 = (GlobalSessionManager) contextManager.getInstance("Type_factory__o_k_w_c_s_c_c_a_GlobalSessionManager__quals__j_e_i_Any_j_e_i_Default");
    final Event<DomainObjectSelectionEvent> _domainObjectSelectionEvent_6 = (Event) contextManager.getContextualInstance("ContextualProvider_factory__j_e_e_Event__quals__Universal", new Class[] { DomainObjectSelectionEvent.class }, new Annotation[] { });
    final TranslationService _translationService_8 = (TranslationService) contextManager.getInstance("Provider_factory__o_j_e_u_c_l_s_TranslationService__quals__j_e_i_Any_j_e_i_Default");
    final DefaultCanvasCommandFactory _canvasCommandFactory_3 = (DefaultCanvasCommandFactory) contextManager.getInstance("Type_factory__o_k_w_c_d_c_c_f_DefaultCanvasCommandFactory__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor");
    final ManagedInstance<org.kie.workbench.common.dmn.client.editors.types.ValueAndDataTypePopoverView.Presenter> _headerEditors_10 = (ManagedInstance) contextManager.getContextualInstance("ContextualProvider_factory__o_j_e_i_c_a_ManagedInstance__quals__Universal", new Class[] { org.kie.workbench.common.dmn.client.editors.types.ValueAndDataTypePopoverView.Presenter.class }, new Annotation[] { });
    final DecisionTableEditorDefinitionEnricher _enricher_11 = (DecisionTableEditorDefinitionEnricher) contextManager.getInstance("Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinitionEnricher__quals__j_e_i_Any_j_e_i_Default");
    final DecisionTableEditorDefinition instance = new DecisionTableEditorDefinition(_definitionUtils_0, _sessionManager_1, _sessionCommandManager_2, _canvasCommandFactory_3, _editorSelectedEvent_4, _refreshFormPropertiesEvent_5, _domainObjectSelectionEvent_6, _listSelector_7, _translationService_8, _hitPolicyEditor_9, _headerEditors_10, _enricher_11, _readOnlyProvider_12);
    registerDependentScopedReference(instance, _listSelector_7);
    registerDependentScopedReference(instance, _editorSelectedEvent_4);
    registerDependentScopedReference(instance, _refreshFormPropertiesEvent_5);
    registerDependentScopedReference(instance, _domainObjectSelectionEvent_6);
    registerDependentScopedReference(instance, _translationService_8);
    registerDependentScopedReference(instance, _headerEditors_10);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }

  public Proxy createProxy(final Context context) {
    final Proxy<DecisionTableEditorDefinition> proxyImpl = new Type_factory__o_k_w_c_d_c_e_e_t_d_DecisionTableEditorDefinition__quals__j_e_i_Any_j_e_i_DefaultProxyImpl();
    proxyImpl.setProxyContext(context);
    return proxyImpl;
  }

  public native static Presenter BaseEditorDefinition_getCellEditorControls(BaseEditorDefinition instance) /*-{
    return instance.@org.kie.workbench.common.dmn.client.editors.expressions.types.BaseEditorDefinition::getCellEditorControls()();
  }-*/;

  public native static DMNGridLayer BaseEditorDefinition_getGridLayer(BaseEditorDefinition instance) /*-{
    return instance.@org.kie.workbench.common.dmn.client.editors.expressions.types.BaseEditorDefinition::getGridLayer()();
  }-*/;

  public native static DecisionTableGridData DecisionTableEditorDefinition_makeGridData_Supplier(DecisionTableEditorDefinition instance, Supplier<Optional<DecisionTable>> a0) /*-{
    return instance.@org.kie.workbench.common.dmn.client.editors.expressions.types.dtable.DecisionTableEditorDefinition::makeGridData(Ljava/util/function/Supplier;)(a0);
  }-*/;

  public native static DMNGridPanel BaseEditorDefinition_getGridPanel(BaseEditorDefinition instance) /*-{
    return instance.@org.kie.workbench.common.dmn.client.editors.expressions.types.BaseEditorDefinition::getGridPanel()();
  }-*/;
}
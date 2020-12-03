package org.jboss.errai.ioc.client;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.errai.ioc.client.container.Context;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.jboss.errai.ioc.client.container.Proxy;
import org.jboss.errai.ioc.client.container.ProxyHelper;
import org.jboss.errai.ioc.client.container.ProxyHelperImpl;
import org.kie.workbench.common.dmn.client.canvas.controls.actions.TextAnnotationTextPropertyProviderImpl;
import org.kie.workbench.common.dmn.client.commands.factory.DefaultCanvasCommandFactory;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.controls.actions.TextPropertyProvider;
import org.kie.workbench.common.stunner.core.client.command.CanvasCommandManager;
import org.kie.workbench.common.stunner.core.graph.Element;
import org.kie.workbench.common.stunner.core.util.DefinitionUtils;

public class Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_Default extends Factory<TextAnnotationTextPropertyProviderImpl> { private class Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_DefaultProxyImpl extends TextAnnotationTextPropertyProviderImpl implements Proxy<TextAnnotationTextPropertyProviderImpl> {
    private final ProxyHelper<TextAnnotationTextPropertyProviderImpl> proxyHelper = new ProxyHelperImpl<TextAnnotationTextPropertyProviderImpl>("Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_Default");
    public void initProxyProperties(final TextAnnotationTextPropertyProviderImpl instance) {

    }

    public TextAnnotationTextPropertyProviderImpl asBeanType() {
      return this;
    }

    public void setInstance(final TextAnnotationTextPropertyProviderImpl instance) {
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

    @Override public int getPriority() {
      if (proxyHelper != null) {
        final TextAnnotationTextPropertyProviderImpl proxiedInstance = proxyHelper.getInstance(this);
        final int retVal = proxiedInstance.getPriority();
        return retVal;
      } else {
        return super.getPriority();
      }
    }

    @Override public boolean supports(Element element) {
      if (proxyHelper != null) {
        final TextAnnotationTextPropertyProviderImpl proxiedInstance = proxyHelper.getInstance(this);
        final boolean retVal = proxiedInstance.supports(element);
        return retVal;
      } else {
        return super.supports(element);
      }
    }

    @Override public void setText(AbstractCanvasHandler canvasHandler, CanvasCommandManager commandManager, Element element, String text) {
      if (proxyHelper != null) {
        final TextAnnotationTextPropertyProviderImpl proxiedInstance = proxyHelper.getInstance(this);
        proxiedInstance.setText(canvasHandler, commandManager, element, text);
      } else {
        super.setText(canvasHandler, commandManager, element, text);
      }
    }

    @Override public String getText(Element element) {
      if (proxyHelper != null) {
        final TextAnnotationTextPropertyProviderImpl proxiedInstance = proxyHelper.getInstance(this);
        final String retVal = proxiedInstance.getText(element);
        return retVal;
      } else {
        return super.getText(element);
      }
    }

    @Override public int hashCode() {
      if (proxyHelper != null) {
        final TextAnnotationTextPropertyProviderImpl proxiedInstance = proxyHelper.getInstance(this);
        final int retVal = proxiedInstance.hashCode();
        return retVal;
      } else {
        return super.hashCode();
      }
    }
  }
  public Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(TextAnnotationTextPropertyProviderImpl.class, "Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_Default", ApplicationScoped.class, false, null, true));
    handle.setAssignableTypes(new Class[] { TextAnnotationTextPropertyProviderImpl.class, Object.class, TextPropertyProvider.class });
  }

  public TextAnnotationTextPropertyProviderImpl createInstance(final ContextManager contextManager) {
    final DefaultCanvasCommandFactory _canvasCommandFactory_0 = (DefaultCanvasCommandFactory) contextManager.getInstance("Type_factory__o_k_w_c_d_c_c_f_DefaultCanvasCommandFactory__quals__j_e_i_Any_o_k_w_c_d_a_q_DMNEditor");
    final DefinitionUtils _definitionUtils_1 = (DefinitionUtils) contextManager.getInstance("Type_factory__o_k_w_c_s_c_u_DefinitionUtils__quals__j_e_i_Any_j_e_i_Default");
    final TextAnnotationTextPropertyProviderImpl instance = new TextAnnotationTextPropertyProviderImpl(_canvasCommandFactory_0, _definitionUtils_1);
    setIncompleteInstance(instance);
    setIncompleteInstance(null);
    return instance;
  }

  public Proxy createProxy(final Context context) {
    final Proxy<TextAnnotationTextPropertyProviderImpl> proxyImpl = new Type_factory__o_k_w_c_d_c_c_c_a_TextAnnotationTextPropertyProviderImpl__quals__j_e_i_Any_j_e_i_DefaultProxyImpl();
    proxyImpl.setProxyContext(context);
    return proxyImpl;
  }
}
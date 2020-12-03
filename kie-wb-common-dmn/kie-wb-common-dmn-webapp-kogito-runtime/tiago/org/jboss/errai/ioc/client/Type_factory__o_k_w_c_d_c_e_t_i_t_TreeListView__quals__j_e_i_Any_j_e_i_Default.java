package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLDivElement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.enterprise.context.Dependent;
import org.jboss.errai.common.client.api.elemental2.IsElement;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ioc.client.container.Context;
import org.jboss.errai.ioc.client.container.ContextManager;
import org.jboss.errai.ioc.client.container.Factory;
import org.jboss.errai.ioc.client.container.FactoryHandleImpl;
import org.jboss.errai.ui.shared.DataFieldMeta;
import org.jboss.errai.ui.shared.Template;
import org.jboss.errai.ui.shared.TemplateUtil;
import org.jboss.errai.ui.shared.api.style.StyleBindingsRegistry;
import org.kie.workbench.common.dmn.client.editors.types.imported.treelist.TreeList.View;
import org.kie.workbench.common.dmn.client.editors.types.imported.treelist.TreeListView;
import org.uberfire.client.mvp.HasPresenter;
import org.uberfire.client.mvp.UberElemental;

public class Type_factory__o_k_w_c_d_c_e_t_i_t_TreeListView__quals__j_e_i_Any_j_e_i_Default extends Factory<TreeListView> { public interface o_k_w_c_d_c_e_t_i_t_TreeListViewTemplateResource extends Template, ClientBundle { @Source("org/kie/workbench/common/dmn/client/editors/types/imported/treelist/TreeListView.html") public TextResource getContents(); }
  public Type_factory__o_k_w_c_d_c_e_t_i_t_TreeListView__quals__j_e_i_Any_j_e_i_Default() {
    super(new FactoryHandleImpl(TreeListView.class, "Type_factory__o_k_w_c_d_c_e_t_i_t_TreeListView__quals__j_e_i_Any_j_e_i_Default", Dependent.class, false, null, true));
    handle.setAssignableTypes(new Class[] { TreeListView.class, Object.class, View.class, UberElemental.class, IsElement.class, HasPresenter.class });
  }

  public void init(final Context context) {
    StyleInjector.inject("/*\n * Copyright 2019 Red Hat, Inc. and/or its affiliates.\n *\n * Licensed under the Apache License, Version 2.0 (the \"License\");\n * you may not use this file except in compliance with the License.\n * You may obtain a copy of the License at\n *\n *     http://www.apache.org/licenses/LICENSE-2.0\n *\n * Unless required by applicable law or agreed to in writing, software\n * distributed under the License is distributed on an \"AS IS\" BASIS,\n * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n * See the License for the specific language governing permissions and\n * limitations under the License.\n */\n\n");
  }

  public TreeListView createInstance(final ContextManager contextManager) {
    final HTMLDivElement _treeItemsContainer_0 = (HTMLDivElement) contextManager.getInstance("ExtensionProvided_factory__e_d_HTMLDivElement__quals__j_e_i_Any_j_e_i_Default_j_i_Named");
    final TreeListView instance = new TreeListView(_treeItemsContainer_0);
    registerDependentScopedReference(instance, _treeItemsContainer_0);
    setIncompleteInstance(instance);
    o_k_w_c_d_c_e_t_i_t_TreeListViewTemplateResource templateForTreeListView = GWT.create(o_k_w_c_d_c_e_t_i_t_TreeListViewTemplateResource.class);
    Element parentElementForTemplateOfTreeListView = TemplateUtil.getRootTemplateParentElement(templateForTreeListView.getContents().getText(), "org/kie/workbench/common/dmn/client/editors/types/imported/treelist/TreeListView.html", "");
    TemplateUtil.translateTemplate("org/kie/workbench/common/dmn/client/editors/types/imported/treelist/TreeListView.html", TemplateUtil.getRootTemplateElement(parentElementForTemplateOfTreeListView));
    Map<String, Element> dataFieldElements = TemplateUtil.getDataFieldElements(TemplateUtil.getRootTemplateElement(parentElementForTemplateOfTreeListView));
    final Map<String, DataFieldMeta> dataFieldMetas = new HashMap<String, DataFieldMeta>(1);
    dataFieldMetas.put("tree-items-container", new DataFieldMeta());
    Map<String, Widget> templateFieldsMap = new LinkedHashMap<String, Widget>();
    TemplateUtil.compositeComponentReplace("org.kie.workbench.common.dmn.client.editors.types.imported.treelist.TreeListView", "org/kie/workbench/common/dmn/client/editors/types/imported/treelist/TreeListView.html", new Supplier<Widget>() {
      public Widget get() {
        return ElementWrapperWidget.getWidget(TemplateUtil.asElement(TreeListView_HTMLDivElement_treeItemsContainer(instance)));
      }
    }, dataFieldElements, dataFieldMetas, "tree-items-container");
    templateFieldsMap.put("tree-items-container", ElementWrapperWidget.getWidget(TemplateUtil.asElement(TreeListView_HTMLDivElement_treeItemsContainer(instance))));
    TemplateUtil.initTemplated(instance, TemplateUtil.getRootTemplateElement(parentElementForTemplateOfTreeListView), templateFieldsMap.values());
    StyleBindingsRegistry.get().updateStyles(instance);
    setIncompleteInstance(null);
    return instance;
  }

  public void generatedDestroyInstance(final Object instance, final ContextManager contextManager) {
    destroyInstanceHelper((TreeListView) instance, contextManager);
  }

  public void destroyInstanceHelper(final TreeListView instance, final ContextManager contextManager) {
    TemplateUtil.cleanupTemplated(instance);
  }

  native static HTMLDivElement TreeListView_HTMLDivElement_treeItemsContainer(TreeListView instance) /*-{
    return instance.@org.kie.workbench.common.dmn.client.editors.types.imported.treelist.TreeListView::treeItemsContainer;
  }-*/;

  native static void TreeListView_HTMLDivElement_treeItemsContainer(TreeListView instance, HTMLDivElement value) /*-{
    instance.@org.kie.workbench.common.dmn.client.editors.types.imported.treelist.TreeListView::treeItemsContainer = value;
  }-*/;
}
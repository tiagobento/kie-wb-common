package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class Type_factory__o_k_w_c_w_c_c_f_CardFrameComponentView__quals__j_e_i_Any_j_e_i_Default_o_k_w_c_w_c_c_f_CardFrameComponentViewTemplateResource_default_InlineClientBundleGenerator implements org.jboss.errai.ioc.client.Type_factory__o_k_w_c_w_c_c_f_CardFrameComponentView__quals__j_e_i_Any_j_e_i_Default.o_k_w_c_w_c_c_f_CardFrameComponentViewTemplateResource {
  private static Type_factory__o_k_w_c_w_c_c_f_CardFrameComponentView__quals__j_e_i_Any_j_e_i_Default_o_k_w_c_w_c_c_f_CardFrameComponentViewTemplateResource_default_InlineClientBundleGenerator _instance0 = new Type_factory__o_k_w_c_w_c_c_f_CardFrameComponentView__quals__j_e_i_Any_j_e_i_Default_o_k_w_c_w_c_c_f_CardFrameComponentViewTemplateResource_default_InlineClientBundleGenerator();
  private void getContentsInitializer() {
    getContents = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/Users/tiagobento/.m2/repository/org/kie/workbench/widgets/kie-wb-common-ui/7.47.0-SNAPSHOT/kie-wb-common-ui-7.47.0-SNAPSHOT-sources.jar!/org/kie/workbench/common/widgets/client/cards/frame/CardFrameComponentView.html
      public String getText() {
        return "<!--\n  ~ Copyright 2019 Red Hat, Inc. and/or its affiliates.\n  ~\n  ~ Licensed under the Apache License, Version 2.0 (the \"License\");\n  ~ you may not use this file except in compliance with the License.\n  ~ You may obtain a copy of the License at\n  ~\n  ~       http://www.apache.org/licenses/LICENSE-2.0\n  ~\n  ~ Unless required by applicable law or agreed to in writing, software\n  ~ distributed under the License is distributed on an \"AS IS\" BASIS,\n  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n  ~ See the License for the specific language governing permissions and\n  ~ limitations under the License.\n  -->\n\n<div data-field=\"view\">\n    <div class=\"col-xs-12 col-lg-3\">\n        <div class=\"card-pf card-pf-view card-pf-view-select card-pf-view-multi-select\">\n            <div class=\"card-pf-body\">\n\n                <div class=\"card-pf-top-element row\">\n                    <span data-field=\"icon\" class=\"fa card-pf-icon-circle\"></span>\n                </div>\n\n                <h2 data-field=\"title-text\" class=\"card-pf-title text-center\"></h2>\n                <div data-field=\"edit-mode\">\n                    <label><input data-field=\"title-input\" type=\"text\"/></label>\n                    <button data-field=\"ok-button\" class=\"btn btn-sm btn-primary\"><i class=\"fa fa-check\"></i></button>\n                    <button data-field=\"close-button\" class=\"btn btn-sm btn-default\"><i class=\"fa fa-times\"></i></button>\n                    <button data-field=\"input-close-button\"><i class=\"fa fa-times-circle\"></i></button>\n                </div>\n\n                <div data-field=\"content\"></div>\n            </div>\n        </div>\n    </div>\n</div>\n";
      }
      public String getName() {
        return "getContents";
      }
    }
    ;
  }
  private static class getContentsInitializer {
    static {
      _instance0.getContentsInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return getContents;
    }
  }
  public com.google.gwt.resources.client.TextResource getContents() {
    return getContentsInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.google.gwt.resources.client.TextResource getContents;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      getContents(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("getContents", getContents());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'getContents': return this.@org.jboss.errai.ioc.client.Type_factory__o_k_w_c_w_c_c_f_CardFrameComponentView__quals__j_e_i_Any_j_e_i_Default.o_k_w_c_w_c_c_f_CardFrameComponentViewTemplateResource::getContents()();
    }
    return null;
  }-*/;
}
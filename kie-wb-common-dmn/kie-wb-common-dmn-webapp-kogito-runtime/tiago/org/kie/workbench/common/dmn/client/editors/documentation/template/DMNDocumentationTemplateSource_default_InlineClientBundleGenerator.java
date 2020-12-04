package org.kie.workbench.common.dmn.client.editors.documentation.template;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class DMNDocumentationTemplateSource_default_InlineClientBundleGenerator implements org.kie.workbench.common.dmn.client.editors.documentation.template.DMNDocumentationTemplateSource {
  private static DMNDocumentationTemplateSource_default_InlineClientBundleGenerator _instance0 = new DMNDocumentationTemplateSource_default_InlineClientBundleGenerator();
  private void documentationTemplateInitializer() {
    documentationTemplate = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/Users/tiagobento/redhat/kiegroup-all/kie-wb-common/kie-wb-common-dmn/kie-wb-common-dmn-client/target/kie-wb-common-dmn-client-7.47.0-SNAPSHOT-sources.jar!/org/kie/workbench/common/dmn/client/editors/documentation/template/dmn-documentation-template.html
      public String getText() {
        return "<!--\n  ~ Copyright 2019 Red Hat, Inc. and/or its affiliates.\n  ~\n  ~ Licensed under the Apache License, Version 2.0 (the \"License\");\n  ~ you may not use this file except in compliance with the License.\n  ~ You may obtain a copy of the License at\n  ~\n  ~       http://www.apache.org/licenses/LICENSE-2.0\n  ~\n  ~ Unless required by applicable law or agreed to in writing, software\n  ~ distributed under the License is distributed on an \"AS IS\" BASIS,\n  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n  ~ See the License for the specific language governing permissions and\n  ~ limitations under the License.\n  -->\n\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n    <link type=\"text/css\" rel=\"stylesheet\" href=\"{{&moduleName}}/css/patternfly.min.css\" media=\"print\"/>\n    <style type=\"text/css\">\n\n        <!--\n        /* General spacing */\n        -->\n        .kie-dmn-documentation {\n            padding: 50px;\n        }\n\n        .kie-dmn-documentation h1,\n        .kie-dmn-documentation h2,\n        .kie-dmn-documentation .table-of-contents {\n            font-weight: 600;\n            margin: 30px 0 20px;\n        }\n\n        .kie-dmn-documentation ol,\n        .kie-dmn-documentation ul {\n            padding: 10px 15px;\n        }\n\n        .kie-dmn-documentation .diagram-image {\n            max-width: 100%;\n        }\n\n        .kie-dmn-documentation .pagebreak {\n            border-bottom: 1px solid #DDDDDD;\n        }\n\n        .kie-dmn-documentation .external-links-title {\n            font-weight: bold;\n        }\n\n        <!--\n        /* Font Size */\n        -->\n\n        .kie-dmn-documentation h1,\n        .kie-dmn-documentation h2 {\n            font-size: 26px;\n        }\n\n        .kie-dmn-documentation p,\n        .kie-dmn-documentation li,\n        .kie-dmn-documentation .cover ul {\n            font-size: 14px;\n        }\n\n        <!--\n        /* Cover */\n        -->\n        .kie-dmn-documentation .cover ul {\n            color: #1A1919;\n            list-style: none;\n            padding: 0;\n        }\n\n        .kie-dmn-documentation .cover ul li {\n            border-bottom: 1px solid #9E9F9E;\n            padding: 7px;\n        }\n\n        .kie-dmn-documentation .cover ul li,\n        .kie-dmn-documentation .cover ul li b {\n            white-space: nowrap;\n            overflow: hidden;\n            text-overflow: ellipsis;\n        }\n\n        .kie-dmn-documentation .cover ul li b,\n        .kie-dmn-documentation .cover ul li.li-header {\n            font-weight: 600;\n        }\n\n        .kie-dmn-documentation .cover ul li.li-header {\n            background-color: #E4E4E3;\n            border-bottom: 1px solid #9E9F9E;\n        }\n\n        .kie-dmn-documentation .cover ul li b {\n            display: inline-block;\n            vertical-align: middle;\n            width: 20%;\n        }\n\n        .kie-dmn-documentation .cover .drools-logo {\n            width: 300px;\n            max-width: 50%;\n            margin: 200px 0 100px;\n        }\n\n        .kie-dmn-documentation .cover .supported-by-red-hat-logo {\n            width: 230px;\n            max-width: 40%;\n            float: right;\n            margin: 15px;\n        }\n\n        <!--\n        /* Table of Contents */\n        -->\n        .kie-dmn-documentation ol.table-of-contents li {\n            margin-bottom: 10px;\n        }\n\n        <!--\n        /* Data Types */\n        -->\n        .kie-dmn-documentation ul.data-types {\n            list-style: none;\n        }\n\n        .kie-dmn-documentation ul.data-types li {\n            padding: 10px;\n            border-top: 1px solid #9E9F9E;\n        }\n\n        .kie-dmn-documentation ul.data-types li.structure-data-type {\n            background-color: #DEF2FF;\n        }\n\n        .kie-dmn-documentation ul.data-types li span.sub-text {\n            font-style: italic;\n            color: #717171;\n        }\n\n        .kie-dmn-documentation ul.data-types li span.normal {\n            font-weight: normal;\n        }\n\n        .kie-dmn-documentation ul.data-types li:last-child {\n            border-bottom: 1px solid #9E9F9E;\n        }\n\n        .kie-dmn-documentation ul.data-types li span {\n            display: inline-block;\n            vertical-align: middle;\n            font-weight: bold;\n            padding-right: 5px;\n            width: 25%;\n            white-space: nowrap;\n            overflow: hidden;\n            text-overflow: ellipsis;\n        }\n\n        .kie-dmn-documentation ul.data-types li span + span {\n            font-weight: normal;\n        }\n\n        <!--\n        /* No results */\n        -->\n        .kie-dmn-documentation .no-results {\n            font-style: italic;\n            opacity: 0.75;\n            padding: 20px;\n        }\n\n        .kie-dmn-documentation li.no-results {\n            margin-left: -15px;\n        }\n\n        <!--\n        /* Icons */\n        -->\n        .kie-dmn-documentation .fa {\n            font-weight: bold;\n            margin-right: 3px;\n            vertical-align: middle;\n        }\n\n        <!--\n        /* DRD components */\n        -->\n        .kie-dmn-documentation .drd-component {\n            padding: 10px 0 20px;\n        }\n\n        .kie-dmn-documentation .drd-component + .drd-component {\n            border-top: 1px solid #E5EEF5;\n            padding: 20px 0;\n        }\n\n        .kie-dmn-documentation .drd-component h4 {\n            font-weight: 600;\n        }\n\n        .kie-dmn-documentation .drd-component h4 span {\n            font-weight: normal;\n        }\n\n        .kie-dmn-documentation .drd-component .diagram-image {\n            padding-top: 5px;\n        }\n\n        <!--\n        /* Media Queries */\n        -->\n        @media (max-width: 2100px) {\n            .kie-dmn-documentation .cover .supported-by-red-hat-logo {\n                margin-top: 30px;\n            }\n        }\n\n        @media screen {\n\n            .kie-print-preview-screen {\n                opacity: 0;\n            }\n\n            .kie-dmn-documentation .pagebreak {\n                margin: 75px 0;\n            }\n        }\n\n        @media print {\n\n            .kie-dmn-documentation .pagebreak {\n                margin: 25px 0;\n                page-break-before: always;\n            }\n\n            .kie-dmn-documentation .cover ul li.li-header,\n            .kie-dmn-documentation ul.data-types li.structure-data-type {\n                -webkit-print-color-adjust: exact !important;\n            }\n\n            .kie-dmn-documentation .cover ul li.li-header {\n                background-color: #E4E4E3 !important;\n            }\n\n            .kie-dmn-documentation ul.data-types li.structure-data-type {\n                background-color: #DEF2FF !important;\n            }\n        }\n    </style>\n</head>\n<body>\n\n<div class=\"kie-dmn-documentation\">\n\n    <!-- ######################################################### -->\n    <!-- Cover -->\n\n    <div class=\"cover\">\n\n        <img src=\"{{supportedByRedHatLogoURI}}\" class=\"supported-by-red-hat-logo\" alt=\"\">\n        <img src=\"{{droolsLogoURI}}\" class=\"drools-logo\" alt=\"\">\n\n        <h1>{{diagramName}}</h1>\n\n        <ul>\n            <li class=\"li-header\">\n                {{i18n.dmnModelDocumentation}}\n            </li>\n            <li>\n                <b>{{i18n.namespace}}</b>\n                {{namespace}}\n            </li>\n            <li>\n                <b>{{i18n.generatedOn}}</b>\n                {{currentDate}}\n            </li>\n            {{#currentUser}}\n            <li>\n                <b>{{i18n.generatedBy}}</b>\n                {{currentUser}}\n            </li>\n            {{/currentUser}}\n            <li>\n                <b>{{i18n.generatedFrom}}</b>\n                {{diagramName}}\n            </li>\n        </ul>\n    </div>\n\n    <div class=\"pagebreak\"></div>\n\n    <!-- ######################################################### -->\n    <!-- Table of Contents -->\n\n    <h2>{{i18n.tableOfContents}}</h2>\n\n    <ol class=\"table-of-contents\">\n        <li>{{diagramName}} - {{i18n.dmnModel}}</li>\n        <li>{{diagramName}} - {{i18n.dataTypes}}</li>\n        <li>{{diagramName}} - {{i18n.drdComponents}}</li>\n    </ol>\n\n    <div class=\"pagebreak\"></div>\n\n    <!-- ######################################################### -->\n    <!-- DMN model -->\n\n    <h2>1. {{diagramName}} - {{i18n.dmnModel}}</h2>\n\n    <p>{{diagramDescription}}</p>\n\n    {{^hasGraphNodes}}\n    <div class=\"no-results\">{{i18n.diagramDoesNotHaveElements}}</div>\n    {{/hasGraphNodes}}\n\n    {{#hasGraphNodes}}\n    <img src=\"{{diagramImage}}\" alt=\"{{diagramName}}\" class=\"diagram-image\"/>\n    {{/hasGraphNodes}}\n\n    <div class=\"pagebreak\"></div>\n\n    <!-- ######################################################### -->\n    <!-- Data Types -->\n\n    <h2>2. {{diagramName}} - {{i18n.dataTypes}}</h2>\n\n    <ul class=\"data-types\">\n        {{#dataTypes}}\n        <li class=\"{{#isTopLevel}}structure-data-type{{/isTopLevel}}\" style=\"padding-left: calc(30px * {{level}} + 10px)\">\n            {{#isTopLevel}}\n            <i class=\"fa fa-angle-right\"></i>\n            {{/isTopLevel}}\n            <span>{{name}}</span>\n            <span>({{type}})</span>\n            {{#listLabel}}<span class=\"sub-text\">- {{listLabel}}</span>{{/listLabel}}\n            {{#constraint}}<span class=\"sub-text\">- {{constraint}}</span>{{/constraint}}\n        </li>\n        {{/dataTypes}}\n        {{^dataTypes}}\n        <li class=\"no-results\">{{i18n.noDataTypes}}</li>\n        {{/dataTypes}}\n    </ul>\n\n    <div class=\"pagebreak\"></div>\n\n    <!-- ######################################################### -->\n    <!-- DRD components -->\n\n    <h2>3. {{diagramName}} - {{i18n.drdComponents}}</h2>\n\n    {{#drds}}\n    <div class=\"drd-component\">\n        <h4>\n            <i class=\"fa fa-angle-right\"></i>\n            {{drdName}}\n            {{#drdType}}\n            <span>\n                ({{drdType}})\n            </span>\n            {{/drdType}}\n        </h4>\n        <p>{{drdDescription}}</p>\n        <ul class=\"data-types\">\n            {{#hasExternalLinks}}\n            <p class=\"external-links-title\">{{i18n.externalLinks}}</p>\n            {{/hasExternalLinks}}\n            {{#drdExternalLinks}}\n            <li style=\"padding-left: calc(30px * {{level}})\">\n                <span class=\"normal\">{{description}}</span>\n                <a href=\"{{url}}\">({{url}})</a>\n            </li>\n            {{/drdExternalLinks}}\n            {{^drdExternalLinks}}\n            <li class=\"no-results\">{{i18n.noExternalLinks}}</li>\n            {{/drdExternalLinks}}\n        </ul>\n        {{#drdBoxedExpressionImage}}\n        <img src=\"{{drdBoxedExpressionImage}}\" alt=\"{{drdName}}\" class=\"diagram-image\"/>\n        {{/drdBoxedExpressionImage}}\n    </div>\n    {{/drds}}\n    {{^drds}}\n    <div class=\"no-results\">{{i18n.noDRDs}}</div>\n    {{/drds}}\n\n</div>\n\n</body>\n</html>\n";
      }
      public String getName() {
        return "documentationTemplate";
      }
    }
    ;
  }
  private static class documentationTemplateInitializer {
    static {
      _instance0.documentationTemplateInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return documentationTemplate;
    }
  }
  public com.google.gwt.resources.client.TextResource documentationTemplate() {
    return documentationTemplateInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.google.gwt.resources.client.TextResource documentationTemplate;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      documentationTemplate(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("documentationTemplate", documentationTemplate());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'documentationTemplate': return this.@org.kie.workbench.common.dmn.client.editors.documentation.template.DMNDocumentationTemplateSource::documentationTemplate()();
    }
    return null;
  }-*/;
}

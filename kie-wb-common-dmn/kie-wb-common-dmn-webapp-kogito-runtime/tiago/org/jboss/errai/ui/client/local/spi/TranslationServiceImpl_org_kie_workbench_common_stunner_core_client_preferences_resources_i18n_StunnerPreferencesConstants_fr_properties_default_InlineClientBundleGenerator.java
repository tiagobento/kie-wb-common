package org.jboss.errai.ui.client.local.spi;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class TranslationServiceImpl_org_kie_workbench_common_stunner_core_client_preferences_resources_i18n_StunnerPreferencesConstants_fr_properties_default_InlineClientBundleGenerator implements org.jboss.errai.ui.client.local.spi.TranslationServiceImpl.org_kie_workbench_common_stunner_core_client_preferences_resources_i18n_StunnerPreferencesConstants_fr_properties {
  private static TranslationServiceImpl_org_kie_workbench_common_stunner_core_client_preferences_resources_i18n_StunnerPreferencesConstants_fr_properties_default_InlineClientBundleGenerator _instance0 = new TranslationServiceImpl_org_kie_workbench_common_stunner_core_client_preferences_resources_i18n_StunnerPreferencesConstants_fr_properties_default_InlineClientBundleGenerator();
  private void getContentsInitializer() {
    getContents = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/Users/tiagobento/.m2/repository/org/kie/workbench/stunner/kie-wb-common-stunner-client-api/7.47.0-SNAPSHOT/kie-wb-common-stunner-client-api-7.47.0-SNAPSHOT.jar!/org/kie/workbench/common/stunner/core/client/preferences/resources/i18n/StunnerPreferencesConstants_fr.properties
      public String getText() {
        return "#\n# Copyright 2018 Red Hat, Inc. and/or its affiliates.\n#\n# Licensed under the Apache License, Version 2.0 (the \"License\");\n# you may not use this file except in compliance with the License.\n# You may obtain a copy of the License at\n#\n#     http://www.apache.org/licenses/LICENSE-2.0\n#\n# Unless required by applicable law or agreed to in writing, software\n# distributed under the License is distributed on an \"AS IS\" BASIS,\n# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n# See the License for the specific language governing permissions and\n# limitations under the License.\n#\n\n########################\n#StunnerPreferences\n########################\nStunnerPreferences.Label=Préférences de Stunner\nStunnerPreferences.StunnerDiagramEditorPreferences=Préférences de l’éditeur de diagramme\nStunnerDiagramEditorPreferences.Label=Champs des préférences de l’éditeur de diagramme\nStunnerDiagramEditorPreferences.AutoHidePalettePanel.Label=Masquer automatiquement le panneau de catégorie\nStunnerDiagramEditorPreferences.AutoHidePalettePanel.Help=Active le masquage automatique du panneau de la barre d’outils associé à une catégorie. Par exemple, lorsqu’un élément de catégorie est sélectionné ou lorsque le pointeur de la souris quitte le panneau.\nStunnerDiagramEditorPreferences.EnableHiDpi.Label=Activer HiDPI\nStunnerDiagramEditorPreferences.EnableHiDpi.Help=Activez cette option si vous utilisez un écran à haute résolution, et que le texte et les objets affichés sont flous. Cette fonction est actuellement désactivée par défaut en raison de graves problèmes de performances HiDPI avec Chrome sur Mac\n\nPropertyValidator.CanvasSizeValidator.InvalidOutOfRange=La valeur est hors plage.";
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
      case 'getContents': return this.@org.jboss.errai.ui.client.local.spi.TranslationServiceImpl.org_kie_workbench_common_stunner_core_client_preferences_resources_i18n_StunnerPreferencesConstants_fr_properties::getContents()();
    }
    return null;
  }-*/;
}
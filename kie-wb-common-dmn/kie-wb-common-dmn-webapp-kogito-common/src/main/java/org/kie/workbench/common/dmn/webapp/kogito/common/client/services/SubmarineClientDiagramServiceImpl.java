/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.dmn.webapp.kogito.common.client.services;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import elemental2.promise.Promise;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.marshalling.client.Marshalling;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.MainJs;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.callbacks.DMN12UnmarshallCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDMNElement;
import org.kie.workbench.common.stunner.core.client.api.ShapeManager;
import org.kie.workbench.common.stunner.core.client.service.ServiceCallback;
import org.kie.workbench.common.stunner.core.diagram.Metadata;
import org.kie.workbench.common.stunner.core.util.StringUtils;
import org.kie.workbench.common.stunner.submarine.api.diagram.SubmarineDiagram;
import org.kie.workbench.common.stunner.submarine.api.editor.DiagramType;
import org.kie.workbench.common.stunner.submarine.api.editor.impl.SubmarineDiagramResourceImpl;
import org.kie.workbench.common.stunner.submarine.api.service.SubmarineDiagramService;
import org.kie.workbench.common.stunner.submarine.client.service.SubmarineClientDiagramService;
import org.uberfire.client.promise.Promises;

@ApplicationScoped
public class SubmarineClientDiagramServiceImpl implements SubmarineClientDiagramService {

    private ShapeManager shapeManager;
    private Caller<SubmarineDiagramService> submarineDiagramServiceCaller;
    private Promises promises;

    public SubmarineClientDiagramServiceImpl() {
        //CDI proxy
    }

    @Inject
    public SubmarineClientDiagramServiceImpl(final ShapeManager shapeManager,
                                             final Caller<SubmarineDiagramService> submarineDiagramServiceCaller,
                                             final Promises promises) {
        this.shapeManager = shapeManager;
        this.submarineDiagramServiceCaller = submarineDiagramServiceCaller;
        this.promises = promises;
    }

    //Submarine requirements

    @Override
    public void transform(final String xml,
                          final ServiceCallback<SubmarineDiagram> callback) {

        final SubmarineDiagram d = (SubmarineDiagram) Marshalling.fromJSON("{\"^EncodedType\":\"org.kie.workbench.common.stunner.submarine.api.diagram.impl.SubmarineDiagramImpl\",\"^ObjectID\":\"1\",\"name\":\"F331E93D-89B9-4989-9BB6-8B7E78260C3B\",\"metadata\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.submarine.api.diagram.impl.SubmarineMetadataImpl\",\"^ObjectID\":\"2\",\"definitionSetId\":\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\",\"profileId\":null,\"title\":\"F331E93D-89B9-4989-9BB6-8B7E78260C3B\",\"shapeSetId\":null,\"canvasRootUUID\":\"_D8228A2C-3190-4632-97AE-5C34B0951ECB\",\"thumbData\":null,\"root\":{\"^EncodedType\":\"org.uberfire.backend.vfs.PathFactory$PathImpl\",\"^ObjectID\":\"3\",\"uri\":\"default:\\/\\/master@system\\/stunner\\/diagrams\",\"fileName\":\".\",\"attributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"4\",\"^Value\":{}},\"hasVersionSupport\":false},\"path\":null},\"graph\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.impl.GraphImpl\",\"^ObjectID\":\"5\",\"nodeStore\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.store.GraphNodeStoreImpl\",\"^ObjectID\":\"6\",\"nodes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"7\",\"^Value\":{\"_D8228A2C-3190-4632-97AE-5C34B0951ECB\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.impl.NodeImpl\",\"^ObjectID\":\"8\",\"inEdges\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"9\",\"^Value\":[]},\"outEdges\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"10\",\"^Value\":[]},\"uuid\":\"_D8228A2C-3190-4632-97AE-5C34B0951ECB\",\"labels\":{\"^EncodedType\":\"java.util.LinkedHashSet\",\"^ObjectID\":\"11\",\"^Value\":[\"org.kie.workbench.common.dmn.api.definition.model.DMNDiagram\",\"dmn_diagram\"]},\"content\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.view.ViewImpl\",\"^ObjectID\":\"12\",\"definition\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.definition.model.DMNDiagram\",\"^ObjectID\":\"13\",\"id\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Id\",\"^ObjectID\":\"14\",\"value\":\"_FFE93143-3D84-4517-9125-8CCF03BFF694\"},\"definitions\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.definition.model.Definitions\",\"^ObjectID\":\"15\",\"_import\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"16\",\"^Value\":[]},\"itemDefinition\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"17\",\"^Value\":[]},\"drgElement\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"18\",\"^Value\":[]},\"artifact\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"19\",\"^Value\":[]},\"elementCollection\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"20\",\"^Value\":[]},\"businessContextElement\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"21\",\"^Value\":[]},\"expressionLanguage\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.ExpressionLanguage\",\"^ObjectID\":\"22\",\"value\":\"\"},\"typeLanguage\":null,\"namespace\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Text\",\"^ObjectID\":\"23\",\"value\":\"https:\\/\\/kiegroup.org\\/dmn\\/_BD2749F0-4735-4E60-A1F6-BD439C9A406B\"},\"exporter\":null,\"exporterVersion\":null,\"name\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Name\",\"^ObjectID\":\"24\",\"value\":\"F331E93D-89B9-4989-9BB6-8B7E78260C3B\"},\"id\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Id\",\"^ObjectID\":\"25\",\"value\":\"_226A2305-8BC1-4E15-8611-C71358A58E0C\"},\"description\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Description\",\"^ObjectID\":\"26\",\"value\":\"\"},\"extensionElements\":null,\"nsContext\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"27\",\"^Value\":{\"\":\"https:\\/\\/kiegroup.org\\/dmn\\/_BD2749F0-4735-4E60-A1F6-BD439C9A406B\",\"dmn\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/MODEL\\/\",\"di\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DI\\/\",\"kie\":\"http:\\/\\/www.drools.org\\/kie\\/dmn\\/1.2\",\"feel\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/FEEL\\/\",\"dmndi\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DMNDI\\/\",\"dc\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DC\\/\"}},\"additionalAttributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"28\",\"^Value\":{}},\"parent\":null},\"nsContext\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"29\",\"^Value\":{}},\"additionalAttributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"30\",\"^Value\":{}},\"parent\":null},\"bounds\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bounds\",\"^ObjectID\":\"31\",\"lr\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bound\",\"^ObjectID\":\"32\",\"x\":0.0,\"y\":0.0},\"ul\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bound\",\"^ObjectID\":\"33\",\"x\":0.0,\"y\":0.0}}}}}}},\"uuid\":\"_0ECD5F75-8478-4BFB-882C-E10784D4E007\",\"labels\":{\"^EncodedType\":\"java.util.LinkedHashSet\",\"^ObjectID\":\"34\",\"^Value\":[\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\"]},\"content\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.definition.DefinitionSetImpl\",\"^ObjectID\":\"35\",\"id\":\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\"}}}");

//        submarineDiagramServiceCaller.call((SubmarineDiagram d) -> {
        updateClientMetadata(d);
        callback.onSuccess(d);
//        }).transform(xml);
    }

    private void testClientSideMarshaller(final String xml) {
        if (StringUtils.isEmpty(xml)) {
            return;
        }

        final DMN12UnmarshallCallback callback = dmn12 -> {
            final JSITDMNElement element = dmn12.getValue();
            GWT.log("break point -- check element is correct!");
        };

        try {
            MainJs.unmarshall(xml, callback);
        } catch (Exception e) {
            GWT.log(e.getMessage());
        }
    }

    @Override
    public Promise<String> transform(final SubmarineDiagramResourceImpl resource) {
        if (resource.getType() == DiagramType.PROJECT_DIAGRAM) {
            return promises.promisify(submarineDiagramServiceCaller,
                                      s -> {
                                          return s.transform(resource.projectDiagram().orElseThrow(() -> new IllegalStateException("DiagramType is PROJECT_DIAGRAM however no instance present")));
                                      });
        }
        return promises.resolve(resource.xmlDiagram().orElse("DiagramType is XML_DIAGRAM however no instance present"));
    }

    private void updateClientMetadata(final SubmarineDiagram diagram) {
        if (null != diagram) {
            final Metadata metadata = diagram.getMetadata();
            if (Objects.nonNull(metadata) && StringUtils.isEmpty(metadata.getShapeSetId())) {
                final String sId = shapeManager.getDefaultShapeSet(metadata.getDefinitionSetId()).getId();
                metadata.setShapeSetId(sId);
            }
        }
    }
}

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

package org.kie.workbench.common.dmn.showcase.client.services;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import elemental2.promise.Promise;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.marshalling.client.Marshalling;
import org.kie.workbench.common.stunner.core.client.api.ShapeManager;
import org.kie.workbench.common.stunner.core.client.service.ServiceCallback;
import org.kie.workbench.common.stunner.core.diagram.Metadata;
import org.kie.workbench.common.stunner.core.util.StringUtils;
import org.kie.workbench.common.stunner.submarine.api.diagram.SubmarineDiagram;
import org.kie.workbench.common.stunner.submarine.api.editor.DiagramType;
import org.kie.workbench.common.stunner.submarine.api.editor.impl.SubmarineDiagramResourceImpl;
import org.kie.workbench.common.stunner.submarine.api.service.SubmarineDiagramService;
import org.kie.workbench.common.stunner.submarine.client.service.SubmarineClientDiagramService;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.VFSService;
import org.uberfire.client.promise.Promises;

@ApplicationScoped
public class SubmarineClientDiagramServiceImpl implements SubmarineClientDiagramService {

    private ShapeManager shapeManager;
    private Caller<VFSService> vfsServiceCaller;
    private Caller<SubmarineDiagramService> submarineDiagramServiceCaller;
    private Promises promises;

    public SubmarineClientDiagramServiceImpl() {
        //CDI proxy
    }

    @Inject
    public SubmarineClientDiagramServiceImpl(final ShapeManager shapeManager,
                                             final Caller<VFSService> vfsServiceCaller,
                                             final Caller<SubmarineDiagramService> submarineDiagramServiceCaller,
                                             final Promises promises) {
        this.shapeManager = shapeManager;
        this.vfsServiceCaller = vfsServiceCaller;
        this.submarineDiagramServiceCaller = submarineDiagramServiceCaller;
        this.promises = promises;
    }

    //DMNDiagramSubmarineWrapper requirements

    public void saveAsXml(final Path path,
                          final String xml,
                          final ServiceCallback<String> callback) {
        vfsServiceCaller.call((Path p) -> {
            callback.onSuccess(xml);
        }).write(path, xml);
    }

    public void loadAsXml(final Path path,
                          final ServiceCallback<String> callback) {
        vfsServiceCaller.call((String x) -> {
            callback.onSuccess(x);
        }).readAllString(path);
    }

    //Submarine requirements

    @Override
    public void transform(final String xml,
                          final ServiceCallback<SubmarineDiagram> callback) {
        final SubmarineDiagram d = (SubmarineDiagram) Marshalling.fromJSON("{\"^EncodedType\":\"org.kie.workbench.common.stunner.submarine.api.diagram.impl.SubmarineDiagramImpl\",\"^ObjectID\":\"1\",\"name\":\"31686780-EE08-437F-B4A5-37AED0FDED8D\",\"metadata\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.submarine.api.diagram.impl.SubmarineMetadataImpl\",\"^ObjectID\":\"2\",\"definitionSetId\":\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\",\"profileId\":null,\"title\":\"31686780-EE08-437F-B4A5-37AED0FDED8D\",\"shapeSetId\":null,\"canvasRootUUID\":\"_911E4847-1193-4A9B-B277-C5060668E669\",\"thumbData\":null,\"root\":{\"^EncodedType\":\"org.uberfire.backend.vfs.PathFactory$PathImpl\",\"^ObjectID\":\"3\",\"uri\":\"default:\\/\\/master@system\\/stunner\\/diagrams\",\"fileName\":\".\",\"attributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"4\",\"^Value\":{}},\"hasVersionSupport\":false},\"path\":null},\"graph\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.impl.GraphImpl\",\"^ObjectID\":\"5\",\"nodeStore\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.store.GraphNodeStoreImpl\",\"^ObjectID\":\"6\",\"nodes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"7\",\"^Value\":{\"_911E4847-1193-4A9B-B277-C5060668E669\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.impl.NodeImpl\",\"^ObjectID\":\"8\",\"inEdges\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"9\",\"^Value\":[]},\"outEdges\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"10\",\"^Value\":[]},\"uuid\":\"_911E4847-1193-4A9B-B277-C5060668E669\",\"labels\":{\"^EncodedType\":\"java.util.LinkedHashSet\",\"^ObjectID\":\"11\",\"^Value\":[\"org.kie.workbench.common.dmn.api.definition.v1_1.DMNDiagram\",\"dmn_diagram\"]},\"content\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.view.ViewImpl\",\"^ObjectID\":\"12\",\"definition\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.definition.v1_1.DMNDiagram\",\"^ObjectID\":\"13\",\"id\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Id\",\"^ObjectID\":\"14\",\"value\":\"_62FFDAAD-8A56-425F-884B-94C5F5810DD4\"},\"definitions\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.definition.v1_1.Definitions\",\"^ObjectID\":\"15\",\"_import\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"16\",\"^Value\":[]},\"itemDefinition\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"17\",\"^Value\":[]},\"drgElement\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"18\",\"^Value\":[]},\"artifact\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"19\",\"^Value\":[]},\"elementCollection\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"20\",\"^Value\":[]},\"businessContextElement\":{\"^EncodedType\":\"java.util.ArrayList\",\"^ObjectID\":\"21\",\"^Value\":[]},\"expressionLanguage\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.ExpressionLanguage\",\"^ObjectID\":\"22\",\"value\":\"\"},\"typeLanguage\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/FEEL\\/\",\"namespace\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Text\",\"^ObjectID\":\"23\",\"value\":\"https:\\/\\/kiegroup.org\\/dmn\\/_3087C764-9228-4807-8D1F-189FEF386C76\"},\"exporter\":null,\"exporterVersion\":null,\"name\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Name\",\"^ObjectID\":\"24\",\"value\":\"31686780-EE08-437F-B4A5-37AED0FDED8D\"},\"id\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Id\",\"^ObjectID\":\"25\",\"value\":\"_2C045400-9C96-43E7-B90B-60622FD06285\"},\"description\":{\"^EncodedType\":\"org.kie.workbench.common.dmn.api.property.dmn.Description\",\"^ObjectID\":\"26\",\"value\":\"\"},\"extensionElements\":null,\"nsContext\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"27\",\"^Value\":{\"\":\"https:\\/\\/kiegroup.org\\/dmn\\/_3087C764-9228-4807-8D1F-189FEF386C76\",\"dmn\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/MODEL\\/\",\"di\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DI\\/\",\"kie\":\"http:\\/\\/www.drools.org\\/kie\\/dmn\\/1.2\",\"feel\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/FEEL\\/\",\"dmndi\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DMNDI\\/\",\"dc\":\"http:\\/\\/www.omg.org\\/spec\\/DMN\\/20180521\\/DC\\/\"}},\"additionalAttributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"28\",\"^Value\":{}},\"parent\":null},\"nsContext\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"29\",\"^Value\":{}},\"additionalAttributes\":{\"^EncodedType\":\"java.util.HashMap\",\"^ObjectID\":\"30\",\"^Value\":{}},\"parent\":null},\"bounds\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bounds\",\"^ObjectID\":\"31\",\"lr\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bound\",\"^ObjectID\":\"32\",\"x\":0.0,\"y\":0.0},\"ul\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.Bound\",\"^ObjectID\":\"33\",\"x\":0.0,\"y\":0.0}}}}}}},\"uuid\":\"_56887E95-A8B9-489A-BA35-5E0DD0F847EC\",\"labels\":{\"^EncodedType\":\"java.util.LinkedHashSet\",\"^ObjectID\":\"34\",\"^Value\":[\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\"]},\"content\":{\"^EncodedType\":\"org.kie.workbench.common.stunner.core.graph.content.definition.DefinitionSetImpl\",\"^ObjectID\":\"35\",\"id\":\"org.kie.workbench.common.dmn.api.DMNDefinitionSet\"}}}");

        // TODO: Uncomment these lines to ignore "mocked" SubmarineDiagram
// submarineDiagramServiceCaller.call((SubmarineDiagram d) -> {
        updateClientMetadata(d);
        callback.onSuccess(d);
// }).transform(xml);
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

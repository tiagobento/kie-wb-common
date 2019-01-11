/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.common.stunner.cm.backend.converters.tostunner.activities;

import org.eclipse.bpmn2.CallActivity;
import org.kie.workbench.common.stunner.bpmn.backend.converters.TypedFactoryManager;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.activities.BaseCallActivityConverter;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.properties.ActivityPropertyReader;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.properties.PropertyReaderFactory;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.CalledElement;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.Independent;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.IsAsync;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.OnEntryAction;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.OnExitAction;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.WaitForCompletion;
import org.kie.workbench.common.stunner.cm.backend.converters.tostunner.properties.CaseManagementActivityPropertyReader;
import org.kie.workbench.common.stunner.cm.definition.BaseCaseManagementReusableSubprocess;
import org.kie.workbench.common.stunner.cm.definition.CaseReusableSubprocess;
import org.kie.workbench.common.stunner.cm.definition.ProcessReusableSubprocess;
import org.kie.workbench.common.stunner.cm.definition.property.subprocess.IsCase;
import org.kie.workbench.common.stunner.cm.definition.property.task.BaseCaseManagementReusableSubprocessTaskExecutionSet;
import org.kie.workbench.common.stunner.cm.definition.property.task.CaseReusableSubprocessTaskExecutionSet;
import org.kie.workbench.common.stunner.cm.definition.property.task.ProcessReusableSubprocessTaskExecutionSet;
import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;

public class CaseManagementCallActivityConverter extends BaseCallActivityConverter<BaseCaseManagementReusableSubprocess> {

    public CaseManagementCallActivityConverter(TypedFactoryManager factoryManager,
                                               PropertyReaderFactory propertyReaderFactory) {
        super(factoryManager, propertyReaderFactory);
    }

    @Override
    protected Node<View<BaseCaseManagementReusableSubprocess>, Edge> createNode(CallActivity activity, ActivityPropertyReader p) {
        Class<? extends BaseCaseManagementReusableSubprocess> clazz = ((CaseManagementActivityPropertyReader) p).isCase() ?
                CaseReusableSubprocess.class : ProcessReusableSubprocess.class;

        return factoryManager.newNode(activity.getId(), clazz);
    }

    @Override
    protected BaseCaseManagementReusableSubprocessTaskExecutionSet createReusableSubprocessTaskExecutionSet(CalledElement calledElement,
                                                                                                            Independent independent,
                                                                                                            WaitForCompletion waitForCompletion,
                                                                                                            IsAsync isAsync,
                                                                                                            OnEntryAction onEntryAction,
                                                                                                            OnExitAction onExitAction,
                                                                                                            ActivityPropertyReader p) {
        return ((CaseManagementActivityPropertyReader) p).isCase() ?
                new CaseReusableSubprocessTaskExecutionSet(calledElement,
                                                           new IsCase(true),
                                                           independent,
                                                           waitForCompletion,
                                                           isAsync,
                                                           onEntryAction,
                                                           onExitAction) :
                new ProcessReusableSubprocessTaskExecutionSet(calledElement,
                                                              new IsCase(false),
                                                              independent,
                                                              waitForCompletion,
                                                              isAsync,
                                                              onEntryAction,
                                                              onExitAction);
    }
}

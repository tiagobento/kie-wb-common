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

package org.kie.workbench.common.dmn.api.definition.v1_1;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.api.definition.HasTypeRef;
import org.kie.workbench.common.dmn.api.definition.v1_1.common.HasTypeRefHelper;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HasTypeRefHelper.class})
public class ListTest {

    private org.kie.workbench.common.dmn.api.definition.v1_1.List list;

    @Before
    public void setup() {
        this.list = spy(new org.kie.workbench.common.dmn.api.definition.v1_1.List());
    }

    @Test
    public void testGetHasTypeRefs() {

        final List<Expression> expression = asList(mock(Expression.class), mock(Expression.class));
        final HasTypeRef hasTypeRef1 = mock(HasTypeRef.class);
        final HasTypeRef hasTypeRef2 = mock(HasTypeRef.class);

        doReturn(expression).when(list).getExpression();

        mockStatic(HasTypeRefHelper.class);
        when(HasTypeRefHelper.getFlatHasTypeRefs(expression)).thenReturn(asList(hasTypeRef1, hasTypeRef2));

        final List<HasTypeRef> actualHasTypeRefs = list.getHasTypeRefs();
        final List<HasTypeRef> expectedHasTypeRefs = asList(list, hasTypeRef1, hasTypeRef2);

        assertEquals(expectedHasTypeRefs, actualHasTypeRefs);
    }
}

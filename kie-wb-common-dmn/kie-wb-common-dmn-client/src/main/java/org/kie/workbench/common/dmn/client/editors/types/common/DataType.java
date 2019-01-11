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

package org.kie.workbench.common.dmn.client.editors.types.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.kie.workbench.common.dmn.client.editors.types.persistence.DataTypeActiveRecord;
import org.kie.workbench.common.dmn.client.editors.types.persistence.common.RecordEngine;

public class DataType extends DataTypeActiveRecord {

    public static final String TOP_LEVEL_PARENT_UUID = "";

    private String uuid;

    private String parentUUID;

    private String name;

    private String type;

    private String constraint;

    private boolean isList;

    private List<DataType> subDataTypes = new ArrayList<>();

    public DataType(final RecordEngine<DataType> recordEngine) {
        super(recordEngine);
    }

    @Override
    protected DataType getRecord() {
        return this;
    }

    public String getUUID() {
        return uuid;
    }

    void setUUID(final String uuid) {
        this.uuid = uuid;
    }

    public String getParentUUID() {
        return parentUUID;
    }

    void setParentUUID(final String parentUUID) {
        this.parentUUID = parentUUID;
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    void setType(final String type) {
        this.type = type;
    }

    public List<DataType> getSubDataTypes() {
        return subDataTypes;
    }

    void setSubDataTypes(final List<DataType> subDataTypes) {
        this.subDataTypes.clear();
        this.subDataTypes.addAll(subDataTypes);
    }

    public boolean isList() {
        return isList;
    }

    void setAsList(final boolean isList) {
        this.isList = isList;
    }

    public boolean hasSubDataTypes() {
        return !subDataTypes.isEmpty();
    }

    public String getConstraint() {
        return constraint;
    }

    void setConstraint(final String constraint) {
        this.constraint = constraint;
    }

    public boolean isTopLevel() {
        return Objects.equals(getParentUUID(), TOP_LEVEL_PARENT_UUID);
    }
}

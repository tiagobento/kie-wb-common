/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.datasource.management.service;



/**
 * Service for getting the information required by the DataSourceDefExplorerScreen and the internal widgets
 * ProjectDataSourceExplorer and GlobalDataSourceExplore. The returned results are calculated by using the
 * AuthorizationManager and depends on current user.
 */

public interface DefExplorerQueryService {

    /**
     * Resolves a query for getting the information to show in the data sources and drivers explorer.
     * The query tries to resolve all the data sources and drivers for a given project (when selected) and also
     * piggybacks the information about the organizational units, repositories, and projects accessible by current user.
     *
     * @return the data source and drivers information found (when found) as well as the organizational units, repositories
     * and projects that currently exists.
     */
    DefExplorerQueryResult executeQuery( final DefExplorerQuery query );

}

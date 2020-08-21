/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.common.widgets.client.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.project.model.Package;
import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.uberfire.mvp.Command;

/**
 * A ListBox that shows a list of Packages from which the user can select
 */
@Dependent
public class PackageListBox
        implements IsElement {

    private PackageListBoxView view;
    private WorkspaceProjectContext projectContext;
    private Map<String, Package> packages;
    private String selectedPackage = null;

    @Inject
    public PackageListBox(final PackageListBoxView view,
                          final WorkspaceProjectContext projectContext) {
        this.view = view;
        this.projectContext = projectContext;
        packages = new HashMap<>();
        view.setPresenter(this);
    }

    public void setUp(final boolean includeDefaultPackage) {
        setUp(includeDefaultPackage,
              null);
    }

    public void setUp(final boolean includeDefaultPackage,
                      final Command packagesLoadedCommand) {

        packages.clear();

        showListOfPackages(includeDefaultPackage,
                           packagesLoadedCommand);
    }

    public void clearSelectElement() {
        view.clearSelectElement();
    }

    private void showListOfPackages(final boolean includeDefaultPackage,
                                    final Command packagesLoadedCommand) {
        // There was a backend call here.
    }

    private List<Package> getSortedPackages(final boolean includeDefaultPackage,
                                            final Set<Package> pkgs) {
        final List<Package> sortedPackages = new ArrayList<>(pkgs);
        Collections.sort(sortedPackages,
                         (p1, p2) -> p1.getCaption().compareTo(p2.getCaption()));

        // Remove default package, if not required (after sorting it is guaranteed to be at index 0)

        if (!includeDefaultPackage
                && !sortedPackages.isEmpty()
                && "".equals(sortedPackages.get(0).getPackageName())) {
            sortedPackages.remove(0);
        }
        return sortedPackages;
    }

    private void addPackagesToSelect(final List<Package> sortedPackages,
                                     final Package activePackage) {

        final Map<String, String> packageNames = new HashMap<>();

        for (Package pkg : sortedPackages) {
            packageNames.put(pkg.getCaption(),
                             pkg.getPackageName());
            packages.put(pkg.getCaption(),
                         pkg);
        }

        selectedPackage = getSelectedPackage(activePackage,
                                             packageNames);

        view.setUp(selectedPackage,
                   packageNames);
    }

    private String getSelectedPackage(final Package activePackage,
                                      final Map<String, String> packageNames) {
        if (packageNames.containsKey(activePackage.getCaption())) {
            return activePackage.getCaption();
        } else if (!packageNames.isEmpty()) {
            return packageNames.keySet().iterator().next();
        } else {
            return null;
        }
    }

    public Package getSelectedPackage() {

        if (packages.isEmpty()) {
            return null;
        }
        return packages.get(selectedPackage);
    }

    @Override
    public HTMLElement getElement() {
        return view.getElement();
    }

    public void onPackageSelected(final String selectedPackage) {
        this.selectedPackage = selectedPackage;
    }
}

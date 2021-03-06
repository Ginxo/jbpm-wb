/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.workbench.wi.client.editors.deployment.descriptor.sections.general;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import elemental2.promise.Promise;
import org.jbpm.workbench.wi.dd.model.DeploymentDescriptorModel;
import org.kie.workbench.common.screens.library.client.settings.SettingsSectionChange;
import org.kie.workbench.common.screens.library.client.settings.util.sections.MenuItem;
import org.kie.workbench.common.screens.library.client.settings.util.sections.Section;
import org.kie.workbench.common.screens.library.client.settings.util.sections.SectionView;
import org.uberfire.client.promise.Promises;

@Dependent
public class DeploymentsGeneralSettingsPresenter extends Section<DeploymentDescriptorModel> {

    private final DeploymentsGeneralSettingsView view;

    protected DeploymentDescriptorModel model;

    @Inject
    public DeploymentsGeneralSettingsPresenter(final Event<SettingsSectionChange<DeploymentDescriptorModel>> settingsSectionChangeEvent,
                                               final MenuItem<DeploymentDescriptorModel> menuItem,
                                               final Promises promises,
                                               final DeploymentsGeneralSettingsView view) {

        super(settingsSectionChangeEvent, menuItem, promises);
        this.view = view;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }

    @Override
    public Promise<Void> setup(final DeploymentDescriptorModel model) {

        this.model = model;

        setupRuntimeStrategiesSelect(model);
        view.setPersistenceUnitName(model.getPersistenceUnitName());
        setupPersistenceModesSelect(model);
        view.setAuditPersistenceUnitName(model.getAuditPersistenceUnitName());
        setupAuditModeSelect(model);

        return promises.resolve();
    }

    void setupAuditModeSelect(final DeploymentDescriptorModel model) {
        view.setupAuditModeSelect(model);
    }

    void setupPersistenceModesSelect(final DeploymentDescriptorModel model) {
        view.setupPersistenceModesSelect(model);
    }

    void setupRuntimeStrategiesSelect(final DeploymentDescriptorModel model) {
        view.setupRuntimeStrategiesSelect(model);
    }

    public void setPersistenceUnitName(final String persistenceUnitName) {
        model.setPersistenceUnitName(persistenceUnitName);
        fireChangeEvent();
    }

    public void setAuditPersistenceUnitName(final String auditPersistenceUnitName) {
        model.setAuditPersistenceUnitName(auditPersistenceUnitName);
        fireChangeEvent();
    }

    @Override
    public SectionView<?> getView() {
        return view;
    }

    @Override
    public int currentHashCode() {
        return model.getAuditMode().hashCode() +
                model.getPersistenceUnitName().hashCode() +
                model.getAuditPersistenceUnitName().hashCode() +
                model.getPersistenceMode().hashCode() +
                model.getRuntimeStrategy().hashCode();
    }
}

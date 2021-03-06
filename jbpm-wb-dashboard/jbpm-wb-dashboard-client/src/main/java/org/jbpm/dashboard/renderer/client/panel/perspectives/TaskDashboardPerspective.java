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
package org.jbpm.dashboard.renderer.client.panel.perspectives;

import javax.enterprise.context.ApplicationScoped;

import org.jbpm.dashboard.renderer.client.panel.i18n.DashboardConstants;
import org.jbpm.workbench.common.client.PerspectiveIds;
import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.MultiScreenWorkbenchPanelPresenter;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

/**
 * A Perspective to show the Task dashboard
 */
@ApplicationScoped
@WorkbenchPerspective(identifier = PerspectiveIds.TASK_DASHBOARD)
public class TaskDashboardPerspective {

    @Perspective
    public PerspectiveDefinition buildPerspective() {
        PerspectiveDefinition perspective = new PerspectiveDefinitionImpl(MultiScreenWorkbenchPanelPresenter.class.getName());
        perspective.setName(DashboardConstants.INSTANCE.taskDashboardName());
        perspective.getRoot().addPart(PerspectiveIds.TASK_DASHBOARD_SCREEN);
        return perspective;
    }
}
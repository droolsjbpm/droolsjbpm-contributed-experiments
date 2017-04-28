/*
 * Copyright 2017 Red Hat Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.services.task.persistence.query;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.services.task.persistence.index.TaskTableService;
import org.kie.api.task.UserGroupCallback;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;

public class TaskOwnedPotentialOwnersByTaskIdsQuery implements MapDBQuery<List<Object[]>> {

    @Override
    public List<Object[]> execute(UserGroupCallback callback,
            Map<String, Object> params, TaskTableService tts,
            boolean singleResult) {
        @SuppressWarnings("unchecked")
        List<Long> taskIds = (List<Long>) params.get("taskIds");
        List<Status> status = Arrays.asList(Status.Created, Status.Ready, Status.Reserved, Status.InProgress, Status.Suspended);
        
        Set<Long> valuesByStatus = new HashSet<>();
        if (status != null) {
            List<String> strStatus = MapDBQueryUtil.asStringStatus(status);
            for (Long value : taskIds) {
                String taskStatus = tts.getTaskStatusById().get(value);
                if (taskStatus != null && strStatus.contains(taskStatus)) {
                    valuesByStatus.add(value);
                }
            }
            taskIds.retainAll(valuesByStatus); //and operation
        }
        
        List<Object[]> retval = new LinkedList<>();
        
        for (Long id : valuesByStatus) {
            if (tts.getById().containsKey(id)) {
                Task task = tts.getById().get(id);
                if (task != null) {
                    for (OrganizationalEntity entity: task.getPeopleAssignments().getPotentialOwners()) {
                        retval.add(new Object[] { id, entity.getId()});
                    }
                }
            }
        }
        return MapDBQueryUtil.paging(params, retval);
    }
}

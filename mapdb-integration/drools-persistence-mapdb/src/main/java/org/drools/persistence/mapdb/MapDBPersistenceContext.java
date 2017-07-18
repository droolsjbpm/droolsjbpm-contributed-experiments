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
package org.drools.persistence.mapdb;

import org.drools.persistence.api.PersistenceContext;
import org.drools.persistence.api.PersistentSession;
import org.drools.persistence.api.PersistentWorkItem;
import org.drools.persistence.api.TransactionManager;
import org.drools.persistence.api.TransactionManagerHelper;
import org.drools.persistence.processinstance.mapdb.MapDBWorkItem;
import org.kie.api.persistence.ObjectStoringStrategy;
import org.mapdb.Atomic;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.serializer.GroupSerializer;
import org.mapdb.serializer.SerializerLong;

public class MapDBPersistenceContext implements PersistenceContext {

    protected DB db;
    protected TransactionManager txm;
    protected ObjectStoringStrategy[] strategies;
    private GroupSerializer<PersistentSession> sessionSerializer = new PersistentSessionSerializer();
    private GroupSerializer<Long> idSerializer = new SerializerLong();
    private GroupSerializer<PersistentWorkItem> workItemSerializer = new PersistentWorkItemSerializer();
    private BTreeMap<Long, PersistentWorkItem> workItemMap;
    private BTreeMap<Long, PersistentSession> sessionMap;
    private Atomic.Long nextSessionId;
    private Atomic.Long nextWorkItemId;

    public MapDBPersistenceContext(DB db, TransactionManager txm, ObjectStoringStrategy[] strategies) {
        this.db = db;
        this.txm = txm;
        this.strategies = strategies;
        this.workItemMap = db.treeMap(new MapDBWorkItem().getMapKey(), idSerializer, workItemSerializer).createOrOpen();
        this.sessionMap = db.treeMap(new MapDBSession().getMapKey(), idSerializer, sessionSerializer).createOrOpen();
        this.nextSessionId = db.atomicLong("sessionId").createOrOpen();
        this.nextWorkItemId = db.atomicLong("workItemId").createOrOpen();
    }
    
    @Override
    public PersistentSession persist(PersistentSession session) {        
        return persist(session, false);
    }
    
    public PersistentSession persist(PersistentSession session, boolean skipUpdatableSet) {
        long id;
        if (session.getId() == null || session.getId() == -1) {
            id = nextSessionId.incrementAndGet();
            session.setId(id);
        } else {
            id = session.getId();
        }
        if (!skipUpdatableSet) {
            TransactionManagerHelper.addToUpdatableSet(txm, session);
        }
        sessionMap.put(id, session); //to be placed in map by triggerupdatesync
        return session;
    }

    @Override
    public PersistentSession findSession(Long id) {
        PersistentSession session = sessionMap.getOrDefault(id, null);
        if (session != null) {
            TransactionManagerHelper.addToUpdatableSet(txm, session);
        }
        return session;
    }

    @Override
    public void remove(PersistentSession session) {
        sessionMap.remove(session.getId());
    }

    @Override
    public boolean isOpen() {
        return !db.getStore().isClosed();
    }

    @Override
    public void joinTransaction() {
    }

    @Override
    public void close() {
        //db.getStore().close();
    }

    @Override
    public PersistentWorkItem persist(PersistentWorkItem workItem) {
        long id;
        if (workItem.getId() == null || workItem.getId() == -1) {
            id = nextWorkItemId.incrementAndGet();
            workItem.setId(id);
        } else {
            id = workItem.getId();
        }
        TransactionManagerHelper.addToUpdatableSet(txm, workItem);
        workItemMap.put(id, workItem);
        return workItem;
    }

    @Override
    public PersistentWorkItem findWorkItem(Long id) {
        PersistentWorkItem workItem = workItemMap.getOrDefault(id, null);
        if (workItem != null) {
            TransactionManagerHelper.addToUpdatableSet(txm, workItem);
        }
        return workItem;
    }

    @Override
    public void remove(PersistentWorkItem workItem) {
        workItemMap.remove(workItem.getId());
    }

    @Override
    public void lock(PersistentWorkItem workItem) {
    }

    @Override
    public PersistentWorkItem merge(PersistentWorkItem workItem) {        
        return merge(workItem, false);
    }
    
    public PersistentWorkItem merge(PersistentWorkItem workItem, boolean skipUpdatableSet) {
        workItemMap.put(workItem.getId(), workItem);
        if (!skipUpdatableSet) {
            TransactionManagerHelper.addToUpdatableSet(txm, workItem);
        }
        return workItem;
    }
}

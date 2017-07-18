package org.jbpm.runtime.manager.mapdb;

import java.util.List;

import org.drools.persistence.mapdb.MapDBEnvironmentName;
import org.jbpm.runtime.manager.impl.mapper.InternalMapper;
import org.jbpm.runtime.manager.mapdb.model.ContextMappingInfoSerializer;
import org.jbpm.runtime.manager.mapdb.model.MapDBContextMappingInfo;
import org.kie.api.runtime.manager.Context;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.Serializer;


public class MapDBMapper extends InternalMapper {

    private final BTreeMap<String, MapDBContextMappingInfo> map;
    private final BTreeMap<Long, MapDBContextMappingInfo> mapById;
    
    
    public MapDBMapper(RuntimeEnvironment environment) {
        DB db = (DB) environment.getEnvironment().get(MapDBEnvironmentName.DB_OBJECT);
        this.map = db.treeMap("runtime-manager-context-mapping", Serializer.STRING, new ContextMappingInfoSerializer()).createOrOpen();
        this.mapById = db.treeMap("runtime-manager-context-mapping-by-id", Serializer.LONG, new ContextMappingInfoSerializer()).createOrOpen();
    }
    
    @Override
    public void saveMapping(Context<?> context, Long ksessionId, String ownerId) {
        MapDBContextMappingInfo mapping = new MapDBContextMappingInfo(context.getContextId().toString(), ksessionId, ownerId);
        map.put(mapping.getContextId(), mapping);
        mapById.put(mapping.getKsessionId(), mapping);

    }

    @Override
    public Long findMapping(Context<?> context, String ownerId) {
        MapDBContextMappingInfo mapping = map.getOrDefault(context.getContextId().toString(), null);
        if (mapping != null) {
            return mapping.getKsessionId();
        }
        return null;
    }

    @Override
    public Object findContextId(Long ksessionId, String ownerId) {
        return mapById.get(ksessionId);
    }

    @Override
    public void removeMapping(Context<?> context, String ownerId) {
        MapDBContextMappingInfo mapping = map.remove(context.getContextId().toString());
        if (mapping != null) {
            mapById.remove(mapping.getKsessionId());
        }
    }

    @Override
    public List<String> findContextIdForEvent(String eventType, String ownerId) {
        // TODO Auto-generated method stub
        return null;
    }

}

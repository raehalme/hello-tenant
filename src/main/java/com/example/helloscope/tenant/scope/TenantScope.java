package com.example.helloscope.tenant.scope;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextHolder;
import com.example.helloscope.tenant.TenantContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TenantScope implements Scope {
    public static final String SCOPE_NAME = "tenant";
    private static final Logger log = LoggerFactory.getLogger(TenantScope.class);
    private final TenantContextProvider tenantContextProvider;
    private final Map<String, Map<String, Object>> scopedObjects
            = Collections.synchronizedMap(new HashMap());
    private final Map<String, Map<String, Runnable>> destructionCallbacks
            = Collections.synchronizedMap(new HashMap());

    public TenantScope(TenantContextHolder tenantContextProvider) {
        this.tenantContextProvider = Objects.requireNonNull(tenantContextProvider);
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> tenantObjects = getTenantObjects();
        return tenantObjects.computeIfAbsent(name, key -> objectFactory.getObject());
    }

    private Map<String, Object> getTenantObjects() {
        String tenantId = getTenantId();
        return scopedObjects.computeIfAbsent(tenantId, key -> {
            log.info("Creating an object namespace for tenant {}", tenantId);
            return Collections.synchronizedMap(new HashMap());
        });
    }

    private String getTenantId() {
        TenantContext tenantContext = tenantContextProvider.getTenantContext();
        return tenantContext.getTenantId();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        getTenantDestructionCallbacks().put(name, callback);
    }

    private Map<String, Runnable> getTenantDestructionCallbacks() {
        String tenantId = getTenantId();
        return destructionCallbacks.computeIfAbsent(tenantId, key -> {
            log.info("Creating an object namespace for tenant {}", tenantId);
            return Collections.synchronizedMap(new HashMap());
        });
    }

    @Override
    public Object remove(String name) {
        getTenantDestructionCallbacks().remove(name);
        return getTenantObjects().remove(name);
    }

    @Override
    public Object resolveContextualObject(String name) {
        return getTenantObjects().get(name);
    }

    @Override
    public String getConversationId() {
        return getTenantId();
    }
}

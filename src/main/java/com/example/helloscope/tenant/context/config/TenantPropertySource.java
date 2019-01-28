package com.example.helloscope.tenant.context.config;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;

public class TenantPropertySource extends PropertySource<TenantContextHolder> {
    private static final Logger log = LoggerFactory.getLogger(TenantPropertySource.class);

    public TenantPropertySource(TenantContextHolder tenantContextHolder) {
        super("tenant", tenantContextHolder);
    }

    @Override
    public Object getProperty(String name) {
        TenantContextHolder tenantContextHolder = getSource();
        if (!tenantContextHolder.isTenantDefined()) {
            return null;
        }
        else {
            TenantContext tenantContext = tenantContextHolder.getTenantContext();
            log.debug("Resolving property {} from tenant context: {}", name, tenantContext);
            if (name.equals("tenant.id")) {
                return tenantContext.getTenantId();
            }
            else {
                return null;
            }
        }
    }
}

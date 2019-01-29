package com.example.helloscope.tenant.context.config;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;

public class TenantPropertySource extends PropertySource<TenantContextProvider> {
    private static final Logger log = LoggerFactory.getLogger(TenantPropertySource.class);

    public TenantPropertySource(TenantContextProvider tenantContextProvider) {
        super("tenant", tenantContextProvider);
    }

    @Override
    public Object getProperty(String name) {
        TenantContextProvider tenantContextProvider = getSource();
        if (!tenantContextProvider.isTenantDefined()) {
            return null;
        }
        else {
            TenantContext tenantContext = tenantContextProvider.getTenantContext();
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

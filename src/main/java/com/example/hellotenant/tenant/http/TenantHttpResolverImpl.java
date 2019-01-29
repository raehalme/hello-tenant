package com.example.hellotenant.tenant.http;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TenantHttpResolverImpl implements TenantHttpResolver {
    private static final String HEADER_TENANT = "X-Tenant";

    @Override
    public String resolveTenantId(HttpServletRequest request) {
        String tenantId = resolveFromHeader(request);
        return tenantId != null ? tenantId : resolveFromHost(request);
    }

    private String resolveFromHeader(HttpServletRequest request) {
        return request.getHeader(HEADER_TENANT);
    }

    private String resolveFromHost(HttpServletRequest request) {
        return null;
    }
}

package com.example.hellotenant.tenant.http;

import javax.servlet.http.HttpServletRequest;

public interface TenantHttpResolver {
    String resolveTenantId(HttpServletRequest request);
}

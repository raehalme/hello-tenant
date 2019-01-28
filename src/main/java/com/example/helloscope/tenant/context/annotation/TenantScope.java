package com.example.helloscope.tenant.context.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(scopeName = com.example.helloscope.tenant.scope.TenantScope.SCOPE_NAME, proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface TenantScope {
}

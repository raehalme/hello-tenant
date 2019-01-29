package com.example.hellotenant.tenant;

import org.slf4j.MDC;

import java.util.Objects;

public class TenantTemplate {
    private static final String MDC_TENANT = "tenant";
    private final TenantContextHolder tenantContextHolder;

    public TenantTemplate(TenantContextHolder tenantContextHolder) {
        this.tenantContextHolder = Objects.requireNonNull(tenantContextHolder);
    }

    public <E extends Exception> void with(TenantContext context, ThrowingRunnable<E> runnable) throws E {
        try {
            setTenantContext(context);
            runnable.run();
        }
        finally {
            clearTenantContext();
        }
    }

    private void setTenantContext(TenantContext context) {
        MDC.put(MDC_TENANT, context.getTenantId());
        tenantContextHolder.setTenantContext(context);
    }

    private void clearTenantContext() {
        tenantContextHolder.clearTenantContext();
        MDC.remove(MDC_TENANT);
    }

    @FunctionalInterface
    public interface ThrowingRunnable<E extends Exception> {
        void run() throws E;
    }
}

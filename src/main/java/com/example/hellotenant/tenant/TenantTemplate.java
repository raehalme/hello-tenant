package com.example.hellotenant.tenant;

import com.example.hellotenant.tenant.holder.TenantContextHolder;
import org.slf4j.MDC;

import java.util.Objects;

/**
 * Simplifies execution of operations where {@link TenantContext} is required to be present.
 */
public class TenantTemplate {
    private static final String MDC_TENANT = "tenant";
    private final TenantContextHolder tenantContextHolder;

    public TenantTemplate(TenantContextHolder tenantContextHolder) {
        this.tenantContextHolder = Objects.requireNonNull(tenantContextHolder);
    }

    /**
     * Invokes the given action so that {@link TenantContextProvider} returns the given {@link TenantContext}.
     *
     * @param context
     * @param runnable
     * @param <E>
     * @throws E
     */
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

    /**
     * A functional interface allowing the method to throw a checked exception.
     *
     * @param <E> Any exception which may be thrown by the method.
     */
    @FunctionalInterface
    public interface ThrowingRunnable<E extends Exception> {
        void run() throws E;
    }
}

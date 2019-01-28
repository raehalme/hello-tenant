package com.example.helloscope.tenant.jms;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Objects;

public class TenantAwareMessageListenerContainer extends DefaultMessageListenerContainer {
    private static final Logger log = LoggerFactory.getLogger(TenantAwareMessageListenerContainer.class);
    private static final String MDC_TENANT_ID = "tenantId";
    private final TenantJmsResolver tenantJmsResolver;
    private final TenantContextHolder tenantContextHolder;

    public TenantAwareMessageListenerContainer(TenantJmsResolver tenantJmsResolver,
                                               TenantContextHolder tenantContextHolder) {
        this.tenantJmsResolver = Objects.requireNonNull(tenantJmsResolver);
        this.tenantContextHolder = Objects.requireNonNull(tenantContextHolder);
    }

    @Override
    protected void doExecuteListener(Session session, Message message) throws JMSException {
        String tenantId = tenantJmsResolver.resolveTenantId(message);
        if (tenantId == null) {
            super.doExecuteListener(session, message);
        }
        else {
            log.info("doExecuteListener: {}", tenantId);
            TenantContext context = TenantContext.of(tenantId);
            try {
                setTenantContext(tenantId, context);
                super.doExecuteListener(session, message);
            }
            finally {
                clearTenantContext();
            }
        }
    }

    private void setTenantContext(String tenantId, TenantContext context) {
        MDC.put(MDC_TENANT_ID, tenantId);
        tenantContextHolder.setTenantContext(context);
    }

    private void clearTenantContext() {
        tenantContextHolder.clearTenantContext();
        MDC.remove(MDC_TENANT_ID);
    }

}

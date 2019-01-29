package com.example.hellotenant.tenant.jms;

import com.example.hellotenant.tenant.TenantContext;
import com.example.hellotenant.tenant.TenantTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Objects;

public class TenantAwareMessageListenerContainer extends DefaultMessageListenerContainer {
    private static final Logger log = LoggerFactory.getLogger(TenantAwareMessageListenerContainer.class);
    private final TenantJmsResolver tenantJmsResolver;
    private final TenantTemplate tenantTemplate;

    public TenantAwareMessageListenerContainer(TenantJmsResolver tenantJmsResolver,
                                               TenantTemplate tenantTemplate) {
        this.tenantJmsResolver = Objects.requireNonNull(tenantJmsResolver);
        this.tenantTemplate = Objects.requireNonNull(tenantTemplate);
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
            tenantTemplate.with(context, () -> super.doExecuteListener(session, message));
        }
    }
}

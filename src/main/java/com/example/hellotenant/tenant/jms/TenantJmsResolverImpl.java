package com.example.hellotenant.tenant.jms;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class TenantJmsResolverImpl implements TenantJmsResolver {
    private static final String PROPERTY_TENANT = "X-Tenant";

    @Override
    public String resolveTenantId(Message message) {
        try {
            return message.getStringProperty(PROPERTY_TENANT);
        }
        catch (JMSException e) {
            throw new IllegalStateException(e);
        }
    }
}

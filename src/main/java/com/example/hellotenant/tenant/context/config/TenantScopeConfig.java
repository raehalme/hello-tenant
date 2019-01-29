package com.example.hellotenant.tenant.context.config;

import com.example.hellotenant.tenant.TenantContextHolder;
import com.example.hellotenant.tenant.TenantTemplate;
import com.example.hellotenant.tenant.jms.TenantAwareMessageListenerContainer;
import com.example.hellotenant.tenant.jms.TenantJmsResolver;
import com.example.hellotenant.tenant.jms.TenantJmsResolverImpl;
import com.example.hellotenant.tenant.scope.TenantScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

@Configuration
public class TenantScopeConfig {
    private static final Logger log = LoggerFactory.getLogger(TenantScopeConfig.class);

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer(TenantScope tenantScope) {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope(TenantScope.SCOPE_NAME, tenantScope);
        return customScopeConfigurer;
    }

    @Bean
    public TenantScope tenantScope(TenantContextHolder tenantContextHolder) {
        return new TenantScope(tenantContextHolder);
    }

    @Bean
    public TenantPropertySource tenantPropertySource(TenantContextHolder tenantContextHolder) {
        return new TenantPropertySource(tenantContextHolder);
    }

    @Bean
    public TenantTemplate tenantTemplate(TenantContextHolder tenantContextHolder) {
        return new TenantTemplate(tenantContextHolder);
    }

    @Bean
    public TenantJmsResolver tenantJmsResolver() {
        return new TenantJmsResolverImpl();
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            TenantJmsResolver tenantJmsResolver,
            TenantTemplate tenantTemplate) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory() {
            @Override
            protected DefaultMessageListenerContainer createContainerInstance() {
                log.info("Creating new TenantAwareMessageListenerContainer");
                return new TenantAwareMessageListenerContainer(tenantJmsResolver, tenantTemplate);
            }
        };
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Configuration
    public static class TenantEnvironmentConfig {
        @Autowired
        private ConfigurableEnvironment env;
        @Autowired
        private TenantPropertySource tenantPropertySource;

        @PostConstruct
        public void init() {
            env.getPropertySources().addFirst(tenantPropertySource);
        }
    }
}

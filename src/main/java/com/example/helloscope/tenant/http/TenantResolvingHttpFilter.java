package com.example.helloscope.tenant.http;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TenantResolvingHttpFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TenantResolvingHttpFilter.class);
    @Autowired
    private TenantContextHolder tenantContextHolder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init:");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String tenantId = resolveTenantId(httpRequest);
        log.info("doFilter: tenantId={}", tenantId);
        TenantContext context = TenantContext.of(tenantId);
        try {
            tenantContextHolder.setTenantContext(context);
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            tenantContextHolder.clearTenantContext();
        }
    }

    private String resolveTenantId(HttpServletRequest httpRequest) {
        return httpRequest.getHeader("X-Tenant");
    }

    @Override
    public void destroy() {
        log.info("destroy:");
    }
}

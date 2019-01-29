package com.example.hellotenant.tenant.http;

import com.example.hellotenant.tenant.TenantContext;
import com.example.hellotenant.tenant.TenantTemplate;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Resolves the current {@link TenantContext}, if any, and makes it accessible for the {@link HttpServletRequest}.
 */
@Component
public class TenantAwareHttpFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TenantAwareHttpFilter.class);
    @Autowired
    private TenantHttpResolver tenantHttpResolver;
    @Autowired
    private TenantTemplate tenantTemplate;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("init:");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
        }
        catch (IOException | ServletException | RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain) throws Exception {
        String tenantId = tenantHttpResolver.resolveTenantId(httpRequest);
        if (tenantId == null) {
            filterChain.doFilter(httpRequest, httpResponse);
        }
        else {
            log.debug("doFilter: {}", tenantId);
            TenantContext context = TenantContext.of(tenantId);
            tenantTemplate.with(context, () -> filterChain.doFilter(httpRequest, httpResponse));
        }
    }

    @Override
    public void destroy() {
        log.info("destroy:");
    }
}

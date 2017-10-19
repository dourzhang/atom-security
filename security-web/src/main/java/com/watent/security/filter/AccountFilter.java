package com.watent.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AccountFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(AccountFilter.class);

    private static final String KEY = "account";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        if (null == SecurityContextHolder.getContext().getAuthentication()) {
//            chain.doFilter(request, response);
//        }

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String value = principal.toString();
            if (logger.isDebugEnabled()) {
                logger.debug("MDC put key:{},value:{}", KEY, value);
            }
            MDC.put(KEY, value);
            chain.doFilter(request, response);
            if (logger.isDebugEnabled()) {
                logger.debug("MDC clear");
            }

        } finally {
            MDC.clear();
        }
    }
}
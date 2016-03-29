package com.sheep.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

public class CustomFilter implements Filter {
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.trace("CustomFilter init ...");
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest) request).getSession();
        logger.error("session class : " + session.getClass().toString());
        logger.trace("CustomFilter doFilter before ...");
        chain.doFilter(request, response);
        logger.trace("CustomFilter doFilter after ...");
        
    }
    
    public void destroy() {
        logger.trace("CustomFilter destroy ...");
    }

}

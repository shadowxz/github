package com.sheep.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sheep.common.log.LoggerFactory;

public class CustomInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    public CustomInterceptor(){
        logger.debug("CustomInterceptor() init ...");
    }
    
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        logger.debug("CustomInterceptor preHandle() ... ");
        
        return true;
    }

    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        logger.debug("CustomInterceptor postHandle() ... ");
        
    }

    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
        logger.debug("CustomInterceptor afterCompletion() ... ");
        
    }

}

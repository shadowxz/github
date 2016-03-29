package com.sheep.web.servlet;

import java.util.Set;

import javax.jws.HandlerChain;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.sheep.common.log.LoggerFactory;

/**
 * 启动
 * 
 * @author t-xiejianqiao
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年6月30日 下午4:20:29
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@HandlesTypes(value = { CustomServlet.class })
public class CustomServletContainerInitializer implements
        ServletContainerInitializer,ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger();
    
    public void onStartup(Set<Class<?>> c, ServletContext ctx)
            throws ServletException {
        logger.trace("===============================xsheep onStartup=============================");
        for (Class<?> clazz : c) {
            logger.debug(clazz.toString());
        }
        
        logger.debug(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE+" : "+ctx.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE).toString());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        
    }

}

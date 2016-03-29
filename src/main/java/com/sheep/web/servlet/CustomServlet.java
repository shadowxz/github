package com.sheep.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

public class CustomServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    public void init(ServletConfig config) throws ServletException {
        logger.info("CustomServlet init invoked ...");
        super.init(config);
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        logger.info("CustomServlet service invoked ...");
    }

    public void destroy() {
        logger.info("CustomServlet destroy invoked ...");
    }

}

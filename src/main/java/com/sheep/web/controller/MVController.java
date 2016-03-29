package com.sheep.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sheep.common.log.LoggerFactory;
import com.sheep.web.bo.XmlRoot;

@RestController
@RequestMapping({"/test"})
public class MVController {
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    @RequestMapping({"/json","oxml"})
    public Object json(HttpServletRequest request){
        
        logger.trace("json request : " + request.getRequestURL() );
        
        return new XmlRoot();
        
    }
    
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
        
        logger.trace("index request : " + request.getRequestURL() );
        
        return new ModelAndView("index");
        
    }
    
    @RequestMapping("/string")
    public String string(HttpServletRequest request){
        
        logger.trace("string request : " + request.getRequestURL() );
        
        return "index";
        
    }
    
    @RequestMapping("/xml")
    public String xml(HttpServletRequest request){
        
        logger.trace("xml request : " + request.getRequestURL() );
        
        return "<root>x</root>";
        
    }
    
}

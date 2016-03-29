package com.sheep.web.ehcache;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.sheep.common.log.LoggerFactory;

@Component
public class CustomCacheManager {
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    private static Cache ehCache;
    
    @Resource
    public void setEhCache(Cache ehCache){
        CustomCacheManager.ehCache = ehCache;
    }
    
    @PostConstruct
    public void init(){
        logger.trace("CustomCacheManager init() ... " + CustomCacheManager.ehCache);
    }
    
}

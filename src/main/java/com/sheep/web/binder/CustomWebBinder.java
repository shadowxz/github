package com.sheep.web.binder;


import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.sheep.common.log.LoggerFactory;

@Component
public class CustomWebBinder implements WebBindingInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger();
	
	public CustomWebBinder(){
		logger.info("CustomWebBinder constructor invoked ...");
	}
	
	public void initBinder(WebDataBinder binder, WebRequest request) {
			
	}

}

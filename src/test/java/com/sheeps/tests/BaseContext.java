package com.sheeps.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.sheep.common.log.LoggerFactory;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:app_config/context/spring-*.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
public class BaseContext {
	
	private static final Logger logger = LoggerFactory.getLogger();

	@Autowired
	protected ApplicationContext application;
	
	@Before
	public void init(){
		logger.debug("================== Test ApplicationContext init ===================");
	}
	
	
}

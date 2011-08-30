package br.edu.gamaesouza.intranet.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	
	public static Object getBean(String beanName){
		
		return applicationContext.getBean( beanName );
		
	}
	
}

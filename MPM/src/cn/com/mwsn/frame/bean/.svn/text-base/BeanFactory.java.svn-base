package cn.com.mwsn.frame.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {
	
	private static ApplicationContext applicationContext;
	
	
	public static Object getBean(String beanName){
		if (applicationContext == null){
			applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		
		return applicationContext.getBean(beanName);
	}

}

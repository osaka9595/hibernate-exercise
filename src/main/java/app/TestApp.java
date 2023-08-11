package app;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import web.member.dao.MemberDao;

public class TestApp {
	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("applicationContext.xml");
        applicationContext.refresh();
            
        MemberDao memberDao = applicationContext.getBean(MemberDao.class);
        System.out.println(memberDao.selectById(1).getNickname());
        
        
        ((ConfigurableApplicationContext) applicationContext).close();
	
		
  }
}

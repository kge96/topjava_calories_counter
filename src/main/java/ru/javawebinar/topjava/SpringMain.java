package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.user.ProfileRestController;


public class SpringMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
		for (String name :  appCtx.getBeanDefinitionNames()) {
			System.out.println(String.format("[%s]", name));
		}
		ProfileRestController mockUserRepository = (ProfileRestController)appCtx.getBean("profileRestController");
		mockUserRepository = appCtx.getBean(ProfileRestController.class);

		System.out.println(mockUserRepository.toString());
		appCtx.close();
	}
}

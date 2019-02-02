package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.MockUserRepositoryImpl;
import ru.javawebinar.topjava.web.user.UserRestController;


public class SpringMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
		for (String name :  appCtx.getBeanDefinitionNames()) {
			System.out.println(String.format("[%s]", name));
		}
		UserRestController mockUserRepository = (UserRestController)appCtx.getBean("userRestController");
		mockUserRepository = appCtx.getBean(UserRestController.class);

		System.out.println(mockUserRepository.toString());
		appCtx.close();
	}
}

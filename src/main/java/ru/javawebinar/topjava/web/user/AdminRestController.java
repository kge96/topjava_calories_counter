package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.api.UserService;

@Controller
public class AdminRestController {

	@Autowired
	private UserService userService;
}

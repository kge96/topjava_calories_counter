package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.api.UserService;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController{

	@Autowired
	private UserService userService;

	@Override
	public User get(int id) {
		return super.get(authUserId());
	}

	public void delete() {
		super.delete(authUserId());
	}

	public void update(User user) {
		super.update(user, authUserId());
	}
}

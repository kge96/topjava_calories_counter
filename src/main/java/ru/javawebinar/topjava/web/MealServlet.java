package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {

	private final Logger log = LoggerFactory.getLogger(MealServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("getAll");
		req.setAttribute("mealList",
				MealsUtil.getWithExceeded(MealsUtil.MEAL_LIST, 2000));
		req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
	}
}

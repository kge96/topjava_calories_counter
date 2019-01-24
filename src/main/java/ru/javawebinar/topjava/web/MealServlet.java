package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

	private final Logger log = getLogger(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("start doGet from MealServlet");
		List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredMealsWithExceed();
		req.setAttribute("mealList", mealWithExceeds);
		getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
//		resp.sendRedirect("meals.jsp");
	}
}

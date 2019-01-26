package ru.javawebinar.topjava.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

	private final Logger log = getLogger(getClass());
	private static final String MEALS = "/mealList.jsp";
	private static final String INSERT_OR_EDIT = "/addMeal.jsp";

	private MealRepository mealRepository = new InMemoryMealRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("start doGet from MealServlet");

		String forward = "";
		String action = req.getParameter("action");

		if (action == null || action.length() == 0) {
			req.setAttribute("mealList", mealRepository.getAll());
			getServletContext().getRequestDispatcher(MEALS).forward(req, resp);
		} else {
			if (action.equalsIgnoreCase("delete")) {
				Optional.ofNullable(req.getParameter("id")).map(Integer::parseInt).ifPresent(id -> mealRepository.delete(id));
				forward = MEALS;
			} else if (action.equalsIgnoreCase("edit")) {
				forward = INSERT_OR_EDIT;
				int id = Integer.parseInt(req.getParameter("id"));
				MealWithExceed meal = mealRepository.getById(id);
				req.setAttribute("meal", meal);
			} else if (action.equalsIgnoreCase("mealList")) {
				req.setAttribute("mealList", mealRepository.getAll());
				forward = MEALS;
			} else {
				forward = INSERT_OR_EDIT;
			}
			req.setAttribute("mealList", mealRepository.getAll());
			getServletContext().getRequestDispatcher(forward).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Meal meal = new Meal();
		if(StringUtils.isNotBlank(req.getParameter("id"))) {
			meal.setId(Integer.parseInt(req.getParameter("id")));
		}
		meal.setCalories(Integer.parseInt(req.getParameter("calories")));
		meal.setDescription(req.getParameter("description"));
		meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
		mealRepository.addMeal(meal);
		req.setAttribute("mealList", mealRepository.getAll());
		getServletContext().getRequestDispatcher(MEALS).forward(req, resp);
	}
}

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class MealServlet extends HttpServlet {
	private final Logger log = LoggerFactory.getLogger(MealServlet.class);

	private MealRepository repository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		repository = new InMemoryMealRepositoryImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");

		if (action == null) {
			log.info("getAll");
			req.setAttribute("mealList",
					MealsUtil.getWithExceeded(repository.getAll(), 2000));
			req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
		} else if(action.equals("delete")) {
			int id = getId(req);
			log.info("Delete {}", id);
			repository.delete(id);
			resp.sendRedirect("meals");
		} else {
			final Meal meal = action.equals("create") ?
					new Meal(LocalDateTime.now(), "", 1000) :
					repository.get(getId(req));
			req.setAttribute("meal", meal);
			req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
				LocalDateTime.parse(req.getParameter("dateTime")),
				req.getParameter("description"),
				Integer.valueOf(req.getParameter("calories")));
		log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
		repository.save(meal);
		resp.sendRedirect("meals");
	}

	private int getId(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("id"));
		return Integer.valueOf(paramId);
	}
}

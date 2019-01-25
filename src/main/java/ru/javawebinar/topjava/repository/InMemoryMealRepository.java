package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class InMemoryMealRepository implements MealRepository{

	@Override
	public void addMeal(Meal meal) {
		ConcurrentMap<Integer, Meal> meals = MealsUtil.getMeals();
		if (meal.getId() != null) {
			meals.replace(meal.getId(), meal);
		} else {
			Integer id = MealsUtil.generateId();
			meal.setId(id);
			meals.putIfAbsent(id, meal);
		}
	}

	@Override
	public boolean delete(Integer id) {
		return MealsUtil.getMeals().remove(id) != null;
	}

	@Override
	public List<Meal> getAll() {
		return new ArrayList<>(MealsUtil.getMeals().values());
	}

	@Override
	public Meal getById(Integer id) {
		return MealsUtil.getMeals().get(id);
	}
}

package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealRepository {

	void addMeal(Meal meal);

	boolean delete(Integer id);

	List<MealWithExceed> getAll();

	MealWithExceed getById(Integer id);
}

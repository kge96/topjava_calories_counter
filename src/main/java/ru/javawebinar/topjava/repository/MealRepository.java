package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
	void addMeal(Meal meal);

	boolean delete(Integer id);

	List<Meal> getAll();

	Meal getById(Integer id);
}

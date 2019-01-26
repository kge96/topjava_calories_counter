package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository{

	private static AtomicInteger MEAL_ID = new AtomicInteger(1000);
	private static ConcurrentMap<Integer, Meal> MEALS = init();

	private static ConcurrentMap<Integer, Meal> init() {
		List<Meal> mealList = Arrays.asList(
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Завтрак", 500),
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 15, 14, 0), "Обед", 600),
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 15, 18, 0), "Ужин", 400),
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 16, 9, 0), "Завтрак", 2100),
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 16, 13, 0), "Обед", 1000),
				new Meal(MEAL_ID.getAndIncrement(), LocalDateTime.of(2019, Month.JANUARY, 16, 19, 0), "Ужин", 400)
		);
		return mealList.stream().collect(Collectors.toConcurrentMap(Meal::getId, meal -> meal));
	}

	@Override
	public void addMeal(Meal meal) {
		Optional.ofNullable(meal.getId()).ifPresentOrElse(
				id -> MEALS.replace(id, meal),
				() -> {
					Integer id = generateId();
					meal.setId(id);
					MEALS.putIfAbsent(id, meal);
				});
	}

	@Override
	public boolean delete(Integer id) {
		return MEALS.remove(id) != null;
	}

	@Override
	public List<MealWithExceed> getAll() {
		return MealsUtil.getFilteredMealsWithExceed(MEALS).values().stream()
				.sorted(Comparator.comparing(MealWithExceed::getDateTime))
				.collect(Collectors.toList());
	}

	@Override
	public MealWithExceed getById(Integer id) {
		return MealsUtil.getFilteredMealsWithExceed(MEALS).get(id);
	}

	private static Integer generateId(){
		return MEAL_ID.getAndIncrement();
	}
}

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealsUtil {

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


    public static ConcurrentMap<Integer, MealWithExceed> getFilteredMealsWithExceed() {
        return getFilteredMealsWithExceed(MEALS, LocalTime.MIN, LocalTime.MAX, 2200);
    }

    public static ConcurrentMap<Integer, MealWithExceed> getFilteredMealsWithExceed (ConcurrentMap<Integer, Meal> mealList, LocalTime start, LocalTime end, int limit) {
        Map<LocalDate, Integer> dayCaloriesCount = mealList.values().stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return mealList.values().stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), start, end))
                .map(meal ->  new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), dayCaloriesCount.get(meal.getDateTime().toLocalDate()) > limit))
                .collect(Collectors.toConcurrentMap(MealWithExceed::getId, value -> value));
    }

    public static ConcurrentMap<Integer, Meal> getMeals() {
		return MEALS;
	}

	public static Integer generateId(){
		return MEAL_ID.getAndIncrement();
	}
}

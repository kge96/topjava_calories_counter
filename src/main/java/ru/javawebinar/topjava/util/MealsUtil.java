package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class MealsUtil {

    public static ConcurrentMap<Integer, MealWithExceed> getFilteredMealsWithExceed(ConcurrentMap<Integer, Meal> meals) {
        return getFilteredMealsWithExceed(meals, LocalTime.MIN, LocalTime.MAX, 2200);
    }

    public static ConcurrentMap<Integer, MealWithExceed> getFilteredMealsWithExceed (ConcurrentMap<Integer, Meal> mealList, LocalTime start, LocalTime end, int limit) {
        Map<LocalDate, Integer> dayCaloriesCount = mealList.values().stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return mealList.values().stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), start, end))
                .map(meal ->  new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), dayCaloriesCount.get(meal.getDateTime().toLocalDate()) > limit))
                .collect(Collectors.toConcurrentMap(MealWithExceed::getId, value -> value));
    }

}

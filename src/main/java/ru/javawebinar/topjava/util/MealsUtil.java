package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 14, 0), "Обед", 600),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 18, 0), "Ужин", 400),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 16, 9, 0), "Завтрак", 2100),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 16, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 16, 19, 0), "Ужин", 400)
        );
        getFilteredMealsWithExceed(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(m -> System.out.println(m.toString()));
    }

    public static List<MealWithExceed> getFilteredMealsWithExceed (List<Meal> mealList, LocalTime start, LocalTime end, int limit) {
        Map<LocalDate, Integer> dayCaloriesCount = mealList.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), start, end))
                .map(meal ->  new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), dayCaloriesCount.get(meal.getDateTime().toLocalDate()) > limit))
                .collect(Collectors.toList());
    }

    public static List<MealWithExceed> getFilteredWithExceedByCycle(List<Meal> meals, LocalTime lt, LocalTime st, LocalTime et, int limit) {
        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();

        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(),Integer::sum));

        final List<MealWithExceed> mealsWithExceed = new ArrayList<>();

        meals.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), st, et)) {
                mealsWithExceed.add(createWithExceed(meal, caloriesSumByDate.get(meal.getCalories()) > limit));
            }
        });
        return  mealsWithExceed;
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceed) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }
}

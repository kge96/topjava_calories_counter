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
}

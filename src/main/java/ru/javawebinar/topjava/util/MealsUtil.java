package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 12, 0), "Обед", 600),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 18, 0), "Ужин", 400),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Завтрак", 300),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2019, Month.JANUARY, 15, 10, 0), "Ужин", 400)
        );
        getFilteredMealsWithExceed(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<MealWithExceed> getFilteredMealsWithExceed (List<Meal> mealList, LocalTime start, LocalTime end, int limit) {
        return null;
    }
}

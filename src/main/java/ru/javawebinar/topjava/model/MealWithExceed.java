package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealWithExceed {

    protected Integer id;

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this(null, dateTime, description, calories, exceed);
    }

    public MealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}

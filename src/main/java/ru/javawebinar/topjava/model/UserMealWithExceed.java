package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {
    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;
    protected final boolean ecxceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean ecxceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.ecxceed = ecxceed;
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

    public boolean isEcxceed() {
        return ecxceed;
    }
}

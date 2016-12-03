package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int limitCaloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDay =
                mealList.stream().collect(
                        Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)
                        )
                );
        List<UserMealWithExceed> filteredMeals =
                mealList.stream()
                        .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                        .map(um -> new UserMealWithExceed(um.getDateTime(),
                                        um.getDescription(),
                                        um.getCalories(),
                                        caloriesPerDay.get(um.getDateTime().toLocalDate()) > limitCaloriesPerDay
                                )
                        )
                        .collect(Collectors.toList());

        return filteredMeals; ////
    }

    public static void main(String[] args) {

        int caloriesPerDay = 2000;
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(11, 0);

        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, startTime, endTime, caloriesPerDay);
        filteredWithExceeded.forEach(System.out::println);
    }
}

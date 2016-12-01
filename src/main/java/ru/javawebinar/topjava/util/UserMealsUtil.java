package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Item;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), true))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Item> mealList = Arrays.asList(
                new Item(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Item(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Item(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Item(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Item(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Item(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMeal> mealList2 = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
//        List<Item> mealList = Arrays.asList(
//                new Item(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", new BigDecimal("29.99")),
//                new Item(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", new BigDecimal("29.99")),
//                new Item(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", new BigDecimal("29.99")),
//                new Item(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", new BigDecimal("29.99")),
//                new Item(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", new BigDecimal("29.99")),
//                new Item(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", new BigDecimal("29.99"))
//        );
//        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        filteredWithExceeded.forEach(System.out::println);
//        Map<BigDecimal, Set<String>> localDates = mealList.stream().collect(Collectors.groupingBy(UserMeal::getDateTime, Collectors.summingInt(UserMeal::getCalories)));
//        localDates.forEach(System.out::println);

        //3 apple, 2 banana, others 1
//        List<Item> items = Arrays.asList(
//                new Item("apple", 10, new BigDecimal("9.99")),
//                new Item("banana", 20, new BigDecimal("19.99")),
//                new Item("orang", 10, new BigDecimal("29.99")),
//                new Item("watermelon", 10, new BigDecimal("29.99")),
//                new Item("papaya", 20, new BigDecimal("9.99")),
//                new Item("apple", 10, new BigDecimal("9.99")),
//                new Item("banana", 10, new BigDecimal("19.99")),
//                new Item("apple", 20, new BigDecimal("9.99"))
//        );

        //group by price
//        Map<BigDecimal, List<Item>> groupByPriceMap =
//                items.stream().collect(Collectors.groupingBy(Item::getPrice));

//        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
//        Map<BigDecimal, Set<String>> result =
//                items.stream().collect(
//                        Collectors.groupingBy(Item::getPrice,
//                                Collectors.mapping(Item::getName, Collectors.toSet())
//                        )
//                );
        Map<Integer, Set<String>> result2 =
                mealList.stream().collect(
                        Collectors.groupingBy(Item::getCalories,
                                Collectors.mapping(Item::getDescription, Collectors.toSet())
                        )
                );
//        Map<LocalDateTime, Set<String>> result3 =
//                mealList2.stream().collect(
//                        Collectors.groupingBy(UserMeal::getDateTime,
//                                Collectors.mapping(UserMeal::getDescription, Collectors.toSet())
//                        )
//                );
//        Map<LocalDateTime, Set<String>> result3 =
//                mealList2.stream().collect(
//                        Collectors.groupingBy(i -> i.getDateTime(),
//                                Collectors.mapping(UserMeal::getDescription, Collectors.toSet())
//                        )
//                );
//        Map<LocalDate, Set<String>> result3 =
//                mealList2.stream().collect(
//                        Collectors.groupingBy(i -> i.getDateTime().toLocalDate(),
//                                Collectors.mapping(UserMeal::getDescription, Collectors.toSet())
//                        )
//                );
        Map<LocalDate, Integer> result3 =
                mealList2.stream().collect(
                        Collectors.groupingBy(i -> i.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)
                        )
                );
//        Map<LocalDate, Set<String>> result3 =
//                mealList2.stream().collect(
//                        Collectors.groupingBy(UserMeal::getDateTime::toLocalDate,
//                        Collectors.mapping(UserMeal::getDescription, Collectors.toSet())
//                );
//        Map<BigDecimal, Set<String>> result2 =
//                mealList.stream().collect(
//                        Collectors.groupingBy(s -> s.())
//                );

//        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
//        result3.forEach(System.out::println);
    }
}

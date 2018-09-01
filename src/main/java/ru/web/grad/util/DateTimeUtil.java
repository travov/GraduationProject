package ru.web.grad.util;

import java.time.LocalDate;

public class DateTimeUtil {

    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static LocalDate getMin(LocalDate min){
        return min == null || min.isBefore(MIN_DATE)? MIN_DATE: min;
    }

    public static LocalDate getMax(LocalDate max){
        return max == null || max.isAfter(MAX_DATE)? MAX_DATE: max;
    }

}

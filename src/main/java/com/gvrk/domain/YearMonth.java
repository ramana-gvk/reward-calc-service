package com.gvrk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;
import java.util.Comparator;

@Data
@AllArgsConstructor
public class YearMonth {
    private int year;
    private Month month;

    private static final Comparator<YearMonth> yearComparator
            = Comparator.comparing(YearMonth::getYear);
    private static final Comparator<YearMonth> monthComparator
            = Comparator.comparing(YearMonth::getMonth);
    public static final Comparator<YearMonth> comparator =
            yearComparator.thenComparing(monthComparator);
}

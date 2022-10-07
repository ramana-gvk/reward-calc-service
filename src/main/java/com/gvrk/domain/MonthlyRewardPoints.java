package com.gvrk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class MonthlyRewardPoints {
    private YearMonth yearMonth;
    private int rewardPoints;
    public static final Comparator<MonthlyRewardPoints> comparator =
            (mrp1, mrp2) -> YearMonth.comparator.compare(mrp1.getYearMonth(), mrp2.getYearMonth());
}

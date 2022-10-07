package com.gvrk.service;

import com.gvrk.domain.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

@Service
public class RewardPointsCalculatorServiceImpl implements RewardPointsCalculatorService {
    private static final Collection<RewardPointsRule> rewardPointsRules =
            List.of(new RewardPointsRule(100, 2)
                  , new RewardPointsRule(50, 1));

    public RewardPoints calculateRewardPoints(Collection<Transaction> transactions) {
        RewardPoints rewardPointsObj = new RewardPoints();
        if (transactions != null) {
            var monthlyRewardPointsList =
            transactions.stream().map(transaction -> {
               int rewardPoints = calculateRewardPointsForDollarSpent(transaction.getDollarsSpent());
               YearMonth yearMonth =  new YearMonth(transaction.getTransactionDate().getYear(),
                        transaction.getTransactionDate().getMonth());
               return ImmutablePair.of(yearMonth, rewardPoints);
            }).collect(groupingBy(ImmutablePair::getLeft, summingInt(ImmutablePair::getRight)))
                    .entrySet().stream().map(entry -> new MonthlyRewardPoints(entry.getKey(), entry.getValue()))
                    .sorted(MonthlyRewardPoints.comparator)
                    .collect(toList());
            rewardPointsObj.setRewardPoints(monthlyRewardPointsList);
        }
        return rewardPointsObj;
    }

    int calculateRewardPointsForDollarSpent(int dollarsSpent) {
        int rewardPoints = 0;
        for (var rewardRule: rewardPointsRules) {
            if (dollarsSpent > rewardRule.getMinQualifyingDollarSpend()) {
                rewardPoints += (dollarsSpent - rewardRule.getMinQualifyingDollarSpend()) * rewardRule.getPointsPerDollarSpent();
                dollarsSpent = rewardRule.getMinQualifyingDollarSpend();
            }
        }
        return rewardPoints;
    }
}

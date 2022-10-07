package com.gvrk.service;

import com.gvrk.domain.MonthlyRewardPoints;
import com.gvrk.domain.RewardPoints;
import com.gvrk.domain.Transaction;
import com.gvrk.domain.YearMonth;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RewardPointsCalculatorServiceImplTest {
    private final RewardPointsCalculatorServiceImpl rewardPointsCalculatorServiceImpl
            = new RewardPointsCalculatorServiceImpl();

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "49, 0",
            "50, 0",
            "70, 20",
            "99, 49",
            "100, 50",
            "120, 90"
    })
    void testCalculateRewardPointsForDollarsSpent(int dollarsSpent, int expectedRewardPoints) {
        int actualRewardPoints = rewardPointsCalculatorServiceImpl.calculateRewardPointsForDollarSpent(dollarsSpent);
        Assertions.assertEquals(expectedRewardPoints, actualRewardPoints
                , "reward points calculation wrong for dollarsSpent = "+dollarsSpent);
    }

    @ParameterizedTest
    @MethodSource("calculateRewardPointsTestData")
    void testCalculateRewardPoints(Collection<Transaction> transactions
                                 , RewardPoints expectedRewardPoints) {
        var actualRewardPoints = rewardPointsCalculatorServiceImpl.calculateRewardPoints(transactions);
        Assertions.assertEquals(expectedRewardPoints, actualRewardPoints, "calculated reward points wrong for transactions: "+transactions);
    }

    private static Stream<Arguments> calculateRewardPointsTestData() {
        return Stream.of(
                Arguments.of(toTransactions(
                            ImmutablePair.of("12/5/2021", 120),
                            ImmutablePair.of("12/29/2021", 232),
                            ImmutablePair.of("1/4/2022", 135),
                            ImmutablePair.of("1/14/2022", 35),
                            ImmutablePair.of("2/23/2022", 90)),
                            toRewardPoints(
                                    ImmutableTriple.of(2021, Month.DECEMBER, 404),
                                    ImmutableTriple.of(2022, Month.JANUARY, 120),
                                    ImmutableTriple.of(2022, Month.FEBRUARY, 40)
                            )
                        )
        );
    }

    private static Collection<Transaction> toTransactions(ImmutablePair<String, Integer>... tdata) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return Arrays.stream(tdata).map(data -> {
            Transaction transaction = new Transaction();
            transaction.setTransactionDate(LocalDate.parse(data.getLeft(), dateFormatter));
            transaction.setDollarsSpent(data.getRight());
            return transaction;
        }).collect(Collectors.toList());
    }

    private static RewardPoints toRewardPoints(ImmutableTriple<Integer, Month, Integer>... rdata) {
        var rewardPoints = Arrays.stream(rdata).map(data ->
            new MonthlyRewardPoints(new YearMonth(data.getLeft(), data.getMiddle())
                                         , data.getRight())
        ).collect(Collectors.toList());
        var rewardPointsObj = new RewardPoints();
        rewardPointsObj.setRewardPoints(rewardPoints);
        return rewardPointsObj;
    }
}

package com.gvrk.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RewardPointsTest {

    @ParameterizedTest
    @MethodSource("rewardPointsTestData")
    void testGetMonthlyRewardPoints(Collection<MonthlyRewardPoints> rewardPointsList, int totalRewardPoints) {
        var rewardPoints = new RewardPoints();
        rewardPoints.setRewardPoints(rewardPointsList);
        Assertions.assertEquals(totalRewardPoints, rewardPoints.getTotalRewardPoints()
                , "Total reward calculation is wrong for "+rewardPointsList);
    }

    private static Stream<Arguments> rewardPointsTestData() {
        return Stream.of(
                Arguments.of(toMonthlyRewardPoints(10, 20, 30), 60),
                Arguments.of(toMonthlyRewardPoints(0), 0),
                Arguments.of(null, 0)
        );
    }

    private static Collection<MonthlyRewardPoints>
        toMonthlyRewardPoints(int... rewardPointsList) {
        return Arrays.stream(rewardPointsList)
                .mapToObj(rewardPoints -> new MonthlyRewardPoints(null, rewardPoints))
                .collect(Collectors.toList());
    }
}

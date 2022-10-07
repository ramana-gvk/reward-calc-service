package com.gvrk.domain;

import lombok.Data;

import java.util.Collection;

@Data
public class RewardPoints {
    private Collection<MonthlyRewardPoints> rewardPoints;

    public int getTotalRewardPoints() {
        int totalRewardPoints = 0;
        if (rewardPoints != null) {
            totalRewardPoints = rewardPoints.stream().map(MonthlyRewardPoints::getRewardPoints)
                    .reduce(0, Integer::sum);
        }
        return totalRewardPoints;
    }
}

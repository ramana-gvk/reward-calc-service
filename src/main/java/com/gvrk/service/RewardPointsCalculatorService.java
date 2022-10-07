package com.gvrk.service;

import com.gvrk.domain.RewardPoints;
import com.gvrk.domain.Transaction;

import java.util.Collection;

public interface RewardPointsCalculatorService {
    RewardPoints calculateRewardPoints(Collection<Transaction> transactions);
}

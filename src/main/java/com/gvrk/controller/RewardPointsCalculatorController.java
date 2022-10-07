package com.gvrk.controller;

import com.gvrk.domain.RewardPoints;
import com.gvrk.domain.Transaction;
import com.gvrk.service.RewardPointsCalculatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RewardPointsCalculatorController {
    private final RewardPointsCalculatorService rewardPointsCalculatorService;

    public RewardPointsCalculatorController(RewardPointsCalculatorService rewardPointsCalculatorService) {
        this.rewardPointsCalculatorService = rewardPointsCalculatorService;
    }

    @PostMapping("/rewardPointsCalculator")
    public RewardPoints calculateRewardPoints(@RequestBody Collection<Transaction> transactions) {
        return rewardPointsCalculatorService.calculateRewardPoints(transactions);
    }
}

package com.gvrk;

import com.gvrk.controller.RewardPointsCalculatorController;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRestServiceApplicationTest {
    @Autowired
    RewardPointsCalculatorController rewardPointsCalculatorController;
    @Test
    void testContextLoads() {
        assertNotNull(rewardPointsCalculatorController
                , "No RewardPointsCalculatorController in context");
    }
}

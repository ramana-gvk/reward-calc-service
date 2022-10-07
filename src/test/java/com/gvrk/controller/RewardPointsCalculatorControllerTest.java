package com.gvrk.controller;

import com.gvrk.service.RewardPointsCalculatorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = {RewardPointsCalculatorController.class, RewardPointsCalculatorServiceImpl.class})
public class RewardPointsCalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RewardPointsCalculatorController controller;

    @ParameterizedTest
    @MethodSource("calculateRewardPointsTestData")
    void testCalculateRewardPoints(String requestJson, String responseJson) throws Exception {
        Assertions.assertNotNull(controller);
        mockMvc.perform(post("/rewardPointsCalculator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    private static Stream<Arguments> calculateRewardPointsTestData() {
        return Stream.of(
                Arguments.of("[{"+
                        "\"transactionDate\": \"2022-10-07\","+
                        "\"dollarsSpent\": 100"+
                "}]",
                        "{\"rewardPoints\":[{\"yearMonth\":{\"year\":2022,\"month\":\"OCTOBER\"},\"rewardPoints\":50}]}"),
                Arguments.of("[{"+
                                "\"transactionDate\": \"2022-10-07\","+
                                "\"dollarsSpent\": 100"+
                                "}]",
                        "{\"rewardPoints\":[{\"yearMonth\":{\"year\":2022,\"month\":\"OCTOBER\"},\"rewardPoints\":50}]}"),
                Arguments.of("[ {\n" +
                        "    \"transactionDate\": \"2021-11-07\",\n" +
                        "    \"dollarsSpent\": 120\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"transactionDate\": \"2021-11-17\",\n" +
                        "    \"dollarsSpent\": 80\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"transactionDate\": \"2021-12-07\",\n" +
                        "    \"dollarsSpent\": 220\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"transactionDate\": \"2022-01-07\",\n" +
                        "    \"dollarsSpent\": 230\n" +
                        "  }]",
                        "{\"rewardPoints\":[{\"yearMonth\":{\"year\":2021,\"month\":\"NOVEMBER\"},\"rewardPoints\":120},{\"yearMonth\":{\"year\":2021,\"month\":\"DECEMBER\"},\"rewardPoints\":290},{\"yearMonth\":{\"year\":2022,\"month\":\"JANUARY\"},\"rewardPoints\":310}],\"totalRewardPoints\":720}")

        );
    }
}

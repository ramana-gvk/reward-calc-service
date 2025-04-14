package com.gvrk.domain;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardPointsRule {
    // need to spend above this dollar amount for reward points
    @Min(0)
    private int minQualifyingDollarSpend;
    // points for each dollar spent over 'minQualifyingDollarSpend'
    // int sufficient here?
    // will there ever be fractional reward point award?
    @Min(1)
    private int pointsPerDollarSpent;
}

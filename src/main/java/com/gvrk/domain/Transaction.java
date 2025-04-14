package com.gvrk.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private LocalDate transactionDate;
    @Min(0)
    private int dollarsSpent;
}

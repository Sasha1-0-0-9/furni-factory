package com.example.furnifactory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinanceReportDto {
    private LocalDate from;
    private LocalDate to;
    private double income;
    private double outcome;
    private double profit;
}

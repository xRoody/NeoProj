package com.example.neoproj.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public interface CalculatorService {
    BigDecimal calc(BigDecimal salary, Integer days);
    BigDecimal calc(BigDecimal salary, LocalDate startDate, LocalDate endDate);
}

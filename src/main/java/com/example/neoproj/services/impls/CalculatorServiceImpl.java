package com.example.neoproj.services.impls;

import com.example.neoproj.DTOs.ResultDTO;
import com.example.neoproj.services.CalculatorService;
import com.example.neoproj.services.HolidaysCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
@AllArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    private HolidaysCalculator holidaysCalculator;

    public BigDecimal calc(BigDecimal salary, Integer days){
        if(days<0) throw new RuntimeException("Days must be positive");
        if (days==0) return BigDecimal.valueOf(0);
        return getSalaryByDay(salary).multiply(BigDecimal.valueOf(days));
    }

    public BigDecimal calc(BigDecimal salary, LocalDate startDate, LocalDate endDate){
        if (startDate.isEqual(endDate)) return BigDecimal.valueOf(0);
        else if (startDate.isAfter(endDate)) throw new RuntimeException("The start date after end date");
        return calc(salary, holidaysCalculator.calcWorkDays(startDate,endDate));
    }

    private BigDecimal getSalaryByDay(BigDecimal salary){
        return salary
                .divide(BigDecimal.valueOf(29.3), 2, RoundingMode.CEILING);
    }
}

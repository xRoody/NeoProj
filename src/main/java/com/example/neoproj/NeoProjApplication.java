package com.example.neoproj;

import com.example.neoproj.services.CalculatorService;
import com.example.neoproj.services.HolidaysCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootApplication
public class NeoProjApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(NeoProjApplication.class, args);
        List<Integer> list=new ArrayList<>();
        list.add(10, 2);
    }
}

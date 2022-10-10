package com.example.neoproj;

import com.example.neoproj.services.CalculatorService;
import com.example.neoproj.services.HolidaysCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorServiceImplTest {

    @MockBean
    private HolidaysCalculator holidaysCalculator;

    @Autowired
    private CalculatorService calculator;

    @BeforeAll
    void before(){
        Mockito.when(holidaysCalculator.calcWorkDays(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 16))).thenReturn(5);
    }

    @Test
    void testCalc(){
        Assertions.assertEquals(new BigDecimal("10000.00"), calculator.calc(BigDecimal.valueOf(29300),10));
    }

    @Test
    void testCalcWithDates(){
        Assertions.assertEquals(new BigDecimal("5000.00"), calculator.calc(BigDecimal.valueOf(29300),LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 16)));
    }
}

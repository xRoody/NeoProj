package com.example.neoproj;

import com.example.neoproj.services.impls.HolidayCalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class HolidaysCalculatorImplTest {
    @Autowired
    private HolidayCalculatorImpl holidaysCalculator;

    @Test
    void calcWorkDaysTest() {
        Assertions.assertEquals(5,holidaysCalculator.calcWorkDays(LocalDate.of(2022,10, 8),LocalDate.of(2022,10, 15)));
        Assertions.assertEquals(6,holidaysCalculator.calcWorkDays(LocalDate.of(2022,10, 8),LocalDate.of(2022,10, 17)));
        Assertions.assertEquals(9, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 14)));
        Assertions.assertEquals(7, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 2, 14), LocalDate.of(2022, 2, 22)));
        Assertions.assertEquals(8, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 2, 14), LocalDate.of(2022, 2, 24)));
        Assertions.assertEquals(4, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 2, 21), LocalDate.of(2022, 2, 25)));
        Assertions.assertEquals(9, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 2, 21), LocalDate.of(2022, 3, 5)));
        Assertions.assertEquals(5, holidaysCalculator.calcWorkDays(LocalDate.of(2022, 4, 29), LocalDate.of(2022, 5, 6)));
    }
}

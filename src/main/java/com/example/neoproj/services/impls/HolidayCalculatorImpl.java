package com.example.neoproj.services.impls;

import com.example.neoproj.services.HolidaysCalculator;
import com.example.neoproj.sources.HolidaySource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class HolidayCalculatorImpl implements HolidaysCalculator {
    private HolidaySource holidaySource;

    @Override
    public Integer calcWorkDays(LocalDate start, LocalDate end) {
        end = end.plusDays(1L);
        int sum = 0;
        long rez = ChronoUnit.DAYS.between(start, end);
        sum += 2 * (rez / 7);
        if (start.getDayOfWeek().getValue() == 7) sum++;
        else if (end.getDayOfWeek().getValue() == 7) sum++;
        else if (start.getDayOfWeek().getValue() > end.getDayOfWeek().getValue()) sum += 2;
        if (start.getMonth().getValue()==end.getMonth().getValue()){
            for (LocalDate date: holidaySource.getAllHolidaysWithoutVacantByMonthAndYear(start.getMonth().getValue(), start.getYear())) {
                if ((date.isAfter(start) && date.isBefore(end)) || date.isEqual(start)) sum++;
            }
        }else {
            for (LocalDate date: holidaySource.getAllHolidaysWithoutVacantByMonthAndYear(start.getMonth().getValue(), start.getYear())) {
                if (date.isAfter(start) || date.isEqual(start)) sum++;
            }
            for (LocalDate date:holidaySource.getAllHolidaysWithoutVacantByMonthAndYear(end.getMonth().getValue(), end.getYear())){
                if (date.isBefore(end)) sum++;
            }
        }
        return (int) rez - sum;
    }
}

package com.example.neoproj.sources;

import java.time.LocalDate;
import java.util.Set;

public interface HolidaySource {
    Set<LocalDate> getAllHolidaysWithoutVacantByMonthAndYear(int month, int year);
}

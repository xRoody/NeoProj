package com.example.neoproj.sources;

import java.time.LocalDate;
import java.util.Set;

public interface HolidaySource {
    Set<LocalDate> getAllHolidaysWithoutVacantByMonth(int month);
}

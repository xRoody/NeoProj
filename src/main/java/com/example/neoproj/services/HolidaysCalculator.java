package com.example.neoproj.services;

import java.time.LocalDate;
import java.util.Date;

public interface HolidaysCalculator {
    Integer calcWorkDays(LocalDate start, LocalDate end);
}


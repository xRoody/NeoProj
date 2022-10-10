package com.example.neoproj.sources.impls;

import com.example.neoproj.sources.HolidaySource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Repository
public class HolidayEnumSource implements HolidaySource {

    private enum Holidays {
        FIRST_WINTER_DAY(LocalDate.of(0, 1, 1)),
        SECOND_WINTER_DAY(LocalDate.of(0, 1, 2)),
        THIRD_WINTER_DAY(LocalDate.of(0, 1, 3)),
        FOURTH_WINTER_DAY(LocalDate.of(0, 1, 4)),
        FIFTH_WINTER_DAY(LocalDate.of(0, 1, 5)),
        SIXTH_WINTER_DAY(LocalDate.of(0, 1, 6)),
        SEVENTH_WINTER_DAY(LocalDate.of(0, 1, 7)),
        EIGHT_WINTER_DAY(LocalDate.of(0, 1, 8)),
        DEFENDERS_DAY(LocalDate.of(0, 2, 23)),
        WOMANS_DAY(LocalDate.of(0, 3, 8)),
        FIRST_MAY_DAY(LocalDate.of(0, 5, 1)),
        VICTORY_DAY(LocalDate.of(0, 5, 9)),
        RUSSIA_DAY(LocalDate.of(0, 6, 12)),
        UNITY_DAY(LocalDate.of(0, 11, 4));


        private final LocalDate localDate;

        Holidays(LocalDate localDate) {
            this.localDate = localDate;
        }
    }

    @Override
    public Set<LocalDate> getAllHolidaysWithoutVacantByMonthAndYear(int month, int year) {
        Set<LocalDate> dates=new HashSet<>();
        for (Holidays h:Holidays.values()){
            LocalDate tmp=LocalDate.of(year, h.localDate.getMonth(), h.localDate.getDayOfMonth());
            if (tmp.getMonth().getValue()==month){
                while (tmp.getDayOfWeek().getValue()==6 || tmp.getDayOfWeek().getValue()==7 || dates.contains(tmp)){
                    tmp=tmp.plusDays(1);
                }
                dates.add(tmp);
            }
        }
        return dates;
    }
}

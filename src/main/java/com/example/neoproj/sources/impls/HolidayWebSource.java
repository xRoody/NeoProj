package com.example.neoproj.sources.impls;

import com.example.neoproj.sources.HolidaySource;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.w3c.dom.traversal.NodeFilter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Repository
public class HolidayWebSource implements HolidaySource{
    private HolidayEnumSource holidayEnumSource;

    @Autowired
    public HolidayWebSource(HolidayEnumSource holidayEnumSource) {
        this.holidayEnumSource = holidayEnumSource;
    }

    private final String[] month=new String[]{
        "январь",
        "февраль",
        "март",
        "апрель",
        "май",
        "июнь",
        "июль",
        "август",
        "сентябрь",
        "октябрь",
        "ноябрь",
        "декабрь"
    };

    @AllArgsConstructor
    private static class Node{
        private int month;
        private int year;
    }

    private final LoadingCache<Node, Set<LocalDate>> cache = Caffeine.newBuilder()
            .expireAfterWrite(12, TimeUnit.HOURS)
            .build(y->{
                try {
                    Document doc = Jsoup.connect("http://www.consultant.ru/law/ref/calendar/proizvodstvennye/"+y.year+"/")
                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
                            .referrer("http://www.google.com")
                            .get();
                    return doc
                            .select("table.cal")
                            .stream()
                            .filter(x->x.select("th.month").text().toLowerCase().equals(month[y.month-1]))
                            .flatMap(x->Stream.of(x.select("td.weekend").text().split(" ")))
                            .map(x-> LocalDate.of(y.year, y.month, Integer.parseInt(x)))
                            .filter(x->x.getDayOfWeek().getValue()<6)
                            .collect(Collectors.toSet());
                } catch (IOException e) {
                    return holidayEnumSource.getAllHolidaysWithoutVacantByMonthAndYear(y.month, y.year);
                }
            });

    public Set<LocalDate> getAllHolidaysWithoutVacantByMonthAndYear(int monthNum, int year) {
        return cache.get(new Node(monthNum, year));
    }
}

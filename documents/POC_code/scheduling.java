/*
    This file holds some proof of concept code I used at the early stages of design to make 
    sure I'm heading in the right direction

*/

package test.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate startDate = LocalDate.parse("2019 09 19", formatter);
        LocalDate endDate = LocalDate.of(2021, 8, 3);

        printAll(getDatesBetween(startDate, endDate));

    }

    public static void printAll(List<?> strings) {
        for(var item : strings) {
            System.out.println(item);
        }
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public LocalDate parseDate(String ddMMyyyy) throws Exception {
        LocalDate date = LocalDate.parse(ddMMyyyy);
        return date;
    }

}

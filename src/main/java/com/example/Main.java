package com.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GSONParser parser = new GSONParser();
        Root root = parser.parse();

        int bound = root.getTickets().size();

        // инициализируем массив строк и добавляем в него данные о дате и времени вылета
        String[] timeDepartureStr = new String[bound];
        for (int i = 0; i < timeDepartureStr.length; i++) {
            timeDepartureStr[i] = root.getTickets().get(i).getDeparture_date() + " " +
                    root.getTickets().get(i).getDeparture_time();
        }

        // инициализируем массив строк и добавляем в него данные о дате и времени прилета
        String[] timeArrivalStr = new String[bound];
        for (int i = 0; i < timeArrivalStr.length; i++) {
            timeArrivalStr[i] = root.getTickets().get(i).getArrival_date() + " " +
                    root.getTickets().get(i).getArrival_time();
        }

        // установка шаблонов для преобразования значений из String в LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

        // преобразование строки в дату с применением шаблонов,
        // также сразу вычитаем 7 часов для получения времени вылета по Тель-Авиву
        LocalDateTime[] timeDeparture = new LocalDateTime[bound];
        for (int i = 0; i < timeDeparture.length; i++) {
            try {
                timeDeparture[i] = LocalDateTime.parse(timeDepartureStr[i], formatter).minusHours(7);
            } catch (DateTimeParseException e) {
                timeDeparture[i] = LocalDateTime.parse(timeDepartureStr[i], formatter1).minusHours(7);
            }
        }

        // преобразование строки в дату с применением шаблона
        LocalDateTime[] timeArrival = new LocalDateTime[bound];
        for (int i = 0; i < timeArrival.length; i++) {
            try {
                timeArrival[i] = LocalDateTime.parse(timeArrivalStr[i], formatter);
            } catch (DateTimeParseException e) {
                timeArrival[i] = LocalDateTime.parse(timeArrivalStr[i], formatter1);
            }
        }

        // находим время полёта каждого рейса в минутах
        long[] flyingPeriod = new long[bound];
        for (int i = 0; i < flyingPeriod.length; i++) {
            flyingPeriod[i] = Duration.between(timeDeparture[i], timeArrival[i]).toMinutes();
        }

        // складываем все значения времени полета
        long averageTime = 0;
        for (long item : flyingPeriod) {
            averageTime += item;
        }

        // вычисляем среднее значение часов и минут
        long hours = (averageTime / flyingPeriod.length) / 60;
        long minutes = (averageTime / flyingPeriod.length) % 60;

        System.out.println("Среднее время полета между городами Владивосток и Тель-Авив составляет " +
                hours + "ч " + minutes + "м.");

        System.out.println("90-й процентиль времени полета между городами Владивосток и Тель-Авив " +
                percentile(flyingPeriod, 90) / 60 + "ч " +
                percentile(flyingPeriod, 90) % 60 + "м.");
    }

    public static long percentile(long[] arr, int percentile) {
        Arrays.sort(arr);
        int index = (int) Math.ceil(percentile / 100.0 * arr.length);
        return arr[index - 1];
    }
}

package com.example;

/*
Класс для сохранения параметров, считываемых и файла JSON
*/

public class Tickets {

    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;

    public Tickets() {
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                '}';
    }
}

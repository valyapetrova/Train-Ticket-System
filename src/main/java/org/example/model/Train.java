package org.example.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Train {
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime time;
    private LocalTime arrival;

    private double price;

    @Override
    public String toString() {
        return "From: " + from + ", To:" + to + ", departure date:" + date + ", leave:" + time + ", arrive:" + arrival;
    }

}

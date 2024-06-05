package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Train {
    private Place from;
    private Place to;
    private LocalDate date;
    private LocalTime time;
    private LocalTime arrival;
    public List<Train> trains = new ArrayList<>();

    public Place getFrom() {
        return from;}

    public void setFrom(Place from) {
        this.from = from;}

    public Place getTo() {
        return to;}

    public void setTo(Place to) {
        this.to = to;}

    public LocalDate getDate() {
        return date;}

    public void setDate(LocalDate date) {
        this.date = date;}

    public LocalTime getTime() {
        return time;}

    public void setTime(LocalTime time) {
        this.time = time;}

    public LocalTime getArrival() {
        return arrival;}

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;}
    public Train(Place from, Place to, LocalDate date, LocalTime time, LocalTime arrival) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.arrival = arrival;
    }
    @Override
    public String toString() {
        return "From: " + from + ", To:" + to + ", departure date:" + date + ", leave:" + time + ", arrive:" + arrival;
    }

}

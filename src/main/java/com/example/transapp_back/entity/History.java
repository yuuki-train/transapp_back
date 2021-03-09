package com.example.transapp_back.entity;

public class History {
    //フィールド
    private int id;

    private int year;

    private int month;

    private int aDay;

    private String day;

    private String line;

    private String departure;

    private String depHour;

    private String depMinute;

    private String destination;

    private String arvHour;

    private String arvMinute;

    private int totalMinutes;

    private String trainType;

    private int totalCharge;

    private int fair;

    private int fee;

    private int changeTrain;

    //getter
    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getADay() {
        return aDay;
    }

    public String getDay() { return day; }

    public String getLine() {
        return line;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDepHour() {
        return depHour;
    }

    public String getDepMinute() {
        return depMinute;
    }

    public String getDestination() {
        return destination;
    }

    public String getArvHour() {
        return arvHour;
    }

    public String getArvMinute() {
        return arvMinute;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public String getTrainType() {
        return trainType;
    }

    public int getTotalCharge() {
        return totalCharge;
    }

    public int getFair() {
        return fair;
    }

    public int getFee() {
        return fee;
    }

    public int getChangeTrain() {
        return changeTrain;
    }


    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setADay(int aDay) {
        this.aDay = aDay;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDepHour(String depHour) {
        this.depHour = depHour;
    }

    public void setDepMinute(String depMinute) {
        this.depMinute = depMinute;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setArvHour(String arvHour) {
        this.arvHour = arvHour;
    }

    public void setArvMinute(String arvMinute) {
        this.arvMinute = arvMinute;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public void setTotalCharge(int totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setFair(int fair) {
        this.fair = fair;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setChangeTrain(int changeTrain) {
        this.changeTrain = changeTrain;
    }
}

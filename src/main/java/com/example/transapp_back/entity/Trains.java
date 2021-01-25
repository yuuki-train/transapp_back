package com.example.transapp_back.entity;

public class Trains {
    //フィールド
    private String id;

    private String line;

    private String departure;

    private String depHour;

    private String depMinute;

    private int depTime;

    private String destination;

    private String arvHour;

    private String arvMinute;

    private int arvTime;

    private int totalMinutes;

    private String trainType;

    private int totalCharge;

    private int fair;

    private int fee;

    private int changeTrain;

    public Trains() {
    }

    //getter
    public String getId() {
        return id;
    }

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

    public int getDepTime() {
        return depTime;
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

    public int getArvTime() {
        return arvTime;
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
    public void setId(String id) {
        this.id = id;
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

    public void setDepTime(int depTime) {
        this.depTime = depTime;
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

    public void setArvTime(int arvTime) {
        this.arvTime = arvTime;
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

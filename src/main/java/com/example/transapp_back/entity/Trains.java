package com.example.transapp_back.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trains")
public class Trains {
    //フィールド
    @Id
    private String id;

    private String line;

    private String departure;

    private int depHour;

    private int depMinute;

    private String destination;

    private int arvHour;

    private int arvMinute;

    private String trainType;

    private int fee;

    private int change;




    //getter
    public String getId(){
        return id;
    }

    public String getLine(){
        return line;
    }

    public String getDeparture(){
        return departure;
    }

    public int getDepHour(){
        return depHour;
    }

    public int getDepMinute(){
        return depMinute;
    }

    public String getDestination(){
        return destination;
    }

    public int getArvHour(){
        return arvHour;
    }

    public int getArvMinute(){
        return arvMinute;
    }

    public String getTrainType(){
        return trainType;
    }

    public int getFee(){
        return fee;
    }

    public int getChange(){return change;}


    //setter
    public void setId(String id){
        this.id = id;
    }

    public void setLine(String line) { this.line = line; }

    public void setDeparture(String departure){
        this.departure = departure;
    }

    public void setDepHour(int depHour){
        this.depHour = depHour;
    }

    public void setDepMinute(int depMinute){
        this.depMinute = depMinute;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public void setArvHour(int arvHour){
        this.arvHour = arvHour;
    }

    public void setArvMinute(int arvMinute){
        this.arvMinute = arvMinute;
    }

    public void setTrainType(String trainType){
        this.trainType = trainType;
    }

    public void setFee(int fee){
        this.fee = fee;
    }

    public void setChange(int change){ this.change = change; }
}

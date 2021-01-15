package com.example.transapp_back.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class SearchDAO {
    public List<Document> timeGet() {


        ConnectionString connection = new ConnectionString(
                "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/diagram?retryWrites=true&w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connection)
                .retryWrites(true)
                .build();

        MongoClient client = MongoClients.create(settings);
        MongoDatabase database = client.getDatabase("diagram");
        MongoCollection<Document> trainTimes = database.getCollection("trainTimes");

        List<Document> times = trainTimes.find().into(new ArrayList<Document>());

        client.close();

        return times;
    }

    public List<Document> trainGet(List<String> lines, List<List<Integer>> searchTime) {


        ConnectionString connection = new ConnectionString(
                "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/diagram?retryWrites=true&w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connection)
                .retryWrites(true)
                .build();

        MongoClient client = MongoClients.create(settings);
        MongoDatabase database = client.getDatabase("diagram");
        MongoCollection<Document> trainTimes = database.getCollection("trains");







        List<Document> times = trainTimes.find().into(new ArrayList<Document>());

        client.close();

        return times;
    }






        /*List<Document> times = trainTimes.find(Filters.eq("_id", line)).into(new ArrayList<Document>());
        int arrayLength = times.get(1).getInteger("arrayLength");
        String depTime [] = new String[arrayLength];
        String arvTime [] = new String[arrayLength];
        depTime = times.get(2).;

        List<Document> documents = trains.find(Filters.and(Filters.eq(key, line),Filters.eq("addFee", addFeeUse))).into(new ArrayList<Document>());
        */


}

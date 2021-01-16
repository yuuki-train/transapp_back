package com.example.transapp_back.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class SearchDAO {
    public List<Document> getTimes() {


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

        List<Document> times = trainTimes.find().into(new ArrayList<>());

        client.close();

        return times;
    }


    public List<Document> getTrains(List<String> lines, List<List<String>> searchTime, int theNumberOfSearch) {


        ConnectionString connection = new ConnectionString(
                "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/diagram?retryWrites=true&w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connection)
                .retryWrites(true)
                .build();

        MongoClient client = MongoClients.create(settings);
        MongoDatabase database = client.getDatabase("diagram");
        MongoCollection<Document> trains = database.getCollection("trains");

        List<Document> trainLists = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++){
            for(int j = 0; j < theNumberOfSearch; j++){
                String trainId = searchTime.get(i).get(j);
                Document train = trains.find(Filters.eq("_id", trainId)).first();
                trainLists.add(train);
            }
        }

        client.close();

        return trainLists;
    }

}

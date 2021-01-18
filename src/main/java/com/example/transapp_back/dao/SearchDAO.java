package com.example.transapp_back.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class SearchDAO {
    public List<String> getTimes(List<String> lines) throws JsonProcessingException {


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

        List<String> times = new ArrayList<>();

        for (String line : lines) {
            String key = "_id";
            Document query = new Document(key, line);
            FindIterable<Document> iterator = trainTimes.find(query);
            MongoCursor<Document> cursor = iterator.iterator();
            Object object = cursor.next();
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(object);
            times.add(json);
        }

        client.close();

        return times;
    }


    public List<String> getTrains(List<String> lines, List<List<String>> searchTime, int theNumberOfSearch)
            throws JsonProcessingException {


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

        List<String> trainLists = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++){
            for(int j = 0; j < theNumberOfSearch; j++){
                String trainId = searchTime.get(i).get(j);
                String key = "_id";
                Document query = new Document(key, trainId);
                FindIterable<Document> iterator = trains.find(query);
                MongoCursor<Document> cursor = iterator.iterator();
                Object object = cursor.next();
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(object);
                trainLists.add(json);
            }
        }

        client.close();

        return trainLists;
    }

}

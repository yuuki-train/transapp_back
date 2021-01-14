package com.example.transapp_back.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class SearchDAO {
    public List<Document> dbGet(String key, String param, String depHour, String depMinute, boolean addFeeUse, boolean reverse) {

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

        List<Document> documents = trains.find(Filters.and(Filters.eq(key, param),Filters.eq("addFee", addFeeUse))).into(new ArrayList<Document>());

        client.close();

        return documents;
    }

}

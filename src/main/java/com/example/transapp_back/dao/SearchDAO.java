package com.example.transapp_back.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import com.mongodb.client.model.Filters;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;



public class SearchDAO {
    public List<Document> getTrains(
            List<String> lines, String hour, String minute, boolean addFeeTrain, int theNumberOfSearch
    ){

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

        //時分を合わせた3~4桁の数字をint型に変換する
        String strSearchTime = hour + minute;
        int searchTime = Integer.parseInt(strSearchTime);

        Document queryDetail;
        Document query;

            queryDetail = new Document("$gte",searchTime);
            query = new Document("depTime", queryDetail);


           for (String line : lines) {
               FindIterable<Document> iterator;

               if (addFeeTrain) {
                   iterator = trains.find(query).limit(theNumberOfSearch)
                           .filter(Filters.eq("lineE", line));
               } else {
                   iterator = trains.find(query).limit(theNumberOfSearch)
                           .filter(Filters.and
                                   (Filters.eq("lineE", line), Filters.eq("addFee", false)));
               }

               for (Document doc : iterator) {
                   trainLists.add(doc);
               }
           }
        client.close();

        return trainLists;
    }

}

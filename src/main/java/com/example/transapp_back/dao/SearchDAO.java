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
            List<String> lines, String hour, String minute, String depOrArv, boolean addFeeTrain, int theNumberOfSearch
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

        String value1;
        String value2 = "lineJ";
        String value3 = "addFee";
        Document sort = new Document("arvTime",-1);

        for (String line : lines) {

            FindIterable<Document> iterator;

            if(depOrArv.equals("depart")) {
                value1 = "depTime";

                if (addFeeTrain) {
                    iterator = trains.find(Filters.and(Filters.gte(value1, searchTime),Filters.eq(value2, line)
                    )).limit(theNumberOfSearch);
                } else {
                    iterator = trains.find(Filters.and(Filters.and(
                            Filters.gte(value1, searchTime), Filters.eq(value2, line)), Filters.eq(value3, addFeeTrain)
                    )).limit(theNumberOfSearch);
                }

            }else{
                value1 = "arvTime";

                if (addFeeTrain) {
                    iterator = trains.find().filter(Filters.and(
                            Filters.lte(value1, searchTime), Filters.eq(value2, line)
                    )).limit(theNumberOfSearch).sort(sort);
                } else {
                    iterator = trains.find(Filters.and(Filters.and(
                            Filters.lte(value1, searchTime), Filters.eq(value2, line)),Filters.eq(value3, addFeeTrain)
                    )).limit(theNumberOfSearch).sort(sort);
                }
            }

            for (Document doc : iterator) {
                trainLists.add(doc);
            }
        }
        client.close();

        return trainLists;
    }

}

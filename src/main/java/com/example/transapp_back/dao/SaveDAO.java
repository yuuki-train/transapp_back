package com.example.transapp_back.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
public class SaveDAO {
    public String saveData(List<String> jsonData) {
        try {
            ConnectionString connection = new ConnectionString(
                    "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/transapp?retryWrites=true&w=majority"
            );
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connection)
                    .retryWrites(true)
                    .build();

            MongoClient client = MongoClients.create(settings);
            MongoDatabase database = client.getDatabase("transapp");
            MongoCollection<Document> save = database.getCollection("savedata");

            //Document doc = Document.parse(jsonData);
            //save.insertOne(doc);
            return "{\"result\":\"OK\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"result\":\"ERROR\"}";
        }
    }
}

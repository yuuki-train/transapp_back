package com.example.transapp_back.dao;

import com.example.transapp_back.entity.Train;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
public class SaveDAO {

    public long checkRecords() {
        ConnectionString connection = new ConnectionString(
                "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/transapp?retryWrites=true&w=majority"
            );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connection)
                .retryWrites(true)
                .build();

        MongoClient client = MongoClients.create(settings);
        MongoDatabase database = client.getDatabase("transapp");
        MongoCollection<Document> saveData = database.getCollection("savedata");
        long records = saveData.countDocuments();
        client.close();
        return records;
    }

    public String save(Document data) {
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
            MongoCollection<Document> saveData = database.getCollection("savedata");

            saveData.insertOne(data);
            client.close();

            return "[{\"result\":\"OK\"}]";

        } catch (Exception e) {
            e.printStackTrace();
            return "[{\"result\":\"ERROR\"}]";
        }
    }

    public List<Document> getHistory(int year, int month, String sort) {
            ConnectionString connection = new ConnectionString(
                    "mongodb+srv://yuuki:yuukidb@cluster0.wdfqa.mongodb.net/transapp?retryWrites=true&w=majority"
            );
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connection)
                    .retryWrites(true)
                    .build();

            MongoClient client = MongoClients.create(settings);
            MongoDatabase database = client.getDatabase("transapp");
            MongoCollection<Document> saveData = database.getCollection("savedata");

            FindIterable<Document> result;

            Document asc = new Document("aDay",1);
            result = saveData.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month))).sort(asc);

            List<Document> historyLists = new ArrayList<>();

            for (Document doc : result) {
                historyLists.add(doc);
            }
            client.close();

            return historyLists;

    }


    //取得したデータをTrainsクラスに格納するメソッド
    public List<Train> setTrainClass(List<Document> trains) {
        List<Train> trainList = new ArrayList<>();

        for (Document train : trains) {
            String id = train.getString("_id");
            String line = train.getString("lineJ");
            String departure = train.getString("departure");
            String depHour = train.getString("depHour");
            String depMinute = train.getString("depMinute");
            int depTime = train.getInteger("depTime");
            String destination = train.getString("destination");
            String arvHour = train.getString("arvHour");
            String arvMinute = train.getString("arvMinute");
            int arvTime = train.getInteger("arvTime");
            int totalMinutes = train.getInteger("totalMinutes");
            String trainType = train.getString("trainType");
            int fair = train.getInteger("fair");
            int fee = train.getInteger("fee");
            int changeTrain = train.getInteger("changeTrain");
            Train elements = new Train();

            int totalCharge = fair + fee;


            elements.setId(id);
            elements.setLine(line);
            elements.setDeparture(departure);
            elements.setDepHour(depHour);
            elements.setDepMinute(depMinute);
            elements.setDepTime(depTime);
            elements.setDestination(destination);
            elements.setArvHour(arvHour);
            elements.setArvMinute(arvMinute);
            elements.setArvTime(arvTime);
            elements.setTotalMinutes(totalMinutes);
            elements.setTrainType(trainType);
            elements.setTotalCharge(totalCharge);
            elements.setFair(fair);
            elements.setFee(fee);
            elements.setChangeTrain(changeTrain);

            trainList.add(elements);
        }
        return trainList;
    }











}

package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SaveDAO;
import com.example.transapp_back.entity.History;
import com.example.transapp_back.entity.Train;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
public class SaveLogic {

    public Document changeFormat(String[] arrayData){
        //idを生成する
        long id = makeId();
        //Listを作成し、整理する
        List<String> dataList = makeDataList(arrayData);
        //json形式のデータを作成する
        String jsonData = makeJsonData(dataList, id);
        //Document形式に変換して返す
        return changeDocument(jsonData);
    }

    public Document changeDocument(String jsonData){
        return Document.parse(jsonData);
    }

    public long makeId(){
        long records = new SaveDAO().checkRecords();
        return records + 1;
    }

    public List<String> makeDataList(String[] arrayData) {

        List<String> dataList = new ArrayList<>(Arrays.asList(arrayData));

        dataList.remove(4);
        dataList.remove(8);
        dataList.remove(11);

        return dataList;
    }

    public String makeJsonData(List<String>dataList, long id){
        List<String> keysList = new ArrayList<>(Arrays.asList("id", "year", "month", "aDay", "day", "line",
                "departure", "depHour", "depMinute", "destination", "arvHour", "arvMinute",
                "totalMinutes", "trainType" ,"totalCharge", "fair", "fee", "changeTrain"));

        StringBuilder jsonData = new StringBuilder("{");

        //idの追加
        String strId = String.valueOf(id);
        jsonData.append("\"").append(keysList.get(0)).append("\": ").append(strId);

        //date以降の要素追加
        for(int i = 0; i < dataList.size(); i++){
            String key = keysList.get(i+1);
            String data = dataList.get(i);
            if(key.equals("year") || key.equals("month")|| key.equals("aDay") ||
                    key.equals("totalMinutes") || key.equals("totalCharge")|| key.equals("fair")
                    || key.equals("fee") || key.equals("changeTrain")){
                jsonData.append(", \"").append(key).append("\": ").append(data);
            }else{
                jsonData.append(", \"").append(key).append("\": \"").append(data).append("\"");
            }
        }

        jsonData.append("}");

        return jsonData.toString();
    }

    //取得したデータをTrainsクラスに格納するメソッド
    public List<History> setHistoryClass(List<Document> historiesFromDB) {
        List<History> historiesList = new ArrayList<>();
        for (Document historyDocument : historiesFromDB) {
            History history = new History();
            history.setId(historyDocument.getInteger("id"));
            history.setYear(historyDocument.getInteger("year"));
            history.setMonth(historyDocument.getInteger("month"));
            history.setADay(historyDocument.getInteger("aDay"));
            history.setDay(historyDocument.getString("day"));
            history.setLine(historyDocument.getString("line"));
            history.setDeparture(historyDocument.getString("departure"));
            history.setDepHour(historyDocument.getString("depHour"));
            history.setDepMinute(historyDocument.getString("depMinute"));
            history.setDestination(historyDocument.getString("destination"));
            history.setArvHour(historyDocument.getString("arvHour"));
            history.setArvMinute(historyDocument.getString("arvMinute"));
            history.setTotalMinutes(historyDocument.getInteger("totalMinutes"));
            history.setTrainType(historyDocument.getString("trainType"));
            history.setTotalCharge(historyDocument.getInteger("totalCharge"));
            history.setFair(historyDocument.getInteger("fair"));
            history.setFee(historyDocument.getInteger("fee"));
            history.setChangeTrain(historyDocument.getInteger("changeTrain"));
            historiesList.add(history);
        }
        return historiesList;
    }
    //検索データを整理し選択するメソッド
    public List<History> sortHistoryList(List<History> historiesList, String sortWith) {

        List<History> sortedHistoriesList;

        if (sortWith.contains("date")) {
            if (sortWith.contains("Desc")) {
                Collections.reverse(historiesList);
            }
            sortedHistoriesList = historiesList;
        } else {
            if (sortWith.contains("Desc")) {
                sortedHistoriesList = historiesList.stream().sorted((train1, train2) -> {
                    if (train1.getTotalCharge() != train2.getTotalCharge()) {
                        return train1.getTotalCharge() < train2.getTotalCharge() ? 1 : -1;
                    } else {
                        return 0;
                    }
                }).collect(Collectors.toList());
            } else {
                sortedHistoriesList = historiesList.stream().sorted((train1, train2) -> {
                    if (train1.getTotalCharge() != train2.getTotalCharge()) {
                        return train1.getTotalCharge() < train2.getTotalCharge() ? -1 : 1;
                    } else {
                        return 0;
                    }
                }).collect(Collectors.toList());
            }
        }
        return sortedHistoriesList;
    }


    public String changeJson(List<History> sortedHistoriesList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(sortedHistoriesList);
    }
}

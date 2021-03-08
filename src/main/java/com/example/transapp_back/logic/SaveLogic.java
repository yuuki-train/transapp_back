package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SaveDAO;
import com.example.transapp_back.entity.Train;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;
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


    //検索データを整理し選択するメソッド
    public List<Train> sortTrains(List<Train> trains, String depOrArv, String priority) {

        String faster = "faster";
        String cheaper = "cheaper";
        List<Train> sortList;

        sortList = trains.stream().sorted((train1, train2) -> {
            if (priority.equals(faster)) {

                if (train1.getTotalMinutes() != train2.getTotalMinutes()) {
                    return train1.getTotalMinutes() < train2.getTotalMinutes() ? -1 : 1;
                }else {
                    return new SearchLogic().timeSort(train1, train2, depOrArv);
                }

            }else if(priority.equals(cheaper)) {
                if (train1.getTotalCharge() != train2.getTotalCharge()) {
                    return train1.getTotalCharge() < train2.getTotalCharge() ? -1 : 1;
                } else {
                    return new SearchLogic().timeSort(train1, train2, depOrArv);
                }
            }else{
                if (train1.getChangeTrain() != train2.getChangeTrain()) {
                    return train1.getChangeTrain() < train2.getChangeTrain() ? -1 : 1;
                } else {
                    return new SearchLogic().timeSort(train1, train2, depOrArv);
                }
            }
        }).collect(Collectors.toList());

        return sortList;
    }

    public Document changeDocument(String jsonData){
        return Document.parse(jsonData);
    }

}

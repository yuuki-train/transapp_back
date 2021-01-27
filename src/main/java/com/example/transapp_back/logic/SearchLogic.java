package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import com.example.transapp_back.entity.Trains;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchLogic {
    //検索する路線を絞り込むメソッド
    public List<String> checkLines(String departure, String destination) {
        //候補路線とその駅名の取得
        ArrayList<String> CandidateLines = new Lines().getCandidateLines();
        ArrayList<ArrayList<String>> Stations = new Lines().getStations();
        List<String> lines = new ArrayList<>();
        int counter = 0;

        //候補路線から検索路線を絞り込む
        for (int i = 0; i < CandidateLines.size(); i++) {
            ArrayList<String> station = Stations.get(i);
            boolean depStation = false;
            boolean arvStation = false;
            //候補路線の駅名とリクエストパラメータ値を比較する。一致すればtrueに。
            for (String j : station) {
                if (departure.equals(j)) {
                    depStation = true;
                }

                if (destination.equals(j)) {
                    arvStation = true;
                }
            }
            //出発駅と到着駅の両方がtrueなら、検索路線に登録。
            if (depStation && arvStation) {
                lines.add(counter, CandidateLines.get(i));
                counter++;
            }

        }
        return lines;
    }

    //取得したデータをTrainsクラスに格納するメソッド
    public List<Trains> setTrainsClass(List<Document> trains) {
        List<Trains> trainsList = new ArrayList<>();

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
            Trains elements = new Trains();

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

            trainsList.add(elements);
        }
        return trainsList;
    }

    //検索データを整理し選択するメソッド
    public List<Trains> sortTrains(List<Trains> trains, String depOrArv, String priority, int theNumberOfSearch) {

        String faster = "faster";
        String cheaper = "cheaper";
        List<Trains> sortList;

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

        List<Trains> results = new ArrayList<>();

        for (int i = 0; i < theNumberOfSearch; i++) {
            try {
                Trains train = sortList.get(i);
                results.add(train);
            }catch(IndexOutOfBoundsException e){
                break;
            }
        }
        return results;
    }

    //指定した時間順にソートするメソッド
    public int timeSort(Trains train1, Trains train2, String depOrArv) {

        if (depOrArv.equals("depart")) {
            if (train1.getArvTime() != train2.getArvTime()) {
                return train1.getArvTime() < train2.getArvTime() ? -1 : 1;
            } else {
                return 0;
            }
        } else {
            if (train1.getDepTime() != train2.getDepTime()) {
                return train1.getDepTime() < train2.getDepTime() ? 1 : -1;
            } else {
                return 0;
            }
        }
    }
}
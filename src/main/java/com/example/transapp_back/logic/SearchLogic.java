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

    //検索データを取得するメソッド
    public List<Document> searchTrains(
            List<String> lines, String hour, String minute, String depOrArv, String[] addFeeTrain, int theNumberOfSearch
    ) {

        boolean useAddFeeTrain = false;

        if (addFeeTrain[0].equals("use")) {
            useAddFeeTrain = true;
        }
        //列車データを取得する。
        return new SearchDAO().getTrains(lines, hour, minute, depOrArv, useAddFeeTrain, theNumberOfSearch);
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
    public List<Trains> sortTrains(List<Trains> trains, String depOrArv, String[] priority, int theNumberOfSearch) {

        String faster = "faster";
        String cheaper = "cheaper";
        List<Trains> timeList;
        List<Trains> sortList;

        if (priority[0].equals(faster)) {
            sortList = trains.stream().sorted((o1, o2) -> {
                if (o1.getArvTime() != o2.getArvTime()) {
                    if (depOrArv.equals("depart")) {
                        return o1.getArvTime() < o2.getArvTime() ? -1 : 1;
                    } else {
                        return o1.getDepTime() < o2.getDepTime() ? 1 : -1;
                    }
                } else {
                    if (o1.getTotalMinutes() != o2.getTotalMinutes()) {
                        return o1.getTotalMinutes() <= o2.getTotalMinutes() ? -1 : 1;
                    } else {
                        return 0;
                    }
                }
            }).collect(Collectors.toList());

        } else {
            //TODO:ロジックの必要性を見直す。
            timeList = trains.stream().sorted((o1, o2) -> {
                if (o1.getArvTime() != o2.getArvTime()) {
                    if (depOrArv.equals("depart")) {
                        return o1.getArvTime() < o2.getArvTime() ? -1 : 1;
                    } else {
                        return o1.getDepTime() < o2.getDepTime() ? 1 : -1;
                    }
                } else {
                    return 0;
                }
            }).collect(Collectors.toList());

            sortList = timeList.stream().sorted((p1, p2) -> {
                if (priority[0].equals(cheaper)) {
                    int total1 = p1.getFair() + p1.getFee();
                    int total2 = p2.getFair() + p2.getFee();
                    if (total1 != total2) {
                        return total1 < total2 ? -1 : 1;
                    } else {
                        return 0;
                    }
                } else {
                    if (p1.getChangeTrain() != p2.getChangeTrain()) {
                        return p1.getChangeTrain() < p2.getChangeTrain() ? -1 : 1;
                    } else {
                        return 0;
                    }
                }
            }).collect(Collectors.toList());
        }

        List<Trains> results = new ArrayList<>();

        for (int i = 0; i < theNumberOfSearch; i++) {
            Trains train = sortList.get(i);
            results.add(train);
        }
        return results;
    }
}
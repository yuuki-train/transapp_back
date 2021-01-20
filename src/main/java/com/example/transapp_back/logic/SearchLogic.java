package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import com.example.transapp_back.entity.Trains;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchLogic {
    //検索する路線を絞り込むメソッド
    public List<String> checkLines(String departure, String destination){
        //候補路線とその駅名の取得
        ArrayList<String> CandidateLines = new Lines().getCandidateLines();
       ArrayList<ArrayList<String>> Stations = new Lines().getStations();
        List<String> lines = new ArrayList<>();
        int counter = 0;

        //候補路線から検索路線を絞り込む
        for(int i = 0; i<CandidateLines.size(); i++){
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
            if(depStation && arvStation){
                lines.add(counter,CandidateLines.get(i));
                counter++;
            }

        }
        return lines;
    }

    //検索データを取得するメソッド
    public List<Document> searchTrains(
            List<String> lines, String hour, String minute, String depOrArv, String[] addFeeTrain, int theNumberOfSearch
    ){

        boolean useAddFeeTrain = false;

        if(addFeeTrain[0].equals("use")){
            useAddFeeTrain = true;
        }
        //列車データを取得する。
        return new SearchDAO().getTrains(lines, hour, minute, depOrArv, useAddFeeTrain, theNumberOfSearch);
    }

    //取得したデータをTrainsクラスに格納するメソッド
    public List<Trains> setTrainsClass(List<Document> trains){
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
            elements.setFair(fair);
            elements.setFee(fee);
            elements.setChangeTrain(changeTrain);

            trainsList.add(elements);
        }
        return trainsList;
    }


    //検索データを整理し選択するメソッド
    public List<String> sortTrains(List<Trains> trains, String depOrArv ,String priority, int theNumberOfSearch) throws JsonProcessingException {


        List<Trains> timeSort = trains.stream().sorted(new Comparator<Trains>() {
            @Override
            public int compare(Trains o1, Trains o2) {
                if(depOrArv.equals("depart")){
                    return o1.getArvTime() <= o2.getArvTime() ? -1 : 1;
                }else{
                    return o1.getDepTime() <= o2.getDepTime() ? 1 : -1;
                }
            }
        }).collect(Collectors.toList());

        String faster = "速さ優先";
        String cheaper = "安さ優先";

        List<Trains> priorSort = timeSort.stream().sorted(new Comparator<Trains>() {
            @Override
            public int compare(Trains o1, Trains o2) {
                if(priority.equals(faster)){
                    return o1.getTotalMinutes() < o2.getTotalMinutes() ? -1 : 1;
                }else if(priority.equals(cheaper)){
                    return (o1.getFair() + o1.getFee()) < (o2.getFair() + o2.getFee()) ? -1 : 1;
                }else{
                    return o1.getChangeTrain() < o2.getChangeTrain() ? -1 : 1;
                }
            }
        }).collect(Collectors.toList());

        List<String> results = new ArrayList<>();

        for(int i= 0; i < theNumberOfSearch; i++){
           Trains train = priorSort.get(0);
           ObjectMapper mapper = new ObjectMapper();
           String json = mapper.writeValueAsString(train);
           results.add(json);
        }

        return results;
    }



}

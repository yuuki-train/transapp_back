package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> searchTrains(
            List<String> lines, String hour, String minute, String depOrArv, int theNumberOfSearch
    ) throws JsonProcessingException {
         //時刻データを取得する。
         List<String> times = new SearchDAO().getTimes(lines);

         //必要な列車を絞り込み、列車データidを取得する。
         List<List<String>> searchTimes = new TimeLogic().selectTime(lines, times, hour, minute, depOrArv, theNumberOfSearch);

         //列車データを取得する。
         return new SearchDAO().getTrains(lines, searchTimes, theNumberOfSearch);
    }

    //検索データを整理し選択するメソッド
    public List<String> selectTrains(List<String> trains, String priority, String[] addFeeTrain, int theNumberOfSearch){
        String faster = "速さ優先";
        String cheaper = "安さ優先";
        String changeTrain = "乗換の少なさ優先";
        boolean useAddFeeTrain = false;

        if(addFeeTrain[0] == "use"){
            useAddFeeTrain = true;
        }


        if (priority == faster){
            List<String> results = new SortLogic().sortFaster(trains, useAddFeeTrain,theNumberOfSearch );
        }




        return results;
    }



}

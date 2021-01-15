package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SearchLogic {
    //検索する路線を絞り込むメソッド
    public List<String> LineCheck(String departure, String destination){
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
     public List<Document> TrainSearch(
            List<String> lines, String hour, String minute, String depOrArv, String addFeeTrain
    ) {
         List<Document> times = new SearchDAO().timeGet();

         if (depOrArv.equals("depart")) {
             List<List<Integer>> searchTime = new TimeLogic().departTime(lines, times, hour, minute);
         }else{
             List<List<Integer>> searchTime = new TimeLogic().arriveTime(lines, times, hour, minute);
         }

         List<Document> trains = new SearchDAO().trainGet(lines, searchTime);



        boolean addFeeUse = false;
        boolean reverse = false;
        // リクエストパラメータ値のチェック
        if (addFeeTrain == "use"){
            addFeeUse = true;
        }

        if (depOrArv =="arrive"){
            reverse = true;
        }



        for(int i = 0; i < lines.size(); i++){
            String key = "lineE";
            String line = lines.get(i);
            for(int j = 0; j < trainData.size(); j++){
                String x = trainData.get(j).getString(key);

            }


        }



    }*/

}

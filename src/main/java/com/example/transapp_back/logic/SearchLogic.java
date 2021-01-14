package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SearchLogic {
    //検索する路線を絞り込むメソッド
    public ArrayList<String> LineCheck(String departure, String destination){
        //候補路線とその駅名の取得
        ArrayList<String> CandidateLines = new Lines().getCandidateLines();
       ArrayList<ArrayList<String>> Stations = new Lines().getStations();
        ArrayList<String> lines = new ArrayList<String>();
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
            ArrayList<String> lines, String hour, String minute, String depOrArv, String addFeeTrain
    ){
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
            List<Document> trainData = new SearchDAO().dbGet(key, line, hour, minute, addFeeUse, reverse);
            for(int j = 0; j < trainData.size(); j++){
                String x = trainData.get(j).getString(key);
            }


        }



    }

}

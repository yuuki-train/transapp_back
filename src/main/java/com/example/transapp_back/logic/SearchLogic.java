package com.example.transapp_back.logic;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Lines;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SearchLogic {
    public ArrayList<String> LineCheck(String departure, String destination){

        ArrayList<String> CandidateLines = new Lines().getCandidateLines();
       ArrayList<ArrayList<String>> Stations = new Lines().getStations();
        ArrayList<String> lines = new ArrayList<String>();
        int counter = 0;

        for(int i = 0; i<CandidateLines.size(); i++){
            ArrayList<String> station = Stations.get(i);
            boolean depStation = false;
            boolean arvStation = false;

            for (String j : station) {
                if (departure.equals(j)) {
                    depStation = true;
                }

                if (destination.equals(j)) {
                    arvStation = true;
                }
            }

            if(depStation && arvStation){
                lines.add(counter,CandidateLines.get(i));
                counter++;
            }

        }
        return lines;
    }

    public List<Document> TrainSearch(ArrayList<String> lines, String depHour, String depMinute){
        for(int i = 0; i < lines.size(); i++){
            String key = "lineE";
            String line = lines.get(i);
            List<Document> trainData = new SearchDAO().dbGet(key,line);
            for(int j = 0; j < trainData.size(); j++){
                String x = trainData.get(j).getString(key);
            }


        }



    }

}

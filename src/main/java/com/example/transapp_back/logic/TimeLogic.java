package com.example.transapp_back.logic;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TimeLogic {
    public List<List<Integer>> departTime(
            List<String> lines, List<Document> times, String hour, String minute
    ){
        String strSearchTime = hour + minute;
        int searchTime = Integer.parseInt(strSearchTime);

        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++){

            List<Integer> lineResults = new ArrayList<>();

            int total = (times.get(i).getInteger("total"));

            int counter = 3;

            for(int j = 1; j <= total; j++){
                if (counter <= 0){
                    break;
                }
                String keyNum = String.valueOf(j);
                String keyStr = "depTime" + keyNum;
                int time = times.get(i).getInteger(keyStr);
                if(searchTime <= time) {
                    lineResults.add(time);
                    counter--;
                }
            }
            results.add(lineResults);
        }

        return results;
    }

    public List<List<Integer>> arriveTime(
            List<String> lines, List<Document> times, String hour, String minute
    ){
        String strSearchTime = hour + minute;
        int searchTime = Integer.parseInt(strSearchTime);

        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++){

            List<Integer> lineResults = new ArrayList<>();

            int total = (times.get(i).getInteger("total"));

            int counter = 3;

            for(int j = total; j <= 0; j--){
                if (counter <= 0){
                    break;
                }
                String keyNum = String.valueOf(j);
                String keyStr = "arvTime" + keyNum;
                int time = times.get(i).getInteger(keyStr);
                if(searchTime >= time) {
                    lineResults.add(time);
                    counter--;
                }
            }
            results.add(lineResults);
        }

        return results;
    }
}

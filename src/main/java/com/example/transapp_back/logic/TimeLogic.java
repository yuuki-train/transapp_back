package com.example.transapp_back.logic;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TimeLogic {
    public List<List<String>> selectTime(
            List<String> lines, List<Document> times, String hour, String minute, String depOrArv, int theNumberOfSearch
    ) {
        String strSearchTime = hour + minute;
        int searchTime = Integer.parseInt(strSearchTime);

        List<List<String>> results = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            List<String> lineResults = new ArrayList<>();

            String shortName = (times.get(i).getString("short"));
            int total = (times.get(i).getInteger("total"));

            if (depOrArv.equals("depart")) {

                for (int j = 1; j <= total; j++) {
                    if (theNumberOfSearch <= 0) {
                        break;
                    }
                    String keyNum = String.valueOf(j);
                    String keyStr = "depTime" + keyNum;
                    int time = times.get(i).getInteger(keyStr);
                    if (searchTime <= time) {
                        String strTime = String.valueOf(time);
                        String id = shortName + strTime;
                        lineResults.add(id);
                        theNumberOfSearch--;
                    }
                }
            } else {
                for (int j = total; j > 0; j--) {
                    if (theNumberOfSearch <= 0) {
                        break;
                    }
                    String keyNum = String.valueOf(j);
                    String keyStr = "arvTime" + keyNum;
                    int time = times.get(i).getInteger(keyStr);
                    if (searchTime >= time) {
                        String strTime = String.valueOf(time);
                        String id = shortName + strTime;
                        lineResults.add(id);
                        theNumberOfSearch--;
                    }
                }
            }
            results.add(lineResults);
        }
        return results;
    }
}
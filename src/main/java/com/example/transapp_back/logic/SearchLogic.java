package com.example.transapp_back.logic;

import com.example.transapp_back.entity.Lines;

public class SearchLogic {
    public String[] LineCheck(String departure, String destination){

        String[] CandidateLines = new Lines().getCandidateLines();
        String[][] Stations = new Lines().getStations();
        String[] Lines = new String[CandidateLines.length];
        int counter = 0;

        for(int i = 0; i<CandidateLines.length; i++){
            String[] station = Stations[i];
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
                Lines[counter] = CandidateLines[i];
                counter++;
            }

        }
        return Lines;
    }
}

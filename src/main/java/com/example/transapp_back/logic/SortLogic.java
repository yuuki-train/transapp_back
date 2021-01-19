package com.example.transapp_back.logic;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SortLogic {
    public List<String> sortFaster(List<String>trains, boolean AddFeeTrain, int theNumberOfTrain){
        List<String> results = new ArrayList<>();

        if(AddFeeTrain){
            for(int i = 0; i < theNumberOfTrain; i++){
                String train = trains.get(i);
                if (train != null){
                    results.add(train);
                }else{
                    break;
                }
            }

            int lines = 2;
            for(int j = theNumberOfTrain * (lines-1); j < theNumberOfTrain * lines; j++) {
                for (int k = 0; k < theNumberOfTrain; k++) {
                    String addTrain = trains.get(j);
                    JSONObject addTrainJson = new JSONObject(addTrain);
                    int addTrainArv = (addTrainJson.getInt("arvTime"));

                    String resultsTrain = results.get(k);
                    JSONObject resultsTrainJson = new JSONObject(resultsTrain);
                    int resultsTrainArv = (resultsTrainJson.getInt("arvTime"));

                    if (addTrainArv < resultsTrainArv) {

                    } else if (addTrainArv == resultsTrainArv) {

                    } else {

                    }


                }
            }



        }else{




        }



    }



}

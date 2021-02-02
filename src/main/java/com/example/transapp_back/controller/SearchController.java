package com.example.transapp_back.controller;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Train;
import com.example.transapp_back.logic.SearchLogic;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @RequestMapping(value = "/search", method = {RequestMethod.POST})
    public String search(@RequestParam Map<String, String> requestParams) {

        boolean addFeeTrainCheck;
        String result;
        try {
            String addFeeTrain = requestParams.get("addFeeTrain");
            addFeeTrainCheck = true;
        }catch(NullPointerException e){
            addFeeTrainCheck = false;
        }
        try{
            String departure = requestParams.get("departure");
            String destination = requestParams.get("destination");
            String hour = requestParams.get("hour");
            String minute = requestParams.get("minute");
            String depOrArv = requestParams.get("depOrArv");
            String priority = requestParams.get("priority");

            int theNumberOfSearch = 3;

            //入力した駅名に対応する路線を洗い出す
            List<String> lines = new SearchLogic().checkLines(departure, destination);

            //必要なデータを検索する
            List<Document> trains =  new SearchDAO().getTrains(
                    lines, hour, minute, depOrArv, addFeeTrainCheck, theNumberOfSearch);

            //Trainsクラスに格納する
            List<Train> trainList = new SearchLogic().setTrainClass(trains);

            //表示するデータを並び変えて選択する
            List<Train> sortList = new SearchLogic().sortTrains(trainList, depOrArv, priority);

            result = new SearchLogic().selectTrains(sortList, theNumberOfSearch);

        }catch(NullPointerException e){
            e.printStackTrace();
            result = "error";
        }

        System.out.println(result);
        return result;
    }

}

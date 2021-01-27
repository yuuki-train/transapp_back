package com.example.transapp_back.controller;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.entity.Trains;
import com.example.transapp_back.logic.SearchLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class SearchController {
    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public String search(@RequestParam Map<String,String> requestParams) throws Exception{

        boolean addFeeTrainCheck;
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

            System.out.println(departure);
            System.out.println(destination);
            System.out.println(hour);
            System.out.println(minute);

            int theNumberOfSearch = 3;

            //入力した駅名に対応する路線を洗い出す
            List<String> lines = new SearchLogic().checkLines(departure, destination);

            //必要なデータを検索する
            List<Document> trains =  new SearchDAO().getTrains(
                    lines, hour, minute, depOrArv, addFeeTrainCheck, theNumberOfSearch);

            //Trainsクラスに格納する
            List<Trains> trainsList = new SearchLogic().setTrainsClass(trains);

            //表示するデータを並び変えて選択する
            List<Trains> sortList = new SearchLogic().sortTrains(trainsList, depOrArv, priority, theNumberOfSearch);

            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(sortList);
        }catch(NullPointerException e){
            return "出発駅と到着駅を正しく指定してください";
        }

    }

}
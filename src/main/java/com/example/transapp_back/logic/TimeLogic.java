package com.example.transapp_back.logic;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimeLogic {
    public List<List<String>> selectTime(
            List<String> lines, List<String> times, String hour, String minute, String depOrArv, int theNumberOfSearch
    ) {
        //時分を合わせた3~4桁の数字をint型に変換する
        String strSearchTime = hour + minute;
        int searchTime = Integer.parseInt(strSearchTime);

        List<List<String>> results = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            List<String> lineResults = new ArrayList<>();
            //JSON形式のデータから路線IDの符号と合計列車数を取得する
            String jsonTimes = times.get(i);
            JSONObject jsonObject = new JSONObject(jsonTimes);
            String shortName = (jsonObject.getString("short"));
            int total = (jsonObject.getInt("total"));

            //出発時刻指定の場合
            if (depOrArv.equals("depart")) {
                //時刻が早い順に全列車数分繰り返し
                for (int j = 1; j <= total; j++) {
                    //検索件数分データを格納したら終了
                    if (theNumberOfSearch <= 0) {
                        break;
                    }
                    //キー値の作成
                    String keyNum = String.valueOf(j);
                    String keyStr = "depTime" + keyNum;
                    //時刻の取得
                    int time = jsonObject.getInt(keyStr);
                    //検索した時刻の方が早ければ、時刻を登録する。
                    if (searchTime <= time) {
                        String strTime = String.valueOf(time);
                        String id = shortName + strTime;
                        lineResults.add(id);
                        theNumberOfSearch--;
                    }
                }
                //到着時刻指定の場合
            } else {
                //時刻が遅い順に全列車数分繰り返し
                for (int j = total; j > 0; j--) {
                    if (theNumberOfSearch <= 0) {
                        break;
                    }
                    String keyNum = String.valueOf(j);
                    String keyStr = "arvTime" + keyNum;
                    int time = jsonObject.getInt(keyStr);
                    //検索した時刻の方が遅ければ、時刻を登録する。
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
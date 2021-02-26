package com.example.transapp_back.controller;

import com.example.transapp_back.dao.SaveDAO;
import com.example.transapp_back.dao.SearchDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SaveController {
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public String search(@RequestBody String saveData) {
        String[] saveDataArray = saveData.split(",");
        //TODO:配列データをオブジェクト化した上でJSONに変換するメソッドを、SaveLogicに作る。
        return "[{\"result\":\"OK\"}]";
        //return new SaveDAO().saveData(jsonData);
    }

}

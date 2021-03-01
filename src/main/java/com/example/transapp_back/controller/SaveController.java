package com.example.transapp_back.controller;

import com.example.transapp_back.dao.SaveDAO;
import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.logic.SaveLogic;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SaveController {
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public String save(@RequestBody String strData) {
        String[] arrayData = strData.split(",");
        Document saveData = new SaveLogic().changeFormat(arrayData);
        String result = new SaveDAO().save(saveData);
        System.out.println(result);
        return result;
    }

}

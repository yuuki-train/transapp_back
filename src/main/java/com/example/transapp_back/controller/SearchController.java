package com.example.transapp_back.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(value = "/getResults", method = RequestMethod.GET)
    @ResponseBody
    public String sendData(String sendList){
        return sendList;
    }

}

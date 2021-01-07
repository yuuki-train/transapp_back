package com.example.transapp_back.dao;

import com.example.transapp_back.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchDAO {
    @Autowired
    private SearchRepository searchRepository;



    public SearchDAO(String[] Lines, String departure, String destination){

        for(int i = 0; i<Lines.length; i++) {
            String line = Lines[i];





        }
    }

}

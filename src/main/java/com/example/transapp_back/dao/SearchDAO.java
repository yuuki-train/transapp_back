package com.example.transapp_back.dao;

import com.example.transapp_back.entity.Trains;
import com.example.transapp_back.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trains")
public class SearchDAO {
    @Autowired
    private SearchRepository searchRepository;

    @GetMapping(value = "/{line}")
    public Trains SearchLines(String line) {
        return searchRepository.findByLine(line);
    }

}

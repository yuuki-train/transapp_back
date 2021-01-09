package com.example.transapp_back.repository;

import com.example.transapp_back.entity.Trains;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchRepository extends MongoRepository<Trains,String>{
    Trains findByLine(String line);



}


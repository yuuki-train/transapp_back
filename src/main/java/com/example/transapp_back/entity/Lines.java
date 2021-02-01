package com.example.transapp_back.entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
public class Lines {
    private final ArrayList<String> CandidateLines;
    private final ArrayList<ArrayList<String>> Stations;

    public Lines() {
        CandidateLines = new ArrayList<>();
        CandidateLines.add("御堂筋線");
        CandidateLines.add("JR線");

        ArrayList<String> Midosuji = new ArrayList<>();
        Midosuji.add("天王寺");
        Midosuji.add("新大阪");

        ArrayList<String> JR = new ArrayList<>();
        JR.add("天王寺");
        JR.add("大阪");
        JR.add("新大阪");

        Stations = new ArrayList<>();
        Stations.add(Midosuji);
        Stations.add(JR);
    }

    public ArrayList<String> getCandidateLines() {
        return CandidateLines;
    }

    public ArrayList<ArrayList<String>> getStations() {
        return Stations;
    }
}
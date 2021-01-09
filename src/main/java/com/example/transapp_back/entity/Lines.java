package com.example.transapp_back.entity;

import java.util.ArrayList;

public class Lines {
    private final ArrayList<String> CandidateLines;
    private final ArrayList<ArrayList<String>> Stations;

    public Lines() {
        CandidateLines = new ArrayList<String>();
        CandidateLines.add("御堂筋線");
        CandidateLines.add("JR線");

        ArrayList<String> Midosuji = new ArrayList<String>();
        Midosuji.add("天王寺");
        Midosuji.add("新大阪");

        ArrayList<String> JR = new ArrayList<String>();
        JR.add("天王寺");
        JR.add("大阪");
        JR.add("新大阪");

        Stations = new ArrayList<ArrayList<String>>();
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
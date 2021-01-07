package com.example.transapp_back.entity;

public class Lines {
    private final String[] CandidateLines;
    private final String[][] Stations;

    public Lines() {
        CandidateLines = new String[]{"御堂筋線", "JR線"};
        Stations = new String[][]{{"天王寺", "新大阪"}, {"天王寺", "大阪", "新大阪"}};
    }

    public String[] getCandidateLines() {
        return CandidateLines;
    }

    public String[][] getStations() {
        return Stations;
    }
}
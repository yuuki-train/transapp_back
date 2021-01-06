package com.example.transapp_back.entity;

public class Lines {
    private String[] Midosuji;
    private String[] JR;

    public Lines(){
      Midosuji = new String[]{"天王寺", "新大阪"};
      JR = new String[]{"天王寺", "大阪", "新大阪"};
    }

    public String[] getMidosuji(){
        return Midosuji;
    }
    public String[] getJR(){
        return JR;
    }

    public void setMidosuji(String[] Midosuji){
        this.Midosuji = Midosuji;
    }

    public void setJR(String[]SetJR){
        this.JR =JR;
    }
}

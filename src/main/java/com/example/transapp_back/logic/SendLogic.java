package com.example.transapp_back.logic;

import com.example.transapp_back.entity.Trains;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
public class SendLogic {

    public String toJavaScript(List<Trains> sortList) throws JsonProcessingException {
        //データをJSONに変換
        List<String> jsonList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Trains train : sortList) {
            String json = mapper.writeValueAsString(train);
            jsonList.add(json);
        }

        HttpURLConnection con = null;
        StringBuilder result = new StringBuilder();
        try{
            URL url = new URL("http://localhost:3000/SearchResults.js");
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "jp");
            con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(String.valueOf(jsonList));
            out.flush();
            con.connect();

            final int status = con.getResponseCode();
            if(status==HttpURLConnection.HTTP_OK){
                final InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                if(null==encoding){
                    encoding = "UTF-8";
                }
                final InputStreamReader inReader = new InputStreamReader(in, encoding);
                final BufferedReader bufReader = new BufferedReader(inReader);
                String line;

                while((line= bufReader.readLine()) != null){
                    result.append(line);
                }
                bufReader.close();
                inReader.close();
                in.close();

            }else{
                System.out.println(status);
            }

        }catch(IOException e1) {
            e1.printStackTrace();
        }finally {
            if (con != null) {
                con.disconnect();
            }

        }
        return result.toString();
    }

}

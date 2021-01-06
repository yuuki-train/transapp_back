package com.example.transapp_back.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.example.transapp_back.entity.Lines;

@WebServlet("/trains")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        String depHour = request.getParameter("depHour");
        String depMinute = request.getParameter("depMinute");
        session.setAttribute("depHour", depHour);
        session.setAttribute("depMinute", depMinute);


        String[] Midosuji = new Lines().getMidosuji();
        String[] JR = new Lines().getJR();
        boolean depMidosuji = false;
        boolean depJR = false;
        boolean arvMidosuji = false;
        boolean arvJR = false;
        int counter = Math.max(Midosuji.length, JR.length);

        for(int i = 0; i<counter; i++){
            if(departure == Midosuji[i]){
                depMidosuji = true;
            }
            if(destination == Midosuji[i]) {
                arvMidosuji = true;
            }

            if(departure == JR[i] ){
                depJR = true;
            }
            if(destination == JR[i] ){
                arvJR = true;
            }
        }

        String[] Line = new String[2];
        if (depMidosuji && arvMidosuji){
            Line[0] = "御堂筋線";
        }
        if (depJR && arvJR){
            Line[1] = "JR";
        }

        session.setAttribute("Line", Line);

    }
}

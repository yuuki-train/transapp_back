package com.example.transapp_back.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

import com.example.transapp_back.dao.SearchDAO;
import com.example.transapp_back.logic.SearchLogic;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        String depHour = request.getParameter("depHour");
        String depMinute = request.getParameter("depMinute");

        //入力した駅名に対応する路線を洗い出す
        String[] Lines = new SearchLogic().LineCheck(departure, destination);

        //DAOを呼び出す
        SearchDAO dbSearch = new SearchDAO(Lines, departure, destination);

        //セッションに登録する
        HttpSession session = request.getSession();
        session.setAttribute("Lines", Lines);


    }
}

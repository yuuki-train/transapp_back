package com.example.transapp_back.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
import com.example.transapp_back.logic.SearchLogic;
import org.bson.Document;

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
        String hour = request.getParameter("hour");
        String minute = request.getParameter("minute");
        String depOrArv = request.getParameter("depOrArv");
        String priority = request.getParameter("priority");
        String addFeeTrain = request.getParameter("addFeeTrain");

        int theNumberOfSearch = 3;

        //入力した駅名に対応する路線を洗い出す
        List<String> lines = new SearchLogic().checkLines(departure, destination);

        //必要なデータを検索する
        List<Document> searchResults = new SearchLogic().searchTrains(
                lines, hour, minute, depOrArv, theNumberOfSearch
        );

        //表示するデータを選択する



        //セッションに登録する
        HttpSession session = request.getSession();
        session.setAttribute("Lines", lines);

        //次の画面にフォワードする。
        request.getRequestDispatcher("").forward(request, response);
    }
}

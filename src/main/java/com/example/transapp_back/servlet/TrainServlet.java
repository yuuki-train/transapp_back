package com.example.transapp_back.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/train")
public class TrainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String depHour = request.getParameter("depHour");
        String depMinute = request.getParameter("depMinute");
        String depTime = depHour + depMinute;
        String searchKey = "M" + depTime;

        HttpSession session = request.getSession();
        session.setAttribute("searchKey", searchKey);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/../controller/SearchController.java");
        dispatcher.forward(request, response);
    }
}

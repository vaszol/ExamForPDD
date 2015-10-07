package ru.vaszol.exam.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.vaszol.exam.web.forms.TestOverForm;
//@WebServlet("/resultTestServlet")
public class resultTestServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        // Параметр
        String parameter = request.getParameter("parameter");
        
        // Старт HTTP сессии
        HttpSession session = request.getSession(true);
        session.setAttribute("parameter", parameter);
 
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Заголовок</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Привет, Host: "+ request.getRemoteHost() +"</P>");
            out.println("<p>Привет, Addr: "+ request.getRemoteAddr() +"</P>");
            out.println("<p>Привет, Port: "+ request.getRemotePort() +"</P>");
            out.println("<p>Привет, User: "+ request.getRemoteUser() +"</P>");
            out.println("");
            out.println("<p>Билет№ ${result.biletId} , совершено ошибок: ${result.error}</p>");
            out.println("<p>Билет№ ${result.biletId} , совершено ошибок: ${result.error}</p>");
            out.println("<div><img class='image' alt='нет картинки' src='image/'></div>");
            out.println("");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    } 
 
    @Override
    public String getServletInfo() {
        return "Пример сервлета";
    }
 
}
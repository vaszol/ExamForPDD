package ru.vaszol.exam.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.Otvet;
import ru.vaszol.exam.logic.TestRequest;
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.TestOverForm;
import ru.vaszol.exam.web.forms.TestPDDForm;

public class TestOverServlet extends HttpServlet{
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");            
       
        if (req.getParameter("update") != null) {        	
            getServletContext().getRequestDispatcher("/testOver.jsp").forward(req, resp);
			return;          
            
        }
       
        getServletContext().getRequestDispatcher("/testOver.jsp").forward(req, resp);
		return;       
        
    }
   
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }




}

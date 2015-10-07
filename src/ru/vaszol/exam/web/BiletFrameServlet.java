package ru.vaszol.exam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Bilet;
import ru.vaszol.exam.logic.Group;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.Student;
 
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.BiletFrameForm;
import ru.vaszol.exam.web.forms.EditVoprosForm;
import ru.vaszol.exam.web.forms.MainFrameForm;
import ru.vaszol.exam.web.forms.ShowVoprosesForm;
 
public class BiletFrameServlet  extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
    	req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //PrintWriter out = resp.getWriter();
        //resp.setContentType ("text/html; charset=UTF-8");
        //resp.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для вывода всех вопросов
        	
        	ShowVoprosesForm form = new ShowVoprosesForm();
        	 
        	try {
        		Collection voproses = ManagementSystem.getInstance().getAllVopotv();
        		
                Collection bilets = ManagementSystem.getInstance().getBilets();
                Bilet bl = new Bilet();  
                form.setBbilets(bilets);            
                form.setVoproses(voproses);            
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
     
            req.setAttribute("form", form);
                 
            getServletContext().getRequestDispatcher("/ShowVoproses.jsp").forward(req, resp);
        	
        }
 
        if (answer == 2) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных о вопросе
            try {
                if (req.getParameter("voprosId") != null) {
                    int voId = Integer.parseInt(req.getParameter("voprosId"));
                    Vopotv vo = ManagementSystem.getInstance().getVopotvById(voId);
                    Collection bilets = ManagementSystem.getInstance().getBilets();
                    EditVoprosForm vForm = new EditVoprosForm();
                    vForm.initFromVopotv(vo);
                    vForm.setBilets(bilets);
                    req.setAttribute("vopros", vForm);
                    getServletContext().getRequestDispatcher("/VoprosEditFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        
        if (answer == 3) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных в таблицу ответов

        }
        
        //готовим отображение для формы 
        String bid = req.getParameter("biletId");
        int biletId = -1;
        if (bid != null) {
            biletId = Integer.parseInt(bid);
        }  
        BiletFrameForm form = new BiletFrameForm();
        try {
            Collection bilets = ManagementSystem.getInstance().getBilets();
            Bilet bl = new Bilet();
            bl.setBiletId(biletId);
            if (biletId == -1) {
                Iterator i = bilets.iterator();
                bl = (Bilet) i.next();
            }
            Collection voproses = ManagementSystem.getInstance().getVopotvFromBilet(bl);
            form.setBiletId(bl.getBiletId());
            form.setBbilets(bilets);            
            form.setVoproses(voproses);            
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
 
        req.setAttribute("form", form);        
        getServletContext().getRequestDispatcher("/MainBilet.jsp").forward(req, resp);
    }
 
// Здесь мы проверям какое действие нам надо сделать – и возвращаем ответ
    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Voproses") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }        
        if (req.getParameter("Otvetes") != null) {
        	return 3;
            /**
        	if (req.getParameter("voprosId") != null) {
                Vopotv vo = new Vopotv();
                vo.setVoprosId(Integer.parseInt(req.getParameter("voprosId")));
                ManagementSystem.getInstance().deleteVopotv(vo);
            }
            return 0;
            */
        }
        return 0;
    }
 
    // Переопределим стандартные методы

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
 
}

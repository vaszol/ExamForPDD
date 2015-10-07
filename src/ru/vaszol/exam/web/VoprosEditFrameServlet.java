package ru.vaszol.exam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Bilet;
import ru.vaszol.exam.logic.Group;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.Student;
 
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.BiletFrameForm;
import ru.vaszol.exam.web.forms.MainFrameForm;

public class VoprosEditFrameServlet extends HttpServlet{
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");       
        //PrintWriter out = resp.getWriter();
        //resp.setContentType ("text/html; charset=UTF-8");
        //resp.setCharacterEncoding("UTF-8");
        String vId = req.getParameter("voprosId");
        // Если пользователь нажал кнопку ОК – тогда мы обновляем данные (добавляем новый или обновляем билет)
        if (vId != null && req.getParameter("OK") != null) {        	
            try {
            	// Если ID вопроса больше 0, то мы редактируем его данные
                if (Integer.parseInt(vId) > 0) {
                    updateVopotv(req);
                } // Иначе это новый вопрос
                else {
                    insertVopotv(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        // А теперь опять получаем данные для отображения на главной форме
        String bs = req.getParameter("biletId");
        int biletId = -1;
        if (bs != null) {
            biletId = Integer.parseInt(bs);
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
    
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
 
    private void updateVopotv(HttpServletRequest req) throws SQLException, ParseException {
        Vopotv s = prepareVopros(req);
        ManagementSystem.getInstance().updateVopotv(s);
    }
 
    private void insertVopotv(HttpServletRequest req) throws SQLException, ParseException {
    	Vopotv s = prepareVopros(req);
        ManagementSystem.getInstance().insertVopotv(s);
    }
 
    private Vopotv prepareVopros(HttpServletRequest req) throws ParseException {
    	Vopotv vo = new Vopotv();
        vo.setVoprosId(Integer.parseInt(req.getParameter("voprosId")));
        vo.setBiletId(Integer.parseInt(req.getParameter("biletId")));        	
        vo.setVoprosText(req.getParameter("voprosText").trim());        
        vo.setPicture(Integer.parseInt(req.getParameter("picture")));
        vo.setTheme(Integer.parseInt(req.getParameter("theme").trim()));        
        vo.setOtvetText1(req.getParameter("otvetText1").trim());
        vo.setOtvetText2(req.getParameter("otvetText2").trim());
        vo.setOtvetText3(req.getParameter("otvetText3").trim());
        vo.setOtvetText4(req.getParameter("otvetText4").trim());
        vo.setOtvetText5(req.getParameter("otvetText5").trim());
        vo.setOtvetText6(req.getParameter("otvetText6").trim());
        vo.setVopname(req.getParameter("vopname").trim());
        return vo;        
    }
}

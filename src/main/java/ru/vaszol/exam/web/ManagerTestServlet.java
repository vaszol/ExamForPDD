package ru.vaszol.exam.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Bilet;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.MySession;
import ru.vaszol.exam.logic.Otvet;
import ru.vaszol.exam.logic.TestRequest;
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.ManagerTestForm;
import ru.vaszol.exam.web.forms.TestOverForm;
import ru.vaszol.exam.web.forms.TestPDDForm;

public class ManagerTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        // Установка кодировки для принятия параметров
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");            
       
        if (req.getParameter("create") != null) {    	
            updateForm(req, resp);						//обновить данные отображения
            insertSession(req, resp);
			return;
        }
        if (req.getParameter("update") != null) {    	
            updateForm(req, resp);						//обновить данные отображения
			return;
        }
        if (req.getParameter("printResult") != null) {    	
            printResult(req, resp);						//печать результата
        	updateForm(req, resp);						//обновить данные отображения
			return;
        }
        if (req.getParameter("session_num") != null) {        	
        	updateSessionTab(req, resp);				//обновить значения сесссий
            updateForm(req, resp);						//обновить данные отображения
			return;
        }
       
        getServletContext().getRequestDispatcher("/managerTest.jsp").forward(req, resp);
		return;       
        
    }
	private void printResult(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {		
		List<TestRequest> userOtvets2 = ManagementSystem.getInstance().getCurTestRequestBySession2(req.getParameter("addrIp"));
		TestOverForm form = new TestOverForm();		
		//form.setBiletId(Integer.parseInt(req.getParameter("biletId")));
		Locale local = new Locale("ru","RU");
		SimpleDateFormat df = new SimpleDateFormat("mm:ss");
	       
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.DEFAULT, local); 
		form.setStudentName("tester");		
        form.setIsDone(false); //не сдал!
        form.setStudentName(req.getParameter("FIO"));
        int error=0; //счетчик ошибок
        Date start=new Date();
        Date stop=new Date();        
        for(TestRequest tr:userOtvets2){
        	if(tr.getVopros_id()==0){
        		start=tr.getOrderDate();
        		req.setAttribute("startTime", df2.format(start));
        	}
    		if(tr.getVopros_id()>0){
    			Otvet otv = ManagementSystem.getInstance().getOtvetsById(tr.getVopros_id()); //получаем правильный ответ
        		Integer prav = otv.getOtvet();
        		Integer otvc = tr.getOtvet(); 
        		if(!otvc.equals(prav)){ //ищем несовпадение
        			error++;
        		}
        		stop=tr.getOrderDate();
        		req.setAttribute("stopTime", df2.format(stop));
    		}
    		form.setBiletId(tr.getVopros_id()/20+1);
        }
        
        
        long diff = stop.getTime()-start.getTime();
        
        req.setAttribute("resultTime", df.format(new Date(diff)));
               
        
        List<TestRequest> userOtvets = ManagementSystem.getInstance().getCurTestRequestBySession(req.getParameter("addrIp"));
        form.setResults(userOtvets);		
		form.setError(error);
		form.setPicture("false.png");
		req.setAttribute("result", form);
		
		getServletContext().getRequestDispatcher("/printResult.jsp").forward(req, resp);
		return;
		
		
		
	}
	private void insertSession(HttpServletRequest req, HttpServletResponse resp) {
		MySession mySession = new MySession();
		mySession.setSession_id("111");
    	mySession.setSession_num(222);
    	mySession.setAddrIp("333");
    	mySession.setStat(0); //указываем статус "решает.."
    	mySession.setCat(0);
    	mySession.setBilet_id(0);
		try {
			ManagementSystem.getInstance().insertMySession(mySession);
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	}

	/**
	 * обновить значения сесссий
	 * @param req
	 * @param resp
	 */	
	private void updateSessionTab(HttpServletRequest req,
			HttpServletResponse resp) {
		// подготовим данные для запроса
		MySession mySession = new MySession();
    	mySession.setSession_id(req.getParameter("studentId"));
    	mySession.setSession_num(Integer.parseInt(req.getParameter("session_num")));
    	mySession.setAddrIp(req.getParameter("addrIp"));
    	mySession.setFIO(req.getParameter("FIO"));
    	//TODO добавить условие проверки остановки/запуска теста 
    	if(Integer.parseInt(req.getParameter("stat"))==1){
    		mySession.setStat(4); //указываем статус "Укажите билет"
    		mySession.setBilet_id(777);
    	}else if(Integer.parseInt(req.getParameter("stat"))==2){
    		mySession.setStat(4); //сбрасываем статус "Укажите билет"
    		mySession.setBilet_id(777);
    	}else if(Integer.parseInt(req.getParameter("stat"))==3){
    		mySession.setStat(4); //сбрасываем статус "Укажите билет"
    		mySession.setBilet_id(777);
    	}else {
    		mySession.setStat(1); //указываем статус "решает.."
    		mySession.setBilet_id(Integer.parseInt(req.getParameter("bilet_id")));
		}
    	mySession.setCat(Integer.parseInt(req.getParameter("cat")));
    	
    	//обновляем базу сессий
    	try {
			ManagementSystem.getInstance().updateSessionsFIO(mySession);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * обновить данные отображения
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// получаем индекс билета
        
		ManagerTestForm form = new ManagerTestForm();        
        try {
        	Collection bilets = ManagementSystem.getInstance().getBilets();
            form.setBilets(bilets);
            Collection sessions = ManagementSystem.getInstance().getSessionsFIO();            
            form.setSessions(sessions);   
            req.setAttribute("managerka", form);            
            getServletContext().getRequestDispatcher("/managerTest.jsp").forward(req, resp);
            return;
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }   
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	}

}

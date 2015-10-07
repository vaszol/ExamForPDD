package ru.vaszol.exam.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Bilet;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.MySession;
import ru.vaszol.exam.logic.Otvet;
import ru.vaszol.exam.logic.Student;
import ru.vaszol.exam.logic.TestRequest;
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.BiletFrameForm;
import ru.vaszol.exam.web.forms.EditVoprosForm;
import ru.vaszol.exam.web.forms.StudentForm;
import ru.vaszol.exam.web.forms.TestOverForm;
import ru.vaszol.exam.web.forms.TestPDDForm;

public class TestPDDServlet extends HttpServlet{
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");            
        int vId = (Integer.parseInt(req.getParameter("voprosId"))); //получаем вопрос id
        int iv = vId-((Integer.parseInt(req.getParameter("biletId"))-1)*20); //получаем номер вопроса
        String var = req.getParameter("variant"); //получаем выбранный ответ
        //int vn = (Integer.parseInt(req.getParameter("vopname")));
        int error=0; //счетчик ошибок                
        // Если пользователь нажал кнопку ОК – тогда мы обновляем данные (проводим зачет ответа)
        if (req.getParameter("OK") != null && Integer.parseInt(var) > 0) {        	
            try {
            	//TODO добавь проверку на выбранный вариант
            	//получаем список отвеченных вопросов билета            	
            	List<TestRequest> userOtvets = ManagementSystem.getInstance().getCurTestRequestBySession(req.getRemoteHost());            	
            	boolean[] isReady = new boolean[20];
            	int i =0;
            	for(TestRequest tr:userOtvets){
            		if(tr.getVopros_id()!=0){
            			isReady[i++]=true;	
            		}            		
            	}
            	//проверка на ещё не отвеченный вопрос            	
            	if(!isReady[iv-1]){
            		saveRequest(req);       
            	}

            	isTest(req, resp);		//проверка на прекращение теста админкой
            	//получаем список результатов текущего теста
            	userOtvets = ManagementSystem.getInstance().getCurTestRequestBySession(req.getRemoteHost());
            	//перебираем ответы пользователя
            	for(TestRequest tr:userOtvets){
            		if(tr.getVopros_id()>0){
            			Otvet otv = ManagementSystem.getInstance().getOtvetsById(tr.getVopros_id()); //получаем правильный ответ
                		Integer prav = otv.getOtvet();
                		Integer otvc = tr.getOtvet(); 
                		if(!otvc.equals(prav)){ //ищем несовпадение
                			error++;
                			if(error>2){ //если имеются 3 ошибки 
                				MySession ses = null;
                				ses=getSessions(req, resp);
                				updateSessionTab(req, resp,ses.getSession_num(), 3);   //передаем параметр "не сдал" в админку                  				
                    			gameOver(req, resp,error);
                    		}
                		}       			
            		}               		
            	}
            	//вычисляем конец билета (индекс 20 вопроса в билете)
            	int endOfBilet = (Integer.parseInt((req.getParameter("biletId")))-1)*20+20;
            	if(vId==endOfBilet){ //проверка на окончание теста
            		MySession ses = null;
    				ses=getSessions(req, resp);
    				updateSessionTab(req, resp,ses.getSession_num(), 2);   //передаем параметр "сдал" в админку      				 
            		happyEnd(req, resp,error);
            	}
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        // TODO иначе вернуть алерт (реализовано JS в test.jsp)
        else{
        	try {
				    	req.setAttribute("param2", "выбери вариант");
				    	//req.setAttribute("param1", m.getAddrIp());
				    	//req.setAttribute("param3", req.getRemoteAddr());
				    	getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
					} catch (ServletException | IOException e) {
						// TODO Автоматически созданный блок catch
						e.printStackTrace();
					}
        }
        
		/**
		 * А теперь опять получаем данные для отображения на форме теста
		 */
        showView(req,resp,vId,error);
		
    }

private void showView(HttpServletRequest req, HttpServletResponse resp, int vId, int error){
	// получаем индекс билета
    String bs = req.getParameter("biletId");
    int biletId = -1;
    if (bs != null) {
        biletId = Integer.parseInt(bs);
    }
    TestPDDForm form = new TestPDDForm(); //форму запроса к станице    
        int voId = vId+1; //выбираем следующий вопрос
        Vopotv vo;
		try {
			vo = ManagementSystem.getInstance().getVopotvById(voId);			
	        form.initTestPDDForm(vo);    
	        form.setError(error);
	        req.setAttribute("test", form);
	        req.setAttribute("OtvetText1", vo.getOtvetText1());
	        req.setAttribute("OtvetText2", vo.getOtvetText2());
	        req.setAttribute("OtvetText3", vo.getOtvetText3());
	        req.setAttribute("OtvetText4", vo.getOtvetText4());
	        req.setAttribute("OtvetText5", vo.getOtvetText5());
	        req.setAttribute("OtvetText6", vo.getOtvetText6());
	        getServletContext().getRequestDispatcher("/testPDD.jsp").forward(req, resp);		
	        return;
		} catch (SQLException | ServletException | IOException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	}

private void isTest(HttpServletRequest req, HttpServletResponse resp) {
	//TODO добавь проверку на прекращение теста админкой
		MySession ses = getSessions(req, resp);
		if(ses.getBilet_id()>100){		//если индекс билета больше 100, то наш тест остановили
			try {
				getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp);	 //ждем одобрения админки
			} catch (ServletException | IOException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
		}
	}

private MySession getSessions(HttpServletRequest req,
			HttpServletResponse resp) {
	MySession ses=null;
	try {			
		Collection<MySession> sesList = ManagementSystem.getInstance().getSessionsFIO();
		for(MySession m:sesList){
			if(m.getAddrIp().equals(req.getRemoteAddr())){
				ses=m;					
			}
		}
	} catch (SQLException e) {
		// TODO Автоматически созданный блок catch
		e.printStackTrace();
	}			
	return ses;
	}

private void updateSessionTab(HttpServletRequest req,
			HttpServletResponse resp, int session_num, int i) {
	// подготовим данные для запроса
			MySession mySession = new MySession();
	    	mySession.setSession_id(req.getSession().getId());
	    	mySession.setSession_num(session_num);
	    	mySession.setAddrIp(req.getRemoteAddr());
	    	mySession.setStat(i); //указываем статус "не сдал"
	    	mySession.setCat(1);
	    	mySession.setBilet_id(666);
	    	//обновляем базу сессий
	    	try {
	    		// обновляем кроме поля ФИО
				ManagementSystem.getInstance().updateSessions(mySession);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}

/** Вывод результатов
 * @param error */
    //при не сдаче:
	private void gameOver(HttpServletRequest req, HttpServletResponse resp, int error) throws SQLException, ParseException, ServletException, IOException {
		TestOverForm form = prepareResult(req);
		form.setIsDone(false); //не сдал!
		form.setError(error);
		form.setPicture("false.png");
		req.setAttribute("result", form);
		getServletContext().getRequestDispatcher("/testOver.jsp").forward(req, resp);
		return;
	}
	//тест пройден:
	private void happyEnd(HttpServletRequest req, HttpServletResponse resp, int error) throws ServletException, IOException, ParseException, SQLException {
		TestOverForm form = prepareResult(req);   
		form.setIsDone(true); //сдал!
		form.setError(error);
		form.setPicture("true.png");
		req.setAttribute("result", form);
		getServletContext().getRequestDispatcher("/testOver.jsp").forward(req, resp);
		return;
	}
/** подготавливаем форму для результата теста 
 * @param req 
 * @throws SQLException */
	private TestOverForm prepareResult(HttpServletRequest req)  throws ParseException, SQLException {
		Collection userOtvets = ManagementSystem.getInstance().getCurTestRequestBySession(req.getRemoteHost());
		TestOverForm tr = new TestOverForm();
		/**
		tr.setBiletId(Integer.parseInt(req.getParameter("biletId")));
		for(TestRequest m:userOtvets){
			
		}*/
		tr.setBiletId(Integer.parseInt(req.getParameter("biletId")));
		tr.setResults(userOtvets);		
		tr.setStudentName("tester");		
        return tr;        
	}

	
/** сохраняем результат рекущего теста в базу*/
	private void saveRequest(HttpServletRequest req) throws SQLException, ParseException {		
		TestRequest t = prepareRequest(req);
        ManagementSystem.getInstance().insertTestRequest(t);		
	}
/**
 * @throws SQLException 
 * @throws NumberFormatException  */
	private TestRequest prepareRequest(HttpServletRequest req)  throws ParseException, NumberFormatException, SQLException {
		TestRequest tr = new TestRequest();		
		tr.setVopros_id(Integer.parseInt(req.getParameter("voprosId")));
		tr.setSessionName(req.getRemoteHost());
		tr.setOtvet(Integer.parseInt(req.getParameter("variant")));
		Otvet otv = ManagementSystem.getInstance().getOtvetsById(Integer.parseInt(req.getParameter("voprosId"))); //получаем правильный ответ
		tr.setTrueOtvet(otv.getOtvet());
        return tr;        
	}
	
/** переопределение запросов */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}

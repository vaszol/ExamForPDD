package ru.vaszol.exam.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.vaszol.exam.logic.Bilet;
import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.MySession;
import ru.vaszol.exam.logic.TestRequest;
import ru.vaszol.exam.logic.Vopotv;
import ru.vaszol.exam.web.forms.TestPDDForm;

public class startExamServlet extends HttpServlet{
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
		
		if (req.getParameter("start") != null) {
			
			MySession ses = null;
			int bilet=0;
			int session_num=0000;
			
			ses=getSessions(req, resp);
			
			if(ses!=null){				
				updateSessionTab(req, resp,ses.getSession_num(),ses.getFIO());
				if(ses.getStat()==1){			
					try {
						saveRequest(req);
					} catch (ParseException e) {
						// TODO Автоматически созданный блок catch
						e.printStackTrace();
					}
					startTest(req, resp,ses.getBilet_id());											//старт теста!!!
				}	else if(ses.getStat()==0){  //Не подключен
					getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки			
				}	else if(ses.getStat()==2){	//Сдал!
					getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки						
				}	else if(ses.getStat()==3){	//Не сдал.
					getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки									
				}	else if(ses.getStat()==4){
					getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки						
				}
				
							
			}else {				
				insertMySession(req,resp,session_num);
				getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки	
			}
				
			return;
        }
		getServletContext().getRequestDispatcher("/startExam.jsp").forward(req, resp); //ждем одобрения админки
		 try {
		    	req.setAttribute("param2", "Ой, что-то не так!");
		    	req.setAttribute("param1", "Ой, что-то не так!");
		    	req.setAttribute("param3", req.getRemoteAddr());
		    	getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
	}

	private void startTest(HttpServletRequest req, HttpServletResponse resp,
			int bilet) throws SQLException, ServletException, IOException {
		Random rand = new Random();
		int i;
		int n = 40; //количество имеющихся билетов
		TestPDDForm form = new TestPDDForm(); //форму запроса к станице		
		
		if(bilet==0){			
			bilet=rand.nextInt(n)+1; 	//индекс билета		
		}
		i=bilet*20+1-20;			//индекс вопроса соответствующий началу билета
		Vopotv vo = ManagementSystem.getInstance().getVopotvById(i);     //выбираем первый вопрос нашего билета                   
	    form.initTestPDDForm(vo);            
	    req.setAttribute("test", form);
	    getServletContext().getRequestDispatcher("/testPDD.jsp").forward(req, resp);
		    
	}

	/**
	 * обновить значения сесссий
	 * @param req
	 * @param resp
	 * @param session_num 
	 * @param FIO 
	 */	
	private void updateSessionTab(HttpServletRequest req,
			HttpServletResponse resp, int session_num, String FIO) {
		// подготовим данные для запроса
		MySession mySession = new MySession();
    	mySession.setSession_id(req.getParameter("studentId"));
    	mySession.setSession_num(session_num);
    	mySession.setAddrIp(req.getParameter("addrIp"));
    	mySession.setStat(1); //указываем статус "решает.."
    	mySession.setCat(1);
    	mySession.setBilet_id(666);
    	mySession.setFIO(FIO);
    	//обновляем базу сессий
    	try {
    				///   обновляем кроме поля ФИО
			ManagementSystem.getInstance().updateSessions(mySession);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * добавить в базу себя
	 * @param req
	 * @param resp
	 * @param session_num 
	 */
	private void insertMySession(HttpServletRequest req, HttpServletResponse resp, int session_num) {
		try {	
			MySession mySession = new MySession();
			mySession.setSession_id(req.getSession().getId());
			mySession.setSession_num(session_num);
			mySession.setAddrIp(req.getRemoteAddr());
			mySession.setStat(4);
			mySession.setCat(0);
			mySession.setBilet_id(0);			
			mySession.setFIO(req.getRemoteAddr());
			ManagementSystem.getInstance().insertMySessionFIO(mySession);
		} catch (SQLException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}		
	}	

	private MySession getSessions(HttpServletRequest req, HttpServletResponse resp) {
		MySession ses=null;
		
		try {			
			Collection<MySession> sesList = ManagementSystem.getInstance().getSessionsFIO();
			for(MySession m:sesList){
				if(m.getAddrIp().equals(req.getRemoteAddr())){
					ses=m;					
				}else{	
					/**
				    try {
				    	req.setAttribute("param2", "не равны");
				    	req.setAttribute("param1", m.getAddrIp());
				    	req.setAttribute("param3", req.getRemoteAddr());
				    	getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
					} catch (ServletException | IOException e) {
						// TODO Автоматически созданный блок catch
						e.printStackTrace();
					}
					*/
				}
			}
				
			//ses = ManagementSystem.getInstance().getSessionsFromIp(req.getRemoteHost());
			
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}			
		return ses;
	}

	private void saveRequest(HttpServletRequest req) throws SQLException, ParseException {
		TestRequest t = prepareRequest(req);
        ManagementSystem.getInstance().insertTestRequest(t);		
	}

	private TestRequest prepareRequest(HttpServletRequest req)  throws ParseException {
		TestRequest tr = new TestRequest();		
		tr.setVopros_id(0);
		tr.setSessionName(req.getRemoteAddr());
		tr.setOtvet(0);		
        return tr;        
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
			processRequest(req, resp);
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
			processRequest(req, resp);
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
    }

}

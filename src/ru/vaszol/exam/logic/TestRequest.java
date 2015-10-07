package ru.vaszol.exam.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class TestRequest {
	
	private int testRequest_id;
	private String sessionName;
	private int vopros_id;
	private int voprosNum;
	private Date OrderDate;
	private int otvet;
	private int trueOtvet;
	
	public TestRequest(ResultSet rs) throws SQLException {
		//"SELECT testRequest_id, sessionName,"
        	//	+ "vopros_id, OrderDate,otvet"
				setTestRequest_id(rs.getInt(1));
        		setSessionName(rs.getString(2));
        		setVopros_id(rs.getInt(3));   //индекс вопроса в базе
        		setVoprosNum(getVopros_id()%20); //записываем номер вопроса по порядку в билете
        		if(getVoprosNum()==0)setVoprosNum(20); //результат деления от числя кратного 20 принимаем за 20
        		setOrderDate(rs.getTimestamp(4));
        		setOtvet(rs.getInt(5));
        		setTrueOtvet(rs.getInt(6));
	}
	public TestRequest(TestRequest tr) {
		setTestRequest_id(tr.getTestRequest_id());
		setSessionName(tr.getSessionName());
		setVopros_id(tr.getVopros_id());  		
		setOrderDate(tr.OrderDate);
		setOtvet(tr.getOtvet());
		setTrueOtvet(tr.getTrueOtvet());
	}
	
	public TestRequest() {
		
	}
	public int getTestRequest_id() {
		return testRequest_id;
	}
	public void setTestRequest_id(int testRequest_id) {
		this.testRequest_id = testRequest_id;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public int getVopros_id() {
		return vopros_id;
	}
	public void setVopros_id(int vopros_id) {
		this.vopros_id = vopros_id;
	}
	public int getOtvet() {
		return otvet;
	}
	public void setOtvet(int otvet) {
		this.otvet = otvet;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	
	public String toString() {
        return testRequest_id + " " + sessionName + " " + vopros_id + " "
                + DateFormat.getDateInstance(DateFormat.SHORT).format(OrderDate)
                + " " + otvet;
    }
 
    public int compareTo(Object obj) {
        return this.toString().compareTo(obj.toString());
    }
	public int getTrueOtvet() {
		return trueOtvet;
	}
	public void setTrueOtvet(int trueOtvet) {
		this.trueOtvet = trueOtvet;
	}
	public int getVoprosNum() {
		return voprosNum;
	}
	public void setVoprosNum(int voprosNum) {
		this.voprosNum = voprosNum;
	}
	
	
	
	
}

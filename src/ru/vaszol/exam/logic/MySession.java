package ru.vaszol.exam.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MySession {
	private int id;
	private String session_id;
    private int session_num;
    private String addrIp;
    private int stat;
    private int cat;
    private int bilet_id;
    private Date OrderDate;
    private String FIO;
    
    
 
    public MySession(ResultSet rs) throws SQLException {
    	setId(rs.getInt(1));
    	setSession_id(rs.getString(2));
    	setSession_num(rs.getInt(3));
    	setAddrIp(rs.getString(4));
    	setStat(rs.getInt(5));
    	setCat(rs.getInt(6));
    	setBilet_id(rs.getInt(7));
    	setOrderDate(rs.getDate(8));
    	setFIO(rs.getString(9));
    }


	public MySession() {
		// TODO Автоматически созданная заглушка конструктора
	}


	public String getSession_id() {
		return session_id;
	}


	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}


	public int getSession_num() {
		return session_num;
	}


	public void setSession_num(int session_num) {
		this.session_num = session_num;
	}

	public String getAddrIp() {
		return addrIp;
	}


	public void setAddrIp(String addrIp) {
		this.addrIp = addrIp;
	}

	public int getStat() {
		return stat;
	}


	public void setStat(int stat) {
		this.stat = stat;
	}


	public int getCat() {
		return cat;
	}


	public void setCat(int i) {
		this.cat = i;
	}


	public int getBilet_id() {
		return bilet_id;
	}


	public void setBilet_id(int bilet_id) {
		this.bilet_id = bilet_id;
	}


	public Date getOrderDate() {
		return OrderDate;
	}


	public void setOrderDate(Date date) {
		OrderDate = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFIO() {
		return FIO;
	}


	public void setFIO(String fIO) {
		this.FIO = fIO;
	}
}

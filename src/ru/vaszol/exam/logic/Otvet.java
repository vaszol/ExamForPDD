package ru.vaszol.exam.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Otvet {
	private int vopros_id;
	private int otvet;
	public Otvet(ResultSet rs) throws SQLException {
		setVopros_id(rs.getInt(1));
    	setOtvet(rs.getInt(2));
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

}

package ru.vaszol.exam.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Vopotv {
	
	private int voprosId;
    private int biletId;
    private String vopname;
    private String voprosText;
    private int picture;
    private int theme;
    private String otvetText1;
    private String otvetText2;
    private String otvetText3;
    private String otvetText4;
    private String otvetText5;
    private String otvetText6;
    
	public Vopotv(ResultSet rs) throws SQLException {
        setVoprosId(rs.getInt(1));
        setBiletId(rs.getInt(2));        
        setVoprosText(rs.getString(3));
        setPicture(rs.getInt(4));
        setTheme(rs.getInt(5));
        setOtvetText1(rs.getString(6));
        setOtvetText2(rs.getString(7));
        setOtvetText3(rs.getString(8));
        setOtvetText4(rs.getString(9));
        setOtvetText5(rs.getString(10));
        setOtvetText6(rs.getString(11));
        setVopname(rs.getString(12));
    }

    public Vopotv() {
    }
 
	public int getVoprosId() {
		return voprosId;
	}

	public void setVoprosId(int voprosId) {
		this.voprosId = voprosId;
	}

	public int getBiletId() {
		return biletId;
	}

	public void setBiletId(int biletId) {
		this.biletId = biletId;
	}

	public String getVoprosText() {
		return voprosText;
	}

	public void setVoprosText(String voprosText) {
		this.voprosText = voprosText;
	}

	public int getPicture() {
		return picture;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}

	public int getTheme() {
		return theme;
	}

	public void setTheme(int theme) {
		this.theme = theme;
	}

	public String getOtvetText1() {
		return otvetText1;
	}

	public void setOtvetText1(String otvetText1) {
		this.otvetText1 = otvetText1;
	}

	public String getOtvetText2() {
		return otvetText2;
	}

	public void setOtvetText2(String otvetText2) {
		this.otvetText2 = otvetText2;
	}

	public String getOtvetText3() {
		return otvetText3;
	}

	public void setOtvetText3(String otvetText3) {
		this.otvetText3 = otvetText3;
	}

	public String getOtvetText4() {
		return otvetText4;
	}

	public void setOtvetText4(String otvetText4) {
		this.otvetText4 = otvetText4;
	}

	public String getOtvetText5() {
		return otvetText5;
	}

	public void setOtvetText5(String otvetText5) {
		this.otvetText5 = otvetText5;
	}

	public String getOtvetText6() {
		return otvetText6;
	}

	public void setOtvetText6(String otvetText6) {
		this.otvetText6 = otvetText6;
	}
 
    public String toString() {
        return voprosId + " : " + biletId + " (" + voprosText + " : "
               + picture + " : " + theme + " [ "
               + otvetText1 + " ; " + otvetText2 + " ; " + otvetText3 + " ; "
               + otvetText4 + " ; " + otvetText4 + " ; " + otvetText4 + " ])";
    }
 	
    public int compareTo(Object obj) {
        return this.toString().compareTo(obj.toString());
    }

	public String getVopname() {
		return vopname;
	}

	public void setVopname(String vopname) {
		this.vopname = vopname;
	}

}

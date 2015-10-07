package ru.vaszol.exam.web.forms;

import java.sql.SQLException;
import java.util.Collection;

import ru.vaszol.exam.logic.ManagementSystem;
import ru.vaszol.exam.logic.Vopotv;


public class TestPDDForm {
	
	private int voprosId;
	private int biletId;
	private String vopname;
	private String voprosText;
	private String picture;
	private int theme; 
	private String otvetText1;
    private String otvetText2;
    private String otvetText3;
    private String otvetText4;
    private String otvetText5;
    private String otvetText6;  
    private int error;

    
    public void initTestPDDForm(Vopotv st) throws SQLException {
    	String pic = ManagementSystem.getInstance().getPictureStringById(st.getPicture());
    	if (pic == ""){
    		pic ="MCRpWjg.png";
    	}
        this.setVoprosId(st.getVoprosId());
        this.setBiletId(st.getBiletId());
        this.setVopname(st.getVopname());
        this.setVoprosText(st.getVoprosText());
        this.setPicture(pic);        
        this.setTheme(st.getTheme());   
        this.setOtvetText1(st.getOtvetText1());
        this.setOtvetText2(st.getOtvetText2());
        this.setOtvetText3(st.getOtvetText3());
        this.setOtvetText4(st.getOtvetText4());
        this.setOtvetText5(st.getOtvetText5());
        this.setOtvetText6(st.getOtvetText6());
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


	public String getVopname() {
		return vopname;
	}


	public void setVopname(String vopname) {
		this.vopname = vopname;
	}


	public String getVoprosText() {
		return voprosText;
	}


	public void setVoprosText(String voprosText) {
		this.voprosText = voprosText;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
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


	public int getError() {
		return error;
	}


	public void setError(int error) {
		this.error = error;
	}


	
}

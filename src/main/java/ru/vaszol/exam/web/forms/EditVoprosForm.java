package ru.vaszol.exam.web.forms;

import java.util.Collection;

import ru.vaszol.exam.logic.Vopotv;

public class EditVoprosForm {
	
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
    private Collection bilets;
    //private Collection voproses;
    
    public void initFromVopotv(Vopotv st) {
        this.voprosId = st.getVoprosId();
        this.biletId = st.getBiletId();
        this.vopname = st.getVopname();
        this.voprosText = st.getVoprosText();
        this.picture = st.getPicture();        
        this.theme = st.getTheme();   
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
    public int getPicture() {
		return picture;
	}
    public void setPicture(int picture) {
		this.picture = picture;
	}
    public String getVoprosText() {
		return voprosText;
	}
    public void setVoprosText(String voprosText) {
		this.voprosText = voprosText;
	}
    public int getTheme() {
		return theme;
	}
    public void setTheme(int theme) {
		this.theme = theme;
	}
    public Collection getBilets() {
		return bilets;
	}
    public void setBilets(Collection bilets) {
		this.bilets = bilets;
	}
/**
    public Collection getVoproses() {
		return voproses;
	}
    public void setVoproses(Collection voproses) {
		this.voproses = voproses;
	}
**/
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

	public String getVopname() {
		return vopname;
	}

	public void setVopname(String vopname) {
		this.vopname = vopname;
	}    
}

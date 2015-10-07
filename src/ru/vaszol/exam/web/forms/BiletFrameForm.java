package ru.vaszol.exam.web.forms;

import java.util.Collection;

public class BiletFrameForm {
		    
    private int biletId;  
    private Collection bilets;
    private Collection voproses;
	
    public int getBiletId() {
		return biletId;
	}
	public void setBiletId(int biletId) {
		this.biletId = biletId;
	}
	
	public Collection getBilets() {
		return bilets;
	}
	public void setBbilets(Collection bilets) {
		this.bilets = bilets;
	}
	public Collection getVoproses() {
		return voproses;
	}
	public void setVoproses(Collection voproses) {
		this.voproses = voproses;
	}
	
}

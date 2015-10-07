package ru.vaszol.exam.web.forms;

import java.util.Collection;

import ru.vaszol.exam.logic.TestRequest;
import ru.vaszol.exam.logic.Vopotv;

public class TestOverForm {

	private boolean isDone;	
    private String studentName;
    private int error;
    private String picture;
    private int biletId;
    private Collection results;
    
	public boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Collection getResults() {
		return results;
	}
	public void setResults(Collection results) {
		this.results = results;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getBiletId() {
		return biletId;
	}
	public void setBiletId(int biletId) {
		this.biletId = biletId;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
}

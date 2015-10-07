package ru.vaszol.exam.web.forms;

import java.util.Collection;

public class ManagerTestForm {
	
	
	private Collection sessions;		//набор сессий
	private Collection bilets;			//набор билетов
	private Collection results;			//набор результатов
	private Collection otvets;			//набор ответов
		
	public Collection getSessions() {
		return sessions;
	}
	public void setSessions(Collection sessions) {
		this.sessions = sessions;
	}
	public Collection getBilets() {
		return bilets;
	}
	public void setBilets(Collection bilets) {
		this.bilets = bilets;
	}
	public Collection getResults() {
		return results;
	}
	public void setResults(Collection results) {
		this.results = results;
	}
	public Collection getOtvets() {
		return otvets;
	}
	public void setOtvets(Collection otvets) {
		this.otvets = otvets;
	}
}

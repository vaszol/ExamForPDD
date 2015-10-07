package ru.vaszol.exam.logic;

public class Bilet {
	private int biletId;
    private String nameBilet;
    
    public int getBiletId() {
		return biletId;
	}
    public void setBiletId(int biletId) {
		this.biletId = biletId;
	}
    public String getNameBilet() {
		return nameBilet;
	}
    public void setNameBilet(String nameBilet) {
		this.nameBilet = nameBilet;
	}
    @Override
    public String toString() {
    	return nameBilet;
    }
}

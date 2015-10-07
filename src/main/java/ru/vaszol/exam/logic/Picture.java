package ru.vaszol.exam.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Picture {
	private int pictureId;
    private String namePicture;
	public Picture(ResultSet rs) throws SQLException {
		setPictureId(rs.getInt(1));
        setNamePicture(rs.getString(2));  
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public String getNamePicture() {
		return namePicture;
	}
	public void setNamePicture(String namePicture) {
		this.namePicture = namePicture;
	}
	public String toString() {
        return namePicture;
    }
}

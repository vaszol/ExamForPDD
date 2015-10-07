package ru.vaszol.exam.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagementSystem {
	private static Connection connection;
    private static ManagementSystem instance;
    private static DataSource dataSource;
 
    private ManagementSystem() {
    }
 
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            try {
                instance = new ManagementSystem();
                Context context = new InitialContext();
                instance.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/StudentsDS");
                connection = dataSource.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    /**получаем сущность группы**/
    public List getGroups() throws SQLException {
        List groups = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT group_id, groupName, curator, speciality FROM groups");
        while (rs.next()) {
            Group gr = new Group();
            gr.setGroupId(rs.getInt(1));
            gr.setNameGroup(rs.getString(2));
            gr.setCurator(rs.getString(3));
            gr.setSpeciality(rs.getString(4));
            groups.add(gr);
        }
        rs.close();
        stmt.close();
        return groups;
    }
    
    /**получаем сущногсть студенты**/
    public Collection getAllStudents() throws SQLException {
        Collection students = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM voproses ORDER BY surName, firstName, patronymic");
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }
     
    /**получаем студентов из нужной группы**/
    public Collection getStudentsFromGroup(Group group, int year) throws SQLException {
        Collection students = new ArrayList();
        PreparedStatement stmt = connection.prepareStatement("SELECT student_id, firstName, patronymic, surName, "
                + "sex, dateOfBirth, group_id, educationYear FROM students "
                + "WHERE group_id =  ? AND  educationYear =  ? "
                + "ORDER BY surName, firstName, patronymic");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student st = new Student(rs);
            students.add(st);
        }
        rs.close();
        stmt.close();
        return students;
    }     
    
    /**получаем нужного студента**/
    public Student getStudentById(int studentId) throws SQLException {
        Student student = null;
        PreparedStatement stmt = connection.prepareStatement("SELECT student_id, firstName, patronymic, surName,"
                + "sex, dateOfBirth, group_id, educationYear FROM students WHERE student_id = ?");
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student = new Student(rs);
        }
        rs.close();
        stmt.close();
        return student;
    }
    
    /** перемещаем студентов из одной группы в другую группу**/
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE students SET group_id =  ?, educationYear=? "
                + "WHERE group_id =  ? AND  educationYear = ?");
        stmt.setInt(1, newGroup.getGroupId());
        stmt.setInt(2, newYear);
        stmt.setInt(3, oldGroup.getGroupId());
        stmt.setInt(4, oldYear);
        stmt.execute();
    }
    /** удаляем студентов группы **/
    public void removeStudentsFromGroup(Group group, int year) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM students WHERE group_id = ? AND educationYear = ?");
        stmt.setInt(1, group.getGroupId());
        stmt.setInt(2, year);
        stmt.execute();
    }
    /** добавить студента **/
    public void insertStudent(Student student) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO students "
                + "(firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)"
                + "VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?)");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, new String(new char[]{student.getSex()}));
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.execute();
    }
    
    /** обновить студента по индексу **/
    public void updateStudent(Student student) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE students "
                + "SET firstName=?, patronymic=?, surName=?, sex=?, dateOfBirth=?, group_id=?,"
                + "educationYear=? WHERE student_id=?");
        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getPatronymic());
        stmt.setString(3, student.getSurName());
        stmt.setString(4, new String(new char[]{student.getSex()}));
        stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
        stmt.setInt(6, student.getGroupId());
        stmt.setInt(7, student.getEducationYear());
        stmt.setInt(8, student.getStudentId());
        stmt.execute();
    }
    /** удалить студента по индексу **/
    public void deleteStudent(Student student) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM students WHERE student_id =  ?");
        stmt.setInt(1, student.getStudentId());
        stmt.execute();
    }
    /** -----------билет------------- **/
    /**получаем сущность билетов**/
    public List getBilets() throws SQLException {
        List bilets = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT bilet_id, biletName FROM bilets ORDER BY bilet_id");
        while (rs.next()) {
            Bilet bl = new Bilet();
            bl.setBiletId(rs.getInt(1));
            bl.setNameBilet(rs.getString(2));
            bilets.add(bl);
        }
        rs.close();
        stmt.close();
        return bilets;
    }
    
    /** -----------вопрос+ответ------------- **/
    
    /**получаем вопросы из базы**/
    public Collection getAllVopotv() throws SQLException {
        Collection vopotv = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT vopros_id, bilet_id, voprosText, picture, theme,"
        		+ " otvetText1, otvetText2, otvetText3, otvetText4, otvetText5, otvetText6, vopname"
                + " FROM vopotv ORDER BY vopros_id, vopname");        
        while (rs.next()) {
        	Vopotv st = new Vopotv(rs);
        	vopotv.add(st);
        }
        rs.close();
        stmt.close();
        return vopotv;
    }
    
    /**получаем вопросы из нужного билета**/
    public Collection getVopotvFromBilet(Bilet bilet) throws SQLException {
        Collection vopotv = new ArrayList();
        PreparedStatement stmt = connection.prepareStatement("SELECT vopros_id, bilet_id, voprosText, picture, theme,"
        		+ " otvetText1, otvetText2, otvetText3, otvetText4, otvetText5, otvetText6, vopname"
                + " FROM vopotv "        		
                + " WHERE bilet_id =  ? "
                + " ORDER BY bilet_id, vopname");
        stmt.setInt(1, bilet.getBiletId());        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	Vopotv st = new Vopotv(rs);
        	vopotv.add(st);
        }
        rs.close();
        stmt.close();
        return vopotv;
    }
        
    /**получаем нужный вопрос**/
    public Vopotv getVopotvById(int voprosId) throws SQLException {
    	Vopotv vopotv = null;
        PreparedStatement stmt = connection.prepareStatement("SELECT vopros_id, bilet_id, voprosText, picture, theme,"
        		+ " otvetText1, otvetText2, otvetText3, otvetText4, otvetText5, otvetText6, vopname"
                + " FROM vopotv WHERE vopros_id =  ? "
                + " ORDER BY vopros_id, vopname");
        stmt.setInt(1, voprosId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	vopotv = new Vopotv(rs);
        }
        rs.close();
        stmt.close();
        return vopotv;
    }

    /** обновить вопрос по индексу **/
	public void updateVopotv(Vopotv vopotv) throws SQLException{
		PreparedStatement stmt = connection.prepareStatement("UPDATE vopotv "
                +" SET vopros_id=?, bilet_id=?, voprosText=?, picture=?, theme=?,"
                +" otvetText1=?, otvetText2=?, otvetText3=?, otvetText4=?, otvetText5=?, otvetText6=? ,vopname=?"
                +" WHERE vopros_id=?");
		stmt.setLong(1, vopotv.getVoprosId());
        stmt.setLong(2, vopotv.getBiletId());
        stmt.setString(3, vopotv.getVoprosText());
        stmt.setLong(4, vopotv.getPicture());        
        stmt.setLong(5, vopotv.getTheme());
        stmt.setString(6, vopotv.getOtvetText1());
        stmt.setString(7, vopotv.getOtvetText2());
        stmt.setString(8, vopotv.getOtvetText3());
        stmt.setString(9, vopotv.getOtvetText4());
        stmt.setString(10, vopotv.getOtvetText5());
        stmt.setString(11, vopotv.getOtvetText6());
        stmt.setString(12, vopotv.getVopname());
        stmt.setLong(13, vopotv.getVoprosId());
        stmt.execute();		
	}

    /** добавить вопрос, если есть запись, то заменить её **/
    public void insertVopotv(Vopotv vopotv) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(" INSERT INTO vopotv (vopros_id, bilet_id, voprosText, picture, theme,"
                		+" otvetText1, otvetText2, otvetText3, otvetText4, otvetText5, otvetText6, vopname)"
                		+" VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)"                		                    
                		+" ON DUPLICATE KEY UPDATE `bilet_id`=' ?', `voprosText`=' ?',"
                		+" `picture`='?', `theme`='?', `vopname`='?',"
                		+" `otvetText1`='?', `otvetText2`='?',"
                		+" `otvetText3`='?', `otvetText4`='?',"
                		+" `otvetText5`='?', `otvetText6`='?'");
        
        
        /////////////////////вставить:
        stmt.setLong(1, vopotv.getVoprosId());
        stmt.setLong(2, vopotv.getBiletId());
        stmt.setString(3, vopotv.getVoprosText());
        stmt.setLong(4, vopotv.getPicture());        
        stmt.setLong(5, vopotv.getTheme());
        stmt.setString(6, vopotv.getOtvetText1());
        stmt.setString(7, vopotv.getOtvetText2());
        stmt.setString(8, vopotv.getOtvetText3());
        stmt.setString(9, vopotv.getOtvetText4());
        stmt.setString(10, vopotv.getOtvetText5());
        stmt.setString(11, vopotv.getOtvetText6());
        stmt.setString(12, vopotv.getVopname());
        ///////////////////////иначе заменить:////////
        stmt.setLong(13, vopotv.getBiletId());
        stmt.setString(14, vopotv.getVoprosText());
        stmt.setLong(15, vopotv.getPicture());        
        stmt.setLong(16, vopotv.getTheme());
        stmt.setString(17, vopotv.getVopname());
        stmt.setString(18, vopotv.getOtvetText1());
        stmt.setString(19, vopotv.getOtvetText2());
        stmt.setString(20, vopotv.getOtvetText3());
        stmt.setString(21, vopotv.getOtvetText4());
        stmt.setString(22, vopotv.getOtvetText5());
        stmt.setString(23, vopotv.getOtvetText6());
        
        stmt.execute();
    }
    
    /** удалить вопрос по индексу **/
    public void deleteVopotv(Vopotv vopotv) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM vopotv WHERE vopros_id =  ?");
        stmt.setInt(1, vopotv.getVoprosId());
        stmt.execute();
    }
    
    /** -----------картинка------------- **/
    /**получаем нужную картинку**/
    public Picture getPictureById(int pictureId) throws SQLException {
    	Picture pic = null;
        PreparedStatement stmt = connection.prepareStatement("SELECT picture_id, namePicture,"
                + " FROM picture WHERE picture_id = ?");
        stmt.setInt(1, pictureId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pic = new Picture(rs);
        }
        rs.close();
        stmt.close();
        return pic;
    }
    /**получаем имя картинки**/
    public String getPictureStringById(int pictureId) throws SQLException {
    	String pic ="";
        PreparedStatement stmt = connection.prepareStatement("SELECT namePicture"
                + " FROM picture WHERE picture_id = ?");
        stmt.setInt(1, pictureId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            pic = (rs.getString(1));
        }
        rs.close();
        stmt.close();
        return pic;
    }
    /** -----------ответ------------- **/
    /** получить ответ **/
    public Otvet getOtvetsById(int otvetsId) throws SQLException {
    	Otvet otvet = null;
        PreparedStatement stmt = connection.prepareStatement("SELECT vopros_id, otvet"
                + " FROM otvets WHERE vopros_id =  ? ");
        stmt.setInt(1, otvetsId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
        	otvet = new Otvet(rs);
        }
        rs.close();
        stmt.close();
        return otvet;
    }
    /** добавить ответ **/
    public void insertOtvet(Otvet otvet) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO otvets "
                + "(vopros_id, otvet)"
                + "VALUES( ?,  ?)");
        stmt.setLong(1, otvet.getVopros_id());
        stmt.setLong(2, otvet.getOtvet());        
        stmt.execute();
    }
    /** обновить ответ по индексу **/
    public void updateStudent(Otvet otvet) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE otvets "
                + " SET vopros_id=?, otvet=?"
                + " WHERE student_id=?");
        stmt.setLong(1, otvet.getVopros_id());
        stmt.setLong(2, otvet.getOtvet());
        stmt.setLong(3, otvet.getVopros_id());
        stmt.execute();
    }
    
    /** -----------результат теста------------- **/
    /** получить результат теста по индексу**/
    public TestRequest getTestRequestId(int testRequest_id) throws SQLException {
    	TestRequest testRequest = null;
        PreparedStatement stmt = connection.prepareStatement("SELECT testRequest_id, sessionName,"
        		+ "vopros_id, OrderDate,otvet,TrueOtvet"
                + " FROM testRequest WHERE testRequest_id =  ? ");
        stmt.setInt(1, testRequest_id);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
        	testRequest = new TestRequest(rs);
        }
        rs.close();
        stmt.close();
        return testRequest;
    }
    /** получить результаты теста **/
    public List getCurTestRequestBySession(String SessionName) throws SQLException {
    	List testRequest = new ArrayList();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM testRequest "
        		+ "where sessionName=? and OrderDate BETWEEN (SELECT MAX(OrderDate) "
        		+ "FROM testRequest where vopros_id=0 and sessionName=?) and now()");
        stmt.setString(1, SessionName);
        stmt.setString(2, SessionName);        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	if(rs.getInt(3)!=0){ //пропускаем нулевые индексы
        		TestRequest tr = new TestRequest(rs);
            	testRequest.add(tr);	
			}        	
        }
        rs.close();
        stmt.close();
        return testRequest;
    }
    
    public List getCurTestRequestBySession2(String SessionName) throws SQLException {
    	List testRequest = new ArrayList();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM testRequest "
        		+ "where sessionName=? and OrderDate BETWEEN (SELECT MAX(OrderDate) "
        		+ "FROM testRequest where vopros_id=0 and sessionName=?) and now()");
        stmt.setString(1, SessionName);
        stmt.setString(2, SessionName);        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	TestRequest tr = new TestRequest(rs);
            testRequest.add(tr);	
		}
        rs.close();
        stmt.close();
        return testRequest;
    }
    /** добавить результат теста **/
    public void insertTestRequest(TestRequest testRequest) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO testRequest "
                + "( sessionName, vopros_id, otvet, TrueOtvet)"
                + "VALUES( ?,  ?,  ?,  ?)");
        //stmt.setLong(1, testRequest.getTestRequest_id());
        stmt.setString(1, testRequest.getSessionName());
        stmt.setLong(2, testRequest.getVopros_id());
        stmt.setLong(3, testRequest.getOtvet());
        stmt.setLong(4, testRequest.getTrueOtvet());
        stmt.execute();
    }
    /** обновить результат теста по индексу **/
    public void updateTestRequest(TestRequest testRequest) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE testRequest "
                + " SET testRequest_id=?, sessionName=?, vopros_id, otvet"
                + " WHERE testRequest_id=?");
        stmt.setLong(1, testRequest.getTestRequest_id());
        stmt.setString(2, testRequest.getSessionName());
        stmt.setLong(3, testRequest.getVopros_id());
        stmt.setLong(4, testRequest.getOtvet());
        stmt.setLong(5, testRequest.getTestRequest_id());
        stmt.execute();
    }
    
    
    /** -----------Сессии------------- **/
    /** получить результат сессий**/
    public List getSessions() throws SQLException {
    	List sessions = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, session_id, session_num, "
        		+ "ip, stat, cat, bilet_id, OrderDate FROM mysession");
        //"SELECT group_id, groupName, curator, speciality FROM groups"
        while (rs.next()) {
        	MySession ses = new MySession(rs);
            sessions.add(ses);
        }
        rs.close();
        stmt.close();
        return sessions;
    }
    /** получить результат сессий
     * @param ipAddr **/
    public MySession getSessionsFromIp(String ipAddr) throws SQLException {
    	MySession ses = null;
    	PreparedStatement stmt = connection.prepareStatement("SELECT id, session_id, session_num, "
        		+ "ip, stat, cat, bilet_id, OrderDate FROM mysession"
        		+ "where ip=?");
        stmt.setString(1, ipAddr);
        ResultSet rs = stmt.executeQuery();
        //"SELECT group_id, groupName, curator, speciality FROM groups"
        while (rs.next()) {
        	ses = new MySession(rs);            
        }
        rs.close();
        stmt.close();
        return ses;
    }
    
    
    
    
    
    /** обновить результат сессий **/
    public void updateSessions(MySession session) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE mysession "
        		+ "SET session_id=?,session_num=?,`ip`=?,"
        		+ "stat=?,bilet_id=?"
        		+ " WHERE ip=?");
        stmt.setString(1, session.getSession_id());
        stmt.setInt(2, session.getSession_num());
        stmt.setString(3, session.getAddrIp());
        stmt.setInt(4, session.getStat());
        stmt.setInt(5, session.getBilet_id());
        stmt.setString(6, session.getAddrIp());
        stmt.execute();
    }
    
    
    //INSERT INTO `sessions`(`session_id`, `session_num`, `ip`, `stat`, `cat`, `bilet_id`, `OrderDate`) VALUES (777,666,555,0,0,1,now())
    
    public void insertMySession(MySession mySession) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO `mysession`"
        		+ "(`session_id`, `session_num`, `ip`, `stat`, `cat`, `bilet_id`, `OrderDate`) "
        		+ "VALUES (?,?,?,?,?,?,now())");
        /**
        		"INSERT INTO sessions"
        		+ "(session_id, session_num, ip, stat, cat, bilet_id, OrderDate) "
        		+ "VALUES (?,  ?,  ?,  ?,  ?,  ?,  now())"
        		+ "ON DUPLICATE KEY UPDATE "
        		+ "session_id=?, session_num=?, "
        		+ "ip=?, stat=?, cat=?, "
        		+ "bilet_id=?, OrderDate=now()");
        		*/
        /////////////////////вставить:
        stmt.setString(1, mySession.getSession_id());
        stmt.setInt(2, mySession.getSession_num());
        stmt.setString(3, mySession.getAddrIp());
        stmt.setInt(4, mySession.getStat());
        stmt.setInt(5, mySession.getCat());
        stmt.setInt(6, mySession.getBilet_id());
        //stmt.setDouble(7, mySession.getOrderDate());
        ///////////////////////иначе заменить:////////        
       /** stmt.setString(8, mySession.getSession_id());
        stmt.setInt(9, mySession.getSession_num());
        stmt.setString(10, mySession.getAddrIp());
        stmt.setInt(11, mySession.getStat());
        stmt.setInt(12, mySession.getCat());
        stmt.setInt(13, mySession.getBilet_id());
        //stmt.setDouble(14, mySession.getOrderDate());
        */
        stmt.execute();
    }
    
    
    ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////
    
    
	public void updateSessionsFIO(MySession session) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("UPDATE `mysession` "
				+ "SET `session_id`=?,`session_num`=?,"
				+ "`ip`=?,`stat`=?,"
				+ "`bilet_id`=?,`fio`=? "
				+ " WHERE ip=?"
				);
        stmt.setString(1, session.getSession_id());
        stmt.setInt(2, session.getSession_num());
        stmt.setString(3, session.getAddrIp());
        stmt.setInt(4, session.getStat());
        stmt.setInt(5, session.getBilet_id());
        stmt.setString(6, session.getFIO());
        stmt.setString(7, session.getAddrIp());
        stmt.execute();
	}

	public Collection getSessionsFIO() throws SQLException {
		List sessions = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, session_id, session_num, "
        		+ "ip, stat, cat, bilet_id, OrderDate, fio FROM mysession");
        //"SELECT group_id, groupName, curator, speciality FROM groups"
        while (rs.next()) {
        	MySession ses = new MySession(rs);
            sessions.add(ses);
        }
        rs.close();
        stmt.close();
        return sessions;
	}

	public void insertMySessionFIO(MySession mySession) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO `mysession`"
        		+ "(`session_id`, `session_num`, `ip`, `stat`, `cat`, `bilet_id`, `OrderDate`, `fio`) "
        		+ "VALUES (?,?,?,?,?,?,now(),?)");
        
        /////////////////////вставить:
        stmt.setString(1, mySession.getSession_id());
        stmt.setInt(2, mySession.getSession_num());
        stmt.setString(3, mySession.getAddrIp());
        stmt.setInt(4, mySession.getStat());
        stmt.setInt(5, mySession.getCat());
        stmt.setInt(6, mySession.getBilet_id());
        stmt.setString(7, mySession.getFIO());
       
        stmt.execute();
	}

}
package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO2 {
	
	private Connection conn;
	
	private ResultSet rs;
	
	public BbsDAO2() { try { 
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	String dbID = "system"; 
	
	String dbPassword = "green1234"; 
	
	Class.forName("oracle.jdbc.driver.OracleDriver"); 
	
	
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword); } 
	
	catch (Exception e) { 
		e.printStackTrace(); 
		} 
	}
	
	public String getDate() {
		String SQL = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL";
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString(1);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return "";
	}
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1)+1;
		}
		return 1;
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return -1;
	}
	
	public int write (String bbsTitle, String userID , String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
		}
	
	public ArrayList<Bbs> getList(int pageNumber){
		String SQL = "SELECT bbsid, bbsTitle, userID, bbsDate, bbsContent, bbsAvailable,PAGECOUNT FROM "
				+ "(SELECT /*+INDEX_DESC(bbs SYS_C008413)*/"
				+ "bbsid, bbsTitle, userID, bbsDate, bbsContent, bbsAvailable,ROWNUM PAGECOUNT FROM "
				+ "bbs WHERE bbsID > 0 AND bbsAvailable = 1 AND ROWNUM <= ?) "
				+ "WHERE PAGECOUNT > ? ORDER BY bbsID DESC";



				


		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber *10);
			pstmt.setInt(2, pageNumber*10 -10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		}
	
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable=1";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			return true;
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		}
	
	}
	
	


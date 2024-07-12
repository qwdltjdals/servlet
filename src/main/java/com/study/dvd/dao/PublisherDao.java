package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Publisher;
import com.study.dvd.util.DBConnectionMgr;

public class PublisherDao {
	
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static List<Publisher> searchPublisherByPublisherName(String searchText) { // 여러개 가져와야 하니까 리스트로 리턴
		List<Publisher> publishers = new ArrayList<Publisher>(); // 배열 생성
		Connection con = null; // 연결하기 위해서 필요
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			StringBuilder sql = new StringBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publishers;
	}
	
	
	public static int save(Publisher publisher) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, publisher.getPublisherName());
			successCount = pstmt.executeUpdate(); // 업데이트 해줌
			rs = pstmt.getGeneratedKeys();
			while(rs.next()) {
				publisher.setPublisherId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return successCount;
	}
	

}

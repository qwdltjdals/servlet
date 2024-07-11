package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Producer;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {

	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();

	public static List<Producer> searchProducerByProducerName(String searchText) {
	      List<Producer> pros = new ArrayList<>(); // 배열 생성
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

			try {
			con = pool.getConnection();
			// 쿼리문 만들기
			StringBuilder sql = new StringBuilder();
			sql.append("select * from producer_tb "); // 데이터베이스에 있는 테이블 이름
			sql.append("where producer_name like ? limit 0, 50"); // limit: 매개변수로 받아도 됨
			pstmt = con.prepareStatement(sql.toString()); 
			pstmt.setString(1, "%" + searchText + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Producer producer = Producer.builder()
						.producerId(rs.getInt(1)) // Į칼럼의 번호
						.producerName(rs.getString(2))
						.build();

				pros.add(producer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return pros;
	}
}
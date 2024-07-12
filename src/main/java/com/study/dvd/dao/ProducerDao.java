package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Producer;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {

	private static DBConnectionMgr pool = DBConnectionMgr.getInstance(); // 싱글톤 객제 가져옴

	public static List<Producer> searchProducerByProducerName(String searchText) { // 여러개 가져와야 하니까 리스트로 리턴
		List<Producer> pros = new ArrayList<Producer>(); // 배열 생성
		Connection con = null; // 연결하기 위해서 필요
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = pool.getConnection();
			// 쿼리문 만들기
			StringBuilder sql = new StringBuilder();
			sql.append("select * from producer_tb "); // 데이터베이스에 있는 테이블 이름
			sql.append("where producer_name like ? limit 0, 50"); // limit: 매개변수로 받아도 됨 이름으로 검색, 50개만
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchText + "%"); // % = 와일드카드 작성에 필요 / 위에 ? 에 들어감
			rs = pstmt.executeQuery(); // 결과 담기

			while (rs.next()) { // 이 함수가 호출될 때마다 row의 행이 바뀜
				Producer producer = Producer.builder() // 프로듀서 객체 호출
						.producerId(rs.getInt(1)) // Į칼럼의 번호
						.producerName(rs.getString(2)).build();

				pros.add(producer); // 배열에 객체 담아줌
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 객체 소멸 시키는 절차 / 다음에 재사용 할려고
		}

		return pros;
	}

	public static int save(Producer producer) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = pool.getConnection();
			String sql = "insert into producer_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // auto intrement된 값을 가져옴
			pstmt.setString(1, producer.getProducerName());
			successCount = pstmt.executeUpdate(); // 업데이트 해줌
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				producer.setProducerId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return successCount;
	}
}
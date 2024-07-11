package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Dvd;
import com.study.dvd.util.DBConnectionMgr;

public class DvdDao {
   private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
                                   // searchText: Ÿ��Ʋ
   public static List<Dvd> searchDvdByTitle(String searchText) {
      List<Dvd> dvds = new ArrayList<>();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         con = pool.getConnection();
         // 쿼리문 만들기
         StringBuilder sql = new StringBuilder();
         sql.append("select * from dvd_view ");
         sql.append("where title like ? limit 0, 50"); //limit: 매개변수로 받아도 됨
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, "%" + searchText + "%");
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            Dvd dvd = Dvd.builder()
                  .dvdId(rs.getInt(1)) // Į칼럼의 번호
                  .registrationNumber(rs.getString(2))
                  .title(rs.getString(3))
                  .producerId(rs.getInt(4))
                  .producerName(rs.getString(5))
                  .publisherId(rs.getInt(6))
                  .publisherName(rs.getString(7))
                  .publicationYear(rs.getInt(8))
                  .databaseDate(rs.getDate(9).toLocalDate())
                  .build();
            
            dvds.add(dvd);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         pool.freeConnection(con, pstmt, rs);
      }
      
      return dvds;
   }
}
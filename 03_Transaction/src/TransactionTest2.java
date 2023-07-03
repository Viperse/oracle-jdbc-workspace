import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ServerInfo;

public class TransactionTest2 {

	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
			
			// 2. DB 연결
			Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
			System.out.println("DB 연결 완료");
			
			PreparedStatement st1 = conn.prepareStatement("SELECT * FROM bank");
			ResultSet rs = st1.executeQuery();
			
			System.out.println("================ 은행 조회 =================");
			while(rs.next()) {
				
				System.out.println(rs.getString("name") + " / " + rs.getString("bankname") + " / " + rs.getInt("balance"));
			}
			
			System.out.println("=========================================");
			
			/*
			 * 민소 -> 도경 : 50만원씩 이체
			 * 이 관련 모든 쿼리를 하나로 묶는다... 하나의 단일 트랜잭션 setAutocommit(), commit(), rollback()... 등등
			 * 사용을 해서 민소님의 잔액이 마이너스가 되면 이체 취소가 되어야 함!
			 * */
			
			conn.setAutoCommit(false);
			
			PreparedStatement st2 = conn.prepareStatement("UPDATE bank SET balance = balance - 500000 WHERE name = ?");
			st2.setString(1, "김민소");			
			int result1 = st2.executeUpdate();
			
			if(result1 == 1) {
				conn.commit();
			} else conn.rollback();
			
			PreparedStatement st3 = conn.prepareStatement("UPDATE bank SET balance = balance + 500000 WHERE name = ?");
			st3.setString(1, "김도경");
			int result2 = st3.executeUpdate();
			
			if(result2 == 1) {
				conn.commit();
			} else conn.rollback();
						
			PreparedStatement st4 = conn.prepareStatement("SELECT balance FROM bank WHERE name = ?");
			st4.setString(1, "김민소");
			
			ResultSet rs2 = st4.executeQuery();
			
			if(rs2.next()) {
				int balance = rs2.getInt("balance");
				
				if(balance < 0) {
					conn.rollback();
				} else conn.commit();
			}
			
			conn.close();
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			rs.close();
			rs2.close();					
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

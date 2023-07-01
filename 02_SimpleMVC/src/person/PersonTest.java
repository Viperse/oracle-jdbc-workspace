package person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import person.config.ServerInfo;

public class PersonTest {
	
	Properties p = new Properties();
	
	public PersonTest() {
		try {
			p.load(new FileInputStream("src/person/config/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 고정적인 반복 -- 디비연결, 자원반납
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}
	
	public void closeAll(Connection conn, PreparedStatement st) throws SQLException {
		if(st != null) st.close();
		if(conn != null) conn.close();
		
	}
	
	public void closeAll(Connection conn, PreparedStatement st, ResultSet rs) throws SQLException {
		if(rs != null) rs.close();
		closeAll(conn, st);
	}
	
	// 변동적인 반복... 비즈니스 로직 DAO(Database Access Object)
	public void addPerson(String name, String address) {
						
		try {
			
			Connection conn = getConnect();
			 
			String query = p.getProperty("jdbc.sql.insert");
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(1, name);
			st.setString(2, address);
			
			int result = st.executeUpdate();
			
			if(result == 1) {
				System.out.println(name + "님 추가");
			}
			
			closeAll(conn, st);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void removePerson(int id) {
		
		
		try {
			
			Connection conn = getConnect();
			
			String query = p.getProperty("jdbc.sql.delete");
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setInt(1, id);
			
			int result = st.executeUpdate();
			
			System.out.println(result + "명 삭제!");	
			
			closeAll(conn, st);

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void updatePerson(int id, String address) {
		
		
		try {
			
			Connection conn = getConnect();
		
			String query = p.getProperty("jdbc.sql.update");
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(1, address);
			st.setInt(2, id);
			
			int result = st.executeUpdate();
			
			System.out.println(result + "명 수정!");	
			
			closeAll(conn, st);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void searchAllPerson() {
		
		try {
			
			Connection conn = getConnect();
			
			String query = p.getProperty("jdbc.sql.select");
			PreparedStatement st = conn.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				
				System.out.println(id + " / " + name + " / " + address);
				
			}
			
			closeAll(conn, st);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void viewPerson(int id) {
		
		try {
			
			Connection conn = getConnect();
			
			String query = p.getProperty("jdbc.sql.select2");
			PreparedStatement st = conn.prepareStatement(query);

			st.setInt(1, id);			
			st.executeUpdate();
			
			ResultSet rs = st.executeQuery();
			
			String name = rs.getString("name");
			String address = rs.getString("address");
			
			System.out.println(id +  " / " + name + " / " + address);
			
			closeAll(conn, st);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		
		


	}

	public static void main(String[] args) {
		
		PersonTest pt = new PersonTest();
		
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
//			pt.addPerson("김강우", "서울");
//			pt.addPerson("고아라", "경기도");
//			pt.addPerson("강태주", "경기도");
			
//			pt.searchAllPerson();
			
//			pt.removePerson(15); // 강태주 삭제
//			
//			pt.searchAllPerson();
			
			pt.updatePerson(13, "제주도");
			
			pt.searchAllPerson();
			
//			pt.viewPerson(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}


}

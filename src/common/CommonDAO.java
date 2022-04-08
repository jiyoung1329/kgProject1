package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonDAO {
		private String url ="jdbc:oracle:thin:@kgproject_high?TNS_ADMIN=C:/Wallet_kgProject";
		private String user = "admin";
		private String pwd = "KGproject1234!";
		
	public Connection makeConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}

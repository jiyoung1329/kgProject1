package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonDAO {
		private static String url ="jdbc:oracle:thin:@kgproject_high?TNS_ADMIN=/Users/gayeonkim/Downloads/Wallet_kgProject";
		private static String user = "admin";
		private static String pwd = "KGproject1234!";
		
	public static Connection makeConnection() {
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

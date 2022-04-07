package login;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public static void main(String[] args) {
		String url ="jdbc:oracle:thin:@db202203302024_high?TNS_ADMIN=C:/Wallet_kgProject";
		String user = "admin";
		String pwd = "KGproject1234!";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

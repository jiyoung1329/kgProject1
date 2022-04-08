package login;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public static void main(String[] args) {
		//String url ="jdbc:oracle:thin:@db202203302024_high?TNS_ADMIN=Wallet_DB202203302024";전자지갑경로입력
		String url ="jdbc:oracle:thin:@kgproject_high?TNS_ADMIN=C:/Wallet_kgProject";

		String user = "admin";
		String pwd = "KGproject1234!";
		

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, pwd);

			System.out.println("드라이브 연결 성공");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
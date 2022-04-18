package room.choice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.CommonDAO;
import room.select.SelectDTO;

public class RoomChoiceDAO { //##0414
	private CommonDAO commonDAO;
	private Connection con;
	
	public RoomChoiceDAO () {
		commonDAO = new CommonDAO();
		con = commonDAO.makeConnection();
	}
	
	public SelectDTO selectNum(int number) { //방번호로 DB에 있는 값을 가져옮
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectDTO selectDto = null;
		String sql = "SELECT * FROM room WHERE num = ?"; // memberDB에서 방번호가 가진 정보를 모두 가져옴

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, number);
			rs = ps.executeQuery();
			if (rs.next()) {
				selectDto = new SelectDTO();
				selectDto.setNum(rs.getInt("num"));
				selectDto.setIsReservation(rs.getInt("isreservation"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectDto;
		
	}
	
	
}

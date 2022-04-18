package room.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.CommonDAO;
import login.LoginDTO;

public class SelectDAO {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private CommonDAO commonDao;//##0414
	
	public SelectDAO() {
		commonDao = new CommonDAO();
		conn = CommonDAO.makeConnection();
	}
	

	
	public void saveSongCount(int count, LoginDTO dto) {
		String query = "update member set songcount = ? where id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, dto.getSongConut() - count);
			ps.setString(2, dto.getId());
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public int checkRoomReserve(String room) {
		String query = "select isreservation from room where num=?";
		int isReservation = 0;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, room);
			rs = ps.executeQuery();
			if (rs.next()) {
				isReservation = Integer.parseInt(rs.getString("isreservation")); 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return isReservation;
		
	}
	//###220414
	public SelectDTO selectNum(int num) { //방번호로 DB에 있는 값을 가져옮
		SelectDTO selectDto = null;
		String sql = "SELECT * FROM room WHERE num = ?"; // memberDB에서 방번호가 가진 정보를 모두 가져옴

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
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
	//###220414
	
	public void update(SelectDTO isRes) {
		String sql = "UPDATE room SET isReservation=? WHERE num = ?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, isRes.getNum());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

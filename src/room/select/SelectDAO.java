package room.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.CommonDAO;
import login.LoginDTO;

public class SelectDAO {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public SelectDAO() {
		conn = CommonDAO.makeConnection();
		
	}
	
	// 임시 함수 : 임시회원 번호 저장
	public LoginDTO callMember() {
		LoginDTO dto = new LoginDTO();
		String query = "select * from member where id = 'user5'";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setIsAdmin(rs.getInt("isadmin"));
				dto.setMobile(rs.getString("mobile"));
				dto.setPw(rs.getString("pw"));
				dto.setSongConut(rs.getInt("songcount"));
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
		return dto;
		
		
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
		int isReservation = -1;
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
}

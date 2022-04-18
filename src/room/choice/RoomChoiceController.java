package room.choice;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import room.select.SelectController;
import room.select.SelectDAO;
import room.select.SelectDTO;

public class RoomChoiceController implements Initializable {
	private RoomChoiceService rcs;
	private Parent rcForm;
	private RoomChoiceDAO roomChoiceDAO;
	private SelectDAO selectDAO;
	private SelectDTO selectDTO;
	private Status status;
	@FXML private Button room1;
	@FXML private Button room2;
	@FXML private Button room3;
	@FXML private Button room4;
	@FXML private Button room5;
	@FXML private Button room6;
	@FXML private Button room7;
	@FXML private Button room8;
	@FXML private Button room9;
	@FXML private Button room10;
	@FXML private Button room11;
	@FXML private Button room12;
	
	public void setRcForm(Parent rcForm) {
		this.rcForm = rcForm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rcs = new RoomChoiceService();
		roomChoiceDAO = new RoomChoiceDAO();
		if(roomChoiceDAO.selectNum(1).getIsReservation() == 1) {
			room1.setDisable(true);
		}
		if(roomChoiceDAO.selectNum(2).getIsReservation() == 1) {
			room2.setDisable(true);
		}if(roomChoiceDAO.selectNum(3).getIsReservation() == 1) {
			room3.setDisable(true);
		}if(roomChoiceDAO.selectNum(4).getIsReservation() == 1) {
			room4.setDisable(true);
		}if(roomChoiceDAO.selectNum(5).getIsReservation() == 1) {
			room5.setDisable(true);
		}if(roomChoiceDAO.selectNum(6).getIsReservation() == 1) {
			room6.setDisable(true);
		}if(roomChoiceDAO.selectNum(7).getIsReservation() == 1) {
			room7.setDisable(true);
		}if(roomChoiceDAO.selectNum(8).getIsReservation() == 1) {
			room8.setDisable(true);
		}if(roomChoiceDAO.selectNum(9).getIsReservation() == 1) {
			room9.setDisable(true);
		}if(roomChoiceDAO.selectNum(10).getIsReservation() == 1) {
			room10.setDisable(true);
		}if(roomChoiceDAO.selectNum(11).getIsReservation() == 1) {
			room11.setDisable(true);
		}if(roomChoiceDAO.selectNum(12).getIsReservation() == 1) {
			room12.setDisable(true);
		}
		
	}
	
	public void rmProc1() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(1);// -> 1번방 가져오는거
		status.setSelectDTO(selectDTO);
		
		roomDisabled(1);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc2() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(2);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(2);
				
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc3() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(3);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(3);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc4() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(4);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(4);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc5() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(5);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(5);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc6() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(6);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(6);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc7() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(7);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(7);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc8() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(8);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(8);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc9() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(9);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(9);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc10() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(10);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(10);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc11() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(11);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(11);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void rmProc12() throws Exception{
		roomChoiceDAO = new RoomChoiceDAO();
		
		selectDTO = roomChoiceDAO.selectNum(12);
		status.setSelectDTO(selectDTO);
		
		roomDisabled(12);
		
		rcs.rmProc();
		CommonService.windowClose(rcForm);
	}
	
	public void roomDisabled(int tmp) {
		roomChoiceDAO = new RoomChoiceDAO();
		
		tmp = status.getSelectDTO().getNum();
		selectDTO = roomChoiceDAO.selectNum(tmp);// -> 1번방 가져오는거
		
		int reserConfirm = selectDTO.getIsReservation();
		if(tmp == 1) {		
			if(reserConfirm == 1)
				room1.setDisable(true);
		}else if(tmp == 2) {
			if(reserConfirm == 1)
				room2.setDisable(true);
		}else if(tmp == 3) {
			if(reserConfirm == 1)
				room3.setDisable(true);
		}else if(tmp == 4) {
			if(reserConfirm == 1)
				room4.setDisable(true);
		}else if(tmp == 5) {
			if(reserConfirm == 1)
				room5.setDisable(true);
		}else if(tmp == 6) {
			if(reserConfirm == 1)
				room6.setDisable(true);
		}else if(tmp == 7) {
			if(reserConfirm == 1)
				room7.setDisable(true);
		}else if(tmp == 8) {
			if(reserConfirm == 1)
				room8.setDisable(true);
		}else if(tmp == 9) {
			if(reserConfirm == 1)
				room9.setDisable(true);
		}else if(tmp == 10) {
			if(reserConfirm == 1)
				room10.setDisable(true);
		}else if(tmp == 11) {
			if(reserConfirm == 1)
				room11.setDisable(true);
		}else if(tmp ==12) {
			if(reserConfirm == 1)
				room12.setDisable(true);
		}
	}


}

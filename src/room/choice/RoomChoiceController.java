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
		roomChoice(1);
	}
	
	public void rmProc2() throws Exception{
		roomChoice(2);
	}
	
	public void rmProc3() throws Exception{
		roomChoice(3);
	}
	
	public void rmProc4() throws Exception{
		roomChoice(4);
	}
	
	public void rmProc5() throws Exception{
		roomChoice(5);
	}
	
	public void rmProc6() throws Exception{
		roomChoice(6);
	}
	
	public void rmProc7() throws Exception{
		roomChoice(7);
	}
	
	public void rmProc8() throws Exception{
		roomChoice(8);
	}
	
	public void rmProc9() throws Exception{
		roomChoice(9);
	}
	
	public void rmProc10() throws Exception{
		roomChoice(10);
	}
	
	public void rmProc11() throws Exception{
		roomChoice(11);
	}
	
	public void rmProc12() throws Exception{
		roomChoice(12);
	}
	
	public void roomChoice(int num) {
		try {
			roomChoiceDAO = new RoomChoiceDAO();

			selectDTO = roomChoiceDAO.selectNum(num);
			status.setSelectDTO(selectDTO);
			
			rcs.rmProc();
			CommonService.windowClose(rcForm);
		} catch(Exception e) {}
	}


}

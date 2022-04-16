package room.choice;

import login.LoginDTO;
import room.charge.ChargeController;
import room.select.SelectController;
import room.select.SelectDTO;

public class Status {
	private static LoginDTO loginDTO;
	private static ChargeController chargeController;
	//###220414
	private static SelectDTO selectDTO;
	private static SelectController selectController;
	
	public static SelectDTO getSelectDTO() {
		return selectDTO;
	}
	
	public static void setSelectDTO(SelectDTO selectDTO) {
		Status.selectDTO = selectDTO;
	}
	
	public static SelectController getSelectController() {
		return selectController;
	}
	
	public static void setSelectController(SelectController selectController) {
		Status.selectController = selectController;
	}
	
	//###220414

	public static LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public static void setLoginDTO(LoginDTO loginDTO) {
		Status.loginDTO = loginDTO;
	}

	public static ChargeController getChargeController() {
		return chargeController;
	}

	public static void setChargeController(ChargeController chargeController) {
		Status.chargeController = chargeController;
	}
	
	
	
}

package room.choice;

import login.LoginDTO;
import room.charge.ChargeController;

public class Status {
	private static LoginDTO loginDTO;
	private static ChargeController chargeController;
	
	

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

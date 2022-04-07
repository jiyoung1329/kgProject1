package main;

import javafx.scene.Parent;
import roomChoice.RoomChoiceController;
import roomChoice.RoomChoiceService;
import roomMenu.RoomMenuController;
import roomMenu.RoomMenuService;

public class Controller {
	private RoomChoiceService rsc;
	private RoomMenuService rms;
	private Parent testForm;
	private RoomChoiceController rcc;
	private RoomMenuController rmc;
	
	public Controller() {
		rsc = new RoomChoiceService();
		rsc.setController(this);
	}

	public RoomChoiceService getRsc() {
		return rsc;
	}

	public void setRsc(RoomChoiceService rsc) {
		this.rsc = rsc;
	}

	public RoomMenuService getRms() {
		return rms;
	}

	public void setRms(RoomMenuService rms) {
		this.rms = rms;
	}

	public Parent getTestForm() {
		return testForm;
	}

	public void setTestForm(Parent testForm) {
		this.testForm = testForm;
	}

	public RoomChoiceController getRcc() {
		return rcc;
	}

	public void setRcc(RoomChoiceController rcc) {
		this.rcc = rcc;
	}

	public RoomMenuController getRmc() {
		return rmc;
	}

	public void setRmc(RoomMenuController rmc) {
		this.rmc = rmc;
	}
	
	

}

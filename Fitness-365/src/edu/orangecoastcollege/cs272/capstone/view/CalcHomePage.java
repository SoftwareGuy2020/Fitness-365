package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.event.ActionEvent;

public class CalcHomePage implements SceneNavigation{

	private static final String FXML_FILE_NAME = "calc_home_page.fxml";
	
	private Controller mController = Controller.getInstance();
	
	// Event Listener on Button.onAction
	@FXML
	public void goToBMIScene() 
	{
		BMICalc obj = new BMICalc();
		
		mController.changeScene(obj.getView(), false);
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToBMRScene(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToBodyFatScene(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToHomeScene(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public Scene getView()
	{
		// TODO Auto-generated method stub
		return null;
	}
}

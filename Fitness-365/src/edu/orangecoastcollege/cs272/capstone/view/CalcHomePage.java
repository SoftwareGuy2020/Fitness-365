package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.event.ActionEvent;

public class CalcHomePage implements SceneNavigation{

	private static final String FXML_FILE_NAME = "CalcHomePage.fxml";

	private Controller mController = Controller.getInstance();

	// Event Listener on Button.onAction
	@FXML
	public void goToBMIScene()
	{
		BMICalc obj = new BMICalc();

		mController.changeScene(obj.getView(), true);
	}
	// Event Listener on Button.onAction
	@FXML
	public void goToBMRScene(ActionEvent event)
	{
		TDEECalc calc = new TDEECalc();
		mController.changeScene(calc.getView(), false);
	}
	// Event Listener on Button.onAction

	@FXML
	public void goToHomeScene(ActionEvent event)
	{
		HomePage page = new HomePage();
		mController.changeScene(page.getView(), true);
	}
	@Override
	public Scene getView()
	{
	    try {
            BorderPane ap = (BorderPane) FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
            return new Scene(ap);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
}

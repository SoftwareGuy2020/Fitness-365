package edu.orangecoastcollege.cs272.capstone.view;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

public class AboutPage extends AnchorPane{
	
	private Controller mController;
	
	@FXML
	private Hyperlink andreaHL, fitnessHL;
	
	public AboutPage()
	{
		mController = Controller.getInstance();
	}
	
	@FXML
	private void goToFitnessGithub()
	{
		mController.openWebpage(fitnessHL.getText());
	}
	
	@FXML
	private void goToAndreaGithub()
	{
		mController.openWebpage(andreaHL.getText());
	}
}

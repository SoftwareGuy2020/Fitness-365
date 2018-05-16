package edu.orangecoastcollege.cs272.capstone.view;


import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HomePage extends VBox implements SceneNavigation {
	private static final String HOMEPAGE_FXML_FILENAME = "HomePage.fxml";
	
	@FXML
	private FoodDiary foodDiaryController;
	@FXML
	private CalcHomePage calculatorsController;
	private Controller mController;

	public HomePage() {
		mController = Controller.getInstance();
	}

	public void signOut() {
		Login login = new Login();
		mController.changeScene(login.getView(), false);
		mController.setCurrentUser(null);
	}

	@Override
	public Scene getView() {		
		VBox vb = null;
		try {
			vb = (VBox) FXMLLoader.load(getClass().getResource(HOMEPAGE_FXML_FILENAME));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Scene(vb);	


	}
	
	public void goToMyAccount()
	{
		// TODO Make MyAccount page. Add transition here for MyAccount Menu Item
	}
	
	public void goToAboutPopUp()
	{
		// TODO Make About pop-up. Add transition here. 
	}
}
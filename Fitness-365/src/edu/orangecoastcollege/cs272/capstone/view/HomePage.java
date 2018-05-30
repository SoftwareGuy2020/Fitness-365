package edu.orangecoastcollege.cs272.capstone.view;


import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomePage extends VBox implements SceneNavigation {
	private static final String HOMEPAGE_FXML_FILENAME = "HomePage.fxml";
	
	@FXML
	private FoodDiary foodDiaryController;
	@FXML
	private CalcHomePage calculatorsController;
	@FXML
	private WorkoutLog workoutLogController;
	@FXML
	private MenuItem myAccountMenuItem, signOutMenuItem, aboutMenuItem;
	
	private Controller mController;
	
	public HomePage() {
		mController = Controller.getInstance();
	}
	
	@FXML
	private void signOut() {
		Login login = new Login();
		mController.changeScene(login.getView(), false);
		mController.setCurrentUser(null);
	}
	/*
	 * (non-Javadoc)
	 * @see edu.orangecoastcollege.cs272.capstone.model.SceneNavigation#getView()
	 */
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
	
	@FXML
	private void goToFavoriteMeals()
	{
		mController.changeScene(new FavoriteMeals().getView(), false);
	}
	
	@FXML
	private void goToMyAccount()
	{
		mController.changeScene(new AccountPage().getView(), false);
	}
	
	@FXML
	private void goToAboutPopUp()
	{
		try
		{
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("about_page.fxml"));
			Pane pane = loader.load();
			
			
			stage.setScene(new Scene(pane));
			stage.initStyle(StageStyle.UTILITY);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.showAndWait();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
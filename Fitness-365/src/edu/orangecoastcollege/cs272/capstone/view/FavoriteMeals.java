package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Meal;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class FavoriteMeals implements SceneNavigation, Initializable {
	@FXML
	private ListView<Meal> favoritesLV;

	@FXML
	private Button deleteButton;

	private Controller mController = Controller.getInstance();
	private static final String FXML_FILE_NAME = "FavoriteMeals.fxml";

	@FXML
	private void goToHomeScreen()
	{
		HomePage home = new HomePage();
        mController.changeScene(home.getView(), true);
	}

	@FXML
	private void selectMeal()
	{
		deleteButton.setVisible(true);
	}

	@FXML
	private void deleteMeal()
	{
		Meal meal = favoritesLV.getSelectionModel().getSelectedItem();

		mController.deleteFavoriteMeal(meal);

		reset();
	}

	private void reset()
	{
		favoritesLV.setItems(mController.getFavoriteMeals());
		deleteButton.setVisible(false);
	}

	@FXML
	private void goToFoodSearch()
	{
		FoodSearch home = new FoodSearch();
        mController.changeScene(home.getView(), true);
	}

	@Override
	public Scene getView()
	{
		try
		{
			BorderPane ap = (BorderPane) FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(ap);

		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		favoritesLV.setItems(mController.getFavoriteMeals());

	}

}

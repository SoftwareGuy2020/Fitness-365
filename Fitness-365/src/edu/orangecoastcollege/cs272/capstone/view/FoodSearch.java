package edu.orangecoastcollege.cs272.capstone.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Meal;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;

import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FoodSearch implements SceneNavigation, Initializable{
	@FXML
	private ListView<Meal> foodLV;
	@FXML
	private ComboBox<String> filterCB;
	@FXML
	private TextField searchTF;
	@FXML
	private Slider calorieSlider;
	@FXML
	private Label itemCountLabel;
	
	private Controller mController = Controller.getInstance();
	private static final String FXML_FILE_NAME = "FoodSearch.fxml";
	
	private ObservableList<Meal> mealsList;


	// Event Listener on ComboBox[#filterCB].onAction
	@FXML
	public void filterFoodGroups()
	{
		String comboBox = filterCB.getSelectionModel().getSelectedItem();
		String search = searchTF.getText();
		int calories = (int) calorieSlider.getValue();
	   
	    mealsList = mController.filter(b -> ((comboBox == null || comboBox.equals(" ") || b.getGroup().equalsIgnoreCase(comboBox)) && 
	    		(search.isEmpty() || b.getName().contains(search)) && b.getCalories() <= calories));

	    foodLV.setItems(mealsList);
	    itemCountLabel.setText(mealsList.size() + " item(s) displayed");
	}
	
	@FXML 
	public void filter()
	{
		filterFoodGroups();
	}
	@FXML
	public void reset()
	{
		foodLV.getSelectionModel().select(-1);
		filterCB.getSelectionModel().select(-1);
		mealsList = mController.getAllMeals();
		foodLV.setItems(mealsList);
		searchTF.clear();
		calorieSlider.setValue(1000);
	    itemCountLabel.setText(mealsList.size() + " item(s) displayed");

	}
	// Event Listener on Button.onAction
	@FXML
	public void addItemtoFD()
	{
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void goToHomeScreen()
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		mealsList = mController.getAllMeals();
		foodLV.setItems(mController.getAllMeals());
		filterCB.setItems(mController.getFoodGroups());
	    itemCountLabel.setText(mealsList.size() + " item(s) displayed");

	}
}

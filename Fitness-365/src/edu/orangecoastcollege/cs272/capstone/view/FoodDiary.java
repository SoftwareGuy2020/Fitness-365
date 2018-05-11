package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Meal;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import jfxtras.scene.control.CalendarPicker;

public class FoodDiary extends VBox implements SceneNavigation {

	private static final String FXML_FILE_NAME = "food_diary.fxml";
	@FXML
	private PieChart macroPieChart;
	@FXML
	private TableView<Meal> breakfastTableView;
	@FXML
	private TableView<Meal> snackTableView;
	@FXML
	private TableView<Meal> lunchTableView;
	@FXML
	private TableView<Meal> dinnerTableView;
	@FXML
	private TextField calorieGoalTF;
	@FXML
	private TextField calorieConsumedTF;
	@FXML
	private TextField calorieRemainingTF;
	@FXML
	private CalendarPicker calendar;
	@FXML
	private Button addMealButton;
	@FXML
	private Button deleteMealButton;
	
	@FXML
    private ComboBox<String> mealCatagoryCB;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField servingSizeTF;

    @FXML
    private TextField servingsTF;

    @FXML
    private TextField caloriesTF;

    @FXML
    private TextField fatTF;

    @FXML
    private TextField carbsTF;

    @FXML
    private TextField proteinTF;
	boolean mValidMeal = false;
	private Controller mController;
	
	public void initialize() {
		
	}

	public FoodDiary() {
		mController = Controller.getInstance();
	}

	@Override
	public Scene getView() {
		try {
			VBox vb = FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(vb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@FXML
	private void addMeal() {
		
		mValidMeal = false;
		
		Stage stage = new Stage();
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("addMealForm.fxml"));
			stage.setScene(new Scene(pane));
			stage.initStyle(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.showAndWait();
			
			/*
			if (mValidMeal) {
				Meal meal = new Meal(mName, mServingSize, mNumServings, mCalories, mFat, mCarbs, mProtein);
				//mController.addMeal(meal);
				
				switch (mCategory) {
				case "Breakfast":
					breakfastTableView.getItems().add(meal);
					break;
					
				case "Lunch":
					lunchTableView.getItems().add(meal);
					break;
					
				case "Dinner":
					dinnerTableView.getItems().add(meal);
					break;
					
				case "Snack":
					snackTableView.getItems().add(meal);
					break;
				}
			}
			*/
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
	}
	
	@FXML
	private void submitAddMealForm(Event e) {
		if (descriptionTF.getText().isEmpty() || servingSizeTF.getText().isEmpty()
				|| servingsTF.getText().isEmpty() || caloriesTF.getText().isEmpty()
				|| fatTF.getText().isEmpty() || carbsTF.getText().isEmpty() || proteinTF.getText().isEmpty())
			return;
		
		
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void cancelMealForm(Event e) {
		((Button)e.getSource()).getScene().getWindow().hide();
	}

}

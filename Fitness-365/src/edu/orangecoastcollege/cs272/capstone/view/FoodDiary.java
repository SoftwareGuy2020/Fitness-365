package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Category;
import edu.orangecoastcollege.cs272.capstone.model.FoodDiaryEntry;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.scene.control.CalendarPicker;

public class FoodDiary extends VBox implements SceneNavigation {

	private static final String FXML_FILE_NAME = "food_diary.fxml";
		
	private Controller mController;
	
	@FXML
	private PieChart macroPieChart;
	@FXML
	private TableView<FoodDiaryEntry> breakfastTableView;
	@FXML
	private TableView<FoodDiaryEntry> snacksTableView;
	@FXML
	private TableView<FoodDiaryEntry> lunchTableView;
	@FXML
	private TableView<FoodDiaryEntry> dinnerTableView;
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

	
	public void initialize() {
		ObservableList<FoodDiaryEntry> entries = mController.getAllFoodDiaryEntries();		
		breakfastTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Breakfast));
		lunchTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Lunch));
		dinnerTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Dinner));
		snacksTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Snack));
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	private void addMeal() {
		try {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("addMealForm.fxml"));		
		Pane pane = loader.load();
				
		AddMealForm form = loader.getController();
		stage.setScene(new Scene(pane));
		stage.initStyle(StageStyle.UTILITY);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.showAndWait();
		
		FoodDiaryEntry entry = form.getEntry();
		
		if (entry != null) {			
			switch (entry.getCategory()) {
			case Breakfast:
				((FilteredList) breakfastTableView.getItems()).getSource().add(entry);				
				break;
			case Lunch:
				((FilteredList) lunchTableView.getItems()).getSource().add(entry);
				break;
			case Dinner:
				((FilteredList) dinnerTableView.getItems()).getSource().add(entry);
				break;
			case Snack:
				((FilteredList) snacksTableView.getItems()).getSource().add(entry);
				break;
			}
			mController.addFoodDiaryEntry(entry);
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

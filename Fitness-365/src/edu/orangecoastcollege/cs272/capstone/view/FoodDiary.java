package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;
import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Category;
import edu.orangecoastcollege.cs272.capstone.model.FoodDiaryEntry;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import jfxtras.scene.control.LocalDatePicker;

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
	private LocalDatePicker calendar;
	@FXML
	private Button addMealButton;
	@FXML
	private Button deleteMealButton;
	private LocalDate selectedDate;
	private ObservableList<FoodDiaryEntry> entries;

	public FoodDiary() {
		mController = Controller.getInstance();
	}

	public void initialize() {
		calorieGoalTF.setText(Integer.toString(mController.getCurrentUser().getTDEE()));
		selectedDate = LocalDate.now();
		calendar.setLocalDate(selectedDate);
		calendar.localDateProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				selectedDate = newValue;
				updateFilters();
			}
		});
		entries = mController.getAllFoodDiaryEntries();
		updateFilters();

		entries.addListener(new ListChangeListener<FoodDiaryEntry>() {
			@Override
			public void onChanged(Change<? extends FoodDiaryEntry> c) {
				c.next();
				if (c.wasAdded()) {
					FoodDiaryEntry entry = c.getAddedSubList().get(0);					
					updateMacros(entry);
				}
				else if (c.wasRemoved()) {
					updateFilters();
				}
			}
		});		
	}

	private void updateFilters() {
		breakfastTableView.setItems(
				entries.filtered(e -> (e.getCategory() == Category.Breakfast && e.getDate().isEqual(selectedDate))));
		lunchTableView.setItems(
				entries.filtered(e -> (e.getCategory() == Category.Lunch) && e.getDate().isEqual(selectedDate)));
		dinnerTableView.setItems(
				entries.filtered(e -> (e.getCategory() == Category.Dinner) && e.getDate().isEqual(selectedDate)));
		snacksTableView.setItems(
				entries.filtered(e -> (e.getCategory() == Category.Snack) && e.getDate().isEqual(selectedDate)));

		resetMacros();
		breakfastTableView.getItems().forEach(c -> updateMacros(c));
		lunchTableView.getItems().forEach(c -> updateMacros(c));
		dinnerTableView.getItems().forEach(c -> updateMacros(c));
		snacksTableView.getItems().forEach(c -> updateMacros(c));
	}

	private void resetMacros() {
		calorieConsumedTF.setText("0");
		calorieRemainingTF.setText(calorieGoalTF.getText());
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		data.addAll(new PieChart.Data("Protein", 0.0), new PieChart.Data("Fat", 0.0), new PieChart.Data("Carbs", 0.0));
		macroPieChart.setData(data);
	}

	protected void updateMacros(FoodDiaryEntry entry) {
		macroPieChart.getData().forEach(e -> {
			String name = e.getName();
			DoubleProperty value;
			if (name.equals("Protein")) {
				value = e.pieValueProperty();
				value.set(value.get() + entry.getMealProtein());
			} else if (name.equals("Fat")) {
				value = e.pieValueProperty();
				value.set(value.get() + entry.getMealFat());
			} else if (name.equals("Carbs")) {
				value = e.pieValueProperty();
				value.set(value.get() + entry.getMealCarbs());
			}
		});
		updateCalorieCounters(entry.getMealCalories());		
	}

	protected void updateCalorieCounters(int mealCalories) {
		int currentConsumed = Integer.parseInt(calorieConsumedTF.getText());
		int caloriesRemaining = Integer.parseInt(calorieRemainingTF.getText());
		calorieConsumedTF.setText(Integer.toString(currentConsumed + mealCalories));
		calorieRemainingTF.setText(Integer.toString(caloriesRemaining - mealCalories));
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
	public void addMeal() {
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
				int key = mController.addFoodDiaryEntry(entry);
				entry.setId(key);
				
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
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void deleteMeal(ActionEvent e) {
		Node focusedNode = ((Button) e.getSource()).getScene().getFocusOwner();
		FoodDiaryEntry entry = null;
		if (focusedNode instanceof TableView<?>) {
			@SuppressWarnings("unchecked")
			TableView<FoodDiaryEntry> focusedTable = (TableView<FoodDiaryEntry>) focusedNode;

			if (focusedTable.equals(breakfastTableView))
				entry = breakfastTableView.getSelectionModel().getSelectedItem();
			else if (focusedTable.equals(lunchTableView))
				entry = lunchTableView.getSelectionModel().getSelectedItem();
			else if (focusedTable.equals(dinnerTableView))
				entry = dinnerTableView.getSelectionModel().getSelectedItem();
			else if (focusedTable.equals(snacksTableView))
				entry = snacksTableView.getSelectionModel().getSelectedItem();

			if (mController.deleteFoodDiaryEntry(entry))
				entries.remove(entry);
		}
	}
}
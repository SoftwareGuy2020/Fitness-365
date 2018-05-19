package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Category;
import edu.orangecoastcollege.cs272.capstone.model.FoodDiaryEntry;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

	private ObservableList<FoodDiaryEntry> entries;
	
	public void initialize() {
        entries = mController.getAllFoodDiaryEntries();
        breakfastTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Breakfast));
        lunchTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Lunch));
        dinnerTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Dinner));
        snacksTableView.setItems(entries.filtered(e -> e.getCategory() == Category.Snack));

        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.addAll(new PieChart.Data("Protein", 0.0), new PieChart.Data("Fat", 0.0),
                new PieChart.Data("Carbs", 0.0));
        macroPieChart.setData(data);
        entries.forEach(e -> updateMacros(e));

        entries.addListener(new ListChangeListener<FoodDiaryEntry>() {

            @Override
            public void onChanged(Change<? extends FoodDiaryEntry> c) {
                c.next();
                if (c.wasAdded()) {
                    FoodDiaryEntry entry = c.getAddedSubList().get(0);
                    updateCalorieCounters(entry.getMealCalories());
                    updateMacros(entry);
                }
            }
        });

        int tdee = mController.getCurrentUser().getTDEE();
        int consumedCalories = 0;
        for (FoodDiaryEntry e : entries)
            consumedCalories += e.getMealCalories();
        calorieGoalTF.setText(Integer.toString(tdee));
        calorieConsumedTF.setText(Integer.toString(consumedCalories));
        calorieRemainingTF.setText(Integer.toString(tdee - consumedCalories));
    }

    protected void updateMacros(FoodDiaryEntry entry) {
        macroPieChart.getData().forEach(e -> {
            String name = e.getName();
            DoubleProperty value;
            if (name.equals("Protein")) {
                value = e.pieValueProperty();
                value.set(value.get() + entry.getMealProtein());
            }
            else if (name.equals("Fat")) {
                value = e.pieValueProperty();
                value.set(value.get() + entry.getMealFat());
            }
            else if (name.equals("Carbs")) {
                value = e.pieValueProperty();
                value.set(value.get() + entry.getMealCarbs());
            }
        });
    }

	protected void updateCalorieCounters(int mealCalories) {
		int currentConsumed = Integer.parseInt(calorieConsumedTF.getText());
		int caloriesRemaining = Integer.parseInt(calorieRemainingTF.getText());
		calorieConsumedTF.setText(Integer.toString(currentConsumed + mealCalories));
		calorieRemainingTF.setText(Integer.toString(caloriesRemaining - mealCalories));
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
package edu.orangecoastcollege.cs272.capstone.view;

import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Workout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import jfxtras.scene.control.LocalDatePicker;

public class WorkoutHistory {

	@FXML
	private TableView<Workout> workoutTableView;
	@FXML
	private LocalDatePicker workoutCalendar;
	@FXML
	private LineChart<String, Number> maxWeightChart;
	@FXML
	private LineChart<String, Number> oneRepMaxChart;

	private Controller mController;
	private LocalDate mSelectedDate;
	private ObservableList<Workout> mAllWorkouts;

	public WorkoutHistory() {
	}

	@FXML
	private void initialize() {
		mController = Controller.getInstance();
		mAllWorkouts = mController.getallWorkouts();

		mSelectedDate = LocalDate.now();
		workoutCalendar.setLocalDate(mSelectedDate);
		workoutCalendar.localDateProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				mSelectedDate = newValue;
				updateFilter();
			}
		});
		updateFilter();
	}

	private void updateFilter() {
		workoutTableView.setItems(mAllWorkouts.filtered(e -> e.getDate().equals(mSelectedDate)));
	}
}

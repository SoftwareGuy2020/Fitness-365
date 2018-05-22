package edu.orangecoastcollege.cs272.capstone.view;

import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import jfxtras.scene.control.LocalDatePicker;

public class WorkoutHistory {
	@FXML
	private Tab historyTab;
	@FXML
	private TableView<Workout> workoutTableView;
	@FXML
	private LocalDatePicker calendar;
	@FXML
	private Tab graphTab;
	@FXML
	private LineChart<LocalDate, Number> maxWeightChart;
	@FXML
	private LineChart<LocalDate, Number> oneRepMaxChart;
	
	private Controller mController;
	private LocalDate mSelectedDate;
	
	public WorkoutHistory() {}
	
	
	public void initialize() {
		mController = Controller.getInstance();
		mSelectedDate = LocalDate.now();
		calendar.setLocalDate(mSelectedDate);
						
		XYChart.Series<LocalDate, Number> series = new XYChart.Series<>();
		ObservableList<Workout> allWorkouts = mController.getallWorkouts();		
		ObservableList<XYChart.Data<LocalDate, Number>> data = series.getData();
		
		for (Workout w : allWorkouts) 
			data.add(new XYChart.Data<LocalDate, Number>(w.getDate(), w.getWeight()));
		maxWeightChart.getData().add(series);
	}

}

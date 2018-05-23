package edu.orangecoastcollege.cs272.capstone.view;

import java.text.DecimalFormat;
import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Exercise;
import edu.orangecoastcollege.cs272.capstone.model.Workout;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.scene.control.ListView;

public class WorkoutLog {
	@FXML
	private TableView<Exercise> exercisesTableView;
	@FXML
	private Spinner<Double> weightSpinner;
	@FXML
	private Spinner<Integer> repSpinner;
	@FXML
	private TableView<Workout> todaysWorkoutTableView;

	private Controller mController;
	private ObservableList<Exercise> mAllExercisesList;
	private String mLastSearchTerm = "";
	
	public WorkoutLog() {}
	
	@FXML
	private void initialize() {
		mController = Controller.getInstance();
		todaysWorkoutTableView.setItems(mController.getallWorkouts().filtered(w -> w.getDate().equals(LocalDate.now())));
		
		mAllExercisesList = mController.getAllExercises();
		exercisesTableView.setItems(mAllExercisesList);
		SpinnerValueFactory.DoubleSpinnerValueFactory weightFactory = new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE);
		weightFactory.setAmountToStepBy(2.5);
		weightFactory.setConverter(new StringConverter<Double>() {
			
			@Override
			public String toString(Double object) {
				DecimalFormat df = new DecimalFormat("0.0");
				return df.format(object.doubleValue());
			}
			
			@Override
			public Double fromString(String string) {
				return Double.parseDouble(string);	
			}
		});
		weightSpinner.setValueFactory(weightFactory);
		TextFormatter<Double> weightFormatter = new TextFormatter<Double>(weightFactory.getConverter(), weightFactory.getValue());
		weightSpinner.getEditor().setTextFormatter(weightFormatter);		
		weightFactory.valueProperty().bindBidirectional(weightFormatter.valueProperty());
		
		SpinnerValueFactory.IntegerSpinnerValueFactory repFactory = new IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
		repFactory.setAmountToStepBy(1);
		repFactory.setConverter(new StringConverter<Integer>() {
			
			@Override
			public String toString(Integer object) {
				return object.toString();
			}
			
			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);	
			}
		});
		repSpinner.setValueFactory(repFactory);
		TextFormatter<Integer> repFormatter = new TextFormatter<Integer>(repFactory.getConverter(), repFactory.getValue());
		repSpinner.getEditor().setTextFormatter(repFormatter);		
		repFactory.valueProperty().bindBidirectional(repFormatter.valueProperty());
		
		
		
	}
	
	@FXML
	private void search(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			TextField source = (TextField) event.getSource();
			String currentSearchTerm = source.getText();
			
			if (!currentSearchTerm.isEmpty()) {
				mLastSearchTerm = currentSearchTerm;
				exercisesTableView.setItems(mAllExercisesList
						.filtered(e -> e.getName().toLowerCase().contains(currentSearchTerm.toLowerCase())));
			}
			else if (currentSearchTerm.isEmpty() && !mLastSearchTerm.isEmpty()) {
				exercisesTableView.setItems(mAllExercisesList);
				mLastSearchTerm = "";
			}
		}
	}
	
	@FXML
	private void addSavedExercise() {
		Exercise exercise = exercisesTableView.getSelectionModel().getSelectedItem();
		if (exercise != null) {
			double weight = weightSpinner.getValue();
			int reps = repSpinner.getValue();

			if (reps != 0 && weight != 0) {				
				int id = mController.addSavedExercise(exercise);				
			}
		}
	}
	
	@FXML
	private void showSavedExercises() {
		Stage stage = new Stage();
		ListView<Exercise> lv = new ListView<>(mController.getAllSavedExercises());
		VBox root = new VBox();
		root.getChildren().add(lv);
		stage.setScene(new Scene(root, 400, 400));
		stage.showAndWait();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	private void addEntry() {
		Exercise exercise = exercisesTableView.getSelectionModel().getSelectedItem();
		if (exercise != null) {
			double weight = weightSpinner.getValue();
			int reps = repSpinner.getValue();

			if (reps != 0 && weight != 0) {
				Workout w = new Workout(-1, mController.getCurrentUser().getId(),
										exercise, weight, reps, LocalDate.now());
				int id = mController.addWorkout(w);
				if (id != -1) {					
					((FilteredList)todaysWorkoutTableView.getItems()).getSource().add(w);
				}
			}
		}
	}
}

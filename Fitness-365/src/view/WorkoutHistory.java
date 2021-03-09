package view;

import java.time.LocalDate;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import jfxtras.scene.control.LocalDatePicker;
import model.Workout;

/**
 * Represents a ledger of past workouts performed by the user.
 * 
 * @author Travis
 *
 */
public class WorkoutHistory {

	@FXML
	private TableView<Workout> workoutTableView;
	@FXML
	private LocalDatePicker workoutCalendar;

	private Controller mController;
	private LocalDate mSelectedDate;
	private ObservableList<Workout> mAllWorkouts;

	/**
	 * Instantiates an instance of WorkoutHistory
	 */
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

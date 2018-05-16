package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;
import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.Category;
import edu.orangecoastcollege.cs272.capstone.model.FoodDiaryEntry;
import edu.orangecoastcollege.cs272.capstone.model.Meal;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Travis
 *
 */
public class AddMealForm extends GridPane implements SceneNavigation {
	private static final double FAT_CALORIES = 9.0;
	private static final double CARBS_CALORIES = 4.0;
	private static final double PROTEIN_CALORIES = 4.0;

	@FXML
	private ComboBox<Category> mealCategoryCB;
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
	@FXML
	private Label addMealErrorLabel;
	@FXML
	private Label categoryError;
	@FXML
	private Label descriptionError;
	@FXML
	private Label servingSizeError;
	@FXML
	private Label servingsError;
	@FXML
	private Label fatError;
	@FXML
	private Label carbsError;
	@FXML
	private Label proteinError;

	private Controller mController;
	private FoodDiaryEntry mEntry;
	private Meal mMeal;

	public void initialize() {
		mController = Controller.getInstance();
	}

	public FoodDiaryEntry getEntry() {
		return mEntry;
	}

	@FXML
	private void submitAddMealForm(ActionEvent e) {
		clearVisibleErrors();

		if (validateAddMealForm()) {
			String name = descriptionTF.getText();
			double servingSize = Double.parseDouble(servingSizeTF.getText());
			double numServings = Double.parseDouble(servingsTF.getText());
			double fat = Double.parseDouble(fatTF.getText());
			double carbs = Double.parseDouble(carbsTF.getText());
			double protein = Double.parseDouble(proteinTF.getText());
			Category category = mealCategoryCB.getValue();
			int calories;

			if (caloriesTF.getText().isEmpty())
				calories = (int) (fat * FAT_CALORIES + carbs * CARBS_CALORIES + protein * PROTEIN_CALORIES);

			else
				calories = Integer.parseInt(caloriesTF.getText());

			mMeal = new Meal(name, servingSize, calories, fat, carbs, protein, null);
			mEntry = new FoodDiaryEntry(mMeal, numServings, category, LocalDate.now(),
									mController.getCurrentUser().getId());
		
		((Button) e.getSource()).getScene().getWindow().hide();
		}
	}

	private boolean validateAddMealForm() {
		boolean isValid = true;

		if (descriptionTF.getText().isEmpty()) {
			descriptionError.setVisible(true);
			isValid = false;
		}
		if (servingSizeTF.getText().isEmpty()) {
			servingSizeError.setVisible(true);
			isValid = false;
		}
		if (servingsTF.getText().isEmpty()) {
			servingsError.setVisible(true);
			isValid = false;
		}
		if (fatTF.getText().isEmpty()) {
			fatError.setVisible(true);
			isValid = false;
		}
		if (carbsTF.getText().isEmpty()) {
			carbsError.setVisible(true);
			isValid = false;
		}
		if (proteinTF.getText().isEmpty()) {
			proteinError.setVisible(true);
			isValid = false;
		}
		if (!isValid) {
			addMealErrorLabel.setVisible(true);
			return false;
		}
		return true;
	}

	private void clearVisibleErrors() {
		descriptionError.setVisible(false);
		servingSizeError.setVisible(false);
		servingsError.setVisible(false);
		fatError.setVisible(false);
		carbsError.setVisible(false);
		proteinError.setVisible(false);
	}

	@FXML
	private void cancelMealForm(Event e) {
		((Button) e.getSource()).getScene().getWindow().hide();
	}

	@Override
	public Scene getView() {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("addMealForm.fxml"));

			return new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

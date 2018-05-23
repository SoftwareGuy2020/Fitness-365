package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class BodyFatCalc implements SceneNavigation{

	private Controller mController = Controller.getInstance();
	private static final String FXML_FILE_NAME = "bodyFatCalc.fxml";

	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private ToggleGroup genderGroup;
	@FXML
	private Label hipLabel;
	@FXML
	private Label forearmLabel;
	@FXML
	private TextField hipTF;
	@FXML
	private TextField weightTF;
	@FXML
	private TextField waistTF;
	@FXML
	private TextField bfTF;
	@FXML
	private TextField forearmTF;
	@FXML
	private TextField wristTF;
	@FXML
	private Label wristLabel;
	@FXML
	private Button updateButton;
	@FXML
	private Label errorLabel;


	@FXML
	public void setMale()
	{
		wristLabel.setVisible(false);
		wristTF.setVisible(false);
		forearmTF.setVisible(false);
		hipTF.setVisible(false);
		hipLabel.setVisible(false);
		forearmLabel.setVisible(false);
		errorLabel.setVisible(false);
		updateButton.setVisible(false);
		weightTF.requestFocus();
	}
	// Event Listener on RadioButton.onAction
	@FXML
	public void setFemale()
	{
		wristLabel.setVisible(true);
		wristTF.setVisible(true);
		forearmTF.setVisible(true);
		hipTF.setVisible(true);
		hipLabel.setVisible(true);
		forearmLabel.setVisible(true);
		errorLabel.setVisible(false);
		updateButton.setVisible(false);
		weightTF.requestFocus();
	}
	// Event Listener on Button[#updateButton].onAction
	@FXML
	public void updateProfile(ActionEvent event)
	{
		mController.getCurrentUser().setCurrentWeight(Integer.parseInt(weightTF.getText()));
	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void cancel()
	{
		setMale();
		wristTF.clear();
		waistTF.clear();
		hipTF.clear();
		forearmTF.clear();
		weightTF.clear();
		bfTF.clear();

		errorLabel.setVisible(false);
		updateButton.setVisible(false);

		HomePage home = new HomePage();
        mController.changeScene(home.getView(), false);
    }
	// Event Listener on Button[#calcButton].onAction
	@FXML
	public void calculate()
	{
		errorLabel.setVisible(false);
		updateButton.setVisible(false);

        NumberFormat num = new DecimalFormat("#0.0");

		if(male.isSelected())
		{
			if(!weightTF.getText().isEmpty() && !waistTF.getText().isEmpty())
			{
				Double lean = ((1.082 * Double.parseDouble(weightTF.getText())) -
						(4.15 * Double.parseDouble(waistTF.getText())) + 94.42);

				bfTF.setText(num.format(Double.valueOf((Double.parseDouble(weightTF.getText()) - lean)
						/ ( Double.parseDouble(weightTF.getText())) * 100)).toString() + " % ");

				updateButton.setVisible(true);
			}
			else
				errorLabel.setVisible(true);
		}
		else if(female.isSelected())
		{
			if(!weightTF.getText().isEmpty() && !wristTF.getText().isEmpty() && !hipTF.getText().isEmpty()
					&& !waistTF.getText().isEmpty() && !forearmTF.getText().isEmpty())
			{
				Double lean = (.732 * Double.parseDouble(weightTF.getText())) -
						(.157 * Double.parseDouble(waistTF.getText()))
								- (.249 * Double.parseDouble(hipTF.getText()))
										+ (.434 * Double.parseDouble(forearmTF.getText()))
												+ ((Double.parseDouble(wristTF.getText()) / 3.14) + 8.987);
				bfTF.setText(num.format(Double.valueOf((Double.parseDouble(weightTF.getText()) - lean)
						/ ( Double.parseDouble(weightTF.getText())) * 100)).toString() + " %");

				updateButton.setVisible(true);
			}
			else
				errorLabel.setVisible(true);
		}
		else
			errorLabel.setVisible(true);
	}

	public void initialize()
	{
		weightTF.requestFocus();
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
}

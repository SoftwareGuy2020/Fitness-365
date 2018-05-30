package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import edu.orangecoastcollege.cs272.capstone.model.SecurityQuestion;
import edu.orangecoastcollege.cs272.capstone.model.Sex;
import edu.orangecoastcollege.cs272.capstone.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignUp extends AnchorPane implements SceneNavigation {
	private static final String FXML_FILE_NAME = "SignUp.fxml";

	public TextField usernameTF;
	public PasswordField passwordField;
	public PasswordField confirmPasswordF;
	public ComboBox<String> securityQuestionCB;
	public TextField securityAnswerTF;
	public DatePicker birthDatePicker;
	public TextField nameTF;
	public ComboBox<Sex> sexCB;
	public Label errorLabel, weightErrorLabel;
	public Button signUpButton;
	public Hyperlink signInLink;
	public Hyperlink forgotPasswordHL;
	public Label usernameTakenLabel;
	
	public TextField currentWeightTF, goalWeightTF;
	public ComboBox<Double> weeklyGoalCB;
	
	private Controller mController;

	public SignUp() {
		mController = Controller.getInstance();
	}

	@FXML
	private void initialize() {
		securityQuestionCB.setItems(FXCollections.observableArrayList(SecurityQuestion.getAllQuestions()));
		sexCB.setItems(FXCollections.observableArrayList(Sex.values()));
		weeklyGoalCB.setItems(FXCollections.observableArrayList(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0));
		weeklyGoalCB.setValue(0.0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see edu.orangecoastcollege.cs272.capstone.model.SceneNavigation#getView()
	 */
	@Override
	public Scene getView() {
		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			 return new Scene(ap);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@FXML
	private void signUpUser() {
		String username = usernameTF.getText();
		if (mController.getUser(username) != null) {
			usernameTakenLabel.setVisible(true);
			forgotPasswordHL.setVisible(true);
			return;
		}
		String typedPW = passwordField.getText();
		String sq = securityQuestionCB.getValue();
		String sa = securityAnswerTF.getText();
		LocalDate birthDate = birthDatePicker.getValue();
		String fullName = nameTF.getText();
		
		if (username.isEmpty() || !typedPW.equals(confirmPasswordF.getText()) || fullName.isEmpty() || sa.isEmpty()) {
			errorLabel.setVisible(true);
			return;
		}
		
		Sex sex = sexCB.getValue();
		double weeklyGoal = weeklyGoalCB.getValue();
		
		try
		{
			double currentWeight = Double.parseDouble(currentWeightTF.getText());
			double goalWeight = Double.parseDouble(goalWeightTF.getText());
			
			User newUser = new User(-1, username, sq, sa, fullName, birthDate, sex, null, 0, currentWeight, goalWeight, currentWeight, weeklyGoal, 0);
			mController.createNewUser(newUser, typedPW);
			mController.setCurrentUser(username);
			HomePage home = new HomePage();
			mController.changeScene(home.getView(), true); 
		}
		catch (NumberFormatException e)
		{
			weightErrorLabel.setVisible(true);
		}

	}
	
	@FXML
	private void loadSignInScene() {
		mController.changeScene(new Login().getView(), false);
	}
	
	@FXML
	private void loadForgotPasswordScene()
	{
		mController.changeScene(new ForgotPassword().getView(), false);
	}
}

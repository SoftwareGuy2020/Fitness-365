package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.PasswordEncryption;
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

/**
 * @author Travis
 *
 */
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
	public Label errorLabel;
	public Button signUpButton;
	public Hyperlink signInLink;
	public Hyperlink forgotPasswordHL;
	public Label usernameTakenLabel;

	private Controller mController;

	public SignUp() {
		mController = Controller.getInstance();
	}

	public void initialize() {
		securityQuestionCB.setItems(FXCollections.observableArrayList(SecurityQuestion.getAllQuestions()));
		sexCB.setItems(FXCollections.observableArrayList(Sex.values()));
	}

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
		Sex sex = sexCB.getValue();

		if (username.isEmpty() || !typedPW.equals(confirmPasswordF.getText()) || fullName.isEmpty()) {
			errorLabel.setVisible(true);
			return;
		}

		User newUser = new User(-1, username, sq, sa, fullName, birthDate, sex, null, 0, 0.0, 0.0, 0.0, 0.0);
		mController.createNewUser(newUser, typedPW);
		mController.setCurrentUser(username);
		HomePage home = new HomePage();
		mController.changeScene(home.getView(), true);

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

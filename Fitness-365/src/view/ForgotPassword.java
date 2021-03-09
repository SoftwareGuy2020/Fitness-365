package view;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.SceneNavigation;
import model.User;

public class ForgotPassword extends AnchorPane implements SceneNavigation {

	private static final String FXML_FILE_NAME = "ForgotPassword.fxml";

	public Label incorrectAnswerLabel, differentPasswordsLabel, securityQuestionLabel, userNotFoundLabel;
	public PasswordField passwordTF, confirmPasswordTF;
	public TextField securityAnswerTF, usernameTF;
	public Button resetPasswordButton, findUserButton, continueButton;

	private Controller mController;
	private User mUser;

	public ForgotPassword() {
		mController = Controller.getInstance();
		mUser = null;
	}

	@FXML
	private void initialize() {
		usernameTF.setFocusTraversable(false);
	}

	@FXML
	private void getUser() {
		User user = mController.getUser(usernameTF.getText());
		if (user != null) {
			userNotFoundLabel.setVisible(false);
			mUser = user;

			differentPasswordsLabel.setVisible(false);
			securityQuestionLabel.setText(user.getSecurityQ());
			securityQuestionLabel.setVisible(true);
			securityAnswerTF.setVisible(true);
			continueButton.setVisible(true);
			incorrectAnswerLabel.setVisible(false);
		} else {
			incorrectAnswerLabel.setVisible(false);
			securityQuestionLabel.setVisible(false);
			securityAnswerTF.setVisible(false);
			continueButton.setVisible(false);
			userNotFoundLabel.setVisible(true);
			usernameTF.requestFocus();
		}
	}

	@FXML
	private void checkSecurityQuestion() {
		if (securityAnswerTF.getText().equalsIgnoreCase(mUser.getSecurityA())) {
			passwordTF.setVisible(true);
			confirmPasswordTF.setVisible(true);
			resetPasswordButton.setVisible(true);
			incorrectAnswerLabel.setVisible(false);
		} else {
			differentPasswordsLabel.setVisible(false);
			passwordTF.setVisible(false);
			confirmPasswordTF.setVisible(false);
			resetPasswordButton.setVisible(false);
			incorrectAnswerLabel.setVisible(true);
			securityAnswerTF.requestFocus();
		}

	}

	@FXML
	private void resetPassword() {
		String password = passwordTF.getText();
		if (password.equals(confirmPasswordTF.getText()) && !password.isEmpty()) {
			differentPasswordsLabel.setVisible(false);
			mController.updateUserPassword(mUser, password);
			mController.changeScene(new Login().getView(), false);
		} else {
			differentPasswordsLabel.setVisible(true);
			passwordTF.clear();
			confirmPasswordTF.clear();
			passwordTF.requestFocus();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.SceneNavigation#getView()
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

}

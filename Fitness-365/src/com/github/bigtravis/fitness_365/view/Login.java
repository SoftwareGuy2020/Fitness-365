package com.github.bigtravis.fitness_365.view;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.github.bigtravis.fitness_365.controller.Controller;
import com.github.bigtravis.fitness_365.model.PasswordEncryption;
import com.github.bigtravis.fitness_365.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Travis
 *
 */
public class Login extends VBox{
	private static final String LOGIN_FXML_FILENAME = "Login.fxml";

	private static final String EMPTY_FIELDS_MESSAGE = "All fields must be complete";
	private static final String FAILED_LOGIN_MESSAGE = "Invalid username/password";
	
	public Label errorLabel;
	
	public TextField userLoginTF;
	
	public PasswordField pwLoginTF;
	
	public Button loginButton;
	
	public Hyperlink signUpLink;
	
	private Controller mController;
	
	public Login() {
		mController = Controller.getInstance();
	}
	
	
	public Scene getLoginScene() throws IOException {
		VBox vb = (VBox) FXMLLoader.load(getClass().getResource(LOGIN_FXML_FILENAME));		 
		return new Scene(vb);
	}
			
	
	@FXML
	private void authenticateLogin(ActionEvent event) {
		String username = userLoginTF.getText();
		String typedPW = pwLoginTF.getText();
		
		if (username.isEmpty() || typedPW.isEmpty()) {
			errorLabel.setText(EMPTY_FIELDS_MESSAGE);
			if (!errorLabel.isVisible())
				errorLabel.setVisible(true);
			
			return;
		}
		if (mController.authenticateLogin(username, typedPW)) {			
			if (errorLabel.isVisible())
				errorLabel.setVisible(false);
			mController.ChangeScene(e -> {
				try {
					return new HomePage().getHomePageScene();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return null;
			});
		}
		else {
			errorLabel.setText(FAILED_LOGIN_MESSAGE);
			if (!errorLabel.isVisible())
				errorLabel.setVisible(true);
		}
	}
	

}

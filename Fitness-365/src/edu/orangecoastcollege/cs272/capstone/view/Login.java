package edu.orangecoastcollege.cs272.capstone.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Travis
 *
 */
public class Login extends AnchorPane implements SceneNavigation {
	private static final String LOGIN_FXML_FILENAME = "Login.fxml";

	private static final String EMPTY_FIELDS_MESSAGE = "All fields must be complete";
	private static final String FAILED_LOGIN_MESSAGE = "Invalid username/password";

	public Label errorLabel;

	public CheckBox rememberUsernameCB;

	public TextField usernameTF;

	public PasswordField passwordTF;

	public Button loginButton;

	public Hyperlink forgotPassHL;

	public Hyperlink signUpHL;

	private Controller mController;
	private static String savedUser = "";

	public Login() {
		mController = Controller.getInstance();
	}

	public void initialize() {
		if (!savedUser.isEmpty()) {
			rememberUsernameCB.setSelected(true);
			usernameTF.setText(savedUser);
		}
		passwordTF.setOnKeyPressed(e -> detectEnterKey(e));
	}

	private void detectEnterKey(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			authenticateLogin();
	}

	public Scene getView() {
		try {
			Scanner input = new Scanner(new File("resources/init.txt"));

			if (input.hasNextLine())
				savedUser = input.nextLine();
			input.close();

			AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource(LOGIN_FXML_FILENAME));
			return new Scene(ap);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@FXML
	private void transitionToSignUpScene() {
		SignUp signUpScene = new SignUp();
		mController.changeScene(signUpScene.getView(), false);
	}
	@FXML
	private void transitionToForgotPasswordScene() {
		mController.changeScene(new ForgotPassword().getView(), false);
	}

	@FXML
	private void authenticateLogin() {

		String username = usernameTF.getText();
		String typedPW = passwordTF.getText();

		if (username.isEmpty() || typedPW.isEmpty()) {
			errorLabel.setText(EMPTY_FIELDS_MESSAGE);
			if (!errorLabel.isVisible())
				errorLabel.setVisible(true);

			return;
		}
		if (mController.authenticateLogin(username, typedPW)) {
			try (PrintWriter output = new PrintWriter(new File("resources/init.txt"))) {
				if (rememberUsernameCB.isSelected())
					output.write(username);
				else {
					output.write("");
					savedUser = "";
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			HomePage homePage = new HomePage();
			mController.changeScene(homePage.getView(), true);

			//CalcHomePage calcPage = new CalcHomePage();
            //mController.changeScene(calcPage.getView(), true);

		} else {
			errorLabel.setText(FAILED_LOGIN_MESSAGE);
			if (!errorLabel.isVisible())
				errorLabel.setVisible(true);
		}
	}
}

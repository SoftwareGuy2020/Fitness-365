package com.github.bigtravis.fitness_365.view;

import com.github.bigtravis.fitness_365.controller.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Travis
 *
 */
public class login extends VBox{
	private static final String LOGIN_FXML_FILENAME = "login.fxml";
	
	
	public TextField userLoginTF;
	
	public TextField pwLoginTF;
	
	public Button loginButton;
	
	public Hyperlink signUpLink;
	
	private Controller mController;
		
	
//	/* (non-Javadoc)
//	 * @see javafx.application.Application#start(javafx.stage.Stage)
//	 */
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		Pane pane = (Pane) FXMLLoader.load(getClass().getResource(LOGIN_FXML_FILENAME));
//		primaryStage.setScene(new Scene(pane));		
//		mController = Controller.getInstance();
//		signUpLink.setOnAction(e -> createSignUpScene(primaryStage));
//		primaryStage.show();
//
//	}
	
	public void createSignUpScene(Stage stage) {
		
	}
	
	@FXML
	private boolean authenticateLogin(ActionEvent event) {
		return true;
	}

	public static void main(String[] args) {}

}

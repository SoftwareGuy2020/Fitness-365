package com.github.bigtravis.fitness_365.view;

import java.io.IOException;

import com.github.bigtravis.fitness_365.controller.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * @author Travis
 *
 */
public class HomePage extends VBox{
	private static final String HOMEPAGE_FXML_FILENAME = "HomePage.fxml";
	
	private Controller mController;
	
	public HomePage() {
		mController = Controller.getInstance();
	}
	
	public Scene getHomePageScene() throws IOException {
		VBox vb = (VBox) FXMLLoader.load(getClass().getResource(HOMEPAGE_FXML_FILENAME));
		return new Scene(vb);
	}
	
	public void signOut() {
		Login login = new Login();
		mController.ChangeScene(e -> {
			try {
				return login.getLoginScene();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			return null;
		});
		
	}
}

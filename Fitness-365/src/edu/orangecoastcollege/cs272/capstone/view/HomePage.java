package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
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
	
	public Scene getHomePageScene(){
		VBox vb = null;
		try {
			vb = (VBox) FXMLLoader.load(getClass().getResource(HOMEPAGE_FXML_FILENAME));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return new Scene(vb);
	}
	
	public void signOut() {
		Login login = new Login();
		mController.changeScene(e -> login.getLoginScene(), false);
	}
}

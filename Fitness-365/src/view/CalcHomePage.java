/**
 * Class which serves as a home page for the calculators
 * 
<<<<<<< HEAD
 * @author Travis
=======
 * 
>>>>>>> branch 'devel' of https://github.com/BigTravis/Fitness-365.git
 */
package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.SceneNavigation;

import java.io.IOException;

import controller.Controller;

public class CalcHomePage implements SceneNavigation {

	private static final String FXML_FILE_NAME = "CalcHomePage.fxml";

	private Controller mController = Controller.getInstance();

	// Event Listener on Button.onAction
	@FXML
	private void goToBMIScene() {
		BMICalc obj = new BMICalc();

		mController.changeScene(obj.getView(), false);
	}

	// Event Listener on Button.onAction
	@FXML
	private void goToBMRScene() {
		TDEECalc calc = new TDEECalc();
		mController.changeScene(calc.getView(), false);
	}
	// Event Listener on Button.onAction

	@FXML
	private void goToHomeScene() {
		HomePage page = new HomePage();
		mController.changeScene(page.getView(), true);
	}

	@FXML
	private void goToBodyFatScene() {
		BodyFatCalc page = new BodyFatCalc();
		mController.changeScene(page.getView(), false);
	}

	/**
	 * Scene navigator
	 */
	@Override
	public Scene getView() {
		try {
			BorderPane ap = (BorderPane) FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(ap);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

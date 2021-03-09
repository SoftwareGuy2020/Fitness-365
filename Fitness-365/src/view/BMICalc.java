/**
 * Class which calculates bmi and updates profile if selected
 * 
 * @author Jason
 */

package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import controller.Controller;
import javafx.scene.layout.BorderPane;
import model.SceneNavigation;

public class BMICalc implements SceneNavigation{

	private Controller mController = Controller.getInstance();
	private static final String FXML_FILE_NAME = "BMICalc.fxml";

	@FXML
	private TextField weightTF;
	@FXML
	private TextField bmiTF;
	@FXML
	private TextField feetTF;
	@FXML
	private TextField inchesTF;
	@FXML
	private Label errorLabel;
	@FXML
	private Button updateButton;


	// Event Listener on Button[#updateButton].onAction
	@FXML
	private void updateProfile()
	{
		mController.getCurrentUser().setCurrentWeight(Integer.parseInt(weightTF.getText()));
	}

	// Event Listener on Button[#cancelButton].onAction
	@FXML
	private void cancel()
	{
		feetTF.clear();
		inchesTF.clear();
		weightTF.clear();
		bmiTF.clear();
		errorLabel.setVisible(false);
		updateButton.setVisible(false);

		HomePage home = new HomePage();
        mController.changeScene(home.getView(), false);
	}

	// Event Listener on Button[#calcButton].onAction
	@FXML
	private void calculate()
	{
		errorLabel.setVisible(false);

		if(!feetTF.getText().isEmpty() && !inchesTF.getText().isEmpty()
		        && !weightTF.getText().isEmpty())
		{
			Double a = Double.parseDouble(weightTF.getText()) * .45;
	        Double h = Double.parseDouble(feetTF.getText()) * 12;
	        Double b = Math.pow(((Double.parseDouble(inchesTF.getText()) + h) * .025), 2);
	        Double c = a / b;

	        NumberFormat num = new DecimalFormat("#0.0");
	        bmiTF.setText((num.format(c).toString()));
	        updateButton.setVisible(true);
		}
		else
			errorLabel.setVisible(true);
	}

	/**
	 * Scene Navigator
	 */
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

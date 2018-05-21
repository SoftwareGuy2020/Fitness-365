package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMICalc implements SceneNavigation{

	private Controller mController = Controller.getInstance();
	private static final String FXML_FILE_NAME = "BMICalc.fxml";
	private static String image = "\resources\bmi.jpg";

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
	public void updateProfile()
	{
		mController.getCurrentUser().setCurrentWeight(Integer.parseInt(weightTF.getText()));
	}

	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void cancel()
	{
		feetTF.clear();
		inchesTF.clear();
		weightTF.clear();
		bmiTF.clear();
		errorLabel.setVisible(false);
		updateButton.setVisible(false);

		CalcHomePage home = new CalcHomePage();
        mController.changeScene(home.getView(), false);
	}

	// Event Listener on Button[#calcButton].onAction
	@FXML
	public void calculate()
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

	// Event Listener on Label.onMouseClicked
	@FXML
	public void openBMICharts()
	{
		Stage newStage = new Stage();

		VBox box = new VBox();
		ImageView view = new ImageView();
		Image mig = new Image(image);

		view.setImage(mig);
		box.getChildren().add(view);
		Scene scene = new Scene(box, 700, 502);
		newStage.setScene(scene);
		newStage.show();
	}
	
	public void initialize()
	{
		feetTF.requestFocus();
	}

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

/**
 * Class which calculates tdee and bmr and updates profile if selected
 * 
 * @author Jason
 */

package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.SceneNavigation;

import java.io.IOException;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class TDEECalc implements SceneNavigation{
	@FXML
	private RadioButton maleCB;
	@FXML
	private RadioButton femaleCB;
	@FXML
	private TextField feetTF;
	@FXML
	private TextField inchesTF;
	@FXML
	private TextField bmrTF;
	@FXML
	private TextField tdeeTF;
	@FXML
	private ComboBox<String> activityCB;

	private static final String FXML_FILE_NAME = "TDEECalc.fxml";
	private Controller mController = Controller.getInstance();

	@FXML
	private Button updateButton;

	@FXML
	private TextField weightTF;
	@FXML
	private TextField ageTF;
	@FXML
	private Label errorLabel;


	// Event Listener on Button[#updateButton].onAction
	@FXML
	private void updateProfile()
	{
		if(!tdeeTF.getText().isEmpty())
			mController.getCurrentUser().setTDEE(Integer.parseInt(tdeeTF.getText()));

		mController.getCurrentUser().setCurrentWeight(Integer.parseInt(weightTF.getText()));

	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	private void cancel()
	{
		maleCB.setSelected(true);
		feetTF.clear();
		inchesTF.clear();
		weightTF.clear();
		bmrTF.clear();
		tdeeTF.clear();
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

		if(!feetTF.getText().isEmpty() && !inchesTF.getText().isEmpty() && !weightTF.getText().isEmpty()
		        && !ageTF.getText().isEmpty()
				&& (maleCB.isSelected() || femaleCB.isSelected()))
		{

	    	if(maleCB.isSelected())
	    	{
	    		double w = Double.parseDouble(weightTF.getText()) * 6.23;
	    		double h = ((Double.parseDouble(feetTF.getText()) * 12) +
	    				Double.parseDouble(inchesTF.getText())) * 12.7;
	    		Integer calc = (int) (66 + w + h - (Double.parseDouble(ageTF.getText()) * 6.8));
	    		bmrTF.setText(calc.toString());
	    	}
	    	else if(femaleCB.isSelected())
	    	{
	    		double w = Double.parseDouble(weightTF.getText()) * 4.35;
	    		double h = ((Double.parseDouble(feetTF.getText()) * 12) +
	    				Double.parseDouble(inchesTF.getText())) * 4.7;
	    		Integer calc = (int) (655 + w + h - (Double.parseDouble(ageTF.getText()) * 4.7));
	    		bmrTF.setText(calc.toString());
	    	}

	    	if(!activityCB.getSelectionModel().isEmpty())
	    	{
	    		Integer index = activityCB.getSelectionModel().getSelectedIndex();

	    		switch(index)
	    		{
	    		case 1:
	    			Integer tdee1 = (int) (Double.parseDouble(bmrTF.getText()) * 1.2);
	    			tdeeTF.setText((tdee1.toString()));
	    			break;
	    		case 2:
	    			Integer tdee2 = (int) (Double.parseDouble(bmrTF.getText()) * 1.375);
	    			tdeeTF.setText(tdee2.toString());
	    			break;
	    		case 3:
	    			Integer tdee3 = (int) (Double.parseDouble(bmrTF.getText()) * 1.55);
	    			tdeeTF.setText(tdee3.toString());
	    			break;
	    		case 4:
	    			Integer tdee4 = (int) (Double.parseDouble(bmrTF.getText()) * 1.725);
	    			tdeeTF.setText(tdee4.toString());
	    			break;
	    		case 5:
	    			Integer tdee = (int) (Double.parseDouble(bmrTF.getText()) * 1.9);
	    			tdeeTF.setText(tdee.toString());
	    			break;
	    		default:
	    			break;

	    		}
	    	}
	    	updateButton.setVisible(true);
		}
    	else
    		errorLabel.setVisible(true);
	}
	
	/**
	 * Scene navigator
	 */
	@Override
	public Scene getView()
	{
		try
		{
			BorderPane ap = (BorderPane) FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(ap);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Initializes combobox
	 */
    public void initialize()
    {
        ObservableList<String> activities = FXCollections.observableArrayList();

        activities.add("");
        activities.add("Sedentary (Little to no exercise)");
        activities.add("Lightly Active (1-3 days/week)");
        activities.add("Moderately Active (3-5 days/week)");
        activities.add("Very Active (6-7 days/week)");
        activities.add("Extremely Active (Exercise/training 2x/day)");

        activityCB.setItems(activities);
		maleCB.setSelected(true);
		feetTF.requestFocus();
    }
}

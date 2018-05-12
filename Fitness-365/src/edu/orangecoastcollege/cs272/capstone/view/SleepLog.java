package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import edu.orangecoastcollege.cs272.capstone.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class SleepLog implements SceneNavigation {

	private static final String FXML_FILE_NAME = "sleep_log.fxml";

	@FXML
	private ProgressIndicator lastEntryPI, averagePI;
	@FXML
	private Button addNewEntryButton, deleteEntryButton, editEntryButton;
	@FXML
	private Text sleepHoursByAgeText;
	@FXML
	private TableView sleepLogTV;
	
	private Controller mController;
	private User mUser;
	
	public void initialize() 
	{
		mUser = mController.getCurrentUser();
		if (mUser != null)
		{
			int userAge = mUser.getAge();

			int recommendedHours = 0;
			
			if (userAge <= 2)
				recommendedHours = 11;
			else if (userAge <= 5)
				recommendedHours = 10;
			else if (userAge <= 13)
				recommendedHours = 9;
			else if (userAge <= 17)
				recommendedHours = 8;
			else
				recommendedHours = 7;
			
			
			sleepHoursByAgeText.setText(recommendedHours + " hrs");
		}
		else
		{
			System.err.println("=========> USER UNINITIALIZED. IGNORE IF USING TESTING BYPASS <=========");
		}
	}
	public SleepLog()
	{
		mController = Controller.getInstance();
	}
	
	@Override
	public Scene getView() 
	{
		try {
			BorderPane bp = FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
			return new Scene(bp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	// TODO Button methods for creating, editing, and deleting entries
	

}

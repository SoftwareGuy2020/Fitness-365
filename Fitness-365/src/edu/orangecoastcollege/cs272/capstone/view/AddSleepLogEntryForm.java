package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import edu.orangecoastcollege.cs272.capstone.model.SleepLogEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.LocalDatePicker;
import jfxtras.scene.control.LocalTimePicker;

public class AddSleepLogEntryForm extends GridPane implements SceneNavigation{

	@FXML
	private LocalTimePicker bedTimePicker, wakeTimePicker;
	@FXML
	private LocalDatePicker datePicker;
	@FXML
	private Button addSleepLogEntryButton;
	@FXML
	private TextField numInterruptionsTF;
	@FXML
	private Label invalidNumberErrorLabel;
	
	private Controller mController;
	
	public void initialize() {
		mController = Controller.getInstance();
	}
	@Override
	public Scene getView() {
		try {			
			
			Pane pane = FXMLLoader.load(getClass().getResource("addSleepLogEntryForm.fxml"));
			return new Scene(pane);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	@FXML
	private void addSleepLogEntry()
	{
		LocalTime sleepTime, wakeTime;
		LocalDate date;
		int numOfInterruptions;
		
		try
		{
			numOfInterruptions = Integer.valueOf(numInterruptionsTF.getText());
			if (numOfInterruptions < 0) 
				throw new NumberFormatException();
		} catch (NumberFormatException e)
		{
			invalidNumberErrorLabel.setVisible(true);
			return;
		}
		invalidNumberErrorLabel.setVisible(false);
		
		sleepTime = bedTimePicker.getLocalTime();
		wakeTime = wakeTimePicker.getLocalTime();
		date = datePicker.getLocalDate();
		
		SleepLogEntry entry = new SleepLogEntry(date, sleepTime, wakeTime, numOfInterruptions);
		
		entry.setID(mController.addSleepLogEntry(entry));
		
	}
	
	

	
}

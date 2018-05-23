package edu.orangecoastcollege.cs272.capstone.view;
import java.io.IOException;
import java.time.LocalTime;

import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import edu.orangecoastcollege.cs272.capstone.model.SceneNavigation;
import edu.orangecoastcollege.cs272.capstone.model.User;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class AccountPage extends GridPane implements SceneNavigation{
	private static final String ACCOUNTPAGE_FXML_FILENAME = "account_page.fxml";

	public Button changePasswordButton, backToHomeButton, applyChangesButton;
	public Text greetingText;
	public ComboBox<Double> weeklyGoalCB;
	public TextField currentWeightTF, goalWeightTF;
	public Label changesAppliedLabel, errorLabel;
	private Controller mController;

	public AccountPage()
	{
		mController = Controller.getInstance();
	}

	public void initialize() {
		weeklyGoalCB.setItems(FXCollections.observableArrayList(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0));
		if (mController.getCurrentUser() != null)
		{
			User current = mController.getCurrentUser();
			StringBuilder timeGreeting = new StringBuilder();
			LocalTime currentTime = LocalTime.now();

			if (currentTime.isAfter(LocalTime.of(3, 59)) && currentTime.isBefore(LocalTime.NOON))
				timeGreeting.append("Good Morning, ");
			else if (currentTime.isAfter(LocalTime.NOON.minusMinutes(1)) && currentTime.isBefore(LocalTime.of(18, 0)))
				timeGreeting.append("Good Afternoon, ");
			else
				timeGreeting.append("Good Evening, ");


			timeGreeting.append(current.getName().trim().split(" ")[0]);
			greetingText.setText(timeGreeting.toString());
			currentWeightTF.setText(Double.toString(current.getCurrentWeight()));
			goalWeightTF.setText(Double.toString(current.getGoalWeight()));
			weeklyGoalCB.setValue(current.getWeeklyGoal());;
		}
	}

	@Override
	public Scene getView() {
		GridPane gp = null;
		try {
			gp = (GridPane) FXMLLoader.load(getClass().getResource(ACCOUNTPAGE_FXML_FILENAME));
			return new Scene(gp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void backToHome()
	{
		mController.changeScene(new HomePage().getView(), false);
	}

	public void applyChanges()
	{
		errorLabel.setVisible(false);
		User currentUser = mController.getCurrentUser();
		double currentBefore = currentUser.getCurrentWeight();
		double goalBefore = currentUser.getGoalWeight();
		double weeklyBefore = currentUser.getWeeklyGoal();
		try
		{
			if (currentBefore != Double.parseDouble(currentWeightTF.getText()) || weeklyBefore != weeklyGoalCB.getValue()
					|| goalBefore != Double.parseDouble(goalWeightTF.getText()))
			{
				String[] values = {goalWeightTF.getText(), currentWeightTF.getText(), Double.toString(weeklyGoalCB.getValue())};
				String[] fields = {"goal_weight", "current_weight", "weekly_goals"};
				if (mController.updateUser(currentUser, fields, values))
				{
				    	changesAppliedLabel.setVisible(true);
				    	PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
				    	visiblePause.setOnFinished(event -> changesAppliedLabel.setVisible(false));
				    	visiblePause.play();
				}
				else
				{
					System.err.println("SQL ERROR");
					changesAppliedLabel.setVisible(false);
				}
			}
		}
		catch (NumberFormatException e)
		{
			errorLabel.setVisible(true);
		}
	}

	public void goToChangePasswordPopUp()
	{
		try
		{
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("reset_password.fxml"));
			Pane pane = loader.load();


			stage.setScene(new Scene(pane));
			stage.initStyle(StageStyle.UTILITY);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.showAndWait();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

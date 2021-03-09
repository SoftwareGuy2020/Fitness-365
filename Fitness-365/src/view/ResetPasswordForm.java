package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResetPasswordForm extends AnchorPane{
	@FXML
	private AnchorPane mPane;
	@FXML
	private PasswordField currentPF, newPF, confirmPF;
	@FXML
	private Label currentErrorLabel, mismatchErrorLabel, newIsOldErrorLabel;
	@FXML
	private Button resetPasswordButton;
	
	private Controller mController;
	
	
	public ResetPasswordForm()
	{
		mController = Controller.getInstance();
	}
	
	@FXML
	private void initialize() {
	    Platform.runLater( () -> mPane.requestFocus() );
	}

	@FXML
	private void resetPassword()
	{
		if (mController.authenticateLogin(mController.getCurrentUser().getUserName(), currentPF.getText()))
		{
			currentErrorLabel.setVisible(false);
			newIsOldErrorLabel.setVisible(false);
			if (newPF.getText().isEmpty() || confirmPF.getText().isEmpty())
			{
				return;
			}
			else if (newPF.getText().equals(confirmPF.getText()))
			{
				
				if(!mController.updateUserPassword(mController.getCurrentUser(), newPF.getText()))
				{
					newIsOldErrorLabel.setVisible(true);
				}
				else
				{
					Stage stage = (Stage) resetPasswordButton.getScene().getWindow();
					stage.close();
				}
			}
			else
			{
				mismatchErrorLabel.setVisible(true);
			}
		}
		else
		{
			mismatchErrorLabel.setVisible(false);
			newIsOldErrorLabel.setVisible(false);
			currentErrorLabel.setVisible(true);
		}
	}
}

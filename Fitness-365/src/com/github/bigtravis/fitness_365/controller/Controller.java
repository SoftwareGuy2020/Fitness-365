package com.github.bigtravis.fitness_365.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author Travis
 *
 */
public class Controller extends Application {
	
	private static Controller mInstance;
	
	

	public static Controller getInstance() {
		if (mInstance == null)
			mInstance = new Controller();

		return mInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}

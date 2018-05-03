package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import jfxtras.scene.control.CalendarPicker;

import javafx.scene.control.TableView;

public class foodDiary {
	@FXML
	private TableView breakfastTableView;
	@FXML
	private TableView snacksTableView;
	@FXML
	private TableView lunchTableView;
	@FXML
	private TableView dinnerTableView;
	@FXML
	private TextField calorieGoalTF;
	@FXML
	private TextField calorieConsumedTF;
	@FXML
	private TextField calorieRemainingTF;
	@FXML
	private CalendarPicker calendar;
	@FXML
	private Button addMealButton;
	@FXML
	private Button deleteMealButton;

}

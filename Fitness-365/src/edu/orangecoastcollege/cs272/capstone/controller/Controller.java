package edu.orangecoastcollege.cs272.capstone.controller;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import edu.orangecoastcollege.cs272.capstone.model.Category;
import edu.orangecoastcollege.cs272.capstone.model.DBModel;
import edu.orangecoastcollege.cs272.capstone.model.Food;
import edu.orangecoastcollege.cs272.capstone.model.FoodDiaryEntry;
import edu.orangecoastcollege.cs272.capstone.model.Meal;
import edu.orangecoastcollege.cs272.capstone.model.PasswordEncryption;
import edu.orangecoastcollege.cs272.capstone.model.Sex;
import edu.orangecoastcollege.cs272.capstone.model.SleepLogEntry;
import edu.orangecoastcollege.cs272.capstone.model.Units;
import edu.orangecoastcollege.cs272.capstone.model.User;
import edu.orangecoastcollege.cs272.capstone.view.Login;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Travis
 *
 */
public class Controller extends Application {

	private static final String DB_NAME = "fitness_365.db";
	private static final String[] TABLE_NAMES = {"users", "exercises", "workout_diary", "food_diary", "saved_workouts", "meals",
												"favorite_meals", "sleep_log", "personal_bests", "pictures", "weight_progress", "food"};
	private static final String[][] FIELD_NAMES = {{"_id", "username", "password", "salt", "security_question",
													"security_answer", "full_name", "birth_date", "sex", "units", "height",
													"starting_weight", "goal_weight", "current_weight", "weekly_goals", "tdee"},
												{"_id", "name", "muscle_group"},
												{"_id", "user_id", "exercise_id", "weight", "reps", "date"},
												{"_id", "meal_id", "num_servings", "category", "date", "user_id"},
												{"_id", "name", "user_id", "exercise_id"},
												{"_id", "name", "serving_size", "calories", "fat", "carbs", "protein"},
												{"_id", "meal_id", "user_id"},
								/*7*/			{"_id", "user_id", "date", "bed_time", "wake_time", "num_wakeups"},
												{"_id", "user_id", "mile_time", "bench_press", "deadlift", "squat"},
												{"_id", "user_id", "pic"},
												{"_id", "date", "weight", "pic_id", "user_id", "bmr", "tdee", "bf_percent", "bmi"},
												{"_id", "name", "group", "calories", "protein", "fat", "carbs", "fiber"}};

	private static final String[][] FIELD_TYPES = { {"INTEGER PRIMARY KEY", "TEXT", "BLOB", "BLOB", "TEXT", "TEXT", "TEXT", "TEXT",
														"INTEGER", "BLOB", "REAL", "REAL", "REAL", "REAL", "REAL", "INTEGER"},
													{"INTEGER PRIMARY KEY", "TEXT", "TEXT"},
													{"INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "REAL", "INTEGER", "TEXT"},
													{"INTEGER PRIMARY KEY", "INTEGER", "REAL", "TEXT", "TEXT", "INTEGER"},
													{"INTEGER PRIMARY KEY", "TEXT", "INTEGER", "INTEGER"},
													{"INTEGER PRIMARY KEY", "TEXT", "REAL", "INTEGER", "REAL", "REAL", "REAL"},
													{"INTEGER PRIMARY KEY", "INTEGER", "INTEGER"},
													{"INTEGER PRIMARY KEY", "INTEGER", "TEXT", "TEXT", "TEXT", "INTEGER"},
													{"INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "REAL", "REAL", "REAL"},
													{"INTEGER PRIMARY KEY", "INTEGER", "BLOB"},
													{"INTEGER PRIMARY KEY", "TEXT", "REAL", "INTEGER", "INTEGER", "REAL", "REAL", "REAL", "INTEGER"},
													{"INTEGER PRIMARY KEY", "TEXT", "TEXT", "REAL", "REAL", "REAL", "REAL", "REAL"}};

	private static final String[][] FOREIGN_KEYS = {{}, {}, {"FOREIGN KEY(" + FIELD_NAMES[2][1] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")",
															"FOREIGN KEY(" + FIELD_NAMES[2][2] + ") REFERENCES " + TABLE_NAMES[1] + "(" + FIELD_NAMES[1][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[3][1] + ") REFERENCES " + TABLE_NAMES[6] + "(" + FIELD_NAMES[6][0] + ")",
													"FOREIGN KEY(" + FIELD_NAMES[3][4] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[4][2] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")",
														"FOREIGN KEY(" + FIELD_NAMES[4][3] + ") REFERENCES " + TABLE_NAMES[1] + "(" + FIELD_NAMES[1][0] + ")"},
													{},
													{"FOREIGN KEY(" + FIELD_NAMES[6][1] + ") REFERENCES " + TABLE_NAMES[5] + "(" + FIELD_NAMES[5][0] + ")",
														"FOREIGN KEY(" + FIELD_NAMES[6][2] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[7][1] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[8][1] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[9][1] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{"FOREIGN KEY(" + FIELD_NAMES[10][2] + ") REFERENCES " + TABLE_NAMES[9] + "(" + FIELD_NAMES[9][0] + ")",
														"FOREIGN KEY(" +  FIELD_NAMES[10][3] + ") REFERENCES " + TABLE_NAMES[0] + "(" + FIELD_NAMES[0][0] + ")"},
													{}};

	private static Controller mInstance;
	private DBModel mDB;
	private Stage mMainStage;
    
	private User mCurrentUser;
	
	private ObservableList<Food> mAllFoodsList;

	public Controller() {}


	public static Controller getInstance() {
		if (mInstance == null)
		{
			mInstance = new Controller();
			
			mInstance.mAllFoodsList = FXCollections.observableArrayList();
		}

		return mInstance;
	}


	@Override
	public void init() throws Exception {
		mInstance = this;
		mDB = new DBModel(DB_NAME, TABLE_NAMES, FIELD_NAMES, FIELD_TYPES, FOREIGN_KEYS);

		// TODO remove this block after debugging
		if (mDB.getRecordCount(TABLE_NAMES[0]) == 0) {
			byte[] salt = PasswordEncryption.generateSalt();
			byte[] password = PasswordEncryption.getEncryptedPassword("password123", salt);
			User user = new User( -1, "player1", "Favorite Color?", "Red", "John Smith",
					LocalDate.of(1991, 1, 11), Sex.Male, new Units[] {Units.POUNDS, Units.INCHES, Units.MILES},
					72, 185.0, 205.0, 185.0, 1.0, 2_871);
			mDB.createUser(TABLE_NAMES[0], Arrays.copyOfRange(FIELD_NAMES[0], 1, FIELD_NAMES[0].length),
					 user, password, salt);
		}
		super.init();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Login login = new Login();
		mMainStage = primaryStage;

		mInstance.changeScene(login.getView(), false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public User getUser(String username) {
		if (username != null) {
			try {
				int userID = mDB.searchUsers(username);
				ResultSet rs = mDB.getRecord(TABLE_NAMES[0], Integer.toString(userID));
				if (rs.next()) {
					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(6),
							rs.getString(7), LocalDate.parse(rs.getString(8)), Sex.parseInt(rs.getInt(9)),
							Units.parse(rs.getBytes(10)),
							rs.getInt(11), rs.getDouble(12), rs.getDouble(13), rs.getDouble(14),
							rs.getDouble(15), rs.getInt(16));
					return user;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private byte[][] getPasswordAndSalt(int userID) throws SQLException {
		ResultSet rs = mDB.getRecord(TABLE_NAMES[0], Integer.toString(userID));
		if (rs.next()) {
			byte[][] set = new byte[][] {rs.getBytes(3), rs.getBytes(4)};
			return set;
		}
		return null;
	}

	public boolean authenticateLogin(String username, String attemptedPW) {
		User user = mInstance.getUser(username);
		if (user == null)
			return false;

		try {
			byte[][] passwordAndSalt = getPasswordAndSalt(user.getId());
			if (passwordAndSalt != null) {
				return PasswordEncryption.authenticate(attemptedPW, passwordAndSalt[0], passwordAndSalt[1]);
			}
		} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createNewUser(User user, String password) {
		try {
			byte[] salt = PasswordEncryption.generateSalt();
			byte[] hashedPassword = PasswordEncryption.getEncryptedPassword(password, salt);
			mDB.createUser(TABLE_NAMES[0], Arrays.copyOfRange(FIELD_NAMES[0], 1, FIELD_NAMES[0].length),
					user, hashedPassword, salt);
		}
		catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean updateUser(User user, String[] fields, String[] values) {
		if (user != null && fields.length == values.length) {
			try {
				return mDB.updateRecord(TABLE_NAMES[0], Integer.toString(user.getId()), fields, values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean updateUserPassword(User user, String newPassword) {
		// can't use previous password as new password
		if (authenticateLogin(user.getUserName(), newPassword))
			return false;

		try {
			byte[] hashedPassword = PasswordEncryption.getEncryptedPassword(newPassword, getPasswordAndSalt(user.getId())[1]);
			return mDB.updateUserPassword(TABLE_NAMES[0], Integer.toString(user.getId()), hashedPassword);
		} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	public void changeScene(Scene scene, boolean resizable) {
		mMainStage.setScene(scene);
		mMainStage.setResizable(resizable);
		mMainStage.setTitle("Fitness 365");
	}
	
	public void setCurrentUser(String username)
	{
		mCurrentUser = getUser(username);
	}
	
	public User getCurrentUser()
	{
		return mCurrentUser;
	}


	public int addMeal(Meal meal) {
		String[] fields = Arrays.copyOfRange(FIELD_NAMES[5], 1, FIELD_NAMES[5].length);
		String[] values = {meal.getName(), Double.toString(meal.getServingSize()),
							Double.toString(meal.getCalories()), Double.toString(meal.getFat()),
							Double.toString(meal.getCarbs()), Double.toString(meal.getProtein())};
		
		try {
			return mDB.createRecord(TABLE_NAMES[5], fields, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}		
	}
	
	public Meal getMeal(int id) {
		String key = Integer.toString(id);		
		try {
			ResultSet rs = mDB.getRecord(TABLE_NAMES[5], key);
			if (rs.next()) {
				return new Meal(rs.getInt(1), rs.getString(2), rs.getDouble(3),
								rs.getInt(4), rs.getDouble(5), rs.getDouble(6),
								rs.getDouble(7));
			}
		} catch (SQLException e) {			
			e.printStackTrace();			
		}
		return null;
	}
	
	public int addFoodDiaryEntry(FoodDiaryEntry entry) {
		if (entry == null)
			return -1;		
		
		try {			
			Meal meal = entry.getMeal();
			String[] mealValues = {meal.getName(), Double.toString(meal.getServingSize()), Integer.toString(meal.getCalories()),
						Double.toString(meal.getFat()), Double.toString(meal.getCarbs()), Double.toString(meal.getProtein())};
			
			ResultSet rs = mDB.getRecordMatch(TABLE_NAMES[5],
						Arrays.copyOfRange(FIELD_NAMES[5], 1, FIELD_NAMES[5].length), mealValues);
			
			int key = (!rs.next()) ? addMeal(meal) : rs.getInt(1);
			entry.getMeal().setId(key);
			
			
			String[] entryValues = {Integer.toString(entry.getMeal().getId()), Double.toString(entry.getNumServings()),
						entry.getCategory().toString(), entry.getDate().toString(),
						Integer.toString(mCurrentUser.getId())};
		
			return mDB.createRecord(TABLE_NAMES[3], Arrays.copyOfRange(FIELD_NAMES[3], 1, FIELD_NAMES[3].length), entryValues);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public ObservableList<FoodDiaryEntry> getAllFoodDiaryEntries() {
		String key = Integer.toString(mCurrentUser.getId());
		ObservableList<FoodDiaryEntry> entries = FXCollections.observableArrayList();
		
		try {
			ArrayList<Integer> mealIdNums = new ArrayList<Integer>();
			ResultSet rs = mDB.getAllRecordsMatch(TABLE_NAMES[3], new String[] {FIELD_NAMES[3][5]},
							new String[]{key});
						
			Meal meal;
			int entryId;
			double numServings;
			Category category;
			LocalDate date;
			
			while (rs.next()) {				
				entryId = rs.getInt(1);
				numServings = rs.getDouble(3);				
				category = Category.valueOf(rs.getString(4));
				date = LocalDate.parse(rs.getString(5));
				mealIdNums.add(rs.getInt(2));
				entries.add(new FoodDiaryEntry(entryId, null, numServings, category, date, mCurrentUser.getId()));				
			}
			// The second for loop and the arraylist mealIdNums is to handle the fact
			// that only one resultset can exist per sqlite statement object.
			// executing a new statement overwrites the previous data in resultset.		
			for (int i = 0; i < mealIdNums.size(); ++i) {
				meal = getMeal(mealIdNums.get(i));
				entries.get(i).setMeal(meal);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entries;
	}
	
	public int addSleepLogEntry(SleepLogEntry entry)
	{
		if (entry == null)
			return -1;	
		
		try {			
			String[] entryValues = {Integer.toString(mCurrentUser.getId()), entry.getDate().toString()
					, entry.getSleepTime().toString(), entry.getWakeTime().toString(), Integer.toString(entry.getNumOfInterruptions())};
			
			return mDB.createRecord(TABLE_NAMES[7],Arrays.copyOfRange(FIELD_NAMES[7], 1, FIELD_NAMES[7].length), entryValues);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public void deleteSleepLogEntry(SleepLogEntry entry)
	{
		String key = String.valueOf(entry.getID());
		try {
			mDB.deleteRecord(TABLE_NAMES[7], key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<SleepLogEntry> getAllSleepLogEntries()
	{
		String key = Integer.toString(mCurrentUser.getId());
		ObservableList<SleepLogEntry> entries = FXCollections.observableArrayList();
		
		try {
			ResultSet rs = mDB.getAllRecordsMatch(TABLE_NAMES[7], new String[] {FIELD_NAMES[7][1]}, new String[] {key});
			
			LocalDate date;
			LocalTime bedTime, wakeTime;
			int entryId, numOfInterruptions;
			
			while (rs.next())
			{
				entryId = rs.getInt(1);
				date = LocalDate.parse(rs.getString(3));
				bedTime = LocalTime.parse(rs.getString(4));
				wakeTime = LocalTime.parse(rs.getString(5));
				numOfInterruptions = rs.getInt(6);
				entries.add(new SleepLogEntry(entryId, date, bedTime, wakeTime, numOfInterruptions));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return entries;
	}
}

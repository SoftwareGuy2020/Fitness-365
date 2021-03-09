package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Represents a SQLite 3 database.
 * @author Travis
 *
 */
public class DBModel implements AutoCloseable {

	private String mDBName;
	private String[] mTableNames;
	private String[][] mFieldNames;
	private String[][] mFieldTypes;
	private Connection mConnection;
	private Statement mStmt;

	/**
	 * Instantiates an instance of DBModel
	 * @param dbName The name of the database
	 * @param tableNames the names of the tables to be created
	 * @param fieldNames the field names of the tables being created
	 * @param fieldTypes the types that the field names are that belong to the tables that are being created.
	 * @param foreignKeys field names for any foreign keys
	 * @throws SQLException
	 */
	public DBModel(String dbName, String[] tableNames, String[][] fieldNames, String[][] fieldTypes, String[][] foreignKeys) throws SQLException {
		mDBName = dbName;
		mTableNames = Arrays.copyOf(tableNames, tableNames.length);
		mFieldNames = Arrays.copyOf(fieldNames, fieldNames.length);
		mFieldTypes = Arrays.copyOf(fieldTypes, fieldTypes.length);

		if (mFieldNames == null || mFieldTypes == null || mFieldNames.length == 0
				|| mFieldNames.length != mFieldTypes.length)
			throw new SQLException("Database field names and types must exist and have the same number of elements.");
		mConnection = connectToDB();
		mStmt = mConnection.createStatement();
		createTables(foreignKeys);
	}



	private void createTables(String[][] foreignKeys) throws SQLException {
		StringBuilder createSQL = null;
		for (int i = 0; i < mTableNames.length; ++i) {

			createSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
			createSQL.append(mTableNames[i]).append("(");

			for (int j = 0; j < mFieldNames[i].length - 1; ++j)
				createSQL.append(mFieldNames[i][j]).append(" ").append(mFieldTypes[i][j]).append(", ");

			createSQL.append(mFieldNames[i][mFieldNames[i].length - 1]).append(" ")
			.append(mFieldTypes[i][mFieldNames[i].length - 1]);

			if (foreignKeys != null) {
				for (int k = 0; k < foreignKeys[i].length; ++k) {
					createSQL.append(", ").append(foreignKeys[i][k]);
				}
			}
			createSQL.append(")");

			mStmt.executeUpdate(createSQL.toString());
		}
	}
	/**
	 *  Search's the user database
	 * @param username (string)
	 * @return the ID of the user, -1 if username is null OR user is not found
	 * @throws SQLException
	 */
	public int searchUsers(String username) throws SQLException {
		if (username != null) {
			ResultSet rs = mStmt.executeQuery("SELECT " + mFieldNames[0][0] + " FROM " + mTableNames[0] + " WHERE "
					+ mFieldNames[0][1] + "='" + username + "'");
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return -1;
	}
	/**
	 * Gets all records from the specified table.
	 * @param table
	 * @return ResultSet of all the records
	 * @throws SQLException
	 */
	public ResultSet getAllRecords(String table) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return null;

		String selectSQL = "SELECT * FROM " + mTableNames[tableIdx];
		return mStmt.executeQuery(selectSQL);
	}

	/**
	 * Gets all records from the specified table that match the field, value combos.
	 * @param table
	 * @param fields
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getAllRecordsMatch(String table, String[] fields, String[] values) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1 || fields.length != values.length)
			return null;

		StringBuilder selectSQL = new StringBuilder("SELECT * FROM ");
		selectSQL.append(mTableNames[tableIdx]).append(" WHERE ");
		for (int i = 0; i < fields.length - 1; ++i) {
			selectSQL.append(fields[i]).append("=")
			.append(convertToSQLText(tableIdx, fields[i], values[i])).append(" AND ");
		}
		selectSQL.append(fields[fields.length - 1]).append("=").append(values[fields.length - 1]);

		return mStmt.executeQuery(selectSQL.toString());
	}

	/**
	 * Gets a record from the specified table at the index, key.
	 * @param table
	 * @param key the index of the record.
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getRecord(String table, String key) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return null;

		String singleRecord = "SELECT * FROM " + mTableNames[tableIdx] + " WHERE " + mFieldNames[tableIdx][0] + "=" + key;
		return mStmt.executeQuery(singleRecord);
	}

	/**
	 * Gets the first record from the specified table that matches the field, value combos.
	 * @param table
	 * @param fields
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getRecordMatch(String table, String[] fields, String[] values) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1 || fields.length != values.length)
			return null;

		StringBuilder selectSQL = new StringBuilder("SELECT * FROM ");
		selectSQL.append(mTableNames[tableIdx]).append(" WHERE ");

		for (int i = 0; i < fields.length - 1; ++i) {
			selectSQL.append(fields[i]).append("=")
			.append(convertToSQLText(tableIdx, fields[i], values[i])).append(" AND ");
		}
		selectSQL.append(fields[fields.length - 1]).append("=").append(values[fields.length - 1]);

		return mStmt.executeQuery(selectSQL.toString());
	}

	/**
	 * Gets the number of records in the specified table.
	 * @param table
	 * @return the number of records
	 * @throws SQLException
	 */
	public int getRecordCount(String table) throws SQLException {
		int count = 0;
		try (ResultSet rs = getAllRecords(table)) {
			if (rs != null) {
				while (rs.next())
					count++;
			}
		}
		return count;
	}

	/**
	 * Creates a record int the specified table.
	 * @param table
	 * @param fields
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public int createRecord(String table, String[] fields, String[] values) throws SQLException {
		if (fields == null || values == null || fields.length == 0 || fields.length != values.length)
			return -1;

		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return -1;

		StringBuilder insertSQL = new StringBuilder("INSERT INTO ");
		insertSQL.append(table).append("(");
		for (int i = 0; i < fields.length; i++)
			insertSQL.append(fields[i]).append((i < fields.length - 1) ? "," : ") VALUES(");
		for (int i = 0; i < values.length; i++)
			insertSQL.append(convertToSQLText(tableIdx, fields[i], values[i])).append((i < values.length - 1) ? "," : ")");
		
		mStmt.executeUpdate(insertSQL.toString());
		// Return the newly generated primary key (as an int)
		return mStmt.getGeneratedKeys().getInt(1);
	}


	/**
	 * Creates a user record in the userTable of the database.
	 * @param userTable the userTable of the database.
	 * @param fields
	 * @param user
	 * @param hash the hashed password
	 * @param salt the salt used in hashing the password
	 * @return
	 * @throws SQLException
	 */
	public int createUser(String userTable, String[] fields, User user, byte[] hash, byte[] salt) throws SQLException {
		int id = -1;
		StringBuilder sqlString = new StringBuilder("INSERT INTO ");
		sqlString.append(userTable).append("(");

		for (int i = 0; i < fields.length; ++i)
			sqlString.append(fields[i]).append((i < fields.length - 1) ? "," : ") VALUES(");
		for (int i = 0; i < fields.length; ++i)
			sqlString.append("?").append((i < fields.length - 1) ? "," : ")");

		PreparedStatement pStmt = mConnection.prepareStatement(sqlString.toString());
		pStmt.setString(1, user.getUserName());
		pStmt.setBytes(2, hash);
		pStmt.setBytes(3, salt);
		pStmt.setString(4, user.getSecurityQ());
		pStmt.setString(5, user.getSecurityA());
		pStmt.setString(6, user.getName());
		pStmt.setString(7, user.getBirthDate());
		pStmt.setInt(8, user.getSex() == Sex.Male ? 0 : 1);
		pStmt.setBytes(9, user.convertUnitsToByteArray());
		pStmt.setInt(10, user.getHeight());
		pStmt.setDouble(11, user.getStartingWeight());
		pStmt.setDouble(12, user.getGoalWeight());
		pStmt.setDouble(13, user.getCurrentWeight());
		pStmt.setDouble(14, user.getWeeklyGoal());
		pStmt.setInt(15, user.getTDEE());
		
		id = pStmt.executeUpdate();
		pStmt.close();
		return id;

	}

	/**
	 * Gets the tableNames array index for the table.
	 * If the table does not exist or table param is null, then -1 is returned;
	 * @param table - the name of the database table
	 * @return the index for the table, or -1 if not found/null.
	 */
	private int getTableIndex(String table) {
		if (table == null)
			return -1;

		for (int i = 0; i < mTableNames.length; ++i) {
			if (mTableNames[i].equals(table))
				return i;
		}
		return -1;
	}

	/**
	 * Updates a specific record in the database table.
	 * @param table the table where the record is located.
	 * @param key the index of the record.
	 * @param fields
	 * @param values
	 * @return True if update completed, false if not.
	 * @throws SQLException
	 */
	public boolean updateRecord(String table, String key, String[] fields, String[] values) throws SQLException {
		if (fields == null || values == null || fields.length == 0 || values.length == 0
				|| fields.length != values.length)
			return false;

		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return false;

		StringBuilder updateSQL = new StringBuilder("UPDATE ");
		updateSQL.append(table).append(" SET ");
		for (int i = 0; i < fields.length; i++)
			updateSQL.append(fields[i]).append("=").append(convertToSQLText(tableIdx, fields[i], values[i]))
					.append((i < values.length - 1) ? "," : " ");

		updateSQL.append("WHERE ").append(mFieldNames[tableIdx][0]).append("=").append(key);
		System.out.println(updateSQL.toString());
		mStmt.executeUpdate(updateSQL.toString());

		return true;
	}

	/**
	 * Updates the user's password in the database table.
	 * @param table the table where the record is located.
	 * @param key the index of the record.
	 * @param newPassword the new hashed password.	 
	 * @return True if update completed, false if not.
	 * @throws SQLException
	 */
	public boolean updateUserPassword(String table, String key, byte[] newPassword) throws SQLException {
		if (key.isEmpty() || newPassword == null)
			return false;

		StringBuilder updateSQL = new StringBuilder("UPDATE ");
		updateSQL.append(table).append(" SET ");
		updateSQL.append(mFieldNames[0][2]).append("=").append("?");

		PreparedStatement pStmt = mConnection.prepareStatement(updateSQL.toString());
		pStmt.setBytes(1, newPassword);
		pStmt.executeUpdate();

		return true;
	}

	/**
	 * Deletes all records in the specified table.
	 * @param table
	 * @throws SQLException
	 */
	public void deleteAllRecords(String table) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return;

		String deleteSQL = "DELETE FROM " + mTableNames[tableIdx];
		mStmt.executeUpdate(deleteSQL);
	}

	/**
	 * Deletes the record in the specified table.
	 * @param table
	 * @param key
	 * @return True if completed, false if not.
	 * @throws SQLException
	 */
	public boolean deleteRecord(String table, String key) throws SQLException {
		int tableIdx = getTableIndex(table);
		if (tableIdx == -1)
			return false;

		String deleteRecord = "DELETE FROM " + mTableNames[tableIdx] + " WHERE " + mFieldNames[tableIdx][0] + "=" + key;
		mStmt.executeUpdate(deleteRecord);
		return true;
	}

	private String convertToSQLText(int tableNameIdx, String field, String value) {

		for (int i = 0; i < mFieldNames[tableNameIdx].length; i++) {
			if (field.equalsIgnoreCase(mFieldNames[tableNameIdx][i])) {
				if (mFieldTypes[tableNameIdx][i].equals("TEXT"))
					return "'" + value + "'";
				else
					break;
			}
		}
		return value;
	}

	private Connection connectToDB() throws SQLException {
		// Load SQLite database classes
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Establish a connection to the database and return that connection
		return DriverManager.getConnection("jdbc:sqlite:" + mDBName);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws SQLException {
		mStmt.close();
		mConnection.close();
	}
}
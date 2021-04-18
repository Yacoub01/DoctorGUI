/*
 * Database Connection Class
 *
 * Responsible for connecting/maintaining/executing/disconnecting with the SQL database.
 *
 * @Author lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DatabaseConnection {

    //Variables used to connect with the database
    private String SQL_HOST, SQL_DATABASE_NAME, SQL_USERNAME,SQL_PASSWORD;
    private int SQL_PORT;

    private static Connection connection; //This is the variable we will use to connect to database

    private Frame errorFrame; //Used when displaying database errors to the user when they arise

    private boolean guiError; //Should the program show a GUI on error.

    /**
     * Constructor for DatabaseConnection
     * @param host - String host name for SQL Server
     * @param databaseName - String name of the database to use
     * @param username - String username for the SQL Server
     * @param password - String password for the SQL Server
     * @param port - int port for the SQL server (3306 is the default port)
     */
    DatabaseConnection(String host, String databaseName, String username, String password, int port){
        this.SQL_HOST = host;
        this.SQL_DATABASE_NAME = databaseName;
        this.SQL_USERNAME = username;
        this.SQL_PASSWORD = password;
        this.SQL_PORT = port;
        guiError = false;
        errorFrame = new JFrame();
        errorFrame.setTitle("Database Error");
    }

    /**
     * Execute a String SQL statement.
     * @param statement - The String value of the SQL statement needing to be executed.
     * @return true if the statement was executed without issues, false if otherwise.
     */
    public boolean executeStatement(String statement) {
        try {
            PreparedStatement stmt = connection.prepareStatement(statement);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) { //An SQL error occurred while executing the statement.
            doError("An issue occurred while attempting to execute a statement with database.");
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    /**
     * Execute a PreparedStatement SQL statement.
     * @param statement - The PreparedStatement value of the SQL statement needing to be executed.
     * @return true if the statement was executed without issues, false if otherwise.
     */
    public boolean executeStatement(PreparedStatement statement) {
        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) { //An SQL error occurred while executing the statement.
            e.printStackTrace();
            System.exit(-1);
        }
        return false;
    }

    /**
     * Query the database using a String statement
     * @param statement - The String value of the statement
     * @return ResultSet for the query if there is one, null if an error occurred
     */
    public ResultSet query(String statement){
        try {
            PreparedStatement stmt = connection.prepareStatement(statement);
            return stmt.executeQuery();
        } catch (SQLException e){
            doError("An issue occurred while attempting to query the database.");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Query the database using a PreparedStatement statement
     * @param statement - The PreparedStatement value of the statement
     * @return ResultSet for the query if there is one, null if an error occurred
     */
    public ResultSet query(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e){
            doError("An issue occurred while attempting to query the database.");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Open a SQL connection with the database.
     * @return true if connection was opened without issues, false if the connection was not established.
     */
    public boolean connect() {
        String conString = "jdbc:mysql://" + SQL_HOST + ":" + SQL_PORT + "/" + SQL_DATABASE_NAME; //String is used by the driver to know what type of server to connect to, what type of connection and the address details
        //String conString = "jdbc:mysql://" + SQL_HOST + ":" + SQL_PORT + "/" + SQL_DATABASE_NAME + "?useSSL=true&requireSSL=false"; //String is like above, however it holds additional SSL details that are used to log into our AZURE test database.
        try { //Try to ensure driver is accessible before attempting a connection (the developer has it in their class path or is automatically put there by Marven)
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) { //DJBC Driver was not found! Can't open connection!
            doError("DatabaseConnection Error > JDBC Driver is unavailable!");
            e.printStackTrace();
            System.exit(-1);
        }
        try { //Try to set up/open the connection to the database.
            connection = DriverManager.getConnection((conString), SQL_USERNAME, SQL_PASSWORD);
        } catch (SQLException e) { //Something went wrong while setting up connection (CONNECTION NOT ESTABLISHED)
            doError("An error occurred while attempting to connect to the database. Are the credentials correct?");
            e.printStackTrace();
            System.exit(-1);
        }
        return true; //Connection was established without a problem.
    }

    /**
     * Close the SQL connection.
     * @return true if connection was closed without issues, false if otherwise.
     */
    public boolean disconnect() {
        try {
            if (connection != null && !connection.isClosed()) { //Ensure the connection is null and the connection isn't already closed.
                connection.close(); //Close the connection.
            }
        } catch (Exception e) {
            doError("An error occurred while attempting close the database connection safely.");
            e.printStackTrace();
            System.exit(-1);
        }
        return true;
    }

    /**
     * Get Connection object
     * Used when needing to create a new PreparedStatement object
     */
    public Connection getConnection(){
        return connection;
    }

    /**
     * Should the program show an error in the event of an SQL Error to the user via a GUI?
     * Set this to true if so, false if not.
     * False by default.
     */
    public void setGUIError(boolean value){
        guiError = value;
    }

    /**
     * Show a error message when an issue comes up.
     * Error will show both in console and in a GUI if guiError is set to true;
     * @param errorMessage
     */
    public void doError(String errorMessage){
        System.out.println("DatabaseConnection Error > " + errorMessage);
        if(guiError){
            JOptionPane.showMessageDialog(errorFrame, errorMessage);
        }
    }

}

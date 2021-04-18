/*
  Database Statements Class

  Holds all pre-defined SQL statements used in interacting with the database.

  @Author Joshua Sylvester @jrs63
  @Author Luke Hadley @lth20

 * Change Log:
 * SPRINT 1
 * jrs63 - Created Create Table Statements
 * lth20 - Refactored class into correct package and changed class name.
 * lth20 - Changed names of statements so the variables are labelled 'CREATE_TABLE_X' for ease of use.
 * lth20 - Added the 'IF NOT EXISTS' statement into the table creation statements
 * jrs63 - Fixed issue with Create Table Statements
 * lth20 - Added statement to drop all tables
 * jrs63 - Updated GET_BOOKING statement to get the first name and last name of a patient as well
 * lth20 - Updated GET_LOGIN statement to return the type of the User for validation later.
 * lth20 - Added missing 'Receptionist' constraint to Login table
 * SPRINT 2
 * jrs63 - Added create table statement for Assigned table
 * jrs63 - Added query to add an assignment
 * jrs63 - Added querys to delete an old assignment
 * lth20 - Added drop table for 'assigned' table
 * jrs63 - Added querys to get doctors and patients
 * lth20 - Added Create/Insert/Query/Delete statements for Messages table.
 * lth20 - Added a Subject line to Message table/statements
 * lth20 - Added GET_PATIENTS_FROM_DOCTOR statement
 * lth20 - Fixed issue in GET_PATIENTS_FROM_DOCTOR ( Changed from LEFT JOIN to JOIN )
 * jrs63 - Added create table for prescriptions
 * jrs63 - Added querys for prescriptions
 * lth20 - Added GET_PATIENT & GET_DOCTOR statement
 * lth20 - Changed booking table to use Timestamp instead of date type ( for compatibility reasons - Date is deprecated and shouldn't be used)
 * lth20 - Changed constraints in Prescription table.
 * lth20 - Changed Messages table date constraint to a Timestamp
 * SPRINT 3
 * jrs63 - Added create table query for Log
 * jrs63 - Added INSERT_LOG query
 * lth20 - Added field into GET_BOOKINGS so that it returns the additional details field
 * lth20 - Added GET_USERS_GIVEN_TYPE statement.
 * jrs63 - Added DELETE_PRESCRIPTIONS
 * lth20 - Quality Assurance / Refactored code
 */

package com.gitlab.co559.group7b.sprint3.database;

public class DatabaseStatements {

	//All CREATE TABLE statements for the project
	public static final String CREATE_TABLE_LOGIN =  "CREATE TABLE IF NOT EXISTS `Login` (\n" +
			" `Login` varchar(50) NOT NULL,\n" +
			" `Password` longtext NOT NULL,\n" +
			" `User Type` varchar(20) NOT NULL CHECK('User Type' IN('Doctor','Patient','Admin', 'Receptionist')),\n" +
			" `User` varchar(50) NOT NULL,\n" +
			" PRIMARY KEY (`Login`),\n" +
			" FOREIGN KEY (User) References User(Email) ) ;";

	public static final String CREATE_TABLE_USER ="CREATE TABLE IF NOT EXISTS `User` (\n" +
			" `Email` varchar(50) NOT NULL,\n" +
			" `FName` varchar(50) NOT NULL,\n" +
			" `LName` varchar(50) NOT NULL,\n" +
			" `PhoneNum` char(11) NOT NULL,\n" +
			" `Address` text NOT NULL,\n" +
			" PRIMARY KEY (`Email`) ) ;";

	public static final String CREATE_TABLE_BOOKING ="CREATE TABLE IF NOT EXISTS `Booking` (\n" +
			" `BookingID` int NOT NULL AUTO_INCREMENT,\n" +
			" `DoctorEmail` varchar(50) NOT NULL,\n" +
			" `PatientEmail` varchar(50) NOT NULL,\n" +
			" `DateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
			" `Status` varchar(20) NOT NULL CHECK('Status' IN('Pending','InProgress', 'Completed', 'Cancelled')),\n" +
			" `additionalDetails` text,\n" +
			" PRIMARY KEY (`BookingID`),\n" +
			" FOREIGN KEY (DoctorEmail) REFERENCES User(Email),\n" +
			" FOREIGN KEY (PatientEmail) REFERENCES User(Email) ) ;";

	public static final String CREATE_TABLE_PRESCRIPTION =  "CREATE TABLE IF NOT EXISTS `Prescription` (\n" +
			" `PrescriptionID` int NOT NULL,\n" +
			" `medName` varchar(255) NOT NULL,\n" +
			" `frequencyCount` int NOT NULL ,\n" +
			" `frequencyDuration` varchar(7) NOT NULL CHECK('frequencyDuration' IN('Minutes', 'Hours', 'Days')),\n" +
			" `emptyStomach` TINYINT(1) NOT NULL,\n" +
			" `timeToTakeCount` int NOT NULL ,\n" +
			" `timeToTakeDuration` varchar(6) NOT NULL CHECK('timeToTakeDuration' IN('Weeks', 'Months', 'Years')),\n" +
			" PRIMARY KEY (`PrescriptionID`, `medName`),\n" +
			" FOREIGN KEY (PrescriptionID) References Booking(BookingID) ) ;";

	public static final String CREATE_TABLE_ASSIGNED = "CREATE TABLE IF NOT EXISTS Assigned (\n" +
			" DoctorEmail varchar(50) NOT NULL,\n" +
			" PatientEmail varchar(50) NOT NULL,\n" +
			" PRIMARY KEY (PatientEmail),\n" +
			" FOREIGN KEY (DoctorEmail) REFERENCES User(Email), \n" +
			" FOREIGN KEY (PatientEmail) REFERENCES User(Email) ) ;";

        public static final String CREATE_TABLE_LOG = "CREATE TABLE IF NOT EXISTS Log (\n" +
			" LogID int NOT NULL AUTO_INCREMENT,\n" +
			" Login varchar(50) NOT NULL,\n" +
            " Time TIMESTAMP NOT NULL,\n" +
			" Action text NOT NULL,\n" +
            " Description text,\n" +
            " PRIMARY KEY (LogID),\n" +
			" FOREIGN KEY (Login) REFERENCES Login(Login) ) ;";

	public static final String CREATE_TABLE_MESSAGES = "CREATE TABLE IF NOT EXISTS Messages(\n" +
			"MessageID int NOT NULL AUTO_INCREMENT,\n" +
			"Sender varchar(50) NOT NULL,\n" +
			"Recipient varchar(50) NOT NULL,\n" +
			"Subject longtext NOT NULL,\n" +
			"Message longtext NOT NULL,\n" +
			"SentDateTime TIMESTAMP NOT NULL,\n" +
			"PRIMARY KEY (MessageID),\n" +
			"FOREIGN KEY (Sender) REFERENCES User(Email),\n" +
			"FOREIGN KEY (Recipient) REFERENCES User(Email) ) ;";

	//Delete table statement
	public static final String DROP_TABLES = "DROP TABLE Log, Prescription, Booking, Login, Assigned, Messages, User;";

	//Booking interaction statements
	public static final String GET_BOOKINGS = "SELECT b.BookingID,b.DoctorEmail,b.PatientEmail,b.DateTime,b.additionalDetails,b.Status,u.FName,u.LName FROM Booking b JOIN User u ON b.PatientEmail = u.Email WHERE year(b.DateTime) = ? and month(b.DateTime) = ? and b.doctorEmail = ? ;";

	public static final String ADD_BOOKING = "INSERT INTO Booking (DoctorEmail,PatientEmail,DateTime,Status) VALUES (?,?,?,?);";

	public static final String UPDATE_BOOKING = "UPDATE Booking SET additionalDetails = ?, Status = ? WHERE BookingID = ? ;";

	//Basic User/Login interaction statements
	public static final String ADD_USER = "INSERT INTO User (Email,FName,LName,PhoneNum,Address) VALUES (?,?,?,?,?);";

	public static final String ADD_LOGIN = "INSERT INTO Login (Login,Password, `User Type`,User) VALUES (?,?,?,?);";

	public static final String GET_LOGIN = "SELECT Login,Password,User,`User Type` FROM Login WHERE Login = ?;";

	public static final String GET_USERS_GIVEN_TYPE = "SELECT User.Email, User.FName, User.LName, User.PhoneNum, User.Address, Login.`User Type` FROM User JOIN Login ON User.Email = Login.User AND Login.`User Type` = ?;";

	//Patient interaction statements
	public static final String GET_PATIENT = "SELECT User.email, User.FName, User.LName, User.PhoneNum, User.Address, Assigned.DoctorEmail\n" +
			"FROM User\n" +
			"JOIN Login\n" +
			"ON Login.User = User.email AND User.email = ?\n" +
			"LEFT JOIN Assigned\n" +
			"ON Assigned.PatientEmail = User.email;";

	public static final String GET_PATIENTS_FROM_DOCTOR = "SELECT User.email, User.FName, User.LName, User.PhoneNum, User.Address, Assigned.DoctorEmail\n" +
			"FROM User\n" +
			"JOIN Login\n" +
			"ON Login.User = User.email AND Login.`User Type` = \"PATIENT\"\n" +
			"JOIN Assigned\n" +
			"ON Assigned.PatientEmail = User.email AND Assigned.DoctorEmail = ?;";

	public static final String GET_PATIENTS = "SELECT User.email, User.FName, User.LName, User.PhoneNum, User.Address, Assigned.DoctorEmail\n" +
			"FROM User\n" +
			"JOIN Login\n" +
			"ON Login.User = User.email AND Login.`User Type` = \"PATIENT\"\n" +
			"LEFT JOIN Assigned\n" +
			"ON Assigned.PatientEmail = User.email;";

	//Doctor interaction statements
	public static final String GET_DOCTORS = "SELECT User.email, User.FName, User.LName, User.PhoneNum, User.Address FROM User, Login WHERE User.email = Login.User AND Login.`User Type` = \"DOCTOR\";";

	public static final String GET_DOCTOR = "SELECT User.email, User.FName, User.LName, User.PhoneNum, User.Address FROM User WHERE User.email = ?;";

	//Messaging interaction statements
	public static final String INSERT_MESSAGE = "INSERT INTO Messages (Sender, Recipient, Subject, Message, SentDateTime) VALUES (?,?,?,?,?);";

	public static final String GET_MESSAGES_FOR_USER = "SELECT * FROM Messages WHERE Recipient = ?;";

	public static final String DELETE_MESSAGE = "DELETE FROM Messages WHERE MessageID = ?;";

	//Prescription interaction statements
	public static final String DELETE_PRESCRIPTIONS = "DELETE FROM Prescription WHERE PrescriptionID = ?;";

	public static final String GET_PRESCRIPTION = "SELECT * FROM Prescription WHERE PrescriptionID = ?;";

	public static final String ADD_PRESCRIPTION = "insert into Prescription VALUES (?,?,?,?,?,?,?);"; //(1,'paracetamol', 4, 'Hours', 0, 2, 'Days') example

	//Doctor/Patient assignment statements
	public static final String ADD_ASSIGNMENT1 = "DELETE FROM Assigned WHERE PatientEmail = ?;"; //run this first to remove any potential old assignment

	public static final String ADD_ASSIGNMENT2 = "INSERT INTO Assigned VALUES (?,?);"; // insert the new assignment

	//Logging interaction statements
	public static final String INSERT_LOG = "INSERT INTO Log (Login, Time, Action, Description) VALUES (?,?,?,?);";

}

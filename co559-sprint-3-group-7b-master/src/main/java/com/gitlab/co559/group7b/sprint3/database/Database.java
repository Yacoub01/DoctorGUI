/*
 * Database Class
 *
 * A class that extends a DatabaseConnection object.
 *
 * This object is used specifically to interface between the application and the database.
 * All application specific methods should be in this class.
 *
 * Encryption done using JBCrypt, referencing:
 * https://www.javadoc.io/doc/org.mindrot/jbcrypt/0.4/org/mindrot/jbcrypt/BCrypt.html#hashpw-java.lang.String-java.lang.String-
 *
 * Authors: @jrs63 Joshua Sylvester & @lth20 Luke Hadley
 *
 * Changelog:
 * SPRINT 1
 * lth20 - Created this class as well as the createTable and createLogin method
 * lth20 - Fixed issue in ordering of create table statement execution
 * lth20 - Added Drop Table method
 * jrs63 - Created getBookingsByDate() and addBooking() methods
 * jrs63 - Polished off getBookingsByDate() method
 * lth20 - Errors now use new doError()
 * lth20 - Added comments, fixed an issue in createLogin(), changed float to string on phone number of createUser()
 * jrs63 - changed getBookingByDate method to get only doctors bookings
 * lth20 - Added Doctor Type checking on validateCredentials()
 * SPRINT 2
 * jrs63 - Created the addAssignment() method
 * lth20 - Added execution of CREATE_TABLE_ASSIGNED on createTables()
 * jrs63 - Created the getDoctos() method and getPatients() method
 * lth20 - Copied over getPatients() from doctor-assignment branch
 * lth20 - Added PasswordEncryption field to class
 * lth20 - Copied over getPatients() from doctor-assignment branch
 * lth20 - Added encryption and hash checking using JBCrypt
 * lth20 - Added Prescription table to createTable() method
 * lth20 - Added getPatient() and getDoctor() methods
 * lth20 - Modified getBookingsByDate() to use Timestamp instead of date
 * lth20 - Added execution of CREATE_TABLE_MESSAGES to createTables()
 * lth20 - Added methods to add/get/delete messages
 * lth20 - Added getEmail() method
 * lth20 - Added subject line to message object.
 * lth20 - Added getter method for currently logged in doctor
 * lth20 - Added getPatientsForDoctor() method to get all patients for a specific doctor.
 * lth20 - Fixed issue in getPatientsForDoctor() where SQL statement was returning redundant data.
 * jrs63 - Changed getBooking() to add in the additional details field
 * SPRINT 3
 * jrs63 - Added method to get prescriptions getPrescriptions()
 * jrs63 - Fixed validateCredentials() so that it sets the global variable login and not the local one
 * jrs63 - Added addLog() Method to insert a log message
 * jrs63 - Added addLog() to login and getBookingsByDate()
 * lth20 - Added a getUsers() method to get all users of a given UserType
 * jrs63 - Added method to get prescriptions getPrescriptions()
 * lth20 - Modified getBookingsByDate() so that it also constructs a booking with additional details.
 * jrs63 - Added remove old Prescriptions when adding new ones in method addPrescription()
 * lth20 - Added more logging to methods added in merge from master
 * lth20 - Quality Assurance / Refactored code
 * lth20 - Added boolean value to toggle on/off logging (needed for Unit Testing)
 * lth20 - Switched database login details to use Dragon SQL and not our Azure test server
 */

package com.gitlab.co559.group7b.sprint3.database;

import com.gitlab.co559.group7b.sprint3.objects.*;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database extends DatabaseConnection{


    //The following values are used in connecting to the database
    //Connect to the Dragon Kent SQL Server using the following credentials
    private static final String SQL_HOST = "dragon.kent.ac.uk";
    private static final int SQL_PORT = 3306;
    private static final String SQL_DATABASE_NAME = "ya217";
    private static final String SQL_USERNAME = "ya217";
    private static final String SQL_PASSWORD = "ppet=us";

    /*
    //The following values are used in connecting to the database
    //In order to connect to the database using 'mysql' command line interface use:
    //  mysql --user=jrs63@ngkb --password=HoppityBoppityBoo01 --host=ngkb.mysql.database.azure.com Assessment1
    private static final String SQL_HOST = "ngkb.mysql.database.azure.com";
    private static final int SQL_PORT = 3306;
    private static final String SQL_DATABASE_NAME = "Assessment1";
    private static final String SQL_USERNAME = "jrs63@ngkb";
    private static final String SQL_PASSWORD = "HoppityBoppityBoo01";
    */

    //Values used to store the currently logged in doctor
    private String login;
    private String email;

    private Boolean doLogging;

    /**
     * Constructor for a new database object
     * Used to interact with the database.
     */
    public Database(){
        super(SQL_HOST, SQL_DATABASE_NAME, SQL_USERNAME, SQL_PASSWORD, SQL_PORT);
        setGUIError(true);
        doLogging = true;
        if (!connect()){ //Try to connect
            return;
        }
        createTables(); //Ensure the tables are in the database
    }

    /**
     * Creates the tables for the database.
     * Can be called on startup, statements only create tables if they do not exist.
     */
    public void createTables(){
        executeStatement(DatabaseStatements.CREATE_TABLE_USER);
        executeStatement(DatabaseStatements.CREATE_TABLE_BOOKING);
        executeStatement(DatabaseStatements.CREATE_TABLE_PRESCRIPTION);
        executeStatement(DatabaseStatements.CREATE_TABLE_LOGIN);
        executeStatement(DatabaseStatements.CREATE_TABLE_ASSIGNED);
        executeStatement(DatabaseStatements.CREATE_TABLE_MESSAGES);
        executeStatement(DatabaseStatements.CREATE_TABLE_LOG);
    }

    /**
     * Query the database for the given credentials to verify their authenticity.
     * Will only return true if the login type is correct for a Doctor type.
     * @param login - String login string used to identify the doctor
     * @param password - String password for the user
     * @return true if the credentials given are valid, false if otherwise
     */
    public boolean validateCredentials(String login, String password){
        try {
            PreparedStatement statement = getConnection().prepareStatement(DatabaseStatements.GET_LOGIN);
            statement.setString(1, login);
            ResultSet results = query(statement);
            if (results.next()){ //Get the next item from the results set, this stops errors on empty result set
                if (results.getString("User Type").equals(UserType.DOCTOR.toString())){
                    String storedHash = results.getString(2);
                    if (BCrypt.checkpw(password, storedHash)){
                        this.login = results.getString(1);
                        email = results.getString(3);
                        addLog("Logged In", "");
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (SQLException e){
            doError("An error occurred while attempting to validate credentials.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to add a user and login into the database.
     * This is not directly needed in the program however is needed for testing.
     * @param email - String email of the user
     * @param firstName - String Firstname of the user
     * @param lastName - String Last name of the user
     * @param phoneNumber - String phonenumber, must be no longer than 11 characters
     * @param address - String address of the user
     * @param login - String login string of the user
     * @param password - String password for the user
     * @param userType - String type of the user
     */
    public void createLogin(String email, String firstName, String lastName, String phoneNumber, String address, String login, String password, String userType){
        try {
            //Create and execute the add statement for a user
            PreparedStatement addUserStatement = getConnection().prepareStatement(DatabaseStatements.ADD_USER);
            addUserStatement.setString(1, email);
            addUserStatement.setString(2, firstName);
            addUserStatement.setString(3, lastName);
            addUserStatement.setString(4, phoneNumber);
            addUserStatement.setString(5, address);
            executeStatement(addUserStatement);
            //Create the login for the user just created
            PreparedStatement addLoginStatement = getConnection().prepareStatement(DatabaseStatements.ADD_LOGIN);
            addLoginStatement.setString(1, login);
            addLoginStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            addLoginStatement.setString(3, userType);
            addLoginStatement.setString(4, email);
            executeStatement(addLoginStatement);
        } catch (SQLException e){
            doError("An error occurred while attempting to create a login");
            e.printStackTrace();
        }
    }

    /**
     * Get the bookings of a doctor for the selected month and year
     * @param month - Int month
     * @param year - Int year
     * @return - List of Bookings that were gathered from the database.
     */
    public ArrayList<Booking> getBookingsByDate(int month , int year)
    {
        ArrayList<Booking> returned = new ArrayList<>();
        try {
            //Get the bookings
            PreparedStatement getBookings = getConnection().prepareStatement(DatabaseStatements.GET_BOOKINGS);
            getBookings.setString(1,String.valueOf(year));
            getBookings.setString(2,String.valueOf(month));
            getBookings.setString(3,email);
            ResultSet results = query(getBookings);
            //For each booking returned from the database, create a Booking object and add to a list
            while(results.next())
            {
                BookingStatus bs = BookingStatus.CANCELLED;
                bs = bs.toEnum(results.getString(6)); //Enum valueOf did not work, had to find a way around. We know exactly what can be in the database so is safe to do like this
                Booking b = new Booking(results.getInt(1),results.getString(2),results.getString(3), results.getTimestamp(4), results.getString(5), bs,results.getString(7),results.getString(8));
                b.setPrescriptions(getPrescriptions(b.getBookingID())); //Get the prescriptions of the booking (if any exist)
                returned.add(b);
            }
            addLog("Got Bookings", "Got their own bookings for the month and year "+month+"/"+year);
            return returned;
        }catch (SQLException e){
            doError("An error occurred while attempting to get bookings by date.");
            e.printStackTrace();
        }
        return returned;
    }
    
    /**
     * Get the Prescriptions of a booking
     * @param bookingID - int bookingID
     * @return - List of Prescriptions that were gathered from the database.
     */
    public ArrayList<Prescription> getPrescriptions(int bookingID)
    {
        ArrayList<Prescription> returned = new ArrayList<>();
        try {
            //Get the prescriptions in the database
            PreparedStatement getPrescriptions = getConnection().prepareStatement(DatabaseStatements.GET_PRESCRIPTION);
            getPrescriptions.setInt(1,bookingID);
            
            ResultSet results = query(getPrescriptions);
            //For each prescription, construct a Prescription object
            while(results.next())
            {
                Duration freq = Duration.MINS; //Enum valueOf did not work, had to find a way around. We know exactly what can be in the database so is safe to do like this
                freq = freq.toEnum(results.getString(4));
                Duration time = Duration.MINS;
                time = time.toEnum(results.getString(7));
                returned.add(new Prescription(results.getString(2),results.getInt(3),freq, results.getBoolean(5), results.getInt(6),time));
            }
            addLog("Got Prescriptions", "Got prescriptions for the booking ID " + bookingID);
        }catch (SQLException e){
            doError("An error occurred while attempting to get bookings by date.");
            e.printStackTrace();
        }
        return returned;
    }

    /**
     * Insert a booking into the database.
     * This is not directly needed in the program however is needed for testing.
     * @param doctorID - String email of the doctor
     * @param patientID - String email of the patient
     * @param date - String date for the appointment
     * @param status - The Status of the booking
     */
    public void addBooking(String doctorID, String patientID, String date, String status)
    {
        try {
            //Add a booking into the database
            PreparedStatement addBooking = getConnection().prepareStatement(DatabaseStatements.ADD_BOOKING);
            addBooking.setString(1, doctorID);
            addBooking.setString(2, patientID);
            addBooking.setString(3, date);
            addBooking.setString(4, status);
            executeStatement(addBooking);
        }catch (SQLException e){
            doError("An error occurred while attempting to get add a booking.");
            e.printStackTrace();
        }
    }

    /**
     * Update a booking with the provided additional details
     * @param bookingID - The Int ID of the booking to update
     * @param additionalDetails - The String additional details to add to the booking
     */
    public void addDetailsIntoBooking(int bookingID, String additionalDetails){
        try {
            PreparedStatement updateBooking = getConnection().prepareStatement(DatabaseStatements.UPDATE_BOOKING);
            updateBooking.setString(1, additionalDetails);
            updateBooking.setString(2,"Completed");
            updateBooking.setInt(3, bookingID);
            executeStatement(updateBooking);
            addLog("Added Details To Booking", "Added the following text into a booking: " +additionalDetails + " - the booking was :" + bookingID );
        } catch (SQLException e) {
            doError("An error occurred while attempting to update the additional details of a booking");
            e.printStackTrace();
        }
    }

    /**
     * Get a list of all patients
     * @return - List of Patients
     */
    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> returned = new ArrayList<>();
        try {
            ResultSet results = query(DatabaseStatements.GET_PATIENTS);
            //For all patients returned from the database, constuct a patient object
            while(results.next())
            {
                Patient pat = new Patient(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                if(results.getString(6) != null){
                    pat.setDoctorEmail(results.getString(6));
                }
                returned.add(pat);
            }
            addLog("Got a list of all Patients", "");
        }catch (SQLException e){
            doError("An error occurred while attempting to get Patients.");
            e.printStackTrace();
        }
        return returned;
    }

    /**
     * Get a list of people given a user type
     * @param type - UserType (doctor, patient, admin, receptionist)
     * @return - List of person object
     */
    public ArrayList<Person> getUsers(UserType type) {
        ArrayList<Person> returned = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(DatabaseStatements.GET_USERS_GIVEN_TYPE);
            statement.setString(1, type.toString());
            ResultSet results = query(statement);
            while(results.next())
            {
                returned.add(new Person(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5)));
            }
            addLog("Got a list of all users", "Got a list of all users of type " + type.toString());
        }catch (SQLException e){
            doError("An error occurred while attempting to get users of type '" + type.toString() + "'.");
            e.printStackTrace();
        }
        return returned;
    }

    /**
     * Get a list of patients that is assigned to a specific doctor
     * @return - List of Patients
     */
    public ArrayList<Patient> getPatientsForDoctor(String doctorEmail) {
        ArrayList<Patient> returned = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(DatabaseStatements.GET_PATIENTS_FROM_DOCTOR);
            statement.setString(1, doctorEmail);
            ResultSet results = query(statement);
            while(results.next())
            {
                Patient pat = new Patient(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                pat.setDoctorEmail(results.getString(6));
                returned.add(pat);
            }
            addLog("Got a list of patients that are assigned to themselves", "");
        }catch (SQLException e){
            doError("An error occurred while attempting to get Patients.");
            e.printStackTrace();
        }
        return returned;
    }

    /**
     * Get a patient object from a valid email
     * @param patientEmail - The email of the patient to get
     * @return - Patient object from the database, possibility of being null if email is not valid
     */
    public Patient getPatient(String patientEmail){
        try {
            addLog("Got information on a specific patient", "Patient email :" + patientEmail);
            PreparedStatement getPatientFromEmail = getConnection().prepareStatement(DatabaseStatements.GET_PATIENT);
            getPatientFromEmail.setString(1, patientEmail);
            ResultSet results = query(getPatientFromEmail);
            while(results.next()){
                Patient patient = new Patient(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                if(results.getString(6) != null){
                    patient.setDoctorEmail(results.getString(6));
                }
                return patient;
            }
        } catch (SQLException e){
            doError("An error occurred while attempting to get Patient '" + patientEmail + "'.");
            e.printStackTrace();
        }
        return null; //No patient with the given email was found
    }

    /**
     * Get a doctor object from a valid email
     * @param doctorEmail - The email of the patient to get
     * @return - Doctor object from the database, possibility of being null if email is not valid
     */
    public Doctor getDoctor(String doctorEmail){
        try {
            PreparedStatement getPatientFromEmail = getConnection().prepareStatement(DatabaseStatements.GET_DOCTOR);
            getPatientFromEmail.setString(1, doctorEmail);
            ResultSet results = query(getPatientFromEmail);
            while(results.next()){
                Doctor doctor = new Doctor(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5));
                return doctor;
            }
        } catch (SQLException e){
            doError("An error occurred while attempting to get Patient '" + doctorEmail + "'.");
            e.printStackTrace();
        }
        return null; //No doctor with the given email was found
    }

    /**
     * Add prescriptions to database for a booking
     * Bookings have the possibility of having multiple prescriptions
     * @param prescription array list of bookings
     * @param bookingID bookingID for primary key
     */
    public void addPrescription(ArrayList<Prescription> prescription, int bookingID){
        try {
            PreparedStatement removeOldPrescriptions = getConnection().prepareStatement(DatabaseStatements.DELETE_PRESCRIPTIONS);
            removeOldPrescriptions.setInt(1,bookingID);
            executeStatement(removeOldPrescriptions);
            for (Prescription p : prescription){
                if (p != null) {
                    PreparedStatement newPrescription = getConnection().prepareStatement(DatabaseStatements.ADD_PRESCRIPTION);
                    newPrescription.setInt(1, bookingID);
                    newPrescription.setString(2, p.getNameDescription());
                    newPrescription.setInt(3, p.getFrequencyCount());
                    newPrescription.setString(4, p.getFrequencyDuration().toString());
                    newPrescription.setBoolean(5, p.getEmptyStomach());
                    newPrescription.setInt(6, p.getTimeToTakeCount());
                    newPrescription.setString(7, p.getTimeToTakeDuration().toString());
                    executeStatement(newPrescription);
                    addLog("Added Prescription To A Booking", "Added a prescription of: " + p.getNameDescription() + " - To the bookingID: "+ bookingID);
                }
            }
        } catch (SQLException e){
            doError("An error occurred while attempting to add a Prescription.");
            e.printStackTrace();
        }
}


    /**
     * Insert a Assignment into the database.
     * @param patientEmail - String email of the doctor
     * @param doctorEmail - String email of the patient
     */
    public void addAssignment(String patientEmail, String doctorEmail)
    {
        try {
            addLog("Added Assignment", "Assigned Patient: " + patientEmail + " - to doctor: "+doctorEmail);
            //remove any old assigments
            PreparedStatement addAssignment1 = getConnection().prepareStatement(DatabaseStatements.ADD_ASSIGNMENT1);
            addAssignment1.setString(1, patientEmail);
            executeStatement(addAssignment1);
            //insert new one
            PreparedStatement addAssignment2 = getConnection().prepareStatement(DatabaseStatements.ADD_ASSIGNMENT2);
            addAssignment2.setString(1, doctorEmail);
            addAssignment2.setString(2, patientEmail);
            executeStatement(addAssignment2);
        }catch (SQLException e){
            doError("An error occurred while attempting to get add an Assignment.");
            e.printStackTrace();
        }
    }

    /**
     * Get a list of all doctors
     * @return - List of Doctors
     */
    public ArrayList<Doctor> getDoctors()
    {
        ArrayList<Doctor> returned = new ArrayList<>();
        try {
            ResultSet results = query(DatabaseStatements.GET_DOCTORS);
            while(results.next())
            {
                returned.add(new Doctor(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5)));
            }
            addLog("Got a list of all doctors", "");
        }catch (SQLException e){
            doError("An error occurred while attempting to Doctors.");
            e.printStackTrace();
        }

        return returned;
    }

    /**
     * Get a list of messages for a specified user
     * @param email - The email of the user who's messages are to be returned
     * @return - List of Messages
     */
    public ArrayList<Message> getMessagesForUser(String email){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement getMessages = getConnection().prepareStatement(DatabaseStatements.GET_MESSAGES_FOR_USER);
            getMessages.setString(1, email);
            ResultSet results = query(getMessages);
            //For all messages in the database, construct a message object
            while (results.next()){
                int messageID = results.getInt(1);
                String sender = results.getString(2);
                String recipient = results.getString(3);
                String subject = results.getString(4);
                String message = results.getString(5);
                Message msg = new Message(messageID, sender, recipient, subject, message, results.getTimestamp(6));
                messages.add(msg);
            }
            addLog("Got all of their messages", "");
        } catch (SQLException e) {
            doError("An error occurred while attempting to get messages for user '" + email + "'.");
            e.printStackTrace();
        }
        return messages;
    }

    /**
     * Insert a message into the Messages table of the database.
     * @param sender - The email of the user who sent the message
     * @param recipient - The email of the user to receive the message
     * @param subject - The subject of the message
     * @param message - The message body
     */
    public void sendMessage(String sender, String recipient, String subject, String message){
        try {
            addLog("Sent Message", sender + " sent a message to " + recipient + " saying : " + message);
            PreparedStatement insertMessage = getConnection().prepareStatement(DatabaseStatements.INSERT_MESSAGE);
            insertMessage.setString(1, sender);
            insertMessage.setString(2, recipient);
            insertMessage.setString(3, subject);
            insertMessage.setString(4, message);
            //Get the current time/date using the following regex syntax
            DateTimeFormatter timeStringFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String now = LocalDateTime.now().format(timeStringFormat);
            insertMessage.setString(5, now);
            executeStatement(insertMessage);
        } catch (SQLException e) {
            doError("An error occurred while attempting to insert a message from user '" + sender + "'.");
            e.printStackTrace();
        }
    }

    /**
     * Delete a message in the database given a Message
     * @param message - Message Object to delete
     */
    public void deleteMessage(Message message){
        try {
            PreparedStatement deleteMessage = getConnection().prepareStatement(DatabaseStatements.DELETE_MESSAGE);
            deleteMessage.setInt(1, message.getMessageID());
            executeStatement(deleteMessage);
            addLog("Deleted a message", "Message from " + message.getSender() + " saying " + message.getMessage());
        } catch (SQLException e) {
            doError("An error occurred while attempting to delete message '" + message.getMessageID() + "'.");
            e.printStackTrace();
        }
    }

    /**
     * Insert a message into the Messages table of the database.
     * @param action - The action name of a log
     * @param description - The description of a action
     */
    public void addLog(String action, String description){
        if (doLogging) {
            try {
                PreparedStatement insertMessage = getConnection().prepareStatement(DatabaseStatements.INSERT_LOG);
                insertMessage.setString(1, login);

                DateTimeFormatter timeStringFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String now = LocalDateTime.now().format(timeStringFormat);
                insertMessage.setString(2, now);
                insertMessage.setString(3, action);
                insertMessage.setString(4, description);

                executeStatement(insertMessage);
            } catch (SQLException e) {
                doError("An error occurred while attempting to insert a log.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete all information in the tables.
     */
    public void dropTables(){
        executeStatement(DatabaseStatements.DROP_TABLES);
    }

    /**
     * Returns the email of the doctor currently logged in
     * @return - String email of the logged in doctor.
     */
    public String getEmail(){ return email; }

    /**
     * Set if logging should be added or not on any database method
     * Is on by default but may need to be turned off (for testing at least)
     * @param log - True to allow logging, false if otherwise.
     */
    public void setDoLogging(Boolean log){ doLogging = log; }

}

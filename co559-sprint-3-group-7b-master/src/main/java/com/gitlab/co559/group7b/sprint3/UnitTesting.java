/*
 * Sprint One Test Class
 *
 * This class is used to test important methods for the features of sprint one.
 *
 * Author: @ya217 & @lth20
 */

package com.gitlab.co559.group7b.sprint3;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.database.DatabaseStatements;

import com.gitlab.co559.group7b.sprint3.objects.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTesting {

    private static Database dbConnection;

    public UnitTesting(){
        System.out.println("DEVELOPER NOTES : You must be connected to the Kent VPN (vega.kent.ac.uk) in order to connect to the database and run the tests." +
                "\nDEVELOPER NOTES : Database will warn about not using SSL as dragon doesn't have SSL connection");
        dbConnection = new Database();
        dbConnection.setGUIError(false);
        dbConnection.setDoLogging(false);
    }

    /**
     * Function that is run at the beginning of each test.
     * Deletes the tables and recreates them with no data in.
     */
    @Before
    public void setUp() {
        dbConnection.setGUIError(false);
        dbConnection.setDoLogging(false);
        dbConnection.dropTables();
        dbConnection.createTables();
    }

    /**
     * Method called at the end of all testing.
     * Inserts fresh data into the database so that the program can be run and tested by developers.
     * @throws SQLException Possibility of SQLException throw on an issue while executing a statement.
     */
    @AfterClass
    public static void tearDown(){
        Prescription p1 = new Prescription("Paracetamol", 2, Duration.DAYS, false, 4, Duration.WEEKS);
        Prescription p2 = new Prescription("Insulin Injection", 1, Duration.HOURS, false, 4, Duration.MONTHS);
        Prescription p3 = new Prescription("Paracetamol", 1, Duration.HOURS, false, 1, Duration.WEEKS);
        //Wipe the database, delete all tables and recreate them
        dbConnection.dropTables();
        dbConnection.createTables();
        //Insert in 5 doctors
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");
        //Add in 10 test users to be used as mock patients
        dbConnection.createLogin("kaden.paul@email.com", "Kaden", "Paul", "07904397958", "63 N. Elm Rd. Laurel, MD 20707", "KP875", "patient1", "Patient");
        dbConnection.createLogin("cory.travers@email.com", "Cory", "Travers", "07837905947", "832 West Main Street Westport, CT 06880", "CT941", "patient2", "Patient");
        dbConnection.createLogin("aysha.lugo@email.com", "Aysha", "Lugo", "07045663861", "53 Grand St. Palm Beach Gardens, FL 33410", "AL861", "patient3", "Patient");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.createLogin("fernando.carter@email.com", "Fernando", "Carter", "07880678782", "8181 Brook St. Riverside, NJ 08075", "FC782", "patient5", "Patient");
        dbConnection.createLogin("marcus.cortez@email.com", "Marcus", "Cortez", "07808578655", "13 Ramblewood Dr. Fairfax, VA 22030", "MC655", "patient6", "Patient");
        dbConnection.createLogin("graig.bridges@email.com", "Craig", "Bridges", "07838652789", "921 San Pablo Ave. Portsmouth, VA 23703", "CB789", "patient7", "Patient");
        dbConnection.createLogin("raymond.king@email.com", "Raymond", "King", "07787842207", "535 Elizabeth Avenue South Lyon, MI 48178", "RK207", "patient8", "Patient");
        dbConnection.createLogin("rosa.grant@email.com", "Rosa", "Grant", "07747096285", "3 S. Miller St. Newark, NJ 07103", "RG285", "patient9", "Patient");
        dbConnection.createLogin("traci.garner@email.com", "Traci", "Garner", "07906563098", "9 Clinton Lane Mcallen, TX 78501", "TG098", "patient10", "Patient");
        //Add 3 test Administrators
        dbConnection.createLogin("adele.robles@email.com", "Adele", "Robles", "07787457207", "780 Glenridge St. Natchez, MS 39120", "AR207", "admin1", "Admin");
        dbConnection.createLogin("leon.daly@email.com", "Leon", "Daly", "07747486585", "41 Charles Drive Glastonbury, CT 06033", "LD585", "admin2", "Admin");
        dbConnection.createLogin("avery.perry@email.com", "Avery", "Perry", "07906568398", "7556 Wentworth Dr. Burbank, IL 60459", "AP398", "admin3", "Admin");
        //Add 3 test Receptionists
        dbConnection.createLogin("terence.foreman@email.com", "Terence", "Foreman", "07788142207", "8521 Shadow Brook St. Nashville, TN 37205", "TF207", "recep1", "Receptionist");
        dbConnection.createLogin("payton.robinson@email.com", "Payton", "Robinson", "07747096735", "88 Delaware Ave. Oak Ridge, TN 37830", "PR735", "recep2", "Receptionist");
        dbConnection.createLogin("francis.greenwood@email.com", "Francis", "Greenwood", "07906560588", "478 Arcadia St. Apt 6, Hamilton, OH 45011", "FG588", "recep3", "Receptionist");
        //Login as jrs63 (logging requires a user to be logged in)
        dbConnection.validateCredentials("jrs63", "test4");
        //Add 3 bookings for doctor ya217 on 8-2022
        dbConnection.addBooking("ya217@kent.ac.uk", "cory.travers@email.com", "2022-8-19 17:20", "InProgress");
        dbConnection.addBooking("ya217@kent.ac.uk", "aysha.lugo@email.com", "2022-8-5 13:20", "InProgress");
        dbConnection.addBooking("ya217@kent.ac.uk", "amy.richardson@email.com", "2022-8-15 17:20", "InProgress");
        //Add 4 bookings for doctor jrs63 on 3-2021
        dbConnection.addBooking("jrs63@kent.ac.uk", "kaden.paul@email.com", "2021-03-17 17:20", "Pending");
        dbConnection.addBooking("jrs63@kent.ac.uk", "marcus.cortez@email.com", "2021-03-8 12:30", "Cancelled");
        dbConnection.addBooking("jrs63@kent.ac.uk", "rosa.grant@email.com", "2021-03-2 10:15", "Pending");
        dbConnection.addBooking("jrs63@kent.ac.uk", "fernando.carter@email.com", "2021-03-5 11:45", "Completed");
        dbConnection.addDetailsIntoBooking(7, "Came in complaining about back pain.");
        ArrayList<Prescription> ps1 = new ArrayList<>();
        ps1.add(p1);
        dbConnection.addPrescription(ps1 , 7);
        //Add 3 Assignments to ya217
        dbConnection.addAssignment("kaden.paul@email.com", "ya217@kent.ac.uk");
        dbConnection.addAssignment("fernando.carter@email.com", "ya217@kent.ac.uk");
        dbConnection.addAssignment( "rosa.grant@email.com", "ya217@kent.ac.uk");
        //Add 4 bookings for doctor jrs63 on 4-2021
        dbConnection.addBooking("jrs63@kent.ac.uk", "traci.garner@email.com", "2021-04-8 15:20", "Completed");
        dbConnection.addDetailsIntoBooking(8, "Came in complaining about chest pains, has been referred to the hospital for an xray and echocardiogram");
        ArrayList<Prescription> ps2 = new ArrayList<>();
        ps2.add(p2);
        dbConnection.addPrescription(ps2, 8);
        dbConnection.addBooking("jrs63@kent.ac.uk", "cory.travers@email.com", "2021-04-6 09:45", "Completed");
        ArrayList<Prescription> ps3 = new ArrayList<>();
        ps3.add(p3);
        dbConnection.addPrescription(ps3, 9);
        dbConnection.addDetailsIntoBooking(9, "Came in for checkup to see how patients scar/stitches were holding up.");
        //Add 7 Messages for doctor ya217
        dbConnection.sendMessage("tp379@kent.ac.uk", "ya217@kent.ac.uk", "Meeting friday", "Meeting in room 9 on Friday @ 10:30AM");
        dbConnection.sendMessage("kaden.paul@email.com", "ya217@kent.ac.uk", "Appointment Rescheduled", "Dear Doctor, I need to reschedule the meeting we have on Wednesday, How can I move this?");
        dbConnection.sendMessage("ghwv2@kent.ac.uk", "ya217@kent.ac.uk", "Vaccine Stock Update", "Restocking of COVID vaccines approved, stock will arrive on Monday @ 7PM. Please can all doctors be in to help order the stock.");
        dbConnection.sendMessage("tp379@kent.ac.uk", "ya217@kent.ac.uk", "Emergency Patient Today @ 10PM", "A Patient has been moved to see you at 10PM today, Name of Fernando Carter, they are on the priority list.");
        dbConnection.sendMessage("ghwv2@kent.ac.uk", "ya217@kent.ac.uk", "Vaccine Stock Update", "Analysis and blood test results for Mr Roy.");
        dbConnection.sendMessage("jrs63@kent.ac.uk", "ya217@kent.ac.uk", "Emergency Patient Today @ 10PM", "Booking cancelled: Mrs Sarah Parker.");
        dbConnection.sendMessage("tp379@kent.ac.uk", "ya217@kent.ac.uk", "Vaccine Stock Update", "Transplant donor found for Mr John Doe.");
    }

    /**
     * Test the validation of logins.
     * Test the password and login username that should return true.
     */
    @Test
    public void testValidationTrue(){
        //Insert user and login information into the database.
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");
        //Run tests
        assertTrue(dbConnection.validateCredentials("lth20", "test"));
        assertTrue(dbConnection.validateCredentials("ghwv2", "test3"));
        assertTrue(dbConnection.validateCredentials("jrs63", "test4"));
        assertTrue(dbConnection.validateCredentials("tp379", "test5"));
    }

    /**
     * Test the validation of logins.
     * Test the password and login username that should return false.
     */
    @Test
    public void testValidationFalse1(){
        //Insert user and login information into the database.
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");
        //Run tests
        assertFalse(dbConnection.validateCredentials("Tejaswini", "test2"));
        assertFalse(dbConnection.validateCredentials("Karadsheh", "test6"));
        assertFalse(dbConnection.validateCredentials("LUKE", "wwwasdd"));
    }

    /**
     * Test the validation of logins.
     * validateCredentials() should return false as user is not of Doctor type.
     */
    @Test
    public void testValidationFalse2(){
        //Insert user and login information into the database.
        dbConnection.createLogin("Felicity.Leon@email.co.uk", "Felicity", "Leon", "07999999333" ,"ADDRESS", "felicity", "fLEON", "Admin");
        //Run test
        assertFalse(dbConnection.validateCredentials("felicity", "fLEON"));
    }

    /**
     * Tests the number of tables in the database
     * Tests if the correct tables are included in the database
     * @throws SQLException Possibility of SQLException throw on an issue while executing a statement.
     */
    @Test
    public void testCreateTables() throws SQLException{
        //Run query to return all names of tables
        ResultSet query = dbConnection.query("SHOW TABLES;");
        //Add all names that came back with the query to a list that can be checked.
        List<String> tableNames = new ArrayList<>();
        while (query.next()){
            tableNames.add(query.getString(1));
        }
        //Ensure that all table names that were expected are in the DB.
        assertTrue(tableNames.contains("Booking")); // NOTE: Table names need to be upper case for dragon SQL server
        assertTrue(tableNames.contains("Login"));
        assertTrue(tableNames.contains("User"));
        assertTrue(tableNames.contains("Assigned"));
        assertTrue(tableNames.contains("Messages"));
        assertTrue(tableNames.contains("Prescription"));
        assertTrue(tableNames.contains("Log"));
        //Ensure that the number of tables in the DB is the number expected.
        assertEquals(7 ,tableNames.size());
    }

    /**
     * Test bookings for a doctor
     * Inserts user and booking data into a database,
     *  adds a single booking for doctor 'lth20' on 10-2021
     * @throws SQLException Possibility of SQLException throw on an issue while executing a statement.
     */
    @Test
    public void testGetBookingsDate1() throws SQLException {
        // Setting up the doctors.
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        //Setting up test cases of patient details.
        PreparedStatement addUserStatement = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement.setString(1, "kaden.paul@email.com");
        addUserStatement.setString(2, "Kaden");
        addUserStatement.setString(3, "Paul");
        addUserStatement.setString(4, "07894567895");
        addUserStatement.setString(5, "111 Road Name Kaden Paul Addresses");
        dbConnection.executeStatement(addUserStatement);
        PreparedStatement addUserStatement1 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement1.setString(1, "cory.travers@email.com");
        addUserStatement1.setString(2, "Cory");
        addUserStatement1.setString(3, "Travers");
        addUserStatement1.setString(4, "07845658745");
        addUserStatement1.setString(5, "222 Road Man Cory Travers Addresses");
        dbConnection.executeStatement(addUserStatement1);
        PreparedStatement addUserStatement2 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement2.setString(1, "aysha.lugo@email.com");
        addUserStatement2.setString(2, "Aysha");
        addUserStatement2.setString(3, "Lugo");
        addUserStatement2.setString(4, "07845967512");
        addUserStatement2.setString(5, "333 Road Name Aysha Lugo Addresses");
        dbConnection.executeStatement(addUserStatement2);
        PreparedStatement addUserStatement3 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement3.setString(1, "amy.richardson@email.com");
        addUserStatement3.setString(2, "Amy");
        addUserStatement3.setString(3, "Richardson");
        addUserStatement3.setString(4, "07845384574");
        addUserStatement3.setString(5, "444 Road Name Amy Richardson Addresses");
        dbConnection.executeStatement(addUserStatement3);
        //Add booking to doctor
        dbConnection.addBooking("lth20@kent.ac.uk", "kaden.paul@email.com", "2021-10-17 17:20", "Pending");
        //Login as that doctor
        dbConnection.validateCredentials("lth20", "test");
        //Run test
        assertEquals(1,  dbConnection.getBookingsByDate(10,2021).size());
    }

    /**
     * Test bookings for a doctor
     * Inserts user and booking data into a database,
     *  adds three bookings for doctor 'lth20' on 8-2021
     * @throws SQLException Possibility of SQLException throw on an issue while executing a statement.
     */
    @Test
    public void testGetBookingsDate2() throws SQLException {
        // Setting up the doctors.
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        //Setting up test cases of patient details.
        PreparedStatement addUserStatement = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement.setString(1, "kaden.paul@email.com");
        addUserStatement.setString(2, "Kaden");
        addUserStatement.setString(3, "Paul");
        addUserStatement.setString(4, "07894567895");
        addUserStatement.setString(5, "111 Road Name Kaden Paul Addresses");
        dbConnection.executeStatement(addUserStatement);
        PreparedStatement addUserStatement1 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement1.setString(1, "cory.travers@email.com");
        addUserStatement1.setString(2, "Cory");
        addUserStatement1.setString(3, "Travers");
        addUserStatement1.setString(4, "07845658745");
        addUserStatement1.setString(5, "222 Road Man Cory Travers Addresses");
        dbConnection.executeStatement(addUserStatement1);
        PreparedStatement addUserStatement2 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement2.setString(1, "aysha.lugo@email.com");
        addUserStatement2.setString(2, "Aysha");
        addUserStatement2.setString(3, "Lugo");
        addUserStatement2.setString(4, "07845967512");
        addUserStatement2.setString(5, "333 Road Name Aysha Lugo Addresses");
        dbConnection.executeStatement(addUserStatement2);
        PreparedStatement addUserStatement3 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement3.setString(1, "amy.richardson@email.com");
        addUserStatement3.setString(2, "Amy");
        addUserStatement3.setString(3, "Richardson");
        addUserStatement3.setString(4, "07845384574");
        addUserStatement3.setString(5, "444 Road Name Amy Richardson Addresses");
        dbConnection.executeStatement(addUserStatement3);
        //Adds the bookings information
        dbConnection.addBooking("lth20@kent.ac.uk", "cory.travers@email.com", "2021-8-5 17:20", "InProgress");
        dbConnection.addBooking("lth20@kent.ac.uk", "aysha.lugo@email.com", "2021-8-5 17:20", "InProgress");
        dbConnection.addBooking("lth20@kent.ac.uk", "amy.richardson@email.com", "2021-8-12 17:20", "InProgress");
        //Logs in as that doctor
        dbConnection.validateCredentials("lth20", "test");
        //Run test
        assertEquals(3, dbConnection.getBookingsByDate(8, 2021).size());
    }

    /**
     * Test bookings for a doctor
     * Inserts user and booking data into a database,
     * adds three bookings for doctor 'ya217' on 8-2022
     * @throws SQLException Possibility of SQLException throw on an issue while executing a statement.
     */
    @Test
    public void testGetBookingsDate3() throws SQLException {
        // Setting up the doctors.
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        //Setting up test cases of patient details.
        PreparedStatement addUserStatement = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement.setString(1, "kaden.paul@email.com");
        addUserStatement.setString(2, "Kaden");
        addUserStatement.setString(3, "Paul");
        addUserStatement.setString(4, "07894567895");
        addUserStatement.setString(5, "111 Road Name Kaden Paul Addresses");
        dbConnection.executeStatement(addUserStatement);
        PreparedStatement addUserStatement1 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement1.setString(1, "cory.travers@email.com");
        addUserStatement1.setString(2, "Cory");
        addUserStatement1.setString(3, "Travers");
        addUserStatement1.setString(4, "07845658745");
        addUserStatement1.setString(5, "222 Road Man Cory Travers Addresses");
        dbConnection.executeStatement(addUserStatement1);
        PreparedStatement addUserStatement2 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement2.setString(1, "aysha.lugo@email.com");
        addUserStatement2.setString(2, "Aysha");
        addUserStatement2.setString(3, "Lugo");
        addUserStatement2.setString(4, "07845967512");
        addUserStatement2.setString(5, "333 Road Name Aysha Lugo Addresses");
        dbConnection.executeStatement(addUserStatement2);
        PreparedStatement addUserStatement3 = dbConnection.getConnection().prepareStatement(DatabaseStatements.ADD_USER);
        addUserStatement3.setString(1, "amy.richardson@email.com");
        addUserStatement3.setString(2, "Amy");
        addUserStatement3.setString(3, "Richardson");
        addUserStatement3.setString(4, "07845384574");
        addUserStatement3.setString(5, "444 Road Name Amy Richardson Addresses");
        dbConnection.executeStatement(addUserStatement3);
        //Logs in as that doctor
        dbConnection.validateCredentials("ya217", "test2");
        dbConnection.addBooking("ya217@kent.ac.uk", "cory.travers@email.com", "2022-8-5 17:20", "InProgress");
        dbConnection.addBooking("ya217@kent.ac.uk", "aysha.lugo@email.com", "2022-8-5 13:20", "InProgress");
        dbConnection.addBooking("ya217@kent.ac.uk", "amy.richardson@email.com", "2022-8-15 17:20", "InProgress");
        //Run test
        assertEquals(3, dbConnection.getBookingsByDate(8, 2022).size());
    }

    /**
     * Tests the get patients method.
     * Checks for the size of all the patients in the database.
     */
    @Test
    public void testGetPatient1(){
        dbConnection.createLogin("kaden.paul@email.com", "Kaden", "Paul", "07904397958", "63 N. Elm Rd. Laurel, MD 20707", "KP875", "patient1", "Patient");
        dbConnection.createLogin("cory.travers@email.com", "Cory", "Travers", "07837905947", "832 West Main Street Westport, CT 06880", "CT941", "patient2", "Patient");
        dbConnection.createLogin("aysha.lugo@email.com", "Aysha", "Lugo", "07045663861", "53 Grand St. Palm Beach Gardens, FL 33410", "AL861", "patient3", "Patient");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.createLogin("fernando.carter@email.com", "Fernando", "Carter", "07880678782", "8181 Brook St. Riverside, NJ 08075", "FC782", "patient5", "Patient");

        ArrayList<Patient> x =  dbConnection.getPatients();

        assertEquals(5, x.size());

    }

    /**
     * Test the get patients method and the get emails.
     * checks if the emails are correct and are in the database.
     */
    @Test
    public void testGetPatient2(){
        dbConnection.createLogin("kaden.paul@email.com", "Kaden", "Paul", "07904397958", "63 N. Elm Rd. Laurel, MD 20707", "KP875", "patient1", "Patient");
        dbConnection.createLogin("cory.travers@email.com", "Cory", "Travers", "07837905947", "832 West Main Street Westport, CT 06880", "CT941", "patient2", "Patient");
        dbConnection.createLogin("aysha.lugo@email.com", "Aysha", "Lugo", "07045663861", "53 Grand St. Palm Beach Gardens, FL 33410", "AL861", "patient3", "Patient");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.createLogin("fernando.carter@email.com", "Fernando", "Carter", "07880678782", "8181 Brook St. Riverside, NJ 08075", "FC782", "patient5", "Patient");

        ArrayList<Patient> x =  dbConnection.getPatients();
        ArrayList<String> Emails = new ArrayList<>();

        for(Patient pat : x){
            Emails.add(pat.getEmail()); }

        assertTrue(Emails.contains("kaden.paul@email.com"));
        assertTrue(Emails.contains("cory.travers@email.com"));
        assertTrue(Emails.contains("amy.richardson@email.com"));
        assertTrue(Emails.contains("fernando.carter@email.com"));
        assertTrue(Emails.contains("aysha.lugo@email.com"));
        assertEquals(5, Emails.size());

    }

    /**
     * Tests the Assign Doctor method.
     * Adds a list of patients and doctors
     * Test if the patient is moved to the correct doctor.
     */
    @Test
    public void testAssignDoctor1(){
        //doctor
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        //patients
        dbConnection.createLogin("kaden.paul@email.com", "Kaden", "Paul", "07904397958", "63 N. Elm Rd. Laurel, MD 20707", "KP875", "patient1", "Patient");
        dbConnection.createLogin("cory.travers@email.com", "Cory", "Travers", "07837905947", "832 West Main Street Westport, CT 06880", "CT941", "patient2", "Patient");
        dbConnection.createLogin("aysha.lugo@email.com", "Aysha", "Lugo", "07045663861", "53 Grand St. Palm Beach Gardens, FL 33410", "AL861", "patient3", "Patient");

        dbConnection.addAssignment("kaden.paul@email.com", "lth20@kent.ac.uk");
        ArrayList<Patient> p = dbConnection.getPatientsForDoctor("lth20@kent.ac.uk");

        assertEquals(1, p.size());
    }

    /**
     * Tests the Assign Doctor method.
     * Adds a list of patients and doctors
     * Test if the patient is moved to the correct doctor.
     */
    @Test
    public void testAssignDoctor2(){
        //doctor
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        //patients
        dbConnection.createLogin("raymond.king@email.com", "Raymond", "King", "07787842207", "535 Elizabeth Avenue South Lyon, MI 48178", "RK207", "patient8", "Patient");
        dbConnection.createLogin("rosa.grant@email.com", "Rosa", "Grant", "07747096285", "3 S. Miller St. Newark, NJ 07103", "RG285", "patient9", "Patient");
        dbConnection.createLogin("traci.garner@email.com", "Traci", "Garner", "07906563098", "9 Clinton Lane Mcallen, TX 78501", "TG098", "patient10", "Patient");

        dbConnection.addAssignment("rosa.grant@email.com", "ya217@kent.ac.uk");
        dbConnection.addAssignment("raymond.king@email.com", "ya217@kent.ac.uk");
        dbConnection.addAssignment("traci.garner@email.com", "ya217@kent.ac.uk");
        ArrayList<Patient> p = dbConnection.getPatientsForDoctor("ya217@kent.ac.uk");
        ArrayList<String> Emails = new ArrayList<>();

        for (Patient pet : p){
            Emails.add(pet.getEmail());
        }
        assertTrue(Emails.contains("rosa.grant@email.com"));
        assertTrue(Emails.contains("raymond.king@email.com"));
        assertTrue(Emails.contains("traci.garner@email.com"));
        assertEquals(3, Emails.size());


    }

    /**
     * Tests the Assign Doctor method.
     * Adds a list of patients and doctors.
     * Test if the patient is moved to the correct doctor.
     */
    @Test
    public void testAssignDoctor3(){
        //doctor
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");

        //Patients
        dbConnection.createLogin("kaden.paul@email.com", "Kaden", "Paul", "07904397958", "63 N. Elm Rd. Laurel, MD 20707", "KP875", "patient1", "Patient");
        dbConnection.createLogin("cory.travers@email.com", "Cory", "Travers", "07837905947", "832 West Main Street Westport, CT 06880", "CT941", "patient2", "Patient");
        dbConnection.createLogin("aysha.lugo@email.com", "Aysha", "Lugo", "07045663861", "53 Grand St. Palm Beach Gardens, FL 33410", "AL861", "patient3", "Patient");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.createLogin("fernando.carter@email.com", "Fernando", "Carter", "07880678782", "8181 Brook St. Riverside, NJ 08075", "FC782", "patient5", "Patient");

        dbConnection.addAssignment("aysha.lugo@email.com", "ghwv2@kent.ac.uk");
        dbConnection.addAssignment("cory.travers@email.com", "ghwv2@kent.ac.uk");
        dbConnection.addAssignment("amy.richardson@email.com", "ghwv2@kent.ac.uk");
        dbConnection.addAssignment("fernando.carter@email.com", "ghwv2@kent.ac.uk");
        dbConnection.addAssignment("kaden.paul@email.com", "ghwv2@kent.ac.uk");

        ArrayList<Patient> p = dbConnection.getPatientsForDoctor("ghwv2@kent.ac.uk");
        ArrayList<String> Emails = new ArrayList<>();

        for (Patient pet : p){
            Emails.add(pet.getEmail());
        }
        assertTrue(Emails.contains("aysha.lugo@email.com"));
        assertTrue(Emails.contains("cory.travers@email.com"));
        assertTrue(Emails.contains("amy.richardson@email.com"));
        assertTrue(Emails.contains("fernando.carter@email.com"));
        assertTrue(Emails.contains("kaden.paul@email.com"));
        assertEquals(5, Emails.size());

    }

    /**
     * Test the get Doctors method
     * Adds a list of doctors
     * Checks if the correct size doctors match the size using getDoctors();
     */
    @Test
    public void getDoctors1(){
        //doctors
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");

        ArrayList<Doctor> x =  dbConnection.getDoctors();

        assertEquals(5, x.size());
    }

    /**
     * Test the get Doctors method
     * Adds a list of doctors
     * Checks if the correct size doctors match the size using getDoctors();
     */
    @Test
    public void getDoctors2(){
        //doctors
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");

        //test
        ArrayList<Doctor> x =  dbConnection.getDoctors();
        ArrayList<String> Emails = new ArrayList<>();

        for(Doctor doc : x){
            Emails.add(doc.getEmail()); }

        assertTrue(Emails.contains("lth20@kent.ac.uk"));
        assertTrue(Emails.contains("ya217@kent.ac.uk"));
        assertTrue(Emails.contains("ghwv2@kent.ac.uk"));
        assertTrue(Emails.contains("jrs63@kent.ac.uk"));
        assertTrue(Emails.contains("tp379@kent.ac.uk"));
        assertEquals(5, Emails.size());

    }

    /**
     * Test the Send Messages method
     * Creates one doctor login and one patient login
     * creates messages between patient and doctor then checks the correct size of messages sent.
     */
    @Test
    public void testSendMessages(){
        //doctor and patient
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");

        //doctor and patient
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "you have corona", "get help");
        dbConnection.sendMessage("amy.richardson@email.com","lth20@kent.ac.uk", "reply", "thank you");
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "re-reply", "welcome");

        //tests
        assertEquals(2 ,dbConnection.getMessagesForUser("amy.richardson@email.com").size());
        assertEquals(1 ,dbConnection.getMessagesForUser("lth20@kent.ac.uk").size());


    }

    /**
     * Test the Delete Messages method
     * Creates one doctor login and one patient login
     * creates messages between patient and doctor then checks the correct size of messages sent
     * after being deleted.
     */
    @Test
    public void testDeleteMessage(){
        //doctor and patient
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");

        //doctor and patient
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "you have corona", "get help");
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "re-reply", "welcome");

        //test
        ArrayList<Message> x =  dbConnection.getMessagesForUser("amy.richardson@email.com");

        for(Message t : x){
            dbConnection.deleteMessage(t);
        }

        assertEquals(0, dbConnection.getMessagesForUser("amy.richardson@email.com").size());
    }

    /**
     * Test the getMessagesUser method
     * Creates one doctor login and one patient login
     * creates messages between patient and doctor then checks the correct size of messages sent
     * for the specific user.
     */
    @Test
    public void testGetMessageUser(){
        //Adding a doctor and a patient object
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        //Send messages between them
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "you have corona", "get help");
        dbConnection.sendMessage("amy.richardson@email.com","lth20@kent.ac.uk", "reply", "thank you");
        dbConnection.sendMessage("lth20@kent.ac.uk","amy.richardson@email.com", "re-reply", "welcome");
        //Get those messages
        ArrayList<Message> x =  dbConnection.getMessagesForUser("amy.richardson@email.com");
        ArrayList<Message> y =  dbConnection.getMessagesForUser("lth20@kent.ac.uk");
        //Check the size of the lists of messages for the size expected
        assertEquals(2, x.size());
        assertEquals(1, y.size());
    }

    /**
     * Test the get patient method
     * creates patient login and patient object
     * assert equals on the details for patient.
     */
    @Test
    public void testGetPatient(){
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");

        Patient p = dbConnection.getPatient("amy.richardson@email.com");

        assertEquals(p.getEmail(), "amy.richardson@email.com");
        assertEquals(p.getAddress(), "8093 South Hillcrest Ave. Georgetown, SC 29440");
        assertEquals(p.getFirstName(), "Amy");
        assertEquals(p.getLastName(), "Richardson");
        assertEquals(p.getPhoneNumber(), "07765558644");

        assertFalse(p.hasDoctor());
    }

    /**
     * Test the get doctor method
     * creates doctor login and doctor object
     * assert equals on the details for doctor.
     */
    @Test
    public void testGetDoctors(){
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");

        Doctor p = dbConnection.getDoctor("lth20@kent.ac.uk");

        assertEquals(p.getEmail(), "lth20@kent.ac.uk");
        assertEquals(p.getAddress(), "ADDRESS");
        assertEquals(p.getFirstName(), "Luke");
        assertEquals(p.getLastName(), "Hadley");
        assertEquals(p.getPhoneNumber(), "07999999999");


    }


    /**
     * Tests getPrescriptions and addPrescriptions.
     * Create two logins for doctor, patient.
     * Adds a booking for patient and doctor.
     * Adds Prescriptions to the booking and tests the details of prescription.
     */
    @Test
    public void testPrescriptions(){
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.addBooking("ya217@kent.ac.uk", "amy.richardson@email.com", "2022-8-5 13:20", "InProgress");


        dbConnection.validateCredentials("ya217","test2");
        ArrayList<Booking> book = dbConnection.getBookingsByDate(8, 2022);
        Prescription p = new Prescription("Paracetomol", 1, Duration.HOURS, true, 3, Duration.MONTHS);
        ArrayList<Prescription> pre = new ArrayList<>();
        pre.add(p);
        dbConnection.addPrescription(pre, book.get(0).getBookingID());

        assertEquals(1, dbConnection.getPrescriptions(book.get(0).getBookingID()).size());
        assertTrue(dbConnection.getPrescriptions(book.get(0).getBookingID()).get(0).getEmptyStomach());
        assertEquals(dbConnection.getPrescriptions(book.get(0).getBookingID()).get(0).getNameDescription(),"Paracetomol" );

    }

    /**
     * Tests getPrescriptions and addPrescriptions.
     * Create two logins for doctor, patient.
     * Adds a booking for patient and doctor.
     * Adds additional details to the booking and tests the details of the additional details added.
     */
    @Test
    public void testAddDetailsIntoBook(){
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.addBooking("ya217@kent.ac.uk", "amy.richardson@email.com", "2022-8-5 13:20", "InProgress");

        dbConnection.validateCredentials("ya217","test2");
        ArrayList<Booking> book = dbConnection.getBookingsByDate(8, 2022);
        dbConnection.addDetailsIntoBooking(1, "Fever");
        ArrayList<Booking> b = dbConnection.getBookingsByDate(8, 2022);
        assertEquals(b.get(0).getAdditionalDetails(), "Fever");
    }

    /**
     * Test the getUsers() method in the database object.
     * Method should return all users of that given Enum type that is in the database.
     */
    @Test
    public void testGetUsersDoctor(){
        dbConnection.createLogin("lth20@kent.ac.uk", "Luke", "Hadley", "07999999999" ,"ADDRESS", "lth20", "test", "Doctor");
        dbConnection.createLogin("ya217@kent.ac.uk", "Yacoub", "Karadsheh", "07999999998", "ADDRESS", "ya217", "test2", "Doctor");
        dbConnection.createLogin("ghwv2@kent.ac.uk", "Helena", "Vegheim", "07999999997", "ADDRESS", "ghwv2", "test3", "Doctor");
        dbConnection.createLogin("jrs63@kent.ac.uk", "Joshua", "Sylvester", "07999999996", "ADDRESS", "jrs63", "test4", "Doctor");
        dbConnection.createLogin("tp379@kent.ac.uk", "Tejaswini", "Parmessur", "07999999995", "ADDRESS", "tp379", "test5", "Doctor");
        ArrayList<Person> people = dbConnection.getUsers(UserType.DOCTOR);

        assertEquals(5, people.size());
    }

    /**
     * Test the getUsers() method in the database object.
     * Method should return all users of that given Enum type that is in the database.
     */
    @Test
    public void testGetUsersPatients(){
        dbConnection.createLogin("amy.richardson@email.com", "Amy", "Richardson", "07765558644", "8093 South Hillcrest Ave. Georgetown, SC 29440", "AR644", "patient4", "Patient");
        dbConnection.createLogin("fernando.carter@email.com", "Fernando", "Carter", "07880678782", "8181 Brook St. Riverside, NJ 08075", "FC782", "patient5", "Patient");
        dbConnection.createLogin("marcus.cortez@email.com", "Marcus", "Cortez", "07808578655", "13 Ramblewood Dr. Fairfax, VA 22030", "MC655", "patient6", "Patient");
        dbConnection.createLogin("graig.bridges@email.com", "Craig", "Bridges", "07838652789", "921 San Pablo Ave. Portsmouth, VA 23703", "CB789", "patient7", "Patient");
        dbConnection.createLogin("raymond.king@email.com", "Raymond", "King", "07787842207", "535 Elizabeth Avenue South Lyon, MI 48178", "RK207", "patient8", "Patient");
        dbConnection.createLogin("rosa.grant@email.com", "Rosa", "Grant", "07747096285", "3 S. Miller St. Newark, NJ 07103", "RG285", "patient9", "Patient");
        dbConnection.createLogin("traci.garner@email.com", "Traci", "Garner", "07906563098", "9 Clinton Lane Mcallen, TX 78501", "TG098", "patient10", "Patient");
        ArrayList<Person> people = dbConnection.getUsers(UserType.PATIENT);
        assertEquals(7, people.size());
    }

    /**
     * Test the getUsers() method in the database object.
     * Method should return all users of that given Enum type that is in the database.
     */
    @Test
    public void testGetUsersAdmins(){
        dbConnection.createLogin("adele.robles@email.com", "Adele", "Robles", "07787457207", "780 Glenridge St. Natchez, MS 39120", "AR207", "admin1", "Admin");
        dbConnection.createLogin("leon.daly@email.com", "Leon", "Daly", "07747486585", "41 Charles Drive Glastonbury, CT 06033", "LD585", "admin2", "Admin");
        dbConnection.createLogin("avery.perry@email.com", "Avery", "Perry", "07906568398", "7556 Wentworth Dr. Burbank, IL 60459", "AP398", "admin3", "Admin");
        ArrayList<Person> people = dbConnection.getUsers(UserType.ADMIN);
        assertEquals(3, people.size());
    }

    /**
     * Test the getUsers() method in the database object.
     * Method should return all users of that given Enum type that is in the database.
     */
    @Test
    public void testGetUsersReceptionists(){
        dbConnection.createLogin("terence.foreman@email.com", "Terence", "Foreman", "07788142207", "8521 Shadow Brook St. Nashville, TN 37205", "TF207", "recep1", "Receptionist");
        dbConnection.createLogin("payton.robinson@email.com", "Payton", "Robinson", "07747096735", "88 Delaware Ave. Oak Ridge, TN 37830", "PR735", "recep2", "Receptionist");
        dbConnection.createLogin("francis.greenwood@email.com", "Francis", "Greenwood", "07906560588", "478 Arcadia St. Apt 6, Hamilton, OH 45011", "FG588", "recep3", "Receptionist");
        ArrayList<Person> people = dbConnection.getUsers(UserType.RECEPTIONIST);
        assertEquals(3, people.size());
    }

}
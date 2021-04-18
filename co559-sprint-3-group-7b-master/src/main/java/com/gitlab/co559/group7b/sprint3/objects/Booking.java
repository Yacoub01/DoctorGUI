/*
    Booking Object Class

    Holds all information that could be taken from the database 'Booking' table.

    Author: @lth20 Luke Hadley & @jrs63 Joshua Sylvester
 */

package com.gitlab.co559.group7b.sprint3.objects;


import com.gitlab.co559.group7b.sprint3.database.Database;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Booking {

    //All fields used to hold information about a booking
    //Exactly like the fields in the Booking database table
    private int bookingID;
    private String doctorEmail;
    private String patientEmail;
    private LocalDateTime dateTime;
    private String additionalDetails;
    private BookingStatus status;
    private String patientFirstName;
    private String patientSecondName;

    //Booking can have multiple prescriptions, the array list holds them
    private ArrayList<Prescription> prescriptions;

    //DateTimeFormatters are used to hold formats so that easy strings can be called to be viewed to users
    private DateTimeFormatter timeFormat;
    private DateTimeFormatter dateFormat;

    /**
     * Constructor of a booking object
     * Information from the database can go into the fields of the object
     * @param bookingID
     * @param doctorEmail
     * @param patientEmail
     * @param dateTime
     * @param additionalDetails
     * @param status
     * @param patientFirstName
     * @param patientSecondName
     */
    public Booking(int bookingID, String doctorEmail, String patientEmail, Timestamp dateTime, String additionalDetails, BookingStatus status, String patientFirstName, String patientSecondName){
        this.bookingID = bookingID;
        this.doctorEmail = doctorEmail;
        this.patientEmail = patientEmail;
        this.dateTime = dateTime.toLocalDateTime();
        this.additionalDetails = additionalDetails;
        this.status = status;
        this.patientFirstName = patientFirstName;
        this.patientSecondName = patientSecondName;
        timeFormat = DateTimeFormatter.ofPattern("HH:mm"); //Setting up the DateTimeFormats
        dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    }

    /**
     * Set the prescriptions of a booking
     * Bookings can have 0-9 prescriptions
     * @param prescriptions
     */
    public void setPrescriptions(ArrayList<Prescription> prescriptions){ this.prescriptions = prescriptions; }

    /**
     * Get the additional details of a booking
     * @return String - Additional details of a booking object
     */
    public String getAdditionalDetails() { return additionalDetails; }

    /**
     * Get the prescriptions assigned to this booking
     * @return ArrayList<Prescription> - the prescriptions the booking holds
     */
    public ArrayList<Prescription> getPrescriptions(){ return prescriptions; }

    /**
     * This is more to do with SQL ID of the booking
     * Not actually needed information to display
     * @return the table ID for the booking
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * Get the doctors email
     * @return the doctors email used to identify the doctor for that booking
     */
    public String getDoctorEmail() {
        return doctorEmail;
    }

    /**
     * Get patient email
     * @return the patients email used to identify the patient for that booking
     */
    public String getPatientEmail() {
        return patientEmail;
    }

    /**
     * Get Date
     * @return return the LocalDate object holding the date of the appointment
     */
    public LocalDate getDate() { return dateTime.toLocalDate(); }

    /**
     * Returns a formatted string for the date variable in the format 'dd MMMM yyyy'
     * @return - String version of the date of the booking
     */
    public String getFormattedDate() { return dateTime.format(dateFormat); }

    /**
     * Returns a formatted string for the date variable in the format 'HH:mm'
     * @return - String version of the time of the booking
     */
    public String getFormattedTime() { return dateTime.format(timeFormat); }

    /**
     * Get status
     * @return status of the appointment
     */
    public BookingStatus getStatus() { return status; }

    /**
     * Set status
     * @param status - String status
     */
    public void setStatus(BookingStatus status)
    {
        this.status = status;
    }
    
    /**
     * Get patientFirstName
     * @return return the patients first name
     */
    public String getPatientFirstName() { return patientFirstName; }
    
    /**
     * Get patientSecondName
     * @return return the patients second name
     */
    public String getPatientSecondName() { return patientSecondName; }

    /**
     * Get a patient object from a valid email
     * @param dbConnection - valid dbConnection variable to use to use as database interface
     * @return - Patient object from the database, possibility of being null if email is not valid
     */
    public Patient getPatient(Database dbConnection) { return dbConnection.getPatient(patientEmail); }

    /**
     * Get a doctor object from a valid email
     * @param dbConnection - valid dbConnection variable to use to use as database interface
     * @return - Doctor object from the database, possibility of being null if email is not valid
     */
    public Doctor getDoctor(Database dbConnection) { return dbConnection.getDoctor(doctorEmail); }

    /**
     * Get a debug string for the content of a Booking
     * @return String of Booking
     */
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", dateTime=" + dateTime.toLocalDate().toString() +
                ", additionalDetails='" + additionalDetails + '\'' +
                ", status='" + status + '\'' +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientSecondName='" + patientSecondName + '\'' +
                ", prescriptions='" + prescriptions.toString() + '\'' +
                '}';
    }

}

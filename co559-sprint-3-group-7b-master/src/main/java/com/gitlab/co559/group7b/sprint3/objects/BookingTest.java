/*
    JUnit Testing class for the Booking object

    This test class tests each individual method for the object.
    Most methods return values stored in fields in the object, and so
        test all of those getter methods.

    The methods:
        getDoctor()
        getPatient()
    Could not be tested in this class as they interact with the
        Database object and are not part of the Booking
        objects fields.

    The 'toString()' method was not tested either as it is purely for debugging.

    Author: lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookingTest {

    //The Booking Objects that are going to be used during the tests.
    private Booking book1, book2;

    //The Prescription objects that will be added to book2.
    private ArrayList<Prescription> prescriptionArrayList;

    //The following are values used in the construction of the book1 object.
    //These can be easily used and asserted.
    private static final int BOOK1_BOOKING_ID = 1;
    private static final String BOOK1_DOCTOR_EMAIL = "jrs63@kent.ac.uk";
    private static final String BOOK1_PATIENT_EMAIL = "kaden.paul@email.com";
    private static final String BOOK1_TIME_STRING = "2021-18-5 17:20";
    private static final String BOOK1_ADDITIONAL_DETAILS = null;
    private static final BookingStatus BOOK1_STATUS = BookingStatus.PENDING;
    private static final String BOOK1_PATIENT_FIRST_NAME = "Kaden";
    private static final String BOOK1_PATIENT_LAST_NAME = "Paul";
    private Timestamp BOOK1_TIMESTAMP;

    //The following are values used in the construction of the book2 object.
    //These can be easily used and asserted.
    private static final int BOOK2_BOOKING_ID = 2;
    private static final String BOOK2_DOCTOR_EMAIL = "ya217@kent.ac.uk";
    private static final String BOOK2_PATIENT_EMAIL = "marcus.cortez@email.com";
    private static final String BOOK2_TIME_STRING = "2021-26-8 12:30";
    private static final String BOOK2_ADDITIONAL_DETAILS = "Came in complaining about chest pains, has been referred to the hospital for an xray and echocardiogram";
    private static final BookingStatus BOOK2_STATUS = BookingStatus.COMPLETED;
    private static final String BOOK2_PATIENT_FIRST_NAME = "Marchus";
    private static final String BOOK2_PATIENT_LAST_NAME = "Cortez";
    private Timestamp BOOK2_TIMESTAMP;

    /**
     * Constructor for the Booking Test class.
     * The timestamp objects need to be parsed from a string at the initialisation of the MessageTest object.
     */
    public BookingTest(){
        Prescription p1 = new Prescription("Paracetamol", 2, Duration.DAYS, false, 4, Duration.WEEKS);
        Prescription p2 = new Prescription("Insulin Injection", 1, Duration.HOURS, false, 4, Duration.MONTHS);
        ArrayList<Prescription> prescriptionArrayList = new ArrayList<>();
        prescriptionArrayList.add(p1);
        prescriptionArrayList.add(p2);
        this.prescriptionArrayList = prescriptionArrayList;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-M HH:mm");
        BOOK1_TIMESTAMP = Timestamp.valueOf(LocalDateTime.parse(BOOK1_TIME_STRING, format));
        BOOK2_TIMESTAMP = Timestamp.valueOf(LocalDateTime.parse(BOOK2_TIME_STRING, format));
    }

    /**
     * Setting up method.
     * This method will be run at the beginning of each test object.
     * This way we have a consistent base for each test.
     */
    @Before
    public void setUp(){
        book1 = new Booking(BOOK1_BOOKING_ID, BOOK1_DOCTOR_EMAIL, BOOK1_PATIENT_EMAIL, BOOK1_TIMESTAMP, BOOK1_ADDITIONAL_DETAILS, BOOK1_STATUS, BOOK1_PATIENT_FIRST_NAME, BOOK1_PATIENT_LAST_NAME);
        book2 = new Booking(BOOK2_BOOKING_ID, BOOK2_DOCTOR_EMAIL, BOOK2_PATIENT_EMAIL, BOOK2_TIMESTAMP, BOOK2_ADDITIONAL_DETAILS, BOOK2_STATUS, BOOK2_PATIENT_FIRST_NAME, BOOK2_PATIENT_LAST_NAME);
        book2.setPrescriptions(prescriptionArrayList);
    }

    @Test
    public void testGetMessageID(){
        assertEquals(BOOK1_BOOKING_ID, book1.getBookingID());
        assertEquals(BOOK2_BOOKING_ID, book2.getBookingID());
    }

    @Test
    public void testGetDoctorEmail(){
        assertEquals(BOOK1_DOCTOR_EMAIL, book1.getDoctorEmail());
        assertEquals(BOOK2_DOCTOR_EMAIL, book2.getDoctorEmail());
    }

    @Test
    public void testGetPatientEmail(){
        assertEquals(BOOK1_PATIENT_EMAIL, book1.getPatientEmail());
        assertEquals(BOOK2_PATIENT_EMAIL, book2.getPatientEmail());
    }

    @Test
    public void testGetAdditionalDetails(){
        assertEquals(BOOK1_ADDITIONAL_DETAILS, book1.getAdditionalDetails());
        assertEquals(BOOK2_ADDITIONAL_DETAILS, book2.getAdditionalDetails());
    }

    @Test
    public void testGetPrescriptions(){
        assertNull(book1.getPrescriptions());
        assertEquals(prescriptionArrayList, book2.getPrescriptions());
    }

    @Test
    public void testGetDate(){
        assertEquals(BOOK1_TIMESTAMP.toLocalDateTime().toLocalDate(), book1.getDate());
        assertEquals(BOOK2_TIMESTAMP.toLocalDateTime().toLocalDate(), book2.getDate());
    }

    @Test
    public void testGetFormattedDate(){
        assertEquals("18 May 2021", book1.getFormattedDate());
        assertEquals("26 August 2021", book2.getFormattedDate());
    }

    @Test
    public void testGetFormattedTime(){
        assertEquals("17:20", book1.getFormattedTime());
        assertEquals("12:30", book2.getFormattedTime());
    }

    @Test
    public void testGetStatus(){
        assertEquals(BookingStatus.PENDING, book1.getStatus());
        assertEquals(BookingStatus.COMPLETED, book2.getStatus());
    }

    @Test
    public void testSetStatus(){
        book1.setStatus(BookingStatus.CANCELLED);
        book2.setStatus(BookingStatus.IN_PROGRESS);
        assertEquals(BookingStatus.CANCELLED, book1.getStatus());
        assertEquals(BookingStatus.IN_PROGRESS, book2.getStatus());
    }

    @Test
    public void testGetPatientFirstName(){
        assertEquals(BOOK1_PATIENT_FIRST_NAME, book1.getPatientFirstName());
        assertEquals(BOOK2_PATIENT_FIRST_NAME, book2.getPatientFirstName());
    }

    @Test
    public void testGetPatientSecondName(){
        assertEquals(BOOK1_PATIENT_LAST_NAME, book1.getPatientSecondName());
        assertEquals(BOOK2_PATIENT_LAST_NAME, book2.getPatientSecondName());
    }

}

/*
    JUnit Testing class for the Patient object

    This test class tests each individual method for the object.
    Most methods return values stored in fields in the object, and so
        test all of those getter methods.

    Author: lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PatientTest {

    //The Patient Objects that are going to be used during the tests.
    private Patient pat1;
    private Patient pat2;

    //The following are values used in the construction of the pat1 object.
    //These can be easily used and asserted.
    private static final String PAT1_EMAIL = "kaden.paul@email.com";
    private static final String PAT1_FIRSTNAME = "Kaden";
    private static final String PAT1_LASTNAME = "Paul";
    private static final String PAT1_PHONENUMBER = "07747486585";
    private static final String PAT1_ADDRESS = "832 West Main Street Westport, CT 06880";

    //The following are values used in the construction of the pat2 object.
    //These can be easily used and asserted.
    private static final String PAT2_EMAIL = "cory.travers@email.com";
    private static final String PAT2_FIRSTNAME = "Cory";
    private static final String PAT2_LASTNAME = "Travers";
    private static final String PAT2_PHONENUMBER = "07837905947";
    private static final String PAT2_ADDRESS = "53 Grand St. Palm Beach Gardens, FL 33410";
    private static final String PAT2_DOCTOR_EMAIL = "ghwv2@kent.ac.uk";

    /**
     * Setting up method.
     * This method will be run at the beginning of each test object.
     * This way we have a consistent base for each test.
     */
    @Before
    public void setUp(){
        pat1 = new Patient(PAT1_EMAIL, PAT1_FIRSTNAME, PAT1_LASTNAME, PAT1_PHONENUMBER, PAT1_ADDRESS);
        pat2 = new Patient(PAT2_EMAIL, PAT2_FIRSTNAME, PAT2_LASTNAME, PAT2_PHONENUMBER, PAT2_ADDRESS);
        pat2.setDoctorEmail(PAT2_DOCTOR_EMAIL);
    }

    /**
     * Unit test to check the getEmail() method.
     */
    @Test
    public void testGetEmail(){
        assertEquals(PAT1_EMAIL, pat1.getEmail());
        assertEquals(PAT2_EMAIL, pat2.getEmail());
    }

    /**
     * Unit test to check the getFirstName() method.
     */
    @Test
    public void testGetFirstName(){
        assertEquals(PAT1_FIRSTNAME, pat1.getFirstName());
        assertEquals(PAT2_FIRSTNAME, pat2.getFirstName());
    }

    /**
     * Unit test to check the getLastName() method.
     */
    @Test
    public void testGetLastName(){
        assertEquals(PAT1_LASTNAME, pat1.getLastName());
        assertEquals(PAT2_LASTNAME, pat2.getLastName());
    }

    /**
     * Unit test to check the getPhoneNumber() method.
     */
    @Test
    public void testGetPhoneNumber(){
        assertEquals(PAT1_PHONENUMBER, pat1.getPhoneNumber());
        assertEquals(PAT2_PHONENUMBER, pat2.getPhoneNumber());
    }

    /**
     * Unit test to check the getAddress() method.
     */
    @Test
    public void testGetAddress(){
        assertEquals(PAT1_ADDRESS, pat1.getAddress());
        assertEquals(PAT2_ADDRESS, pat2.getAddress());
    }

    /**
     * Unit test to check the getDoctorEmail() method.
     */
    @Test
    public void testGetDoctorEmail(){
        assertNull(pat1.getDoctorEmail());
        assertEquals(PAT2_DOCTOR_EMAIL, pat2.getDoctorEmail());
    }

    /**
     * Unit test to check the getDoctorEmail() method.
     */
    @Test
    public void testSetDoctorEmail(){
        pat1.setDoctorEmail("jrs63@kent.ac.uk");
        assertEquals("jrs63@kent.ac.uk", pat1.getDoctorEmail());
    }

    /**
     * Unit test to check the hasDoctor() method.
     */
    @Test
    public void testHasDoctor(){
        assertFalse(pat1.hasDoctor());
        assertTrue(pat2.hasDoctor());
    }

}

/*
    JUnit Testing class for the Booking object

    This test class tests each individual method for the object.
    Most methods return values stored in fields in the object, and so
        test all of those getter methods.

    Author: ya217 Yacoub AlKaradsheh
 */

package com.gitlab.co559.group7b.sprint3.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class PersonTest {

    //The Person Objects that are going to be used during the tests.
    private Person person1, person2;

    //The following are values used in the construction of the person1 object.
    //These can be easily used and asserted.

    private static final String PERSON1_DOCTOR_EMAIL = "jrs63@kent.ac.uk";
    private static final String PERSON1_FIRSTNAME = "Joshua";
    private static final String PERSON1_LASTNAME = "Sylvester";
    private static final String PHONE_NUMBER = "07999999996";
    private static final String ADDRESS = "ADDRESS";

    //The following are values used in the construction of the person2 object.
    //These can be easily used and asserted.

    private static final String PERSON2_PATIENT_EMAIL = "amy.richardson@email.com";
    private static final String PERSON2_FIRSTNAME = "Amy";
    private static final String PERSON2_LASTNAME = "Richardson";
    private static final String PHONE_NUMBER2 = "07765558644";
    private static final String ADDRESS2 = "8093 South Hillcrest Ave. Georgetown, SC 29440";


    /**
     * Setting up method.
     * This method will be run at the beginning of each test object.
     * This way we have a consistent base for each test.
     */
    @Before
    public void setUp(){
        person1 = new Person(PERSON1_DOCTOR_EMAIL,PERSON1_FIRSTNAME,PERSON1_LASTNAME,PHONE_NUMBER,ADDRESS);
        person2 = new Person(PERSON2_PATIENT_EMAIL,PERSON2_FIRSTNAME,PERSON2_LASTNAME,PHONE_NUMBER2,ADDRESS2);

    }

    @Test
    public void testGetEmail(){
        assertEquals(PERSON1_DOCTOR_EMAIL, person1.getEmail());
        assertEquals(PERSON2_PATIENT_EMAIL, person2.getEmail());
    }

    @Test
    public void testGetFirstName(){
        assertEquals(PERSON1_FIRSTNAME, person1.getFirstName());
        assertEquals(PERSON2_FIRSTNAME,person2.getFirstName() );
    }

    @Test
    public void testGetLastName(){
        assertEquals(PERSON1_LASTNAME, person1.getLastName());
        assertEquals(PERSON2_LASTNAME, person2.getLastName());
    }

    @Test
    public void testGetPhoneNumber(){
        assertEquals(PHONE_NUMBER, person1.getPhoneNumber());
        assertEquals(PHONE_NUMBER2, person2.getPhoneNumber());
    }

    @Test
    public void testGetAddress(){
        assertEquals(ADDRESS, person1.getAddress());
        assertEquals(ADDRESS2,person2.getAddress());
    }





}

/*
    JUnit Testing class for the Booking object

    This test class tests each individual method for the object.
    Most methods return values stored in fields in the object, and so
        test all of those getter methods.

    The 'toString()' method was not tested as it is purely for debugging.
    Author: @ya217 Yacoub AlKaradsheh
 */

package com.gitlab.co559.group7b.sprint3.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class PrescriptionTest {

    //The Person Objects that are going to be used during the tests.
    private Prescription prescription1, prescription2;

    //The following are values used in the construction of the prescription1 object.
    //These can be easily used and asserted.

    private static final String NAME_DESCRIPTION = "Advil";
    private static final int FREQUENCY_COUNT = 23;
    private static final Duration FREQUENCY_DURATION = Duration.HOURS;
    private static final boolean EMPTY_STOMACH = true;
    private static final int TIME_TO_TAKE_COUNT = 15;
    private static final Duration TIME_TO_TAKE_DURATION= Duration.YEARS;

    //The following are values used in the construction of the prescription2 object.
    //These can be easily used and asserted.

    private static final String NAME_DESCRIPTION2 = "Cough Syrup";
    private static final int FREQUENCY_COUNT2 = 20;
    private static final Duration FREQUENCY_DURATION2 = Duration.MINS;
    private static final boolean EMPTY_STOMACH2 = true;
    private static final int TIME_TO_TAKE_COUNT2 = 18;
    private static final Duration TIME_TO_TAKE_DURATION2= Duration.MONTHS;


    /**
     * Setting up method.
     * This method will be run at the beginning of each test object.
     * This way we have a consistent base for each test.
     */
    @Before
    public void setUp(){
        prescription1 = new Prescription(NAME_DESCRIPTION,FREQUENCY_COUNT,FREQUENCY_DURATION,EMPTY_STOMACH,TIME_TO_TAKE_COUNT,TIME_TO_TAKE_DURATION);
        prescription2 = new Prescription(NAME_DESCRIPTION2,FREQUENCY_COUNT2,FREQUENCY_DURATION2,EMPTY_STOMACH2,TIME_TO_TAKE_COUNT2,TIME_TO_TAKE_DURATION2);

    }

    @Test
    public void testGetNameDescription(){
        assertEquals(NAME_DESCRIPTION, prescription1.getNameDescription());
        assertEquals(NAME_DESCRIPTION2, prescription2.getNameDescription());
    }

    @Test
    public void testGetFrequencyCount(){
        assertEquals(FREQUENCY_COUNT, prescription1.getFrequencyCount());
        assertEquals(FREQUENCY_COUNT2, prescription2.getFrequencyCount() );
    }

    @Test
    public void testGetFrequencyDuration(){
        assertEquals(FREQUENCY_DURATION, prescription1.getFrequencyDuration());
        assertEquals(FREQUENCY_DURATION2, prescription2.getFrequencyDuration());
    }

    @Test
    public void testGetEmptyStomach(){
        assertEquals(EMPTY_STOMACH, prescription1.getEmptyStomach());
        assertEquals(EMPTY_STOMACH2, prescription2.getEmptyStomach());
    }

    @Test
    public void testGetTimeToTakeCount(){
        assertEquals(TIME_TO_TAKE_COUNT, prescription1.getTimeToTakeCount());
        assertEquals(TIME_TO_TAKE_COUNT2,prescription2.getTimeToTakeCount());
    }


    @Test
    public void testGetTimeToTakeDuration(){
        assertEquals(TIME_TO_TAKE_DURATION, prescription1.getTimeToTakeDuration());
        assertEquals(TIME_TO_TAKE_DURATION2,prescription2.getTimeToTakeDuration());
    }



}

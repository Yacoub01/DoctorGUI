/*
    JUnit Testing class for the Message object

    This test class tests each individual method for the object.
    Most methods return values stored in fields in the object, and so
        test all of those getter methods.

    Author: lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    //The Message Objects that are going to be used during the tests.
    private Message msg1;
    private Message msg2;

    //The following are values used in the construction of the msg1 object.
    //These can be easily used and asserted.
    private static final int MSG1_MESSAGE_ID = 1;
    private static final String MSG1_SENDER = "tp379@kent.ac.uk";
    private static final String MSG1_RECIPIENT = "ya217@kent.ac.uk";
    private static final String MSG1_SUBJECT = "Meeting friday";
    private static final String MSG1_MESSAGE = "Meeting in room 9 on Friday @ 10:30AM";
    private static final String MSG1_TIMESTAMP_STR = "03-26-2021 15:30:00";
    private Timestamp MSG1_TIMESTAMP;

    //The following are values used in the construction of the msg2 object.
    //These can be easily used and asserted.
    private static final int MSG2_MESSAGE_ID = 2;
    private static final String MSG2_SENDER ="kaden.paul@email.com";
    private static final String MSG2_RECIPIENT = "ya217@kent.ac.uk";
    private static final String MSG2_SUBJECT = "Appointment Rescheduled";
    private static final String MSG2_MESSAGE = "Dear Doctor, I need to reschedule the meeting we have on Wednesday, How can I move this?";
    private static final String MSG2_TIMESTAMP_STR = "07-18-2021 11:45:00";
    private Timestamp MSG2_TIMESTAMP;

    /**
     * Constructor for the Message Test class.
     * The timestamp objects need to be parsed from a string at the initialisation of the MessageTest object.
     */
    public MessageTest(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        MSG1_TIMESTAMP = Timestamp.valueOf(LocalDateTime.parse(MSG1_TIMESTAMP_STR, format));
        MSG2_TIMESTAMP = Timestamp.valueOf(LocalDateTime.parse(MSG2_TIMESTAMP_STR, format));
    }

    /**
     * Setting up method.
     * This method will be run at the beginning of each test object.
     * This way we have a consistent base for each test.
     */
    @Before
    public void setUp(){
        msg1 = new Message(MSG1_MESSAGE_ID, MSG1_SENDER, MSG1_RECIPIENT, MSG1_SUBJECT, MSG1_MESSAGE, MSG1_TIMESTAMP);
        msg2 = new Message(MSG2_MESSAGE_ID, MSG2_SENDER, MSG2_RECIPIENT, MSG2_SUBJECT, MSG2_MESSAGE, MSG2_TIMESTAMP);
    }

    /**
     * Unit test to check the getMessageID() method.
     */
    @Test
    public void testGetMessageID(){
        assertEquals(MSG1_MESSAGE_ID, msg1.getMessageID());
        assertEquals(MSG2_MESSAGE_ID, msg2.getMessageID());
    }

    /**
     * Unit test to check the getSender() method.
     */
    @Test
    public void testGetSender(){
        assertEquals(MSG1_SENDER, msg1.getSender());
        assertEquals(MSG2_SENDER, msg2.getSender());
    }

    /**
     * Unit test to check the getRecipient() method.
     */
    @Test
    public void testGetRecipient(){
        assertEquals(MSG1_RECIPIENT, msg1.getRecipient());
        assertEquals(MSG2_RECIPIENT, msg2.getRecipient());
    }

    /**
     * Unit test to check the getSubject() method.
     */
    @Test
    public void testGetSubject(){
        assertEquals(MSG1_SUBJECT, msg1.getSubject());
        assertEquals(MSG2_SUBJECT, msg2.getSubject());
    }

    /**
     * Unit test to check the getMessage() method.
     */
    @Test
    public void testGetMessage(){
        assertEquals(MSG1_MESSAGE, msg1.getMessage());
        assertEquals(MSG2_MESSAGE, msg2.getMessage());
    }

    /**
     * Unit test to check the getSentDateTime() method.
     */
    @Test
    public void testGetSentDateTime(){
        assertEquals(MSG1_TIMESTAMP.toLocalDateTime(), msg1.getSentDateTime());
        assertEquals(MSG2_TIMESTAMP.toLocalDateTime(), msg2.getSentDateTime());
    }

    /**
     * Unit test to check the getFormattedDateTime() method.
     */
    @Test
    public void testGetFormattedDateTime(){
        assertEquals("26 March 2021 15:30:00", msg1.getFormattedDateTime());
        assertEquals("18 July 2021 11:45:00", msg2.getFormattedDateTime());
    }

}

/*
    Message Object

    Object used to interface between the database and message.

    A Message object holds information about a message that was sent from
     one person to another person.

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    //All fields used to hold information about a message
    //Exactly like the fields in the Message database table
    private int messageID;
    private String sender;
    private String recipient;
    private String subject;
    private String message;
    private Timestamp timestamp;
    private LocalDateTime sentDateTime;

    //DateTimeFormatters are used to hold formats so that easy string can be called to be viewed to users
    private DateTimeFormatter timeStringFormat;

    /**
     * Constructor for a Message object
     * Use when reading a message into the program from the Database.
     * @param messageID
     * @param sender
     * @param recipient
     * @param subject
     * @param message
     * @param sentDateTime
     */
    public Message(int messageID, String sender, String recipient, String subject, String message, Timestamp sentDateTime){
        this.messageID = messageID;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.timestamp = sentDateTime;
        this.sentDateTime = timestamp.toLocalDateTime();
        timeStringFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"); //Setting up the DateTimeFormats
    }

    /**
     * The SQL Table Primary Key reference for this message
     *  Will be used when a message is to be deleted
     * @return Int - Primary Key reference of the message in the Database
     */
    public int getMessageID() { return messageID; }

    /**
     * The email of the person who sent the message
     * @return String - email of sender
     */
    public String getSender() { return sender; }

    /**
     * The email of the person who is to receive the message
     * @return String - email of recipient
     */
    public String getRecipient() { return recipient; }

    /**
     * The subject line of the message
     * @return String - subject line of the message
     */
    public String getSubject() { return subject; }

    /**
     * The message body of the message
     * @return String - message itself
     */
    public String getMessage() { return message; }

    /**
     * Get the date/time of when the message was sent
     * @return LocalDateTime - when the message was sent
     */
    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    /**
     * Get the formatted date/time string
     * @return String - date/time that is easily readable and pre formatted
     */
    public String getFormattedDateTime(){
        return sentDateTime.format(timeStringFormat);
    }
}

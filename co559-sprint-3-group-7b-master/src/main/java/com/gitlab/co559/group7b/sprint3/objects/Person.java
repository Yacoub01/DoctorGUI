/*
    Person Object

    Object used to interface between the database in the User table

    Author: @lth20 Luke Hadley & @jrs63 Joshua Sylvester
 */

package com.gitlab.co559.group7b.sprint3.objects;

public class Person {

    //Fields used in the Person Object
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    /**
     * Constructor for a Person object
     * @param email - String email for the person
     * @param firstName - String firstname for the person
     * @param lastName - String lastname for the person
     * @param phoneNumber - String phone number for the person
     * @param address - String address for the person
     * 
     */
    public Person(String email, String firstName, String lastName, String phoneNumber, String address){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Get the email of the person
     * @return - String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the first name of the person
     * @return - String first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the person
     * @return - String last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the phone number of the person
     * @return - String phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Get the address of the person
     * @return - String address
     */
    public String getAddress() {
        return address;
    }

}

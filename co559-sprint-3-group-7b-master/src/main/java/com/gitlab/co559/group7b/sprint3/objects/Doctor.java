/*
    Doctor Object

    Object used to interface between the database and a user/login of type Doctor

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

public class Doctor extends Person{

    /**
     * Constructor for a Doctor object
     * @param email - String email for the person
     * @param firstName - String firstname for the person
     * @param lastName - String lastname for the person
     * @param phoneNumber - String phone number for the person
     * @param address - String address for the person
     */
    public Doctor(String email, String firstName, String lastName, String phoneNumber, String address){
        super(email, firstName, lastName, phoneNumber, address);
    }

}

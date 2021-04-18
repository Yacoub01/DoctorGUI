/*
    UserType Enum

    Enum type for all the 4 possible user types ( Doctor, Patient, Admin, Receptionist )

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

public enum UserType {

    //User types
    DOCTOR("Doctor"),
    PATIENT("Patient"),
    ADMIN("Admin"),
    RECEPTIONIST("Receptionist");

    private String userType;

    UserType(String userType) {this.userType = userType; }

    /**
     * Get a string value of a user type
     * @return - String user type
     */
    @Override
    public String toString() {
        return userType;
    }

}

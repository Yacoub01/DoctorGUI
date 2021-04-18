/*
    Patient Object

    Object used to interface between the database and a user/login of type Patient

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

public class Patient extends Person{

    private String doctorEmail; //The email of the doctor (if assigned one)
    private boolean hasDoctor; //Is the patient assigned to a doctor or not?

    /**
     * Constructor for a Patient object
     * @param email - String email for the person
     * @param firstName - String firstname for the person
     * @param lastName - String lastname for the person
     * @param phoneNumber - String phone number for the person
     * @param address - String address for the person
     */
    public Patient(String email, String firstName, String lastName, String phoneNumber, String address){
        super(email, firstName, lastName, phoneNumber, address);
        doctorEmail = null;
        hasDoctor = false;
    }

    /**
     * Set the email of the doctor who is assigned to this patient
     * @param doctorEmail
     */
    public void setDoctorEmail(String doctorEmail) {
        hasDoctor = true;
        this.doctorEmail = doctorEmail;
    }

    /**
     * Get the email of the doctor who is assigned to this patient
     * @return - String email of the doctor who is assigned to. Potentially 'null' if one has not been set.
     *  use 'hasDoctor()' to check first.
     */
    public String getDoctorEmail(){
        return doctorEmail;
    }

    /**
     * Return a boolean value if the patient has a doctor assigned to them already or not.
     * @return - Boolean true if a patient is assigned to a doctor, false if otherwise.
     */
    public boolean hasDoctor(){ return hasDoctor; }

}

/*
    Booking Status enum type

    Holds all types of status' that a booking can hold.
    This marries up to the 4 status' that can be held in the Booking database table

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

public enum BookingStatus {

    //Status types
    PENDING("Pending"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String status;

    BookingStatus(String status) { this.status = status; }

    /**
     * Get a string value of a booking status
     * @return - String booking status
     */
    @Override
    public String toString() {
        return status;
    }

    /**
     * Switches a String version of the enum to an actual enum of type BookingStatus
     * @param status - The string to switch.
     * @return BookingStatus enum, possible of being null if string could not be switched
     */
    public BookingStatus toEnum(String status){
        switch (status){
            case "Pending":
                return PENDING;
            case "InProgress":
                return IN_PROGRESS;
            case "Completed":
                return COMPLETED;
            case "Cancelled":
                return CANCELLED;
            default:
                return null;
        }
    }

}

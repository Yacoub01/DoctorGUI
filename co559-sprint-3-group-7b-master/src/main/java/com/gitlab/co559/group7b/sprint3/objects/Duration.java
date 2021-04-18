/*
    Duration Enum class

    Object used to show how often a prescription should be taken.

    Author: @lth20 Luke Hadley & jrs63 Joshua Sylvester
 */

package com.gitlab.co559.group7b.sprint3.objects;

public enum Duration {

    //Duration types
    MINS("Minutes"),
    HOURS("Hours"),
    DAYS("Days"),
    WEEKS("Weeks"),
    MONTHS("Months"),
    YEARS("Years");

    private String duration; //String variable of a Duration

    Duration(String duration){ this.duration = duration; }

    public Duration toEnum(String status){
        switch (status){
            case "Minutes":
                return MINS;
            case "Hours":
                return HOURS;
            case "Days":
                return DAYS;
            case "Weeks":
                return WEEKS;
            case "Months":
                return MONTHS;
            case "Years":
                return YEARS;
            default:
                return null;
        }
    }
    
    /**
     * Get a string version of the Duration to be able to display to the user.
     * @return
     */
    @Override
    public String toString() {
        return this.duration;
    }

}

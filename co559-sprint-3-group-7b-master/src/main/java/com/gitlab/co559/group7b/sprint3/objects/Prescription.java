/*
    Prescription Object

    Object used to interface between the database and the Prescription table

    Author: @lth20 Luke Hadley
 */

package com.gitlab.co559.group7b.sprint3.objects;

public class Prescription {

    private String nameDescription;
    private int frequencyCount;
    private Duration frequencyDuration;
    private boolean emptyStomach;
    private int timeToTakeCount;
    private Duration timeToTakeDuration;

    /**
     * Construct a new Prescription object
     * @param nameDescription - The string main description of the prescription
     * @param frequencyCount - The frequency the prescription should be taken
     * @param frequencyDuration - How often the prescription should be taken
     * @param emptyStomach - If the prescription needs to be taken with an empty stomach
     * @param timeToTakeCount - How long the patient should be taking the prescription for
     * @param timeToTakeDuration - How long the patient should be taking the prescription
     */
    public Prescription(String nameDescription, int frequencyCount, Duration frequencyDuration, boolean emptyStomach, int timeToTakeCount, Duration timeToTakeDuration){
        this.nameDescription = nameDescription;
        this.frequencyCount = frequencyCount;
        this.frequencyDuration = frequencyDuration;
        this.emptyStomach = emptyStomach;
        this.timeToTakeCount = timeToTakeCount;
        this.timeToTakeDuration = timeToTakeDuration;
    }

    /**
     * Get the description of the prescription
     * @return - Description of the prescription
     */
    public String getNameDescription() { return nameDescription; }

    /**
     * Get he frequency the prescription should be taken
     * @return - frequency count
     */
    public int getFrequencyCount() { return frequencyCount; }

    /**
     * Get how often the prescription should be taken
     * @return - frequency duration
     */
    public Duration getFrequencyDuration() { return frequencyDuration; }

    /**
     * Get if the prescription needs to be taken with an empty stomach
     * @return - int empty stomach or not
     */
    public boolean getEmptyStomach(){return emptyStomach;}

    /**
     * Get how long the patient should be taking the prescription for
     * @return - time to take count
     */
    public int getTimeToTakeCount() { return timeToTakeCount; }

    /**
     * Get how long the patient should be taking the prescription
     * @return - time to take duration
     */
    public Duration getTimeToTakeDuration() { return timeToTakeDuration; }

    @Override
    public String toString() {
        return "Prescription{" +
                "nameDescription='" + nameDescription + '\'' +
                ", frequencyCount=" + frequencyCount +
                ", frequencyDuration=" + frequencyDuration +
                ", emptyStomach=" + emptyStomach +
                ", timeToTakeCount=" + timeToTakeCount +
                ", timeToTakeDuration=" + timeToTakeDuration +
                '}';
    }

}

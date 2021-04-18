/**
 *  EditViewBookings is class with two user stories combined:
 *      it allows the user to view all their bookings without being able to edit it,
 *      unless they press the edit button which will give the user full access to editing the booking.
 *      The user is able to confirm and send confirmation message saying that the edits have been done.
 *
 *  Authors : @ya217 Yacoub Alkaradsheh(Added functionality and refactored), @tp379 Tejaswini Parmessur (designer of frame. Added the components)
 *            @lth20 Luke Hadley (Added functionality).
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Booking;
import com.gitlab.co559.group7b.sprint3.objects.Duration;
import com.gitlab.co559.group7b.sprint3.objects.Prescription;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditViewBookings extends JFrame implements ActionListener {

        private Container con = getContentPane();
        private JPanel enterPanel = new JPanel();
        private JLabel enterTitle = new JLabel("VIEW/EDIT BOOKING DETAILS");
        private JLabel doctor_label = new JLabel("Doctor : ");
        private JTextField date_text ;
        private JTextField time_text ;
        private JLabel addDetails_label = new JLabel("Additional Details ");
        private JLabel line_label = new JLabel("PRESCRIPTION TABLE");
        private JLabel underline_label = new JLabel("");

        private JLabel freq_label = new JLabel("Frequency ");
        private Booking booking;
        private JButton edit = new JButton("Edit Bookings");
        private JButton finished = new JButton("Done Viewing");

       //ComboBox field for frequency of medication.
        private String[] freq_data = {"Select", "minute(s)", "hour(s)", "day(s)"};
        private JComboBox freq1_list = new JComboBox(freq_data);
        private JComboBox freq2_list = new JComboBox(freq_data);
        private JComboBox freq3_list = new JComboBox(freq_data);
        private JComboBox freq4_list = new JComboBox(freq_data);
        private JComboBox freq5_list = new JComboBox(freq_data);
        private JComboBox freq6_list = new JComboBox(freq_data);
        private JComboBox freq7_list = new JComboBox(freq_data);
        private JComboBox freq8_list = new JComboBox(freq_data);
        private JComboBox freq9_list = new JComboBox(freq_data);

       //Checkbox fields for empty stomachs
        private JLabel empty_label = new JLabel("Empty Stomach ");
        private JCheckBox check1 = new JCheckBox("");
        private JCheckBox check2 = new JCheckBox("");
        private JCheckBox check3 = new JCheckBox("");
        private JCheckBox check4 = new JCheckBox("");
        private JCheckBox check5 = new JCheckBox("");
        private JCheckBox check6 = new JCheckBox("");
        private JCheckBox check7 = new JCheckBox("");
        private JCheckBox check8 = new JCheckBox("");
        private JCheckBox check9 = new JCheckBox("");

       //Text fields for duration of medication
        private JLabel dur_label = new JLabel("Duration ");
        private JTextField dur1_text = new JTextField(2);
        private JTextField dur2_text = new JTextField(2);
        private JTextField dur3_text = new JTextField(2);
        private JTextField dur4_text = new JTextField(2);
        private JTextField dur5_text = new JTextField(2);
        private JTextField dur6_text = new JTextField(2);
        private JTextField dur7_text = new JTextField(2);
        private JTextField dur8_text = new JTextField(2);
        private JTextField dur9_text = new JTextField(2);

        //ComboBoxes fields for duration of medication
        private String[] dur_data = {"Select", "week(s)", "month(s)", "year(s)"};
        private JComboBox dur1_list = new JComboBox(dur_data);
        private JComboBox dur2_list = new JComboBox(dur_data);
        private JComboBox dur3_list = new JComboBox(dur_data);
        private JComboBox dur4_list = new JComboBox(dur_data);
        private JComboBox dur5_list = new JComboBox(dur_data);
        private JComboBox dur6_list = new JComboBox(dur_data);
        private JComboBox dur7_list = new JComboBox(dur_data);
        private JComboBox dur8_list = new JComboBox(dur_data);
        private JComboBox dur9_list = new JComboBox(dur_data);

        private JLabel line_label2 = new JLabel("");

        private JLabel patient_label = new JLabel("Patient Name : ");
        private JLabel date_picker = new JLabel("Date and Time: ");
        private JTextField doctor_text;
        private JTextField patient_text;
        private JTextArea add_details = new JTextArea(5,20);

        private JLabel med_label = new JLabel("Medication(s) ");

        // Text fields for Medications
        private JTextField med1_text = new JTextField(25);
        private JTextField med2_text = new JTextField(25);
        private JTextField med3_text = new JTextField(25);
        private JTextField med4_text = new JTextField();
        private JTextField med5_text = new JTextField();
        private JTextField med6_text = new JTextField();
        private JTextField med7_text = new JTextField();
        private JTextField med8_text = new JTextField();
        private JTextField med9_text = new JTextField();

        // Text fields and drop-downs for Frequency
        private JTextField freq1_text = new JTextField(2);
        private JTextField freq2_text = new JTextField(2);
        private JTextField freq3_text = new JTextField(2);
        private JTextField freq4_text = new JTextField(2);
        private JTextField freq5_text = new JTextField(2);
        private JTextField freq6_text = new JTextField(2);
        private JTextField freq7_text = new JTextField(2);
        private JTextField freq8_text = new JTextField(2);
        private JTextField freq9_text = new JTextField(2);

        // CONFIRM AND SEND BUTTON
        private JButton send = new JButton("Confirm and Send");

        private Database dbConnections;

        /**
         * Constructor for enter visit details frame.
         * @param dbConnections
         * @param booking
         */
        EditViewBookings(Database dbConnections, Booking booking){
            this.dbConnections = dbConnections;
            this.booking = booking;

            // Doctor, Patient, Date and Time textfields are connected to Booking class to get data.
            doctor_text = new JTextField(booking.getDoctorEmail());
            patient_text = new JTextField(booking.getPatientSecondName() + " " + booking.getPatientFirstName());

            prescription1();
            additionalD();

            //setting all editable to false.
            patient_text.setEditable(false);
            doctor_text.setEditable(false);

            time_text = new JTextField(booking.getFormattedTime());
            date_text = new JTextField(booking.getFormattedDate());

            time_text.setEditable(false);
            date_text.setEditable(false);

            dur1_list.setEnabled(false);
            dur2_list.setEnabled(false);
            dur3_list.setEnabled(false);
            dur4_list.setEnabled(false);
            dur5_list.setEnabled(false);
            dur6_list.setEnabled(false);
            dur7_list.setEnabled(false);
            dur8_list.setEnabled(false);
            dur9_list.setEnabled(false);

            dur1_text.setEditable(false);
            dur2_text.setEditable(false);
            dur3_text.setEditable(false);
            dur4_text.setEditable(false);
            dur5_text.setEditable(false);
            dur6_text.setEditable(false);
            dur7_text.setEditable(false);
            dur8_text.setEditable(false);
            dur9_text.setEditable(false);

            freq1_text.setEditable(false);
            freq2_text.setEditable(false);
            freq3_text.setEditable(false);
            freq4_text.setEditable(false);
            freq5_text.setEditable(false);
            freq6_text.setEditable(false);
            freq7_text.setEditable(false);
            freq8_text.setEditable(false);
            freq9_text.setEditable(false);

            freq1_list.setEnabled(false);
            freq2_list.setEnabled(false);
            freq3_list.setEnabled(false);
            freq4_list.setEnabled(false);
            freq5_list.setEnabled(false);
            freq6_list.setEnabled(false);
            freq7_list.setEnabled(false);
            freq8_list.setEnabled(false);
            freq9_list.setEnabled(false);

            med1_text.setEditable(false);
            med2_text.setEditable(false);
            med3_text.setEditable(false);
            med4_text.setEditable(false);
            med5_text.setEditable(false);
            med6_text.setEditable(false);
            med7_text.setEditable(false);
            med8_text.setEditable(false);
            med9_text.setEditable(false);

            add_details.setEditable(false);

            check1.setEnabled(false);
            check2.setEnabled(false);
            check3.setEnabled(false);
            check4.setEnabled(false);
            check5.setEnabled(false);
            check6.setEnabled(false);
            check7.setEnabled(false);
            check8.setEnabled(false);
            check9.setEnabled(false);

            setSize(600,600);
            setTitle("View/Edit Visit Details");
            setLayout(null);
            setResizable(false);
            send.setVisible(false);
            send.setEnabled(false);
            editButton();

            comp();
            locationSize();
            Done();

        }

        /**
         * Sets Location of size of all components.
         */
        public void locationSize(){

            enterTitle.setBounds(3, 10, 600, 15);
            enterTitle.setFont(new Font("Verdana", Font.PLAIN, 15));
            enterTitle.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));

            doctor_label.setBounds(10, 30, 80, 25);

            patient_label.setBounds(10, 55, 100, 25);

            date_picker.setBounds(10, 80, 135, 25);

            doctor_text.setBounds(150, 30, 195, 25);

            patient_text.setBounds(150, 55, 195, 25);

            date_text.setBounds(150, 80, 115, 25);

            time_text.setBounds(260, 80, 60, 25);

            addDetails_label.setBounds(10, 105, 150, 25);

            add_details.setBounds(10, 130, 480, 90);
            add_details.setBorder(new LineBorder(Color.gray));

            //PRESCRIPTIONS TABLE
            // Medications: label and text fields.
            line_label.setBounds(3, 237, 600, 15);
            line_label.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));

            underline_label.setBounds(3, 257, 600, 15);
            underline_label.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));

            med_label.setBounds(10, 250, 100, 25);

            med1_text.setBounds(5, 275, 175, 25);

            med2_text.setBounds(5, 300, 175, 25);

            med3_text.setBounds(5, 325, 175, 25);

            med4_text.setBounds(5, 350, 175, 25);

            med5_text.setBounds(5, 375, 175, 25);

            med6_text.setBounds(5, 400, 175, 25);

            med7_text.setBounds(5, 425, 175, 25);

            med8_text.setBounds(5, 450, 175, 25);

            med9_text.setBounds(5, 475, 175, 25);


            // Frequency: label, text fields and drop-downs.

            freq_label.setBounds(230, 250, 100, 25);

            freq1_text.setBounds(196, 275, 25, 25);

            freq2_text.setBounds(196, 300, 25, 25);

            freq3_text.setBounds(196, 325, 25, 25);

            freq4_text.setBounds(196, 350, 25, 25);

            freq5_text.setBounds(196, 375, 25, 25);

            freq6_text.setBounds(196, 400, 25, 25);

            freq7_text.setBounds(196, 425, 25, 25);

            freq8_text.setBounds(196, 450, 25, 25);

            freq9_text.setBounds(196, 475, 25, 25);

            // Frequency: ComboBoxes.
            freq1_list.setBounds(215, 275, 110, 25);

            freq2_list.setBounds(215, 300, 110, 25);

            freq3_list.setBounds(215, 325, 110, 25);

            freq4_list.setBounds(215, 350, 110, 25);

            freq5_list.setBounds(215, 375, 110, 25);

            freq6_list.setBounds(215, 400, 110, 25);

            freq7_list.setBounds(215, 425, 110, 25);

            freq8_list.setBounds(215, 450, 110, 25);

            freq9_list.setBounds(215, 475, 110, 25);

            // Empty Stomach: label and checkboxes.

            empty_label.setBounds(485, 250, 150, 25);

            check1.setBounds(515, 273, 175, 25);

            check2.setBounds(515, 298, 175, 25);

            check3.setBounds(515, 323, 175, 25);

            check4.setBounds(515, 348, 175, 25);

            check5.setBounds(515, 373, 175, 25);

            check6.setBounds(515, 398, 175, 25);

            check7.setBounds(515, 422, 175, 25);

            check8.setBounds(515, 446, 175, 25);

            check9.setBounds(515, 470, 175, 25);


            // Duration: label, text fields and drop-downs.

            dur_label.setBounds(370, 250, 150, 25);

            dur1_text.setBounds(341, 275, 25, 25);

            dur2_text.setBounds(341, 300, 25, 25);

            dur3_text.setBounds(341, 325, 25, 25);

            dur4_text.setBounds(341, 350, 25, 25);

            dur5_text.setBounds(341, 375, 25, 25);

            dur6_text.setBounds(341, 400, 25, 25);

            dur7_text.setBounds(341, 425, 25, 25);

            dur8_text.setBounds(341, 450, 25, 25);

            dur9_text.setBounds(341, 475, 25, 25);

            // Duration: ComboBoxes

            dur1_list.setBounds(360, 275, 110, 25);

            dur2_list.setBounds(360, 300, 110, 25);

            dur3_list.setBounds(360, 325, 110, 25);

            dur4_list.setBounds(360, 350, 110, 25);

            dur5_list.setBounds(360, 375, 110, 25);

            dur6_list.setBounds(360, 400, 110, 25);

            dur7_list.setBounds(360, 425, 110, 25);

            dur8_list.setBounds(360, 450, 110, 25);

            dur9_list.setBounds(360, 475, 110, 25);


            // CONFIRM AND SEND BUTTON

            send.setBounds(190, 520, 150, 35);

            edit.setBounds (440, 30, 150, 30);

            finished.setBounds(440, 60, 150, 30);

            line_label2.setBounds(3, 495, 600, 15);
            line_label2.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));

        }



        /**
         * Adds all components for enter visit details.
         */
        public void comp(){
            con.add(enterPanel);

            // Adding all general titles, texts and labels.
            con.add(enterTitle);
            con.add(doctor_label);
            con.add(patient_label);
            con.add(date_picker);
            con.add(doctor_text);
            con.add(patient_text);
            con.add(date_text);
            con.add(time_text);
            con.add(addDetails_label);
            con.add(add_details);
            con.add(line_label);
            con.add(underline_label);

            //Adding all medication labels and texts
            con.add(med_label);
            con.add(med1_text);
            con.add(med2_text);
            con.add(med3_text);
            con.add(med4_text);
            con.add(med5_text);
            con.add(med6_text);
            con.add(med7_text);
            con.add(med8_text);
            con.add(med9_text);

            //Adding all frequency labels and texts
            con.add(freq_label);
            con.add(freq1_text);
            con.add(freq2_text);
            con.add(freq3_text);
            con.add(freq4_text);
            con.add(freq5_text);
            con.add(freq6_text);
            con.add(freq7_text);
            con.add(freq8_text);
            con.add(freq9_text);
            con.add(freq9_text);

            //Adding all frequency ComboBoxes
            con.add(freq1_list);
            con.add(freq2_list);
            con.add(freq3_list);
            con.add(freq4_list);
            con.add(freq5_list);
            con.add(freq6_list);
            con.add(freq7_list);
            con.add(freq8_list);
            con.add(freq9_list);

            //Adding all Empty Stomach labels and CheckBoxes
            con.add(empty_label);
            con.add(check1);
            con.add(check2);
            con.add(check3);
            con.add(check4);
            con.add(check5);
            con.add(check6);
            con.add(check7);
            con.add(check8);
            con.add(check9);

            //Adding all duration labels and texts
            con.add(dur_label);
            con.add(dur1_text);
            con.add(dur2_text);
            con.add(dur3_text);
            con.add(dur4_text);
            con.add(dur5_text);
            con.add(dur6_text);
            con.add(dur7_text);
            con.add(dur8_text);
            con.add(dur9_text);

            //Adding all duration ComboBoxes
            con.add(dur1_list);
            con.add(dur2_list);
            con.add(dur3_list);
            con.add(dur4_list);
            con.add(dur5_list);
            con.add(dur6_list);
            con.add(dur7_list);
            con.add(dur8_list);
            con.add(dur9_list);

            con.add(send);

            con.add(line_label2);
            con.add(line_label2);

            con.add(edit);
            con.add(finished);
        }

        public void additionalD(){
            add_details.setText(booking.getAdditionalDetails());
        }

    /**
     * Sets the details of each prescription according what the user inputed.
     */
    public void prescription1(){
            ArrayList<Prescription> prescriptions = booking.getPrescriptions();

            int counter = 1;
            for (Prescription p : prescriptions){
                if (counter == 1){
                    setPrescription(p, med1_text, freq1_text, freq1_list, dur1_text, dur1_list, check1);
                }
                else if (counter == 2){
                    setPrescription(p, med2_text, freq2_text, freq2_list, dur2_text, dur2_list, check2);
                }
                else if (counter == 3){
                    setPrescription(p, med3_text, freq3_text, freq3_list, dur3_text, dur3_list, check3);
                }
                else if (counter == 4) {
                    setPrescription(p, med4_text, freq4_text, freq4_list, dur4_text, dur4_list, check4);
                }
                else if (counter == 5){
                    setPrescription(p, med5_text, freq5_text, freq5_list, dur5_text, dur5_list, check5);
                }
                else if (counter == 6){
                    setPrescription(p, med6_text, freq6_text, freq6_list, dur6_text, dur6_list, check6);
                }
                else if (counter == 7) {
                    setPrescription(p, med7_text, freq7_text, freq7_list, dur7_text, dur7_list, check7);
                }
                else if (counter == 8){
                    setPrescription(p, med8_text, freq8_text, freq8_list, dur8_text, dur8_list, check8);
                }
                else if (counter == 9){
                    setPrescription(p, med9_text, freq9_text, freq9_list, dur9_text, dur9_list, check9);
                }

                counter++;
            }


        }

    /**
     * Adds the content from the past booking to the frame
     * for the view booking user story.
     * @param p
     * @param descriptionField
     * @param freqText
     * @param freList
     * @param durText
     * @param durList
     * @param check
     */
    public void setPrescription(Prescription p, JTextField descriptionField, JTextField freqText, JComboBox freList, JTextField durText, JComboBox durList, JCheckBox check){
            String freqCount =""+p.getFrequencyCount();
            String durCount =""+p.getTimeToTakeCount();
            descriptionField.setText(p.getNameDescription());
            freqText.setText(freqCount);
            durText.setText(durCount);
            check.setEnabled(p.getEmptyStomach());

            switch (p.getFrequencyDuration()){
                case MINS:
                    freList.setSelectedIndex(1);
                    break;
                case HOURS:
                    freList.setSelectedIndex(2);
                    break;
                case DAYS:
                    freList.setSelectedIndex(3);
                    break;
            }

        switch (p.getTimeToTakeDuration()){
            case WEEKS:
                durList.setSelectedIndex(1);
                break;
            case MONTHS:
                durList.setSelectedIndex(2);
                break;
            case YEARS:
                durList.setSelectedIndex(3);
                break;
        }
    }

    /**
     * Takes in inputs of the details of prescriptions
     * Checks if the description of the prescription if null, return null.
     * if not null, then added all details needed into object created then return it.
     * @param descriptionField
     * @param freqText
     * @param freList
     * @param durText
     * @param durList
     * @param check
     * @return Prescription
     */
    public Prescription getPrescriptionsFromFields(JTextField descriptionField, JTextField freqText, JComboBox freList, JTextField durText, JComboBox durList, JCheckBox check){
        try {
            if (descriptionField.getText() == null) {
                return null;
            } else {
                String m = descriptionField.getText();
                String freqCount = freqText.getText();
                int x = Integer.parseInt(freqCount);
                int frequ = freList.getSelectedIndex();
                int dur = durList.getSelectedIndex();
                Duration f = null;
                Duration d = null;
                switch (frequ){
                    case 1:
                        f = Duration.MINS;
                        break;
                    case 2:
                        f = Duration.HOURS;
                        break;
                    case 3:
                        f = Duration.DAYS;
                        break;
                    default:

                        return null;
                }
                boolean n = check.isSelected();
                String time = durText.getText();

                int timeDur = Integer.parseInt(time);

                switch (dur){
                    case 1:
                        d = Duration.WEEKS;
                        break;
                    case 2:
                        d = Duration.MONTHS;
                        break;
                    case 3:
                        d = Duration.YEARS;
                        break;
                    default:
                        return null;
                }
                Prescription p = new Prescription(m, x, f, n, timeDur, d);

                return p;
            }
        }
        catch(NumberFormatException e){

            return null;
        }
    }

    /**
     * when pressed changes the frame from editable to uneditable.
     */
    public void editButton(){
            edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean x = false;
                if(edit.getText().equals("Edit Bookings")) {
                    x = true;
                    edit.setText("Done");
                }
                else{
                    x = false;
                    edit.setText("Edit Bookings");
                }
                    dur1_list.setEnabled(x);
                    dur2_list.setEnabled(x);
                    dur3_list.setEnabled(x);
                    dur4_list.setEnabled(x);
                    dur5_list.setEnabled(x);
                    dur6_list.setEnabled(x);
                    dur7_list.setEnabled(x);
                    dur8_list.setEnabled(x);
                    dur9_list.setEnabled(x);

                    dur1_text.setEditable(x);
                    dur2_text.setEditable(x);
                    dur3_text.setEditable(x);
                    dur4_text.setEditable(x);
                    dur5_text.setEditable(x);
                    dur6_text.setEditable(x);
                    dur7_text.setEditable(x);
                    dur8_text.setEditable(x);
                    dur9_text.setEditable(x);

                    freq1_text.setEditable(x);
                    freq2_text.setEditable(x);
                    freq3_text.setEditable(x);
                    freq4_text.setEditable(x);
                    freq5_text.setEditable(x);
                    freq6_text.setEditable(x);
                    freq7_text.setEditable(x);
                    freq8_text.setEditable(x);
                    freq9_text.setEditable(x);

                    freq1_list.setEnabled(x);
                    freq2_list.setEnabled(x);
                    freq3_list.setEnabled(x);
                    freq4_list.setEnabled(x);
                    freq5_list.setEnabled(x);
                    freq6_list.setEnabled(x);
                    freq7_list.setEnabled(x);
                    freq8_list.setEnabled(x);
                    freq9_list.setEnabled(x);

                    med1_text.setEditable(x);
                    med2_text.setEditable(x);
                    med3_text.setEditable(x);
                    med4_text.setEditable(x);
                    med5_text.setEditable(x);
                    med6_text.setEditable(x);
                    med7_text.setEditable(x);
                    med8_text.setEditable(x);
                    med9_text.setEditable(x);

                    add_details.setEditable(x);

                    check1.setEnabled(x);
                    check2.setEnabled(x);
                    check3.setEnabled(x);
                    check4.setEnabled(x);
                    check5.setEnabled(x);
                    check6.setEnabled(x);
                    check7.setEnabled(x);
                    check8.setEnabled(x);
                    check9.setEnabled(x);
                    send.setVisible(true);
                    send.setEnabled(true);

                    //when pressed, all changes will be saved and the doctor will be notified of the changes,
                    //And will be taken back to the welcomeScreen.
                    send.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            // Doctor receives confirmation message via email.
                            String messageToDoctor = "Booking details for patient " + booking.getPatientSecondName() + " " + booking.getPatientFirstName()
                                    + " has been updated.";
                            // Patient receives confirmation message via email (For illustration purposes).
                            String messageToPatient = "The details for your booking on the " + booking.getFormattedDate() + ", " + booking.getFormattedTime()
                                    + " has been updated.";
                            String subjectPatient = "Booking details updated";

                            // Subject for doctor message
                            String subjectDoctor = "Booking details updated";

                            ArrayList<Prescription> prescriptions = new ArrayList<>();
                            prescriptions.add(getPrescriptionsFromFields(med1_text, freq1_text, freq1_list, dur1_text, dur1_list, check1));
                            prescriptions.add(getPrescriptionsFromFields(med2_text, freq2_text, freq2_list, dur2_text, dur2_list, check2));
                            prescriptions.add(getPrescriptionsFromFields(med3_text, freq3_text, freq3_list, dur3_text, dur3_list, check3));
                            prescriptions.add(getPrescriptionsFromFields(med4_text, freq4_text, freq4_list, dur4_text, dur4_list, check4));
                            prescriptions.add(getPrescriptionsFromFields(med5_text, freq5_text, freq5_list, dur5_text, dur5_list, check5));
                            prescriptions.add(getPrescriptionsFromFields(med6_text, freq6_text, freq6_list, dur6_text, dur6_list, check6));
                            prescriptions.add(getPrescriptionsFromFields(med7_text, freq7_text, freq7_list, dur7_text, dur7_list, check7));
                            prescriptions.add(getPrescriptionsFromFields(med8_text, freq8_text, freq8_list, dur8_text, dur8_list, check8));
                            prescriptions.add(getPrescriptionsFromFields(med9_text, freq9_text, freq9_list, dur9_text, dur9_list, check9));

                            for (int counter = 0; counter < prescriptions.size(); counter++){
                                if (prescriptions.get(counter) == null){
                                    prescriptions.remove(counter);
                                }
                            }
                            dbConnections.addPrescription(prescriptions, booking.getBookingID());
                            // Additional info passed into the booking details
                            String addInfo = add_details.getText();
                            int bookingID = booking.getBookingID();

                            dbConnections.addDetailsIntoBooking(bookingID, addInfo);
                            dbConnections.sendMessage(dbConnections.getEmail(), booking.getDoctorEmail(), subjectDoctor, messageToDoctor);
                            //dbConnections.sendMessage(booking.getPatientEmail(),  booking.getDoctorEmail(), messageToPatient);
                            // Show pop up
                            JOptionPane.showMessageDialog(new JFrame(), "Visit details has been edited and updated");
                            WelcomeScreen WS = new WelcomeScreen(dbConnections);
                            WS.setVisible(true);
                            dispose();
                        }
                    });

                    }
                });
            }


    /**
     * When pressed it will take the user back to viewBooking.
     */
    public void Done() {
        finished.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBooking VB = new ViewBooking(dbConnections, booking);
                VB.setVisible(true);
                dispose();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}

/**
 *  EnterDetails class is the JFrame the allows the user to enter details of the visit
 *  for the specific patient.
 *  After all details have been entered the the user will the press the confirm button closing the frame and
 *  confirming the changes.
 *
 *  Authors : @ya217 Yacoub Alkaradsheh(Added functionality and refactored), @tp379 Tejaswini Parmessur (designer of frame. Added the components)
 *            @ghwv2 Gjyri Vegheim(Added functionality), @lth20 Luke Hadley (Added functionality).
 */
package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EnterDetails extends JFrame implements ActionListener{
    private Container con = getContentPane();
    private JPanel enterPanel = new JPanel();
    private JLabel enterTitle = new JLabel("ENTER BOOKING DETAILS");
    private JLabel doctor_label = new JLabel("Doctor : ");
    private JTextField date_text ;
    private JTextField time_text ;
    private JLabel addDetails_label = new JLabel("Additional Details ");
    private JLabel line_label = new JLabel("PRESCRIPTION TABLE");
    private JLabel underline_label = new JLabel("");
    private JLabel freq_label = new JLabel("Frequency ");
    private Booking booking;

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
    private JTextField patient_text ;
    private JTextArea add_details = new JTextArea(5,20);

    // Text fields for Medications
    private JLabel med_label = new JLabel("Medication(s) ");
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

    // Confirm and send button
    private JButton send = new JButton("Confirm and Send");

    private Database dbConnections;

    /**
     * Constructor for enter visit details frame.
     * @param dbConnections
     * @param booking
     */
    EnterDetails(Database dbConnections, Booking booking){
        this.dbConnections = dbConnections;
        this.booking = booking;

        // Doctor, Patient, Date and Time textfields are connected to Booking class to get data.
        doctor_text = new JTextField(booking.getDoctorEmail());
        patient_text = new JTextField(booking.getPatientSecondName() + " " + booking.getPatientFirstName());

        patient_text.setEditable(false);
        doctor_text.setEditable(false);

        time_text = new JTextField(booking.getFormattedTime());
        date_text = new JTextField(booking.getFormattedDate());

        time_text.setEditable(false);
        date_text.setEditable(false);

        setSize(600,600);
        setTitle("Enter Visit Details");
        setLayout(null);
        setResizable(false);

        comp();
        locationSize();
        sendButton();
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
        //Medications: label and text fields.
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

        // Duration

        dur1_list.setBounds(360, 275, 110, 25);

        dur2_list.setBounds(360, 300, 110, 25);

        dur3_list.setBounds(360, 325, 110, 25);

        dur4_list.setBounds(360, 350, 110, 25);

        dur5_list.setBounds(360, 375, 110, 25);

        dur6_list.setBounds(360, 400, 110, 25);

        dur7_list.setBounds(360, 425, 110, 25);

        dur8_list.setBounds(360, 450, 110, 25);

        dur9_list.setBounds(360, 475, 110, 25);


        // Confirm and send button

        send.setBounds(190, 520, 150, 35);



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
    public Prescription prescription(JTextField descriptionField, JTextField freqText, JComboBox freList, JTextField durText, JComboBox durList, JCheckBox check){
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
                       System.out.println("switch2");
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
     * Send button sends messages to doctor and patient
     * Also updates booking so that it now contains the additional details
     */
    public void sendButton() {
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Doctor receives confirmation message via email.
                String messageToDoctor = "Booking details for patient " + booking.getPatientSecondName() + " " + booking.getPatientFirstName()
                        + "has been updated.";
                // Patient receives confirmation message via email (For illustration purposes).
                String messageToPatient = "The details for your booking on the " + booking.getFormattedDate() + ", " + booking.getFormattedTime()
                        + " has been updated.";
                String subjectPatient = "Booking details updated";

                // Subject for doctor message
                String subjectDoctor = "Booking details updated";

                ArrayList<Prescription> prescriptions = new ArrayList<>();
                prescriptions.add(prescription(med1_text, freq1_text, freq1_list, dur1_text, dur1_list, check1));
                prescriptions.add(prescription(med2_text, freq2_text, freq2_list, dur2_text, dur2_list, check2));
                prescriptions.add(prescription(med3_text, freq3_text, freq3_list, dur3_text, dur3_list, check3));
                prescriptions.add(prescription(med4_text, freq4_text, freq4_list, dur4_text, dur4_list, check4));
                prescriptions.add(prescription(med5_text, freq5_text, freq5_list, dur5_text, dur5_list, check5));
                prescriptions.add(prescription(med6_text, freq6_text, freq6_list, dur6_text, dur6_list, check6));
                prescriptions.add(prescription(med7_text, freq7_text, freq7_list, dur7_text, dur7_list, check7));
                prescriptions.add(prescription(med8_text, freq8_text, freq8_list, dur8_text, dur8_list, check8));
                prescriptions.add(prescription(med9_text, freq9_text, freq9_list, dur9_text, dur9_list, check9));
                for (int counter = 0; counter < prescriptions.size(); counter++){
                    if (prescriptions.get(counter) == null){
                        prescriptions.remove(counter);
                    }
                }
                dbConnections.addPrescription(prescriptions, booking.getBookingID());

                // Additional info passed into the booking details
                String addInfo = add_details.getText();
                int bookingID = booking.getBookingID();
                System.out.println(bookingID);
                dbConnections.addDetailsIntoBooking(bookingID, addInfo);
                dbConnections.sendMessage(dbConnections.getEmail(), booking.getDoctorEmail(), subjectDoctor, messageToDoctor);
                //dbConnections.sendMessage(booking.getPatientEmail(),  booking.getDoctorEmail(), messageToPatient);
                // Show pop up
                JOptionPane.showMessageDialog(new JFrame(), "Visit details has been updated");
                dispose();
                WelcomeScreen WS = new WelcomeScreen(dbConnections);
                WS.setVisible(true);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) { }
}

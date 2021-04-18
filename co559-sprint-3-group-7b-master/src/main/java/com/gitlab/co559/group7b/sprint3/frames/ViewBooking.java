/*
 * ViewBooking class
 * Creates new window displaying booking and patient information for the selected booking from WelcomeScreen,
 * this could be a booking in the future, past or present.
 *
 * Author: @ghwv2 Gjyri Vegheim & @ya217 Yacoub Alkaradsheh
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.objects.Booking;
import com.gitlab.co559.group7b.sprint3.database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBooking extends JFrame implements ActionListener {
    private Container cons = getContentPane();
    private Booking patient;
    private Database dbConnection;
    private JFrame status;

    private JPanel infoPanel = new JPanel();
    private JLabel infoTitle = new JLabel("Details Of Patient:");
    private JLabel options = new JLabel("Booking Options:");
    private JLabel foreName = new JLabel();
    private JLabel surName = new JLabel();
    private JLabel patientEmail = new JLabel();
    private JLabel dateOfBooking = new JLabel();
    private JLabel comment = new JLabel();

    private JButton EnterD = new JButton("Enter Visit Details");
    private JButton viewEdit = new JButton("View/Edit Details");
    private JButton back = new JButton("<--Back");

    /**
     * Constructor
     * @param dbConnection - Database connection
     * @param patient - Booking object
     */
    ViewBooking(Database dbConnection, Booking patient) {
        this.patient = patient;
        this.dbConnection = dbConnection;
        createJFrame();
        comp();
        LocationSize();
        ED();
        editView();
        checkBooking();
        backButton();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { }

    /**
     * Creates the JFrame with size, title and close operation.
     */
    public void createJFrame() {
        setTitle("View patient");
        setSize(450,400);
        cons.setLayout(null);
        setResizable(false);
        displayInformation();
        setVisible(true);
    }

    /*
      Add all elements to frame in correct order
    */
    public void comp() {
        // everything listed first will be shown above the stuff underneath
        cons.add(infoTitle);
        cons.add(foreName);
        cons.add(surName);
        cons.add(EnterD);
        cons.add(patientEmail);
        cons.add(dateOfBooking);
        cons.add(comment);
        cons.add(infoPanel);
        cons.add(viewEdit);
        cons.add(back);
        cons.add(options);
    }

    /**
     * Sets placement, size and other properties of elements
     */
    public void LocationSize() {
        infoTitle.setBounds(20,30,300,30);
        infoTitle.setFont(new Font(null, Font.PLAIN, 15));

        infoPanel.setSize(400, 130);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.setBounds(15,80,401,130);

        EnterD.setBounds( 15, 250, 150, 30);
        viewEdit.setBounds(160, 250, 150, 30);
        back.setBounds(300, 25,100,30);
        options.setBounds(20, 220, 300, 30);
        options.setFont(new Font(null, Font.PLAIN, 16));

        foreName.setBounds(20,80, 300, 60);
        foreName.setFont(new Font(null, Font.PLAIN, 15));
        surName.setBounds(20, 100, 300, 60);
        surName.setFont(new Font(null, Font.PLAIN, 15));
        patientEmail.setBounds(20, 120, 300, 60);
        patientEmail.setFont(new Font(null, Font.PLAIN, 15));
        dateOfBooking.setBounds(20, 140, 300, 60);
        dateOfBooking.setFont(new Font(null, Font.PLAIN, 15));
        comment.setBounds(20, 160, 400, 60);
        comment.setFont(new Font(null, Font.PLAIN, 15));


    }

    // Display all information about specific booking
    public void displayInformation() {
        surName.setText("Surname: " + patient.getPatientSecondName());
        foreName.setText("Forename: " + patient.getPatientFirstName());
        patientEmail.setText("Email: " + patient.getPatientEmail());
        dateOfBooking.setText("Date of Booking: " + patient.getDate());
        comment.setText("Address: " + patient.getPatient(dbConnection).getAddress());
    }

    /**
     * Button for enter visit details.
     * When pressed takes you to new frame for enter visit details.
     */
    public void ED () {
        EnterD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                EnterDetails ED = new EnterDetails(dbConnection, patient);
                ED.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * Button for view/edit booking details
     * When pressed take to frame of the details of the visit.
     */
    public void editView(){
        viewEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                EditViewBookings EVD = new EditViewBookings(dbConnection, patient);
                EVD.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * checks if the booking has been completed or not
     * and corresponds the usage of buttons to its status.
     */
    public void checkBooking(){
            switch(patient.getStatus()){
                case CANCELLED:
                    JOptionPane.showMessageDialog(status,"Booking Cancelled");
                    EnterD.setEnabled(false);
                    viewEdit.setEnabled(false);
                    break;
                case PENDING:
                    JOptionPane.showMessageDialog(status,"Booking Pending");
                    EnterD.setEnabled(false);
                    viewEdit.setEnabled(false);
                    break;
                case IN_PROGRESS:
                    JOptionPane.showMessageDialog(status,"Booking In Progress");
                    EnterD.setEnabled(true);
                    viewEdit.setEnabled(false);
                    break;
                case COMPLETED:
                    JOptionPane.showMessageDialog(status,"Booking Completed");
                    if(patient.getAdditionalDetails() != null){
                        EnterD.setEnabled(false);
                        viewEdit.setEnabled(true);
                    }
                    else {
                        EnterD.setEnabled(true);
                        viewEdit.setEnabled(false);
                    }
                    break;

            }
    }

    /**
     * takes user back to welcome screen when button is pressed.
     */
    public void backButton(){
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                WelcomeScreen WS = new WelcomeScreen(dbConnection);
                WS.setVisible(true);
                dispose();
            }
        });
    }
}
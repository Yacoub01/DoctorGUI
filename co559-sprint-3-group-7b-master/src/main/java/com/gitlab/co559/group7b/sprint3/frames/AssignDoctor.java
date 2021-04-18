/*
 * Class for JFrame window to assign new doctor to selected patient.
 *  Called from ViewAll object frame.
 *
 * Author: @ghwv2 Gjyri Vegheim
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Doctor;
import com.gitlab.co559.group7b.sprint3.objects.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignDoctor extends JFrame implements ActionListener {
    private Database dbConnection;
    private Container cons = getContentPane();
    private JLabel title = new JLabel("Assign new doctor:");
    private JButton assign = new JButton("Assign");
    private JLabel currentDoc = new JLabel();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> doctorList = new JList<>();
    private JPanel list = new JPanel();
    private ArrayList<Doctor> doctorsArr;
    private Patient patient;

    /**
     * Constructor
     * @param dbConnection - Database connection
     * @param patient - Patient object
     */
    public AssignDoctor(Database dbConnection, Patient patient) {
        this.dbConnection = dbConnection;
        doctorsArr = dbConnection.getDoctors();
        this.patient = patient;
        createJFrame();
        comp();
        locationSize();
        displayInformation();
        assignButton();
    }

    /**
     * Creates the JFrame with size, title and layout.
     */
    private void createJFrame() {
        setTitle("Assign Doctor");
        setSize(550,400);
        setResizable(false);
        setLayout(null);
    }

    /**
     *  Display current doctor and a list of all doctors
     */
    private void displayInformation() {
        // Set text of current doctor to the correct one (pass in patient object)
        // If (check hasDoctor())
        if(patient.hasDoctor()) {
            currentDoc.setText("Current Doctor: " + patient.getDoctorEmail());
        } else {
            currentDoc.setText("No current doctor assigned");
        }
        cons.add(currentDoc);

        // Display Doctor list
        // Surname, forename
        for(int i = 0; i < doctorsArr.size(); i++) {
            Doctor x = doctorsArr.get(i);
            listModel.addElement((i+1) + ". " + x.getLastName() + ", " + x.getFirstName());
        }
        // Add to frame
        doctorList.setModel(listModel);
    }

    // Add to window
    private void comp() {
        cons.setLayout(null);
        // Order is important, everything listed first will be shown above the stuff underneath
        cons.add(title);
        cons.add(assign);
        list.add(doctorList);
        cons.add(list);
    }

    // Set properties
    private void locationSize() {
        title.setBounds(20,60,330,35);
        title.setFont(new Font(null, Font.PLAIN, 30));

        currentDoc.setBounds(20,30,330,20);
        currentDoc.setFont(new Font(null, Font.PLAIN, 15));

        list.setBounds(-30,110,340,230);
        doctorList.setBounds(-30,0, 400, 300);
        doctorList.setFont(new Font(null, Font.PLAIN, 15));

        assign.setBounds(380, 310, 90, 30);
    }

    /*
      Button listener on Assign Button.
      Assigns patient to selected doctor
     */
    public void assignButton() {
        assign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Check whether or not patient has been selected
                if(doctorList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select a doctor before assigning");
                } else {
                    // Get selected doctor
                    String selected = doctorList.getSelectedValue();
                    String[] sel = selected.split(". ");
                    String f = sel[0];
                    int y = Integer.parseInt(f);

                    Doctor doc = doctorsArr.get(y-1);
                    // Assign doctor email to patient email
                    dbConnection.addAssignment(patient.getEmail(), doc.getEmail());

                    sendConfirmation(doc);
                    // Show pop up
                    JOptionPane.showMessageDialog(new JFrame(), "Doctor assigned");
                    dispose();
                }
            }
        });
    }

    /*
     Sends confirmation message to the doctor (and patient if the system had the possibility
     for a patient to log in)
     */
    public void sendConfirmation(Doctor doctor) {
        // Message sent to doctor
        String messageToDoctor = "You have been assigned a new patient. Name: " + patient.getLastName()
                                + ", " + patient.getFirstName();
        // No way to receive messages for patients so this is just for illustration
        String messageToPatient = "You have been assigned a new doctor. Name: " + doctor.getLastName()
                                + ", " + doctor.getFirstName();
        String subjectPatient = "New assigned doctor";

        // Subject for doctor message
        String subjectDoctor = "New assigned patient";

        // Send message to doctor
        dbConnection.sendMessage(dbConnection.getEmail(), doctor.getEmail(), subjectDoctor, messageToDoctor);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}

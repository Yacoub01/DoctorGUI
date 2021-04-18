/*
  View Own is the JFrame that displays the patients of the doctor currently logged in.

  Author: @ghwv2 Gjyri Vegheim
 */
package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewOwn extends JFrame implements ActionListener {

    private Database dbConnection;
    private Container con = getContentPane();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> patientList = new JList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private JButton goBackButton = new JButton("<- Back" );
    private JLabel title = new JLabel("Your Patients:");
    private JPanel VPanel = new JPanel();
    private ArrayList<Patient> db = new ArrayList<>();

    /**
     * Constructor for view all patience
     * @param dbConnection - Database connection
     */
    public ViewOwn(Database dbConnection){
        this.dbConnection = dbConnection;
        setSize(1000,700);
        setTitle("View Own Patients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(true);
        patientList.setFixedCellHeight(50);
        VPanel.setLayout(new BorderLayout( ));
        setSizeLocation();
        comp();
        goBack();
        viewList();
    }

    /**
     * Adds components to the frame
     * Order of the way it is added matters.
     */
    private void comp() {
        con.add(goBackButton);
        con.add(title);
        con.add(VPanel);
        VPanel.add(new JScrollPane(patientList));

    }

    /**
     * Sets the location of size of the components.
     */
    private void setSizeLocation() {
        title.setBounds(30,30,375,30);
        title.setFont(new Font(null, Font.PLAIN, 20));

        goBackButton.setBounds(750,30,90,30);
        VPanel.setBounds(30,80,700, 401);
        VPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Go back button
     * When pressed sends the user back to the welcome screen.
     */
    public void goBack() {
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                WelcomeScreen WS = new WelcomeScreen(dbConnection);
                WS.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * Outputs the list of all the details of the patient
     * in the JPanel (VPanel).
     */
    public void viewList() {
        db = dbConnection.getPatientsForDoctor(dbConnection.getEmail());
        for(int i = 0; i < db.size(); i++) {
            Patient x = db.get(i);
            listModel.addElement((i + 1) + ". " + x.getFirstName() + ", " + x.getLastName() + ", "
                    + "PhoneNumber: " + x.getPhoneNumber() + ", "
                    + "Address: " + x.getAddress());
        }
        patientList.setModel(listModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) { }
}

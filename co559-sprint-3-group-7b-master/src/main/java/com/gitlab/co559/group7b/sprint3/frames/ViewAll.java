/**
 * ViewAll class
 * Creates a frame the shows all the patients in the database.
 *
 * Author: @ghwv2 Gjyri Vegheim, @ya217 Yacoub Alkaradsheh & @jrs63 Joshua Sylvester
 */
package com.gitlab.co559.group7b.sprint3.frames;

import java.awt.event.ActionEvent;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewAll extends JFrame implements ActionListener {
    private Database dbConnection;
    Container con = getContentPane();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> VLIST = new JList<>();
    ArrayList<Patient> VA = new ArrayList<>();
    JLabel ViewBookingT = new JLabel("All Patients Listings:");
    JButton GoBackButton = new JButton("<- Back" );
    JPanel VPanel = new JPanel();
    JButton AssDoctor = new JButton("assign Doc");
    JButton refresh = new JButton("Refresh");


    /**
     * Constructor for view all patience
     * @param dbConnection
     */
    public ViewAll(Database dbConnection){
        this.dbConnection = dbConnection;
        setSize(1000,700);
        setTitle("View All Patients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(true);
        VPanel.setLayout(new BorderLayout( ));
        VLIST.setFixedCellHeight(50);
        setSizeLocation();
        comp();
        GoBack();
        viewAllList();
        refreshPage();
    }

    /**
     * Adds components to the frame
     * Order of the way it is added matters.
     */
    public void comp(){
        con.add(GoBackButton);
        con.add(refresh);
        con.add(ViewBookingT);
        con.add(AssDoctor);
        con.add(VPanel);
        VPanel.add(new JScrollPane(VLIST));

    }

    /**
     * Sets the location of size of the components.
     */
    public void setSizeLocation(){
        ViewBookingT.setBounds(30,30,375,30);
        ViewBookingT.setFont(new Font(null, Font.PLAIN, 20));

        GoBackButton.setBounds(750,30,90,30);
        refresh.setBounds(750, 70, 90, 30);
        AssDoctor.setBounds(450, 500, 120, 30);
        VPanel.setBounds(30,80,700, 401);
        VPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    /**
     * Go back button
     * When pressed sends the user back to the welcome screen.
     */
    public void GoBack() {

        GoBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                WelcomeScreen WS= new WelcomeScreen(dbConnection);
                WS.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * refreshes the frame after the button has been pressed show changes.
     */
    public void refreshPage() {
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                listModel.clear();
                viewAllList();
            }
        });
    }

    /**
     * Outputs the list of all the details of the patient
     * in the JPanel (VPanel).
     */
    public void viewAllList() {
        VA = dbConnection.getPatients();
        for(int i = 0; i < VA.size(); i++) {
            Patient x = VA.get(i);
            if(x.hasDoctor()) {
                listModel.addElement((i + 1) + ". " + x.getFirstName() + ", " + x.getLastName() + ", "
                        + "PhoneNumber: " + x.getPhoneNumber() + ", "
                        + "Address: " + x.getAddress() + ", "
                        + "Doctor Email: " + x.getDoctorEmail());

            }
            else{
                listModel.addElement((i + 1) + ". " + x.getFirstName() + ", " + x.getLastName() + ","
                        + "PhoneNumber: " + x.getPhoneNumber() + ", "
                        + "Address: " + x.getAddress());

            }
        }
        VLIST.setModel(listModel);
        // takes user to new frame for assign new doctor.
        AssDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(VLIST.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select a patient");
                } else {
                    String selected = VLIST.getSelectedValue();
                    String[] sel = selected.split(". ");
                    String f = sel[0];
                    int y = Integer.parseInt(f);

                    Patient patient = VA.get(y - 1);
                    AssignDoctor AD = new AssignDoctor(dbConnection, patient);
                    AD.setVisible(true);
                }
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) { }

}




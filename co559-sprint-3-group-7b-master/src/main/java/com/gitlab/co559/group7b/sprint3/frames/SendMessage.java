/**
 * SendMessage is the JFrame that lets the user send a message to an admin, receptionists or other doctors.
 *
 * Author: @ghwv2 Gjyri Vegheim
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Person;
import com.gitlab.co559.group7b.sprint3.objects.UserType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SendMessage extends JFrame implements ActionListener {
    private Database dbConnections;
    private Container cons = getContentPane();
    private JLabel sendTitle = new JLabel("Send message");
    private JLabel subjectTitle = new JLabel("Subject: ");
    private JButton sendButton = new JButton("Send");
    private JButton backButton = new JButton("<- Back");
    private JComboBox<Person> userType = new JComboBox(UserType.values());
    private JComboBox<String> emails = new JComboBox<>();
    private JTextArea messageTextArea = new JTextArea(5,20);
    private JTextArea subjectTextArea = new JTextArea(1,5);

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Constructor
     * @param dbConnections - Database connection
     */
    public SendMessage (Database dbConnections) {
        this.dbConnections = dbConnections;
        locationSize();
        comp();
        setTitle("Message");
        setSize(550,500);
        setResizable(false);
        setLayout(null);
        send();
        twoComboBox();
        back();
    }

    /**
     * Sets placement, size and other properties of elements
     */
    private void locationSize() {
        sendTitle.setBounds(30,30,200,30);
        sendTitle.setFont(new Font(null, Font.PLAIN, 25));

        sendButton.setBounds(400, 410, 90, 30);

        messageTextArea.setBounds(30, 220, 480, 130);
        messageTextArea.setBorder(new LineBorder(Color.gray));

        subjectTextArea.setBounds(80, 180, 200, 20);
        subjectTextArea.setBorder(new LineBorder(Color.gray));

        subjectTitle.setBounds(30, 180, 100, 20);

        userType.setBounds(30, 70, 185, 22);
        // Prevent action events from being fired when the up/down arrow keys are used
        userType.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        emails.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        emails.setBounds(30, 95, 185, 22);
        emails.setVisible(false);

        backButton.setBounds(400,30,90,30);
    }

    /*
      Add all elements to frame in correct order
    */
    private void comp() {
        cons.add(userType);
        cons.add(emails);
        cons.add(sendTitle);
        cons.add(subjectTitle);
        cons.add(sendButton);
        cons.add(backButton);
        cons.add(subjectTextArea);
        cons.add(messageTextArea);
    }

    /*
      Sends message with all given details.
     */
    public void send() {
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Get subject and message
                String message = messageTextArea.getText();
                String subject = subjectTextArea.getText();

                if(emails.getSelectedItem() != null) { // Check that there is a recipient selected
                    if(!subject.equals("")) { // Check that there is a subject to the message
                        // Get recipient
                        String email = (String)emails.getSelectedItem();

                        // Send message
                        dbConnections.sendMessage(dbConnections.getEmail(), email, subject, message);

                        JOptionPane.showMessageDialog(new JFrame(), "Message sent");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Please write a subject");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select recipient");
                }
            }
        });
    }

    /**
     * List people from selected item in first combobox
     */
    public void twoComboBox() {
        userType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emails.removeAllItems();
                emails.setVisible(true);
                // Get list of people
                ArrayList<Person> list = dbConnections.getUsers((UserType) Objects.requireNonNull(userType.getSelectedItem()));
                for(Person p : list) {
                    String email = p.getEmail();
                    emails.addItem(email);
                }
            }
        });
    }

    /**
     * Go back to Welcome screen button
     */
    public void back() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }
}
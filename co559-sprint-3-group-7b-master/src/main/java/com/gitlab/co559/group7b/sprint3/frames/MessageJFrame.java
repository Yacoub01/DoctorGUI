/**
 * MessageJFrame class is a frame the allows the user to read and view the select message.
 * The user will see the message they selected from the welcome screen.
 */
package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Message frame
 * Displays the message selected at welcome screen with sender, receiver, subject and message
 */
public class MessageJFrame extends JFrame implements ActionListener {
    Database dbConnections;
    private Container cons = getContentPane();
    Message msg;
    JButton done  = new JButton("Done");
    JLabel from = new JLabel();
    JLabel to = new JLabel();
    JLabel subject = new JLabel();
    JLabel date = new JLabel();
    JTextArea message = new JTextArea();
    JPanel msgPanel = new JPanel(new BorderLayout());


    /**
     * Constructor for Message Jframe
     * @param dbconnections
     * @param msg
     */
    public MessageJFrame (Database dbconnections, Message msg){
        this.dbConnections = dbconnections;
        this.msg = msg;
        locationSize();
        comp();
        setTitle("Message");
        setSize(550,400);
        setResizable(false);
        setLayout(null);
        showMessageInfo();
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setEditable(false);
        doneButton();

    }

    /**
     * sets the location and size of the components in JFrame
     */
    private void locationSize() {
      done.setBounds(375, 10, 70,40);
      to.setBounds (50, 5, 500, 100);
      from.setBounds (50, 25, 500, 100);
      date.setBounds(50, 45, 500, 100);
      subject.setBounds(50, 65, 500, 100);
      msgPanel.setBounds(50,100, 500, 200);
      msgPanel.setBorder( BorderFactory.createEmptyBorder(50, 0, 60, 170));
    }

    /**
     * adds the components into the JFrame
     */
    private void comp() {
        cons.add(from);
        cons.add(done);
        cons.add(subject);
        cons.add(date);
        cons.add(to);
        cons.add(msgPanel, BorderLayout.WEST);
        msgPanel.add(message);
    }

    /**
     * Shows all the messages and set texts of all
     * the components in JFrame
     */
    private void showMessageInfo(){
        to.setText("To: " + msg.getRecipient());
        message.setText(msg.getMessage());
        from.setText("From: " + msg.getSender());
        subject.setText("Subject: " + msg.getSubject());
        date.setText("Date Sent: " + msg.getFormattedDateTime());
    }


    /**
     * Closes the message box.
     */
    public void doneButton() {
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { }
}

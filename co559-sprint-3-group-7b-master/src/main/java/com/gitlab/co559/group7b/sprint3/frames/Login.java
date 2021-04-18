/**
 * Login class is a frame that allows the user to input their credentials.
 * If inputed correctly, they will be sent to the welcome screen,
 * if not they will have message sent to them asking to try again.
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JFrame frame = new JFrame(); // creates frame for error message if password or username is incorrect.
    private Database dbConnection; // connects database to JFrame.
    Container con = getContentPane();

    // Fields for JFrame.
    private JTextField TextArea = new JTextField();
    private JPasswordField pass1 = new JPasswordField();
    private JLabel Password = new JLabel("Password:");
    private JLabel UserNamerL = new JLabel("LogIn:");
    private JButton Login = new JButton ("LogIn");
    private JCheckBox PassShow = new JCheckBox("Show Password");
    private JButton reset = new JButton ("Reset");


    /**
     * Constructor for Login JFrame
     * Adds all of the components to the JFrame.
     * @param dbConnection
     */
    public Login(Database dbConnection){
        this.dbConnection = dbConnection;

        setSize(600,600);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLayout(null);
        setResizable(false);

        setLocationSize();

        con.add(UserNamerL);

        con.add(Password);
        pass1.setEchoChar('*');
        con.add(TextArea);

        con.add(pass1);

        con.add(Login);

        con.add(reset);

        con.add(PassShow);
        passwordUserCheck();
        resetButton();
        showPassword();


    }

    /**
     * Sets the size and location of the JFrame, and features within the JFrame.
     */
    public void setLocationSize(){
        UserNamerL.setBounds(50,120,100,30);

        Password.setBounds(50,185,100,30);

        pass1.setBounds(150,185,150,30);

        Login.setBounds(45,270,100,30);

        reset.setBounds(250,270,100,30);

        PassShow.setBounds(150, 220, 200, 30);

        TextArea.setBounds(150,120,150,30);

    }

    /**
     * Checks if username or password are incorrect after pressing login button
     * If they are correct moves you on to the next JFrame
     * If not sends an message to user.
     */
    public void passwordUserCheck() {
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usname = TextArea.getText();
                String Pass = pass1.getText();
                if (dbConnection.validateCredentials(usname, Pass)) { // checks if username or password is correct from method
                    WelcomeScreen WS = new WelcomeScreen(dbConnection);
                    WS.setVisible(true); // Sets welcome screen frame visible for user if username and password are correct.
                    setVisible(false); // Sets Login JFrame invisible when username and password are correct.
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Wrong password or username");
                }
            }
        });
    }

    /**
     * Resets the text files for username and password
     * When button is pressed
     */
    public void resetButton (){
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TextArea.setText("");
                pass1.setText("");
            }
        });
    }

    /**
     * When check box is clicked allows the user to see the password uncencored.
     * When unchecked the password is re-cencored.
     */
    public void showPassword(){
        PassShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(PassShow.isSelected()) {
                    pass1.setEchoChar((char) 0);
                }else{
                    pass1.setEchoChar('*');
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
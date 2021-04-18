/*
  WelcomeScreen class is the JFrame window that shows after login.
  It is personal to each doctor, since it displays new messages and personal bookings.

  The main features are:
    - Find personal bookings by searching by month and year.
    - View own patients button
    - View all patients button
    - Write new message button
    - View messages
    - Delete messages that you have seen
    - Log out

  Author: @ghwv2 Gjyri Vegheim, @ya217 Yacoub Alkaradsheh, @lth20 Luke Hadley & @jrs63 Joshua Sylvester
 */

package com.gitlab.co559.group7b.sprint3.frames;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.objects.Booking;
import com.gitlab.co559.group7b.sprint3.objects.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

class WelcomeScreen extends JFrame implements ActionListener {
    private Container cons = getContentPane();
    private JLabel welcomeTitle = new JLabel("Welcome!");
    private JButton logOutButton = new JButton("Log out");

    // Fields for bookings part of the frame
    private JLabel bookingTitle = new JLabel("Bookings");
    private JLabel monthTitle = new JLabel("Month:");
    private JLabel yearTitle = new JLabel("Year:");
    private JButton getSpinnerNumb = new JButton("Submit");
    private JButton viewPatient = new JButton("View");
    private JPanel bookingFrame = new JPanel();
    private JPanel list = new JPanel();
    private JSpinner month = new JSpinner(new SpinnerDateModel());
    private JSpinner year = new JSpinner(new SpinnerDateModel());
    private JSpinner.DateEditor monthEditor = new JSpinner.DateEditor(month, "MM");
    private JSpinner.DateEditor yearEditor = new JSpinner.DateEditor(year, "yyyy");
    private JList<String> bookingList = new JList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Database dbConnection;

    // Fields for messages part of the frame
    private JPanel messageFrame = new JPanel();
    private JLabel messageTitle = new JLabel("Messages");
    private DefaultListModel<String> MessageList = new DefaultListModel<>();
    private ArrayList<Message> MessagesL = new ArrayList<>();
    private JList<String> ML = new JList<>();
    private JButton viewMessage = new JButton("View");
    private JButton deleteMessage = new JButton ("Delete Message");
    private JButton refreshButton = new JButton("Refresh");
    private JPanel messageTest = new JPanel();
    private JButton sendMessage = new JButton("Send new message");

    // Fields for view patients
    private JButton ViewAllButton = new JButton("View All Patients");
    private JButton viewOwnButton = new JButton("View Own Patients");

    /**
      Constructor calls all methods to create frame and all ActionListeners
      @param dbConnection - Database connection
     */
    public WelcomeScreen(Database dbConnection) {
        this.dbConnection = dbConnection;
        createJFrame();
        comp();
        LocationSize();
        logOut();
        getMonthAndYear();
        ViewAll();
        ViewOwn();
        viewMessages();
        ML.setFixedCellHeight(50);
        delete();
        refreshMessages();
        sendNewMessage();
        viewMessageListen();
    }

    /**
     * Sets placement, size and other properties of elements
     */
    public void LocationSize() {
        welcomeTitle.setBounds(30,30,150,30);
        welcomeTitle.setFont(new Font(null, Font.PLAIN, 30));

        logOutButton.setBounds(750,30,90,30);

        messageFrame.setSize(100, 500);
        messageFrame.setBorder(BorderFactory.createLineBorder(Color.black));
        messageFrame.setBounds(400,140,450,501);

        messageTest.setBounds(450,190,390,391);

        messageTitle.setFont(new Font(null, Font.PLAIN, 25));
        messageTitle.setBounds(420,150,150,30);

        bookingFrame.setSize(100, 500);
        bookingFrame.setBorder(BorderFactory.createLineBorder(Color.black));
        bookingFrame.setBounds(15,140,401,501);

        bookingTitle.setFont(new Font(null, Font.PLAIN, 25));
        bookingTitle.setBounds(35,150,150,30);

        getSpinnerNumb.setBounds(270,200,80,25);
        month.setBounds(80, 200, 50, 25);
        month.setEditor(monthEditor);
        year.setBounds(175, 200, 70, 25);
        year.setEditor(yearEditor);
        monthTitle.setBounds(35, 200, 50, 25);
        yearTitle.setBounds(140, 200, 50, 25);

        list.setBounds(40, 250, 350, 300);
        bookingList.setBounds(15,0, 400, 300);
        bookingList.setFont(new Font(null, Font.PLAIN, 15));

        viewPatient.setBounds(35, 590, 100, 40);
        viewPatient.setVisible(false);

        viewOwnButton.setBounds(30,77,150,40);
        ViewAllButton.setBounds(195, 77, 150, 40);

        ML.setBounds(15,300,200,150);

        viewMessage.setBounds(500, 590, 115, 40 );
        deleteMessage.setBounds(650, 590, 120, 40);
        refreshButton.setBounds(730, 150, 90, 40);
        sendMessage.setBounds(550, 150, 150, 40);
    }

    /**
     * Creates the JFrame for welcome screen with size, title and close operation.
     */
    public void createJFrame() {
        setTitle("Welcome Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLayout(null);
        cons.setLayout(null);
        setVisible(true);
    }

    /*
      Add all elements to frame in correct order
     */
    public void comp() {
        // everything listed first will be shown above the stuff underneath
        cons.add(viewPatient);
        cons.add(welcomeTitle);
        cons.add(messageTitle);
        cons.add(bookingTitle);
        cons.add(yearTitle);
        cons.add(monthTitle);
        messageTest.add(new JScrollPane(ML));
        cons.add(messageTest);
        list.add(bookingList);
        cons.add(list);
        cons.add(month);
        cons.add(year);
        cons.add(ViewAllButton);
        cons.add(viewOwnButton);
        cons.add(refreshButton);
        cons.add(sendMessage);
        cons.add(viewMessage);
        cons.add(deleteMessage);
        cons.add(getSpinnerNumb);
        cons.add(logOutButton);
        cons.add(messageFrame);
        cons.add(bookingFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Dispose of window and open LogIn window when "Log out" is pressed
    public void logOut () {
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Login login = new Login(dbConnection);
                login.setVisible(true);
                dispose();
                dbConnection.addLog("LogOut", "");
                JOptionPane.showMessageDialog(login,"Successfully logged out");
            }
        });
    }

    /**
     * View All patients button,
     * takes you to the view all frame when pressed.
     */
    public void ViewAll() {
       ViewAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ViewAll view = new ViewAll(dbConnection);
                view.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * View Own patients button,
     * takes you to the view own frame when pressed.
     */
    public void ViewOwn() {
        viewOwnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ViewOwn view = new ViewOwn(dbConnection);
                view.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * When choosing date and time, display bookings for the given time.
     * Also displays view button once a booking is selected.
     * Author: ghwv2
     */
    public void getMonthAndYear() {
        getSpinnerNumb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear last submit
                listModel.clear();

                // Get selected month
                Object value = month.getValue();
                Date date = (Date)value;
                SimpleDateFormat format = new SimpleDateFormat("MM");
                String m = format.format(date);
                int MM = Integer.parseInt(m);

                // Get selected year
                Object value2 = year.getValue();
                Date date2 = (Date)value2;
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
                String y = format2.format(date2);
                int YYYY = Integer.parseInt(y);

                // Update list
                bookings = dbConnection.getBookingsByDate(MM, YYYY);

                // View button is only visible when there are bookings on screen
                if(bookings.isEmpty()) { viewPatient.setVisible(false);
                } else { viewPatient.setVisible(true); }

                // Display correct information for each booking in format:
                // Surname, forename, date of booking
                for(int i = 0; i < bookings.size(); i++) {
                    Booking x = bookings.get(i);
                    listModel.addElement((i+1) + ". " + x.getPatientSecondName() + ", " + x.getPatientFirstName()
                            + ". Date: " + x.getDate());
                }

                // Add to frame
                bookingList.setModel(listModel);

                // View button action listener, if clicked get Booking object and create new ViewBooking frame
                viewPatient.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(bookingList.getSelectedValue() == null) {
                            JOptionPane.showMessageDialog(new JFrame(), "Please select a booking");
                        } else {
                            String selected = bookingList.getSelectedValue();
                            String[] sel = selected.split(". ");
                            String f = sel[0];
                            int y = Integer.parseInt(f);

                            Booking book = bookings.get(y-1);

                            ViewBooking VB = new ViewBooking(dbConnection, book);
                            VB.setVisible(true);
                            dispose();
                        }
                    }
                });
            }
        });
    }

    /**
     * Sets all messages in the database visible to user,
     * lists all messages out in welcome screen,
     * and allows user to view the selected message they desire.
     */
    public void viewMessages() {
        MessagesL = dbConnection.getMessagesForUser(dbConnection.getEmail());
        for (int i = 0; i < MessagesL.size(); i++) {
            Message x = MessagesL.get(i);
            MessageList.addElement((i+1) + ". " + x.getSubject()+ " " + "From: " + x.getSender());

        }

        ML.setModel(MessageList);
    }

    /**
     *  View message button listener
     *  Displays MessagesJFrame if clicked
     *  If no message is selected it will display a pop up asking the user to select a message
     */
    public void viewMessageListen() {
        viewMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(ML.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select a message");
                } else {
                    String selected = ML.getSelectedValue();
                    String[] sel = selected.split(". ");
                    String f = sel[0];
                    int y = Integer.parseInt(f);

                    Message msg = MessagesL.get(y-1);
                    MessageJFrame messages = new MessageJFrame(dbConnection, msg);
                    messages.setVisible(true);
                }
            }
        });
    }

    /**
     * Refresh the users' messages.
     */
    public void refreshMessages(){
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    MessageList.clear();
                    viewMessages();
            }
        });
    }

    /**
     * Delete selected message
     */
    public void delete() {
        deleteMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ML.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please select a message");
                } else {
                    String selected = ML.getSelectedValue();
                    String[] sel = selected.split(". ");
                    String f = sel[0];
                    int y = Integer.parseInt(f);

                    Message msg = MessagesL.get(y - 1);
                    dbConnection.deleteMessage(msg);
                    MessageList.clear();
                    viewMessages();
                    JOptionPane.showMessageDialog(new JFrame(), "Message deleted");
                }
            }
        });
    }

    /**
     * Send new message
     */
    public void sendNewMessage() {
        sendMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SendMessage message = new SendMessage(dbConnection);
                message.setVisible(true);
            }
        });
    }
}
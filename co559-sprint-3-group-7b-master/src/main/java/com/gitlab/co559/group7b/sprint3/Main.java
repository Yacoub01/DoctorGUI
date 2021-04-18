/*
  Database Statements Class

  Holds all pre-defined SQL statements used in interacting with the database.

  Author Yacoub Alkaradsheh @ya217
  Author Joshua Sylvester @jrs63
  Author Gjyri Vegheim @ghwv2
  Author Luke Hadley @lth20
  Author Tejaswini Parmessur @tp379
*/

package com.gitlab.co559.group7b.sprint3;

import com.gitlab.co559.group7b.sprint3.database.Database;
import com.gitlab.co559.group7b.sprint3.frames.Login;


public class Main {

    private Database dbConnection;

    // create login frame on start
    Main() {
        dbConnection = new Database();
        Login frame =  new Login(dbConnection);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

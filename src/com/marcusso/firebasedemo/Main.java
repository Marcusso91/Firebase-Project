package com.marcusso.firebasedemo;

import com.marcusso.firebase.util.FirebaseConfig;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.firebase.*;
import javax.swing.*;

public class Main {
        
// SHA1 kódolás, regisztráció jelszó 2X, email,username,telefonszám lekérdezés regisztrációnál 
    public static void main(String[] args) {
        new FirebaseConfig().initFirebase();

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            Menu m = new Menu();
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
}

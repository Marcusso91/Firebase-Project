package com.marcusso.firebasedemo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import com.google.firebase.internal.Nullable;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import javafx.concurrent.Task;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class LoginPanel extends JPanel {

    private Firestore db;

    public LoginPanel() {
        initComponents();
        // Initialize Firebase Cloud FireStore
        this.db = FirestoreClient.getFirestore();
          this.check_box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected

                    password_field.setEchoChar((char) 0);
                    password_field.setForeground(Color.BLACK);

                } else {//checkbox has been deselected
                    password_field.setEchoChar('*');
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        password_field = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        email_field = new javax.swing.JTextField();
        check_box = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(510, 330));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(150, 0, 210, 60);

        password_field.setForeground(new java.awt.Color(153, 153, 153));
        password_field.setText("password");
        password_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_fieldFocusLost(evt);
            }
        });
        jPanel1.add(password_field);
        password_field.setBounds(100, 140, 330, 30);

        login.setBackground(new java.awt.Color(0, 79, 144));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel1.add(login);
        login.setBounds(180, 230, 150, 30);

        email_field.setForeground(new java.awt.Color(102, 102, 102));
        email_field.setText("Email");
        email_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                email_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                email_fieldFocusLost(evt);
            }
        });
        jPanel1.add(email_field);
        email_field.setBounds(100, 100, 330, 30);
        jPanel1.add(check_box);
        check_box.setBounds(440, 150, 21, 21);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed

        try {
            if (this.email_field.getText().trim().equals("Email") || this.password_field.getText().trim().equals("password")) {

                throw new FirebaseException("The fields can't empty!");
            }

            String sha1_password = this.sha1(this.password_field.getText().trim());

            // Create a reference to the users collection
            CollectionReference users = db.collection("users");
            // Create a query against the collection.
            Query query = users.whereEqualTo("email", this.email_field.getText().trim()).whereEqualTo("password", sha1_password);
            // retrieve  query results asynchronously using query.get()
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            String username = "";
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                username = document.getString("username");
            }

            if (!username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Welcome " + username + "!", "Successfully login", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new FirebaseException("Incorrect email or password!");
            }
            //clear jtext fields
            this.clearTextFields();

            this.removeAll();
            this.repaint();
            this.add(new UsersTable());
            this.updateUI();

        } catch (FirebaseException fe) {
            JOptionPane.showMessageDialog(null, fe.getError_message(), "Firebase error!", JOptionPane.WARNING_MESSAGE);

        } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage(), "Filling error!", JOptionPane.WARNING_MESSAGE);

        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Firebase error!", JOptionPane.WARNING_MESSAGE);
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Firebase error!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_loginActionPerformed

    /**
     * Hashing with SHA1
     *
     * @param input String to hash
     * @return String hashed
     */
    public String sha1(String input) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private void clearTextFields() {

        this.password_field.setText("");
        this.email_field.setText("");
    }

    private void email_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_fieldFocusLost

        if (this.email_field.getText().equals("")) {
            this.email_field.setText("Email");
            this.email_field.setForeground(new Color(117, 117, 117));
        }

    }//GEN-LAST:event_email_fieldFocusLost

    private void email_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_fieldFocusGained

        if (this.email_field.getText().equals("Email")) {
            this.email_field.setText("");
            this.email_field.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_email_fieldFocusGained


    private void password_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusGained

        if (this.password_field.getText().equals("password")) {
            this.password_field.setText("");
            this.password_field.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_password_fieldFocusGained

    private void password_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusLost

        if (this.password_field.getText().equals("")) {
            this.password_field.setText("password");
            this.password_field.setForeground(new Color(117, 117, 117));
        }

    }//GEN-LAST:event_password_fieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox check_box;
    private javax.swing.JTextField email_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password_field;
    // End of variables declaration//GEN-END:variables
}

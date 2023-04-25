package com.marcusso.firebasedemo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.*;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class RegisterPanel extends JPanel {

    private Firestore db;

    public RegisterPanel() {
        initComponents();
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
        create_account = new javax.swing.JButton();
        email_field = new javax.swing.JTextField();
        phone_field = new javax.swing.JTextField();
        name_field = new javax.swing.JTextField();
        check_box = new javax.swing.JCheckBox();
        password_field_confirm = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(510, 330));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(160, 10, 210, 60);

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
        password_field.setBounds(80, 190, 330, 30);

        create_account.setBackground(new java.awt.Color(0, 153, 0));
        create_account.setForeground(new java.awt.Color(255, 255, 255));
        create_account.setText("Create Account");
        create_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_accountActionPerformed(evt);
            }
        });
        jPanel1.add(create_account);
        create_account.setBounds(190, 280, 130, 30);

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
        email_field.setBounds(80, 70, 330, 30);

        phone_field.setForeground(new java.awt.Color(102, 102, 102));
        phone_field.setText("Phone");
        phone_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phone_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phone_fieldFocusLost(evt);
            }
        });
        jPanel1.add(phone_field);
        phone_field.setBounds(80, 150, 330, 30);

        name_field.setForeground(new java.awt.Color(117, 117, 117));
        name_field.setText("Username");
        name_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                name_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_fieldFocusLost(evt);
            }
        });
        jPanel1.add(name_field);
        name_field.setBounds(80, 110, 330, 30);
        jPanel1.add(check_box);
        check_box.setBounds(410, 200, 20, 21);

        password_field_confirm.setForeground(new java.awt.Color(153, 153, 153));
        password_field_confirm.setText("password");
        password_field_confirm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password_field_confirmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_field_confirmFocusLost(evt);
            }
        });
        jPanel1.add(password_field_confirm);
        password_field_confirm.setBounds(80, 230, 330, 30);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidPassword(String password) {

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

    private boolean isNumber(String text) {

        for (int i = 0; i < text.length(); i++) {

            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private void create_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_accountActionPerformed

        try {
            
            // input check
            if (this.email_field.getText().equals("Email") || this.password_field_confirm.getText().equals("password") || this.phone_field.getText().equals("Phone") || this.name_field.getText().equals("Display Name")) {

                throw new FirebaseException("The fields can't empty!");
            } else if (!this.email_field.getText().trim().contains("@")) {

                throw new FirebaseException("The email field must contain @!");

            } else if (!this.phone_field.getText().trim().startsWith("+")) {

                throw new FirebaseException("The phone field must start with +!");
            } else if (!this.isNumber(this.phone_field.getText().trim().substring(1, this.phone_field.getText().length() - 1))) {

                throw new FirebaseException("The phone field must not contain letter or special caracter expect +!");

            } else if (this.password_field_confirm.getText().trim().length() < 8) {

                throw new FirebaseException("The password must 8 caracter length!");
            } else if (!this.isValidPassword(this.password_field_confirm.getText().trim())) {

                throw new FirebaseException("The password must contain one uppercase, one lowercase, one special caracter and one number!");
            } else if (phone_field.getText().trim().length() < 12) {

                throw new FirebaseException("The phone field must 12 caracter length!");
            } else if (this.name_field.getText().trim().length() < 4) {
                throw new FirebaseException("The name field cannot be less than 4 characters!");
            }

            // Create a reference to the users collection
            CollectionReference users = db.collection("users");
            // Create a query against the collection.
            this.checkDataInDataBase(users, "email", this.email_field.getText().trim(), "Such an email already exists in the database! " + this.email_field.getText().trim());
            this.checkDataInDataBase(users, "username", this.name_field.getText().trim(), "Such an username already exists in the database! " + this.name_field.getText().trim());
            this.checkDataInDataBase(users, "phone", this.phone_field.getText().trim(), "Such a phone number already exists in the database! " + this.phone_field.getText().trim());

            if (!this.password_field.getText().trim().equals(this.password_field_confirm.getText().trim())) {

                throw new FirebaseException("The two password must match!");
            }
            String sha1_password = this.sha1(this.password_field.getText().trim());

            User user = new User(this.name_field.getText().trim(), this.email_field.getText().trim(), sha1_password, this.phone_field.getText().trim());
            ApiFuture<WriteResult> future = db.collection("users").document().create(user);

            JOptionPane.showMessageDialog(null, "Successfully created new user", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            this.clearTextFields();

        } catch (FirebaseException fe) {
            JOptionPane.showMessageDialog(null, fe.getError_message(), "Filling error!", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(null, ie.getMessage(), "Filling error!", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException ix) {
            JOptionPane.showMessageDialog(null, ix.getMessage(), "Firebase Error!", JOptionPane.WARNING_MESSAGE);
        } catch (ExecutionException ee) {
            JOptionPane.showMessageDialog(null, ee.getMessage(), "Firebase Error!", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_create_accountActionPerformed

    private void checkDataInDataBase(CollectionReference users, String field_name, String field_text, String error_message) throws FirebaseException, InterruptedException, ExecutionException {

// Create a query against the collection.
        Query query = users.whereEqualTo(field_name, field_text);
// retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        // retrieve a QuerySnapshot using  querySnapshot.get();
        QuerySnapshot qs = querySnapshot.get();
        //check a QuerySnapshot if there is a field data that we write in the jtextfield and throw an exception
        if (!qs.isEmpty()) {
            throw new FirebaseException(error_message);
        }
    }

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

        this.name_field.setText("");
        this.phone_field.setText("");
        this.password_field.setText("");
        this.email_field.setText("");
        this.password_field_confirm.setText("");
    }

    private void name_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_fieldFocusLost

        if (this.name_field.getText().equals("")) {
            this.name_field.setText("Username");
            this.name_field.setForeground(new Color(117, 117, 117));
        }

    }//GEN-LAST:event_name_fieldFocusLost

    private void phone_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phone_fieldFocusLost

        if (this.phone_field.getText().equals("")) {
            this.phone_field.setText("Phone");
            this.phone_field.setForeground(new Color(117, 117, 117));
        }

    }//GEN-LAST:event_phone_fieldFocusLost

    private void phone_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phone_fieldFocusGained

        if (this.phone_field.getText().equals("Phone")) {
            this.phone_field.setText("");
            this.phone_field.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_phone_fieldFocusGained

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


    private void name_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_fieldFocusGained
        if (this.name_field.getText().equals("Username")) {
            this.name_field.setText("");
            this.name_field.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_name_fieldFocusGained

    private void password_field_confirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_field_confirmFocusGained

        if (this.password_field_confirm.getText().equals("password")) {
            this.password_field_confirm.setText("");
            this.password_field_confirm.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_password_field_confirmFocusGained

    private void password_field_confirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_field_confirmFocusLost

        if (this.password_field_confirm.getText().equals("")) {
            this.password_field_confirm.setText("password");
            this.password_field_confirm.setForeground(new Color(117, 117, 117));
        }

    }//GEN-LAST:event_password_field_confirmFocusLost

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
    private javax.swing.JButton create_account;
    private javax.swing.JTextField email_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField name_field;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPasswordField password_field_confirm;
    private javax.swing.JTextField phone_field;
    // End of variables declaration//GEN-END:variables
}

package com.marcusso.firebasedemo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import com.google.firebase.internal.Nullable;
import java.awt.Color;
import java.awt.Font;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javax.swing.*;
import javax.swing.table.*;

public class UsersTable extends JPanel {
    
    private Firestore db;
    private DefaultTableModel dtm;
    
    public UsersTable() {
        initComponents();
        // Initialize Firebase Cloud FireStore
        this.db = FirestoreClient.getFirestore();
        this.table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.table.getTableHeader().setOpaque(false);
        this.table.getTableHeader().setBackground(new Color(32, 136, 203));
        this.table.getTableHeader().setForeground(new Color(255,255, 255));
        this.table.setRowHeight(25);
        this.dtm = (DefaultTableModel) this.table.getModel();
        try {
            this.queryDataBaseIntoTable();
            
        } catch (InterruptedException ix) {
            JOptionPane.showMessageDialog(null, ix.getMessage(), "Firebase Error!", JOptionPane.WARNING_MESSAGE);
        } catch (ExecutionException ee) {
            JOptionPane.showMessageDialog(null, ee.getMessage(), "Firebase Error!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(510, 330));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Users");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(150, 0, 210, 60);

        table.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Email", "Password Hash", "Phone number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(255, 102, 102));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 60, 510, 300);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void queryDataBaseIntoTable() throws InterruptedException, ExecutionException {

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            
            User user = document.toObject(User.class);
            Vector<Object> rowData = new Vector<Object>();
            rowData.add(user.getUsername());
            rowData.add(user.getEmail());
            rowData.add(user.getPassword());
            rowData.add(user.getPhone());
            this.dtm.addRow(rowData);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

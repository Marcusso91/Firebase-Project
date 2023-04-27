package com.marcusso.firebase.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.*;

public class FirebaseConfig {


    public void initFirebase() {

        FileInputStream serviceAccount = null;
        try {
            //your .json filename that generated in your firebase account: https://firebase.google.com
            //there is a .readme file to how to do it
            serviceAccount = new FileInputStream("credentials.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
         

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {

            try {
                serviceAccount.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

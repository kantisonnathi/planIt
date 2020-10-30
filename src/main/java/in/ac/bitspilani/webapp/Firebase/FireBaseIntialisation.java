package in.ac.bitspilani.webapp.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FireBaseIntialisation {
    public void initialization() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\sahit\\FirebaseSDK");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ereefr.firebaseio.com")
                .build();
        if(FirebaseApp.getApps().isEmpty())
        {
            FirebaseApp.initializeApp(options);
        }
        FirebaseApp.initializeApp(options);
    }
    public Firestore getFirebase()
    {
        return FirestoreClient.getFirestore();
    }
    }


package ca.sheridancollege.su11.services;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FirebaseConfig is a configuration class responsible for setting up and initializing
 * the connection to the Firebase Firestore database.
 */

@Configuration
public class FirebaseConfig {

	// The path to the Firebase credentials file
    @Value("${firebase.credentials.path}")
    private String firebaseCredentialsPath;


    /**
     * Initializes the Firebase Firestore connection and returns an instance
     * of the Firestore database.
     * 
     * @return Firestore instance connected to the Firebase Firestore database.
     * @throws IOException if there is an issue reading the credentials file.
     */
    
    @Bean
    public Firestore firestore() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(firebaseCredentialsPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore();
    }
    
    
}
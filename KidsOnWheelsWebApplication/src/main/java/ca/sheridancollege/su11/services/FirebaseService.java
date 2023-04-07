package ca.sheridancollege.su11.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.FirebaseApp;

import ca.sheridancollege.su11.beans.Parent;

/**
 * FirebaseService is a service class that handles the interaction with
 * the Firebase Firestore database for Parent objects.
 */

@Service
public class FirebaseService {
	
	// Instance of Firestore to interact with the database
	private final Firestore firestore;
	
	/**
	 * Constructor for FirebaseService that initializes the Firestore instance.
	 * 
	 * @param firestore The Firestore instance connected to the Firebase Firestore database.
	 */
	public FirebaseService(Firestore firestore) {
		this.firestore = firestore;
	}
	
	/**
	 * Adds a Parent object to the Firebase Firestore database.
	 * 
	 * @param parent The Parent object to be added to the database.
	 * @return The added Parent object with its assigned ID.
	 */
	
	public Parent addParent(Parent parent) {
		DocumentReference documentReference = firestore.collection("users").document();
		
		parent.setId(documentReference.getId());
		 
		//save the parent object, including the id, to Firestore
		documentReference.set(parent);
		//parent.setId(documentReference.getId());
		return parent;
	}
	
	/**
	 * Retrieves all Parent objects from the Firebase Firestore database.
	 * 
	 * @return A list of Parent objects.
	 * @throws ExecutionException If there is an error during the execution of the query.
	 * @throws InterruptedException If the query is interrupted while waiting for the result.
	 */
	public List<Parent> getAllParents() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = firestore.collection("users").get().get().getDocuments();
        List<Parent> parents = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Parent parent = document.toObject(Parent.class);
            parent.setId(document.getId());
            parents.add(parent);
        }
        return parents;
    }
	
//	public String addParent(Parent parent) throws ExecutionException, InterruptedException {
//	    Map<String, Object> data = new HashMap<>();
//	    data.put("userName", parent.getUserName());
//	    data.put("password", parent.getPassword());
//	    data.put("firstName", parent.getFirstName());
//	    data.put("lastName", parent.getLastName());
//	    data.put("gender", parent.getGender());
//	    data.put("phone", parent.getPhone());
//	    data.put("email", parent.getEmail());
//	    data.put("address", parent.getAddress());
//
//	    DocumentReference addedDocRef = firestore.collection("Users").add(data).get();
//	    return addedDocRef.getId();
//	}
	
	
	
	
}

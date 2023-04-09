package ca.sheridancollege.su11.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.FirebaseApp;

import ca.sheridancollege.su11.beans.Child;
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
	
	
	public Parent getParentById(String id) throws ExecutionException, InterruptedException {
	    DocumentReference documentReference = firestore.collection("users").document(id);
	    ApiFuture<DocumentSnapshot> future = documentReference.get();
	    DocumentSnapshot document = future.get();
	    if (document.exists()) {
	        return document.toObject(Parent.class);
	    } else {
	        return null;
	    }
	}

	public Parent updateParent(Parent parent) {
	    DocumentReference documentReference = firestore.collection("users").document(parent.getId());
	    documentReference.set(parent);
	    return parent;
	}

	public void deleteParent(String id) throws ExecutionException, InterruptedException {
	    DocumentReference documentReference = firestore.collection("users").document(id);
	    ApiFuture<WriteResult> writeResult = documentReference.delete();
	    writeResult.get();
	}
	
	
//	public List<Child> getChildrenByParentId(String parentId) throws ExecutionException, InterruptedException {
//	    CollectionReference childrenRef = firestore.collection("users").document(parentId).collection("children");
//	    Query query = childrenRef.orderBy("firstName");
//	    List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
//	    List<Child> children = new ArrayList<>();
//	    for (QueryDocumentSnapshot document : documents) {
//	        Child child = document.toObject(Child.class);
//	        child.setId(document.getId());
//	        children.add(child);
//	    }
//	    return children;
//	}
//
//	public void addChildToParent(String parentId, Child child) {
//	    DocumentReference parentRef = firestore.collection("users").document(parentId);
//	    CollectionReference childrenRef = parentRef.collection("children");
//	    DocumentReference newChildRef = childrenRef.document();
//	    child.setId(newChildRef.getId());
//	    newChildRef.set(child);
//	}

	 public Child addChild(String parentId, Child child) {
	        DocumentReference documentReference = firestore.collection("users").document(parentId).collection("children").document();
	        child.setId(documentReference.getId());
	        documentReference.set(child);
	        return child;
	    }

	    public List<Child> getChildrenByParentId(String parentId) throws ExecutionException, InterruptedException {
	        List<QueryDocumentSnapshot> documents = firestore.collection("users").document(parentId).collection("children").get().get().getDocuments();
	        List<Child> children = new ArrayList<>();
	        for (QueryDocumentSnapshot document : documents) {
	            Child child = document.toObject(Child.class);
	            child.setId(document.getId());
	            children.add(child);
	        }
	        return children;
	    }
	    
	    
	    public Parent getParentByPhoneNumber(Long phone) throws ExecutionException, InterruptedException {
	    	 System.out.println("Searching for phone number: " + phone);
	    	List<QueryDocumentSnapshot> documents = firestore.collection("users")
	                .whereEqualTo("phone", phone)
	                .get()
	                .get()
	                .getDocuments();
	        
	        System.out.println("Number of documents found: " + documents.size());
	        
	        if (documents.size() == 1) {
	            Parent parent = documents.get(0).toObject(Parent.class);
	            parent.setId(documents.get(0).getId());
	            return parent;
	        }
	        return null;
	    }
	
	
	
	
}

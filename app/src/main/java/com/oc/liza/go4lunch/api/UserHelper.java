package com.oc.liza.go4lunch.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.oc.liza.go4lunch.models.firebase.User;

public class UserHelper {

    private static final String COLLECTION_NAME = "users";

    // Access a Cloud Firestore instance from your Activity
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return db.collection(COLLECTION_NAME);
    }


    // --- CREATE ---

    public static Task<Void> createUser(String uid, String username, String urlPicture, String restaurant) {
        User userToCreate = new User(uid, username, urlPicture, restaurant);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);

    }


    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateRestaurant(String restaurant, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("restaurant", restaurant);
    }

    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).delete();
    }

}

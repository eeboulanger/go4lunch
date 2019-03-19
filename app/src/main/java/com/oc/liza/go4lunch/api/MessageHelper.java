package com.oc.liza.go4lunch.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.oc.liza.go4lunch.models.firebase.Message;
import com.oc.liza.go4lunch.models.firebase.User;

import java.util.Date;

public class MessageHelper {

        private static final String COLLECTION_NAME = "messages";
        private static final String CHAT="chat";

        // --- GET ---

        public static Query getAllMessageForChat(){
            return ChatHelper.getChatCollection()
                    .document(CHAT)
                    .collection(COLLECTION_NAME)
                    .orderBy("dateCreated")
                    .limit(50);
        }

    public static Task<DocumentReference> createMessageForChat(String textMessage, User userSender, Date date){

        // 1 - Create the Message object
        Message message = new Message(textMessage, userSender, date);

        // 2 - Store Message to Firestore
        return ChatHelper.getChatCollection()
                .document(CHAT)
                .collection(COLLECTION_NAME)
                .add(message);
    }
}

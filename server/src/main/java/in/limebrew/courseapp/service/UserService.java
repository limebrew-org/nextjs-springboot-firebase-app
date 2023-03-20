package in.limebrew.courseapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.annotation.DocumentId;
import in.limebrew.courseapp.entity.User;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    @Autowired
    private final Firestore firestore;

    private final String collectionName = "users";

    public UserService(Firestore firestore) {
        this.firestore = firestore;
    }

    public void getAllUsers() throws ExecutionException, InterruptedException {

        //? asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = firestore.collection("users").get();
        //? future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.getData());
        }

    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("users").document().set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String updateUser(String id, User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("users").document(id).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection("users").document(id).delete();
        return collectionApiFuture.get().getUpdateTime().toString();
    }

}

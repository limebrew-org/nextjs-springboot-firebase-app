package in.limebrew.courseapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import in.limebrew.courseapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    @Autowired
    private final Firestore firestore;

    private final String userCollection = "users";

    public UserService(Firestore firestore) {
        this.firestore = firestore;
    }

    public void getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(userCollection).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.getData());
        }
    }

    public DocumentSnapshot getUserById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = firestore.collection(userCollection).document(id).get();
        return documentSnapshotApiFuture.get();
    }

    public DocumentSnapshot getUserByEmail(String email) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = firestore.collection(userCollection).document(email).get();
        return documentSnapshotApiFuture.get();
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document().set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String updateUser(String id, User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document(id).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document(id).delete();
        return collectionApiFuture.get().getUpdateTime().toString();
    }

}

package in.limebrew.courseapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import in.limebrew.courseapp.entity.Course;
import in.limebrew.courseapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CourseService {

    @Autowired
    private final Firestore firestore;

    private final String userCollection = "courses";

    public CourseService(Firestore firestore) {
        this.firestore = firestore;
    }

    public void getAllCourses() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(userCollection).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.getData());
        }

    }

    public DocumentSnapshot getCourseById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = firestore.collection(userCollection).document(id).get();
        return documentSnapshotApiFuture.get();
    }

    public String createCourse(Course course) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document().set(course);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String updateCourse(String id, Course course) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document(id).set(course);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteCourse(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(userCollection).document(id).delete();
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}

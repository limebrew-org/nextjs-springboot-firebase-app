package in.limebrew.courseapp.config.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.util.Set;

@Configuration
public class FirebaseConfig {
    private static final String GOOGLE_API_SCOPE = "https://www.googleapis.com/auth/cloud-platform";

    @Autowired
    ResourceLoader resourceLoader;

    @Value( "${firebase.project-id}" )
    private String projectId;

    @Value("${firebase.firestore.db.url}")
    private String firestoreDBUrl;


    @PostConstruct
    public FirebaseApp initializeFirebaseApp() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.getApplicationDefault().createScoped( Set.of( GOOGLE_API_SCOPE ) ))
                .setDatabaseUrl(firestoreDBUrl)
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}

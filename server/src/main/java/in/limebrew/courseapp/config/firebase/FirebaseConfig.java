package in.limebrew.courseapp.config.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
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

    @Value( "${gcp.project-id}" )
    private String projectId;

    @Bean
    public FirebaseApp fireBaseApp() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.getApplicationDefault().createScoped( Set.of( GOOGLE_API_SCOPE ) ))
                .build();
        return FirebaseApp.initializeApp(options);
    }
}

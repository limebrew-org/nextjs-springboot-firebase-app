package in.limebrew.courseapp.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @DocumentId
    public String id;

    public String name;

    public String email;

    public String bio;
}

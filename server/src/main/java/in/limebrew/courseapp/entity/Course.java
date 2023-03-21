package in.limebrew.courseapp.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @DocumentId
    public String id;

    public String name;

    public String authorId;

    public String authorName;

    public String createdAt;

    public String price;

    public String videoUrl;

    public Long subscriberCount;
}

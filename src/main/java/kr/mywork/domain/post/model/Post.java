package kr.mywork.domain.post.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

    @Column(length = 200)
    private String title;

    @Column(length = 30)
    private String companyName;

    @Column(length = 30)
    private String authorName;

    @Column(length = 500)
    private String content;

    @Column(length = 30)
    private String approval;

    private Boolean deleted;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

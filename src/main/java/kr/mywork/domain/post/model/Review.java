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
public class Review {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

    private UUID postId;

    @Column(length = 200)
    private String comment;

    @Column(length = 30)
    private String companyName;

    @Column(length = 30)
    private String authorName;

    @Column(nullable = false)
    private Boolean deleted;

    private UUID parentReviewId;

    private Integer level;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

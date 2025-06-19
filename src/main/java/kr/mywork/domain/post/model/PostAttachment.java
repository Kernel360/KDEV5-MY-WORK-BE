package kr.mywork.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAttachment {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    @Getter
    private UUID id;

    @Getter
    private UUID postId;

    @Column(length = 300)
    private String path;

    @Column(nullable = false)
    private Boolean deleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean active;

    private PostAttachment(final UUID postId, final String path, final Boolean deleted, final Boolean active) {
        this.postId = postId;
        this.path = path;
        this.deleted = deleted;
        this.active = active;
    }

    public static PostAttachment inactivePostAttachment(final UUID postId, final String path) {
        return new PostAttachment(postId, path, false, false);
    }

    public boolean activate() {
        this.active = true;
        return true;
    }

    public boolean isActive() {
        return this.active;
    }

    public void updateActive(final Boolean active) {
        this.active = active;
    }
}

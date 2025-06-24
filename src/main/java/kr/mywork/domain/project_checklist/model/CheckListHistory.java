package kr.mywork.domain.project_checklist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckListHistory {

    private static final String CREATED_MESSAGE = "체크리스트가 생성되었습니다.";

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

    private UUID checkListId;

    @Column(length = 30)
    private String companyName;

    @Column(length = 30)
    private String authorName;

    @Column(nullable = false)
    private String approval;

    @Column(length = 500)
    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private CheckListHistory(final UUID checkListId, final String companyName, final String authorName,
        final String approval, final String content) {
        this.checkListId = checkListId;
        this.companyName = companyName;
        this.authorName = authorName;
        this.approval = approval;
        this.content = content;
    }

    public static CheckListHistory CreationHistory(final UUID checkListId, final String companyName,
        final String authorName, final String approval) {
        return new CheckListHistory(checkListId, companyName, authorName, approval, CREATED_MESSAGE);
    }
}

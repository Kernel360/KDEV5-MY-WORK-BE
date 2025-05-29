package kr.mywork.domain.projectCheckList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectCheckListHistory {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

    @Column(length = 50)
    private String devManager;

    @Column(length = 50)
    private String clientManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_check_list_id")
    private ProjectCheckList projectCheckList;

    @Column(nullable = false)
    private Boolean approval;

    @Column(length = 500)
    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

}

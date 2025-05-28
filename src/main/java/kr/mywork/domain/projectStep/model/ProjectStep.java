package kr.mywork.domain.projectStep.model;

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
public class ProjectStep {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

//    이 필드는 Project entity가 작성되면 주석 해제 예정.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id")
//    private Project project;

    @Column(length = 30)
    private String title;

    private Integer orderNum;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
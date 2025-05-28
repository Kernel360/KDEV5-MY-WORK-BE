package kr.mywork.domain.projectCheckList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectCheckList {

    @Id
    @UnixTimeOrderedUuidGeneratedValue
    private UUID id;

    @Column(length = 100)
    private String title;

    @Column(nullable = false)
    private UUID devCompanyId;

    @Column(nullable = false)
    private UUID clientCompanyId;

    @Column(nullable = false)
    private UUID projectStepIdId;

    @Column(nullable = false)
    private Boolean approval;
}

package kr.mywork.domain.project_member.service.dto.response;

import java.util.UUID;

public record ProjectMemberDto(
        UUID id,
        UUID projectId,
        UUID memberId,
        Boolean isManager
) {
}

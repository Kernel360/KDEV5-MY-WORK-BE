package kr.mywork.domain.project_member.service.dto.request;

import java.util.UUID;

public record ProjectManagerUpdateRequest(UUID memberId , UUID projectId) {
}

package kr.mywork.interfaces.project.controller.dto.response;

import java.util.UUID;

public record ProjectStatusUpdateResponse(UUID projectId, String status) {
}

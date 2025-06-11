package kr.mywork.domain.project_checklist.service.dto.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListApprovalRequest {

	private UUID id;

	private String approval;
}

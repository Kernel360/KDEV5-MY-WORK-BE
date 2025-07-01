package kr.mywork.interfaces.project_step.dto.response;

import java.util.List;

import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepPostTotalCountResponse;

public record ProjectStepsWithPostTotalCountWebResponse(List<ProjectStepPostTotalCountResponse> steps) {
}

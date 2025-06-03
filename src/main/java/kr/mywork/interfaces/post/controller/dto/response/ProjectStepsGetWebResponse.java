package kr.mywork.interfaces.post.controller.dto.response;

import java.util.List;

import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepGetResponse;

public record ProjectStepsGetWebResponse(List<ProjectStepGetResponse> steps) {
}

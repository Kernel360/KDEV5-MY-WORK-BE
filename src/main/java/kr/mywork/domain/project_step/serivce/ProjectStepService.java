package kr.mywork.domain.project_step.serivce;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectStepService {

	private final ProjectStepRepository projectStepRepository;

	public Integer saveAll(
		final UUID projectId, final List<ProjectStepCreateRequest> projectStepCreateRequests) {
		// TODO projectId 를 기반으로 Project 존재 유무 검증 필요

		final List<ProjectStep> projectSteps = projectStepCreateRequests.stream()
			.map(ProjectStepCreateRequest::toEntity)
			.toList();

		final List<ProjectStep> savedProjectSteps = projectStepRepository.saveAll(projectSteps);

		return savedProjectSteps.size();
	}
}

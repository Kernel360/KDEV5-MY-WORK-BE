package kr.mywork.domain.project_checklist.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListErrorType;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListNotFoundException;
import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.repository.ProjectCheckListRepository;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListUpdateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListApprovalResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListUpdateResponse;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectCheckListService {

	private final ProjectCheckListRepository projectCheckListRepository;
	private final CompanyRepository companyRepository;
	private final ProjectStepRepository projectStepRepository;

	@Transactional
	public ProjectCheckListCreateResponse createProjectCheckList(
		ProjectCheckListCreateRequest projectCheckListRequest) {

		companyRepository.findByIdAndType(projectCheckListRequest.getClientCompanyId(), CompanyType.CLIENT)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		companyRepository.findByIdAndType(projectCheckListRequest.getDevCompanyId(), CompanyType.DEV)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		//TODO 프로젝트 단계 존재하는지 체크해야함.

		ProjectCheckList projectCheckList = projectCheckListRepository.save(projectCheckListRequest);

		return ProjectCheckListCreateResponse.from(projectCheckList);

	}

	@Transactional(readOnly = true)
	public ProjectCheckListDetailResponse getProjectCheckList(UUID checkListId) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(checkListId)
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		return new ProjectCheckListDetailResponse(projectCheckList);
	}

	@Transactional
	public ProjectCheckListUpdateResponse updateProjectCheckList(
		ProjectCheckListUpdateRequest projectCheckListUpdateRequest) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(projectCheckListUpdateRequest.getId())
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		projectCheckList.update(projectCheckListUpdateRequest);
		return ProjectCheckListUpdateResponse.from(projectCheckList);
	}

	@Transactional
	public UUID deleteProjectCheckList(UUID checkListId) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(checkListId)
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		projectCheckList.softDelete();
		return projectCheckList.getId();
	}

	@Transactional
	public ProjectCheckListApprovalResponse approvalProjectCheckList(ProjectCheckListApprovalRequest projectCheckListApprovalRequest) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(projectCheckListApprovalRequest.getId())
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		projectCheckList.changeApproval(projectCheckListApprovalRequest);
		return ProjectCheckListApprovalResponse.from(projectCheckList);
	}
}

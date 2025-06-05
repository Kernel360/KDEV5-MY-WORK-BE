package kr.mywork.domain.project_checklist.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.repository.ProjectCheckListRepository;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
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

}

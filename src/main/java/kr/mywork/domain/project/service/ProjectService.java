package kr.mywork.domain.project.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private final MemberRepository memberRepository;

	@Transactional
	public List<ProjectMemberResponse> findMemberByCompanyId(UUID companyId ,UUID projectId) {

		List<Member> companyMembers = memberRepository.findMemberListByCompanyId(companyId,projectId);

		return companyMembers.stream()
			.map(member -> new ProjectMemberResponse(
				member.getId(),
				member.getName()
			)).collect(Collectors.toList());
	}
}

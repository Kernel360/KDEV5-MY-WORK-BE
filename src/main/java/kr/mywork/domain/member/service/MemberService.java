package kr.mywork.domain.member.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public List<CompanyMemberResponse> findMemberByCompanyId(UUID companyId, long offset){

		return memberRepository.findMemberByComapnyId(companyId,offset).stream()
			.map(CompanyMemberResponse::fromEntity)
			.collect(Collectors.toList());

	}

	public long countMembersByCompanyId(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}
}

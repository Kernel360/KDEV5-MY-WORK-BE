package kr.mywork.domain.member.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Value("${member.page.size}")
	private int memberPageSize;

	private final MemberRepository memberRepository;

	@Transactional
	public List<CompanyMemberResponse> findMemberByCompanyId(UUID companyId, int page) {

		return memberRepository.findMemberByCompanyId(companyId,page,memberPageSize).stream()
			.map(CompanyMemberResponse::fromEntity)
			.collect(Collectors.toList());

	}

	public long countMembersByCompanyId(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}
}

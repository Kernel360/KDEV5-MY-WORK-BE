package kr.mywork.domain.member.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.mywork.domain.member.errors.EmailAlreadyExistsException;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;
import org.springframework.data.domain.Pageable;
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
	public List<CompanyMemberResponse> findMemberByCompanyId(UUID companyId, Pageable pageable) {

		return memberRepository.findMemberByComapnyId(companyId,pageable).stream()
			.map(CompanyMemberResponse::fromEntity)
			.collect(Collectors.toList());

	}
	@Transactional
	public long countMembersByCompanyId(UUID companyId) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId);
	}

	@Transactional
	public UUID createMember(MemberCreateRequest request) {
		//이메일 검사
		if(memberRepository.existsByEmail(request.getEmail())){
			throw new EmailAlreadyExistsException(MemberErrorType.EMAIL_ALREADY_EXISTS);
		}

		String encPassword = request.getPassword(); // TODO 암호화 필요.

		MemberCreateRequest memberCreateRequest = new MemberCreateRequest(
				request.getId(),
				request.getCompanyId(),
				request.getName(),
				request.getDepartment(),
				request.getPosition(),
				request.getRole(),
				request.getPhoneNumber(),
				request.getEmail(),
				request.getBirthDate(),
				encPassword

		);

		final Member savedMember = memberRepository.save(memberCreateRequest);

		return savedMember.getId();
	}
}

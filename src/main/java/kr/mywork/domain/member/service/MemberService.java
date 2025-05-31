package kr.mywork.domain.member.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.errors.EmailAlreadyExistsException;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberIdNotFoundException;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;
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

	@Transactional
    public UUID deleteMember(UUID memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(()-> new MemberIdNotFoundException(MemberErrorType.ID_NOT_FOUND));

		//더티체킹
		member.softDelete();

		return member.getId();
	}
}

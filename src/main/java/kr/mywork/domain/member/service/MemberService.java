package kr.mywork.domain.member.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.errors.EmailAlreadyExistsException;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberIdNotFoundException;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import kr.mywork.domain.member.service.dto.request.MemberUpdateRequest;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Value("${member.page.size}")
	private int memberPageSize;

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public List<CompanyMemberResponse> findMemberByCompanyId(UUID companyId, int page) {

		return memberRepository.findMemberByCompanyId(companyId, page, memberPageSize)
			.stream()
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
		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException(MemberErrorType.EMAIL_ALREADY_EXISTS);
		}

		String encodedPassword =passwordEncoder.encode(
			request.getBirthDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

		Member member = new Member(request.getCompanyId(), request.getName(), request.getDepartment(),
			request.getPosition(), request.getRole(), request.getPhoneNumber(), request.getEmail(), encodedPassword,
			request.getBirthDate());

		final Member savedMember = memberRepository.save(member);

		return savedMember.getId();
	}

	@Transactional
	public UUID deleteMember(UUID memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberIdNotFoundException(MemberErrorType.ID_NOT_FOUND));

		//더티체킹
		member.softDelete();

		return member.getId();
	}

	@Transactional
	public UUID updateMember(MemberUpdateRequest memberUpdateRequest) {
		Member member = memberRepository.findById(memberUpdateRequest.getId())
			.orElseThrow(() -> new MemberIdNotFoundException(MemberErrorType.ID_NOT_FOUND));

		String encodedPassword =passwordEncoder.encode(memberUpdateRequest.getPassword());
		MemberUpdateRequest memberUpdateWithEncodedPassword = MemberUpdateRequest.setPasswordEncode(memberUpdateRequest,encodedPassword);

		member.updateFrom(memberUpdateWithEncodedPassword);

		return member.getId();
	}

	@Transactional
	public long countTotalmembersByCondition(String keyword, String keywordType,UUID companyId) {
		return memberRepository.countTotalmembersByCondition(keyword, keywordType,companyId);
	}

	@Transactional
	public List<MemberSelectResponse> findMembersBySearchWithPaging(final int page, String keyword,
		String keywordType,UUID companyId) {

		return memberRepository.findMembersBySearchWithPaging(page, memberPageSize, keyword, keywordType,companyId);
	}

}

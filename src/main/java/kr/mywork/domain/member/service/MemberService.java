package kr.mywork.domain.member.service;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogCreateEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogDeleteEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;
import kr.mywork.domain.member.errors.*;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import kr.mywork.domain.member.service.dto.request.MemberUpdateRequest;
import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;
import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;
import kr.mywork.interfaces.member.controller.dto.request.ResetPasswordWebRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Value("${member.page.size}")
	private int memberPageSize;

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public List<CompanyMemberResponse> findMemberByCompanyId(UUID companyId, int page, String memberName) {

		return memberRepository.findMemberByCompanyId(companyId, page, memberPageSize, memberName)
			.stream()
			.map(CompanyMemberResponse::fromEntity)
			.collect(Collectors.toList());

	}

	@Transactional
	public long countMembersByCompanyId(UUID companyId, String memberName) {
		return memberRepository.countByCompanyIdAndDeletedFalse(companyId,memberName);
	}

	@Transactional
	public UUID createMember(MemberCreateRequest request, LoginMemberDetail loginMemberDetail) {
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

		eventPublisher.publishEvent(new ActivityLogCreateEvent(savedMember, loginMemberDetail));

		return savedMember.getId();
	}

	@Transactional
	public UUID deleteMember(UUID memberId, LoginMemberDetail loginMemberDetail) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberIdNotFoundException(MemberErrorType.ID_NOT_FOUND));

		//더티체킹
		member.softDelete();

		eventPublisher.publishEvent(new ActivityLogDeleteEvent(member, loginMemberDetail));

		return member.getId();
	}

	@Transactional
	public UUID updateMember(final MemberUpdateRequest memberUpdateRequest, LoginMemberDetail loginMemberDetail) {
		Member member = memberRepository.findById(memberUpdateRequest.id())
			.orElseThrow(() -> new MemberIdNotFoundException(MemberErrorType.ID_NOT_FOUND));

		Member before = Member.copyOf(member);

		member.updateFrom(
			memberUpdateRequest.companyId(),
			memberUpdateRequest.name(),
			memberUpdateRequest.department(),
			memberUpdateRequest.position(),
			memberUpdateRequest.role(),
			memberUpdateRequest.phoneNumber(),
			memberUpdateRequest.email(),
			memberUpdateRequest.birthDate(),
			memberUpdateRequest.deleted());

		eventPublisher.publishEvent(new ActivityModifyEvent(before, member, loginMemberDetail));

		return member.getId();
	}

	@Transactional
	public long countTotalmembersByCondition(String keyword, String keywordType,UUID companyId) {
		return memberRepository.countTotalmembersByCondition(keyword, keywordType,companyId);
	}

	@Transactional
	public List<MemberSelectResponse> findMembersBySearchWithPaging(final int page, final String keyword,
		final String keywordType, final UUID companyId) {
		return memberRepository.findMembersBySearchWithPaging(page, memberPageSize, keyword, keywordType,companyId);
	}

	@Transactional
	public MemberDetailResponse findMemberDetailByMemberId(UUID memberId) {

		return memberRepository.findMemberDetailByMemberId(memberId);
	}
	@Transactional
	public UUID resetMemberPassword(ResetPasswordWebRequest resetPasswordWebRequest){
		Member member = memberRepository.findFirstByEmail(resetPasswordWebRequest.email())
			.orElseThrow(() -> new MemberNotFoundException(MemberErrorType.MEMBER_NOT_FOUND));

		String rawPassword =resetPasswordWebRequest.password();
		boolean isMath = passwordEncoder.matches(rawPassword, member.getPassword());

		if(isMath){
			String encodedPassword =passwordEncoder.encode(resetPasswordWebRequest.newPassword());
			member.setPasswordEncode(encodedPassword);
		}else{
			throw new PasswordFailException(MemberErrorType.WRONG_PASSWORD);
		}

		return member.getId();
	}
}

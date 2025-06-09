package kr.mywork.domain.auth.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthNotFoundException;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberNotFoundException;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final CompanyRepository companyRepository;

	@Override
	public MemberDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			final Member member = memberRepository.findByEmailAndDeletedFalse(email)
				.orElseThrow(() -> new MemberNotFoundException(MemberErrorType.MEMBER_NOT_FOUND));
			final Company company = companyRepository.findById(member.getCompanyId())
				.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

			return new MemberDetails(
				member.getId(),
				member.getName(),
				member.getEmail(),
				member.getPassword(),
				new SimpleGrantedAuthority(member.getRole().getRoleName()),
				member.getCompanyId(),
				company.getName(),
				company.getLogoImagePath(),
				company.getType().name());
		} catch (MemberNotFoundException | CompanyNotFoundException exception) {
			log.warn("exception name: {}, message: {}", exception.getClass().getName(), exception.getMessage());
			throw new AuthNotFoundException(AuthErrorType.AUTHENTICATION_NOT_FOUND);
		}
	}
}

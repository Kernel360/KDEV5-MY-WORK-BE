package kr.mywork.domain.auth.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public MemberDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		return memberRepository.findByEmailAndDeletedFalse(email)
			.map(member -> {
				return new MemberDetails(
					member.getId(),
					member.getName(),
					member.getEmail(),
					member.getPassword(),
					new SimpleGrantedAuthority(member.getRole().getRoleName())
				);
			})
			.orElseThrow(() -> new AuthException(AuthErrorType.AUTHENTICATION_FAILED));
	}
}

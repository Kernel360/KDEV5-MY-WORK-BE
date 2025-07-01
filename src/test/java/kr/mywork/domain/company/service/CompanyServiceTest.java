package kr.mywork.domain.company.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.company.errors.CompanyAlreadyExistException;
import kr.mywork.domain.company.model.CompanyId;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

	@InjectMocks
	private CompanyService companyService;

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private CompanyIdRepository companyIdRepository;

	@Test
	@DisplayName("존재하는 사업자 번호로 회사 생성하면 실패")
	void 존재하는_사업자_번호로_회사_생성하면_실패 () {
	    // given
		final String businessNumber = "123-12345";
		final UUID companyId = UUID.fromString("0197c45c-256b-77d8-9d8b-5933907f8ff3");

		final CompanyCreateRequest companyCreateRequest = new CompanyCreateRequest(
			companyId,
			"마이워크 주식회사",
			"소프트웨어 납품 회사입니다.",
			businessNumber,
			"서울특별시 강남구",
			"DEV",
			"02-5951-0214",
			"mywork@email.com",
			"fileName.png");

		final LoginMemberDetail loginMemberDetail = new LoginMemberDetail(
			UUID.fromString("0197c45d-f987-7a63-85b5-218bdf1e551c"),
			"시스템 관리자",
			UUID.fromString("0197c45e-5b1f-72ad-ac08-1d164b44b30c"),
			"관리자 회사",
			"ROLE_SYSTEM_ADMIN",
			"SYSTEM");

		given(companyIdRepository.findById(any())).willReturn(Optional.of(new CompanyId(companyId)));
		given(companyRepository.existsByBusinessNumber(businessNumber)).willReturn(true);

		// when, then
		assertThatThrownBy(() -> companyService.createCompany(companyCreateRequest, loginMemberDetail))
			.isInstanceOf(CompanyAlreadyExistException.class);
	}
}

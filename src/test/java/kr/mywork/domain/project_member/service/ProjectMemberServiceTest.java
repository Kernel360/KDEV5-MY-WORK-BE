package kr.mywork.domain.project_member.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;

class ProjectMemberServiceTest {

	private ProjectMemberService projectMemberService;
	private ProjectMemberRepository projectMemberRepository;

	@BeforeEach
	void setUp() {
		this.projectMemberRepository = Mockito.mock(ProjectMemberRepository.class);
		this.projectMemberService = new ProjectMemberService(projectMemberRepository);
	}

	@Test
	@DisplayName("제거된 프로젝트 멤버를 다시 할당하면 기존 프로젝트 멤버를 호출한다")
	void 제거된_프로젝트_멤버를_다시_할당하면_기존_프로젝트_멤버를_호출한다 () {
	    // given
		final UUID projectId = UUID.fromString("01975df2-edc4-74b7-83b6-47c96d2c2deb");
		final UUID memberId = UUID.fromString("01975df3-3e13-7b2f-b99b-2cb28f0eeecb");

		given(projectMemberRepository.existsByMemberIdAndProjectIdAndDeleted(any(), any(), anyBoolean()))
			.willReturn(true);
		given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
			.willReturn(Optional.of(new ProjectMember(projectId, memberId)));

		// when
		projectMemberService.addMemberToCompany(projectId, memberId);

		// then
		verify(projectMemberRepository, times(1)).findByMemberIdAndProjectId(any(), any());
		verify(projectMemberRepository, never()).save(any());
	}

	@Test
	@DisplayName("제거된 프로젝트 멤버가 없으면 프로젝트 멤버를 생성한다")
	void 제거된_프로젝트_멤버가_없으면_프로젝트_멤버를_생성한다 () {
		// given
		final UUID projectId = UUID.fromString("01975df2-edc4-74b7-83b6-47c96d2c2deb");
		final UUID memberId = UUID.fromString("01975df3-3e13-7b2f-b99b-2cb28f0eeecb");

		given(projectMemberRepository.existsByMemberIdAndProjectIdAndDeleted(any(), any(), anyBoolean()))
			.willReturn(false);
		given(projectMemberRepository.save(any()))
			.willReturn(new ProjectMember(projectId, memberId));

		// when
		projectMemberService.addMemberToCompany(projectId, memberId);

		// then
		verify(projectMemberRepository, never()).findByMemberIdAndProjectId(any(), any());
		verify(projectMemberRepository, times(1)).save(any());
	}

}

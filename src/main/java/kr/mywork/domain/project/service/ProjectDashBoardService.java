package kr.mywork.domain.project.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.dashboard.service.dto.response.DashboardCountSummaryResponse;
import kr.mywork.domain.dashboard.service.dto.response.DashboardPopularProjectsResponse;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;
import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.model.ProjectAmountChartRole;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project.repository.ProjectAssignRepository;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project.service.dto.request.NearDeadlineProjectRequest;
import kr.mywork.domain.project.service.dto.response.DashboardMostPostProjectResponse;
import kr.mywork.domain.project.service.dto.response.NearDeadlineProjectResponse;
import kr.mywork.domain.project.service.dto.response.ProjectAmountSummaryResponse;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectDashBoardService {

	@Value("${dashboard.page.size}")
	private int dashboardPageSize;

	private final ProjectRepository projectRepository;
	private final PostRepository postRepository;
	private final ProjectAssignRepository projectAssignRepository;
	private final ProjectMemberRepository projectMemberRepository;

	@Transactional(readOnly = true)
	public DashboardCountSummaryResponse getSummaryTotalCount(LoginMemberDetail loginMemberDetail) {

		final String userType = loginMemberDetail.roleName();
		final UUID companyId = loginMemberDetail.companyId();
		final UUID memberId = loginMemberDetail.memberId();

		if (MemberRole.SYSTEM_ADMIN.isSameRoleName(userType)) {

			return fetchProjectSummaryByProjectIds(null);

		} else if (MemberRole.DEV_ADMIN.isSameRoleName(userType) || MemberRole.CLIENT_ADMIN.isSameRoleName(userType)
			&& companyId != null) {

			final List<UUID> projectIds = projectAssignRepository.getCompanyAdminProjectIds(companyId, userType).stream()
				.map(ProjectAssign::getProjectId)
				.toList();

			return fetchProjectSummaryByProjectIds(projectIds);

		} else if (MemberRole.USER.isSameRoleName(userType) && memberId != null) {

			final List<UUID> projectIds = projectMemberRepository.getUserProjectIds(memberId).stream()
				.map(ProjectMember::getProjectId)
				.toList();

			return fetchProjectSummaryByProjectIds(projectIds);

		} else {
			throw new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND);
		}
	}

	private DashboardCountSummaryResponse fetchProjectSummaryByProjectIds(List<UUID> projectIds){
		LocalDateTime now = LocalDateTime.now();

		Long totalCount = projectRepository.getSummaryProjectTotalCount(projectIds);
		Long inProgressCount = projectRepository.getSummaryInProgressProjectTotalCount(projectIds, now);
		Long completedCount = projectRepository.getSummaryCompletedProjectTotalCount(projectIds, now);

		return new DashboardCountSummaryResponse(totalCount, inProgressCount, completedCount);
	}

	@Transactional
	public List<DashboardPopularProjectsResponse> getMostPostProjectsTopFive(LoginMemberDetail memberDetail) {
		final String memberRole = memberDetail.roleName();
		//가져온 프로젝트들의 ID에 이름을 과 순서를 매칭 해주는 메소드 [ buildPopularResponse ]
		if(MemberRole.SYSTEM_ADMIN.isSameRoleName(memberRole)){
			return buildPopularResponse(postRepository.findMostPostProjectTopFive(null));
		}
		if(MemberRole.CLIENT_ADMIN.isSameRoleName(memberRole) || MemberRole.DEV_ADMIN.isSameRoleName(memberRole)){
			final UUID companyId = memberDetail.companyId(); //회사 기준으로 -> 프로젝트 ID들 가져와야함.
			final List<UUID> companyProjectIds = projectAssignRepository.findCompanyProjectsByCompanyId(companyId,memberRole);

			return buildPopularResponse(postRepository.findMostPostProjectTopFive(companyProjectIds));
		}
		if(MemberRole.USER.isSameRoleName(memberRole)){
			UUID memberId = memberDetail.memberId(); //멤버 아이디 기준으로 프로젝트 ID를 가져와야함
			final List<UUID> memberProjectIds = projectMemberRepository.findProjectIdsByMemberId(memberId);

			return buildPopularResponse(postRepository.findMostPostProjectTopFive(memberProjectIds));
		}
		throw new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND);
	}

	private List<DashboardPopularProjectsResponse> buildPopularResponse(List<DashboardMostPostProjectResponse> mostPostProjects) {
		List<UUID> ids = mostPostProjects.stream()
			.map(DashboardMostPostProjectResponse::projectId)
			.toList();
		//프로젝트들의 ID 이름을 가져온다.
		List<Project> popularProjects = projectRepository.findProjectsNameById(ids);
		//리스트를 맵형태로 전환
		Map<UUID, Project> projectMap = popularProjects.stream()
			.collect(Collectors.toMap(Project::getId, p -> p));
		//리스트에 값을 맵에서 찾아서 맵핑
		return mostPostProjects.stream()
			.map(dto -> {
				Project p = projectMap.get(dto.projectId());
				return new DashboardPopularProjectsResponse(p.getId(), p.getName(), dto.postCount());
			})
			.toList();
	}

	@Transactional(readOnly = true)
	public List<NearDeadlineProjectResponse> findNearDeadlineProjectsByLoginMember(
		final int page,
		final NearDeadlineProjectRequest nearDeadlineProjectRequest,
		final LocalDate baseDate
	) {
		final String userType = nearDeadlineProjectRequest.getMemberRole();
		final LocalDateTime now = baseDate.atStartOfDay();

		if (MemberRole.SYSTEM_ADMIN.getRoleName().equals(userType)) {
			final List<Project> projects = projectRepository.findAllNearDeadlineProjects(page, dashboardPageSize, baseDate);
			return projects.stream()
				.map(this::toResponseWithDday)
				.toList();
		}

		if (MemberRole.CLIENT_ADMIN.getRoleName().equals(userType) || MemberRole.DEV_ADMIN.getRoleName().equals(userType)) {
			final UUID companyId = nearDeadlineProjectRequest.getCompanyId();
			final List<ProjectAssign> assigns = projectAssignRepository.findAllByCompanyId(companyId, userType);
			final List<UUID> projectIds = assigns.stream()
				.map(ProjectAssign::getProjectId)
				.distinct()
				.toList();

			final List<Project> projects = projectRepository.findAllNearDeadlineProjectsByProjectIds(projectIds, page, dashboardPageSize, now);
			return projects.stream()
				.map(this::toResponseWithDday)
				.toList();
		}

		final UUID memberId = nearDeadlineProjectRequest.getMemberId();
		final List<ProjectMember> projectMembers = projectMemberRepository.findAllByMemberId(memberId);
		final List<UUID> projectIds = projectMembers.stream()
			.map(ProjectMember::getProjectId)
			.distinct()
			.toList();

		final List<Project> projects = projectRepository.findAllNearDeadlineProjectsByProjectIds(projectIds, page, dashboardPageSize, now);
		return projects.stream()
			.map(this::toResponseWithDday)
			.toList();
	}

	private NearDeadlineProjectResponse toResponseWithDday(Project project) {
		int dday = Math.max(0, (int) ChronoUnit.DAYS.between(LocalDate.now(), project.getEndAt().toLocalDate()));
		return NearDeadlineProjectResponse.of(project.getId(), project.getName(), project.getEndAt(), dday);
	}

	@Transactional(readOnly = true)
	public Long countNearDeadlineProjectsByLoginMember(final NearDeadlineProjectRequest nearDeadlineProjectRequest, final LocalDate baseDate) {
		final String memberRole = nearDeadlineProjectRequest.getMemberRole();

		if (MemberRole.SYSTEM_ADMIN.getRoleName().equals(memberRole)) {
			return projectRepository.countNearDeadlineProjects(baseDate);
		}

		if (MemberRole.CLIENT_ADMIN.getRoleName().equals(memberRole) || MemberRole.DEV_ADMIN.getRoleName().equals(
			memberRole)) {
			final UUID companyId = nearDeadlineProjectRequest.getCompanyId();
			final List<ProjectAssign> assigns = projectAssignRepository.findAllByCompanyId(companyId, memberRole);
			final List<UUID> projectIds = assigns.stream()
				.map(ProjectAssign::getProjectId)
				.toList();

			return projectRepository.countNearDeadlineProjectsByProjectIds(projectIds, baseDate);
		}

		final UUID memberId = nearDeadlineProjectRequest.getMemberId();
		final List<ProjectMember> projectMembers = projectMemberRepository.findAllByMemberId(memberId);
		final List<UUID> projectIds = projectMembers.stream()
			.map(ProjectMember::getProjectId)
			.distinct()
			.toList();

		return projectRepository.countNearDeadlineProjectsByProjectIds(projectIds, baseDate);
	}

	@Transactional
	public List<ProjectAmountSummaryResponse> findProjectAmountSummary(LoginMemberDetail memberDetail, String chartType, LocalDate today) {
		//로그인한 유저의 타입별로 프로젝트 Ids를 반환Add commentMore actions
		final List<UUID> projectIds = getProjectIdsByRoleName(memberDetail);
		String status = "COMPLETED";

		if (ProjectAmountChartRole.CHART_WEEK.isSameChartType(chartType)) {
			//프로젝트 조회 (1달전의 주의 첫날 월요일 기준)
			LocalDate oneMonthAgo = today.minusMonths(1);
			LocalDate startOfWeek = oneMonthAgo.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
			LocalDateTime startDate = startOfWeek.atStartOfDay();

			//프로젝트 날짜 기준으로 조회.
			final List<Project> projects = projectRepository.findCompletedProjectsByIdsWithDate(projectIds, startDate,status);

			List<ProjectAmountSummaryResponse> result = new ArrayList<>();

			for (int i = 3; i >= 0; i--) {
				LocalDateTime weekStart = startDate.plusWeeks(i);
				//반복문은 7씩 증가 하지만 종료일은 오늘기준으로 가야함
				LocalDateTime weekEnd = (i < 3) ? startDate.plusWeeks(i + 1) : today.atStartOfDay();
				// 계산 통계 합계
				long totalAmount = projects.stream()
					.filter(project -> {
						LocalDateTime endAt = project.getEndAt();
						return !endAt.isBefore(weekStart) && endAt.isBefore(weekEnd);
					})
					.mapToLong(Project::getProjectAmount)
					.sum();

				//몇월인지 구함
				int month = weekStart.getMonthValue();
				//몇째 주 인지 구함
				int weekOfMonth = weekStart.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
				String label = month + "월" + weekOfMonth + "주";

				result.add(new ProjectAmountSummaryResponse(label, totalAmount));
			}
			return result;
		}

		if (ProjectAmountChartRole.CHART_MONTH.isSameChartType(chartType)) {
			//프로젝트 월초 6개월 치 데이터
			YearMonth sixMonthAgo = YearMonth.from(today).minusMonths(5);
			LocalDate startOfMonth = sixMonthAgo.atDay(1);
			LocalDateTime startDate = startOfMonth.atStartOfDay();
			//프로젝트 날짜 기준으로 조회.
			final List<Project> projects = projectRepository.findCompletedProjectsByIdsWithDate(projectIds, startDate,status);

			List<ProjectAmountSummaryResponse> result = new ArrayList<>();


			for (int i = 5; i >= 0; i--) {
				YearMonth targetMonth = YearMonth.from(today).minusMonths(i);
				LocalDateTime monthStart = targetMonth.atDay(1).atStartOfDay();            // 월의 시작일
				LocalDateTime monthEnd = targetMonth.atEndOfMonth().plusDays(1).atStartOfDay(); // 다음 달 1일 00:00

				// 계산 통계 합계
				long totalAmount = projects.stream()
					.filter(project -> {
						LocalDateTime endAt = project.getEndAt();
						return !endAt.isBefore(monthStart) && endAt.isBefore(monthEnd);
					})
					.mapToLong(Project::getProjectAmount)
					.sum();

				//몇월인지 구함
				String label = targetMonth.getMonthValue() + "월";

				result.add(new ProjectAmountSummaryResponse(label, totalAmount));
			}
			return result;

		}
		return Collections.emptyList();
	}

	private List<UUID> getProjectIdsByRoleName(LoginMemberDetail memberDetail) {
		String roleName = memberDetail.roleName();
		if (MemberRole.CLIENT_ADMIN.isSameRoleName(roleName) || MemberRole.DEV_ADMIN.isSameRoleName(roleName)) {
			return projectAssignRepository.findCompanyProjectsByCompanyId(memberDetail.companyId(), roleName);
		} else if (MemberRole.USER.isSameRoleName(roleName)) {
			return projectMemberRepository.findProjectIdsByMemberId(memberDetail.memberId());
		} else {
			throw new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND);
		}
	}
}

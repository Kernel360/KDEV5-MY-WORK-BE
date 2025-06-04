package kr.mywork.interfaces.project.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;

public record ProjectMemberListWebResponse(
	List<ProjectMemberWebResponse> members  // 제네릭 타입을 WebResponse로 변경
) {
	public static ProjectMemberListWebResponse from(List<ProjectMemberResponse> serviceDtos) {
		List<ProjectMemberWebResponse> webDtos = serviceDtos.stream()
			.map(ProjectMemberWebResponse::fromService)
			.collect(Collectors.toList());
		return new ProjectMemberListWebResponse(webDtos);
	}
}

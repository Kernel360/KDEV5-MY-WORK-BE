package kr.mywork.interfaces.project.controller.dto.request;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectBulkDeleteWebRequest {
	private List<UUID> ids;
}
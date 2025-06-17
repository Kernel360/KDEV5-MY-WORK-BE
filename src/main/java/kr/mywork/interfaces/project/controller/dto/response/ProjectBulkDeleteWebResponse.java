package kr.mywork.interfaces.project.controller.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectBulkDeleteWebResponse {
	private List<UUID> deletedIds;
}
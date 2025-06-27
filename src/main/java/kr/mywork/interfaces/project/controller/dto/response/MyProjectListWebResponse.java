package kr.mywork.interfaces.project.controller.dto.response;

import java.util.List;

public record MyProjectListWebResponse(List<MyProjectSelectWebResponse> projects) { }

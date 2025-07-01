package kr.mywork.interfaces.company.controller.dto.response;

import java.util.List;

public record CompanyListWebResponse(List<CompanySelectWebResponse> companies, Long totalCount) {
}

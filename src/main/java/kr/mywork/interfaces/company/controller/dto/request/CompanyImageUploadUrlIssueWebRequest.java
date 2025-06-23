package kr.mywork.interfaces.company.controller.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CompanyImageUploadUrlIssueWebRequest {
	private final UUID companyId;
	private final String fileName;
}

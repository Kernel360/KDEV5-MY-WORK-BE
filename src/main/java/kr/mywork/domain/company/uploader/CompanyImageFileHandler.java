package kr.mywork.domain.company.uploader;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

public interface CompanyImageFileHandler {
	URL createUploadUrl(UUID companyId, String fileName);
	URL issueDownloadUrl(String filePath, Duration duration);
	void deleteImage(UUID companyId);
}

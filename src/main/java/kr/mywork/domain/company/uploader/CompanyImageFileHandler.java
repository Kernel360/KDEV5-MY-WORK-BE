package kr.mywork.domain.company.uploader;

import java.net.URL;
import java.util.UUID;

public interface CompanyImageFileHandler {
	URL createUploadUrl(UUID companyId, String fileName);
}

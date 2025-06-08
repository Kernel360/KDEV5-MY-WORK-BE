package kr.mywork.domain.project_member.service.dto.response;

import java.util.UUID;

public record CompanyMemberInProjectResponse (UUID memberId, String memberName, String email){
}

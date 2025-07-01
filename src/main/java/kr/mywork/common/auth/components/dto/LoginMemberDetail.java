package kr.mywork.common.auth.components.dto;

import java.util.UUID;

public record LoginMemberDetail(UUID memberId, String memberName, UUID companyId, String companyName, String roleName, String companyType) {
}

package kr.mywork.interfaces.project_member.controller.dto.request;

import kr.mywork.domain.project_member.service.dto.request.ProjectManagerUpdateRequest;

import java.util.UUID;

public record ProjectManagerUpdateWebRequest(UUID memberId , UUID projectId) {

    public  ProjectManagerUpdateRequest toServiceDto(){
        return new ProjectManagerUpdateRequest(
                this.memberId,
                this.projectId
        );

    }
}

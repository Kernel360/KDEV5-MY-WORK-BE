package kr.mywork.domain.project_step.serivce.dto.request;

import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepGetResponse;

import java.util.UUID;

public record ProjectStepDetailRequest (UUID projectStepId, String title, Integer orderNum){

    public static ProjectStepDetailRequest from(ProjectStepGetResponse response){
        return new ProjectStepDetailRequest(response.projectStepId(),response.title(),response.orderNum());
    }
}

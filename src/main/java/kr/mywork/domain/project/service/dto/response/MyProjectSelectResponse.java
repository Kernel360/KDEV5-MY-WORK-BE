package kr.mywork.domain.project.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MyProjectSelectResponse(
        UUID id,
        String name,
        String detail,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endAt
) {
    public static MyProjectSelectResponse of(
        UUID projectId, String projectName,String detail, LocalDateTime startAt, LocalDateTime endAt
    ){
        return new MyProjectSelectResponse(projectId,projectName,detail,startAt,endAt);
    }
}

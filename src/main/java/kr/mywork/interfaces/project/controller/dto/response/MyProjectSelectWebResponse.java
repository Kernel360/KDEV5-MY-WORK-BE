package kr.mywork.interfaces.project.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mywork.domain.project.service.dto.response.MyProjectSelectResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record MyProjectSelectWebResponse(
        UUID id,
        String name,
        String detail,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endAt
) {
    public static MyProjectSelectWebResponse from(MyProjectSelectResponse response){
        return new MyProjectSelectWebResponse(
                response.id(),
                response.name(),
                response.detail(),
                response.startAt(),
                response.endAt()
        );
    }
}

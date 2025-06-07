package kr.mywork.interfaces.post.controller.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReviewsSelectWebResponse(@JsonProperty("reviews") List<ReviewSelectWebResponse> reviewSelectWebResponses) {
}

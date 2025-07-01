package kr.mywork.interfaces.post.controller.dto.response;

import java.util.List;

public record PostListSelectWebResponse(List<PostSelectWebResponse> posts, Long totalCount) {
}

package kr.mywork.common.api.components.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project_member.service.ProjectMemberService;
import kr.mywork.domain.project_member.service.dto.response.ProjectMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PermissionCheckInterceptor implements HandlerInterceptor {

    private final ProjectMemberService projectMemberService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        UUID memberId = memberDetails.getId();
        UUID projectId = UUID.fromString(request.getParameter("projectId"));
        String memberRole = memberDetails.getAuthorityAsStr();


        if(MemberRole.SYSTEM_ADMIN.isSameRoleName(memberRole)){
            return true;
        }

        if (!request.getMethod().equalsIgnoreCase("GET")) {
            Optional<ProjectMemberDto> projectMemberDto = projectMemberService.findProjectManagerByMemberIdAndProjectId(memberId, projectId);
            if (projectMemberDto.isEmpty()) {
                writeErrorResponse(response);
                return false;

            }
        }

        return true;
    }
    private void writeErrorResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.error(
                ProjectErrorType.PROJECT_PERMISSION_NOT_FOUND.getCode().name(),
                ProjectErrorType.PROJECT_PERMISSION_NOT_FOUND.getMessage()
        );

        objectMapper.writeValue(response.getWriter(), apiResponse);
    }
}

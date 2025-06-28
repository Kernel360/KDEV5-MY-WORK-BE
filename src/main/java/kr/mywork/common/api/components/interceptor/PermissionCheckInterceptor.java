package kr.mywork.common.api.components.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PermissionCheckInterceptor implements HandlerInterceptor {

    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "인증 정보가 없습니다.");
        }

        if (!(authentication.getPrincipal() instanceof MemberDetails memberDetails)) {
            return sendJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "인증된 사용자가 아닙니다.");
        }

        String memberRole = memberDetails.getAuthorityAsStr();
        UUID memberId = memberDetails.getId();
        UUID projectId = UUID.fromString(request.getParameter("projectId"));

        if(MemberRole.SYSTEM_ADMIN.isSameRoleName(memberRole)){
            return true;
        }

        if (!request.getMethod().equalsIgnoreCase("GET")) {
            Optional<ProjectMember> projectManager = projectMemberRepository.findProjectManagerByMemberIdAndProjectId(memberId, projectId);
            if (projectManager.isEmpty()) {
                return sendJsonError(response, HttpServletResponse.SC_FORBIDDEN, "해당 프로젝트에 대한 접근 권한이 없습니다.");
            }
        }

        return true;
    }




    private boolean sendJsonError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(String.format("""
            {
              "result": "ERROR",
              "data": null,
              "error": {
                "code": "INTERCEPTOR_%d",
                "message": "%s",
                "data": null
              }
            }
            """, status, message));
        response.flushBuffer();
        return false;
    }
}

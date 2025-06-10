package kr.mywork.infrastructure.project_member.rdb;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.project.model.ProjectMember;

public interface JpaProjectMemberRepository extends JpaRepository<ProjectMember, UUID> {
	Optional<ProjectMember> findByMemberIdAndProjectId(UUID memberId, UUID projectId);
}

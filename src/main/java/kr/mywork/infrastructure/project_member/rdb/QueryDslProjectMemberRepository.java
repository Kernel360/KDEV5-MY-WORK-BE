package kr.mywork.infrastructure.project_member.rdb;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslProjectMemberRepository implements ProjectMemberRepository {

	final JpaProjectMemberRepository jpaProjectMemberRepository;

	@Override
	public ProjectMember save(ProjectMember projectMember) {
		return jpaProjectMemberRepository.save(projectMember);
	}
}

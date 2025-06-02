package kr.mywork.infrastructure.post.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.post.model.PostId;

public interface JpaPostIdRepository extends JpaRepository<PostId, UUID> {
}

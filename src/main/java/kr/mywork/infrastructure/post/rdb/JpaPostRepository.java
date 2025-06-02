package kr.mywork.infrastructure.post.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.post.model.Post;

public interface JpaPostRepository extends JpaRepository<Post, UUID> {
}

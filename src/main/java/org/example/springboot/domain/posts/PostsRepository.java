package org.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJpa에서 기본 메소드만으로 해결할 수 있지만
    // @Query가 훨씬 가독성이 좋으니 선택해서 사용 가능
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

package Monitoring.Project.weather.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository <Post, Long> {
    // 제목으로 게시물 검색
    List<Post> findByTitleContaining(String keyword);

    // 내용으로 게시물 검색
    List<Post> findByContentsContaining(String keyword);

    // 제목 또는 내용으로 게시물 검색
    List<Post> findByTitleContainingOrContentsContaining(String titleKeyword, String contentKeyword);
}

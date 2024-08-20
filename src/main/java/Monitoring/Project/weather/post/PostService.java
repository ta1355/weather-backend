package Monitoring.Project.weather.post;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostDto dto) {
        postRepository.save(new Post(dto.user(), dto.title(), dto.contents()));
    }

    @Transactional
    public void update(Long id, PostDto dto) {
        Post updatePost = postRepository.findById(id).orElse(null);
        updatePost.update(dto.contents(), dto.title());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }


    public void findAll() {
        postRepository.findAll();
    }

    @Transactional
    public void findById(Long id) {
        Post findPost = postRepository.findById(id).orElse(null);
        findPost.Views();
    }

    //제목 검색 서비스
    public List<Post> searchPostsByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    //내용 검색 서비스
    public List<Post> searchPostsByContent(String keyword) {
        return postRepository.findByContentsContaining(keyword);
    }

    //제목 + 내용 검색 서비스
    public List<Post> searchPostsByTitleOrContent(String keyword) {
        return postRepository.findByTitleContainingOrContentsContaining(keyword, keyword);
    }
}

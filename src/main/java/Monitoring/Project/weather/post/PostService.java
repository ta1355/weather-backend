package Monitoring.Project.weather.post;

import Monitoring.Project.weather.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(RequestPostDto dto) {
        User user = dto.user();
        postRepository.save(new Post(user, dto.title(), dto.contents()));
    }

    @Transactional
    public void update(Long id, RequestPostDto dto) {
        Post updatePost = postRepository.findById(id).orElse(null);
        updatePost.update(dto.contents(), dto.title());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }


    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional
        public RequestPostDto findById(Long id) {
        Post findPost = postRepository.findById(id).orElse(null);

        RequestPostDto NewPost = new RequestPostDto(findPost.getTitle(), findPost.getContents(), findPost.getUser());
        findPost.Views();
        return NewPost;
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

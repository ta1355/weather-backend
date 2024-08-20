package Monitoring.Project.weather.post;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostDto dto) {
        postRepository.save(new Post(dto.user(), dto.title(), dto.content()));
    }

    @Transactional
    public void update(Long id, PostDto dto) {
        Post updatePost = postRepository.findById(id).orElse(null);
        updatePost.update(dto.content(), dto.title());
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
}

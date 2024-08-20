package Monitoring.Project.weather.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping("/createPost")
    public void createPost(@RequestBody PostDto dto) {
        postService.create(dto);
    }

    //게시글 수정
    @PutMapping("/updatePost/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostDto dto) {
        postService.update(id, dto);
    }

    //게시글 삭제(하드)
    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    //게시글 조회
    @GetMapping("/post")
    public void findAll() {
        postService.findAll();
    }

    //게시글 상세 조회
    @GetMapping("/post/{id}")
    public void findById(@PathVariable Long id) {
        postService.findById(id);
    }

    //검색 기능 (제목 , 내용 , 제목+내용)
    @GetMapping("/search/title")
    public ResponseEntity<List<Post>> searchPostsByTitle(@RequestParam String keyword) {
        List<Post> posts = postService.searchPostsByTitle(keyword);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search/content")
    public ResponseEntity<List<Post>> searchPostsByContent(@RequestParam String keyword) {
        List<Post> posts = postService.searchPostsByContent(keyword);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPostsByTitleOrContent(@RequestParam String keyword) {
        List<Post> posts = postService.searchPostsByTitleOrContent(keyword);
        return ResponseEntity.ok(posts);
    }
}

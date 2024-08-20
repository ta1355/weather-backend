package Monitoring.Project.weather.post;

import org.springframework.web.bind.annotation.*;

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
    public void  updatePost(@PathVariable Long id, @RequestBody PostDto dto) {
     postService.update(id, dto);
    }

    //게시글 삭제(하드)
    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    //게시글 조회
    @GetMapping("/post")
    public  void  findAll() {
        postService.findAll();
    }

    //게시글 상세 조회
    @GetMapping("/post/{id}")
    public  void  findById(Long id) {
        postService.findById(id);
    }
}

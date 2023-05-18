package com.company.postservice.controller;

import com.company.postservice.client.CommentsClient;
import com.company.postservice.client.PostDetailsClient;
import com.company.postservice.dto.PostCreateDTO;
import com.company.postservice.dto.PostDetail;
import com.company.postservice.dto.PostDetailsCreateDTO;
import com.company.postservice.entity.Post;
import com.company.postservice.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final PostDetailsClient postDetailsClient;
    private final CommentsClient commentsClient;

    @PostMapping("/create")
    public ResponseEntity<Post> create(@RequestBody PostCreateDTO dto) {
        log.info("Creating post with title: {}", dto.title());
        Post post = postRepository.save(Post.builder()
                .title(dto.title())
                .summary(dto.summary())
                .build());
        postDetailsClient.create(PostDetailsCreateDTO.builder()
                .postId(post.getId())
                .body(dto.body())
                .build());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Post> get(@PathVariable Integer id) {
        log.info("Getting post with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: %s".formatted(id)));
        return ResponseEntity.ok(post);
    }

    @GetMapping("/details/get/{id}")
    public ResponseEntity<PostDetail> getDetail(@PathVariable Integer id) {
        log.info("Getting post details with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: %s".formatted(id)));
        return ResponseEntity.ok(PostDetail.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .summary(post.getSummary())
                        .body(postDetailsClient.getDetail(post.getId()))
                        .comments(commentsClient.getAllByPostId(post.getId()))
                .build());
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<Post>> getAll() {
        log.info("Getting all posts");
        return ResponseEntity.ok(postRepository.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        log.info("Deleting post with id: {}", id);
        postRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}

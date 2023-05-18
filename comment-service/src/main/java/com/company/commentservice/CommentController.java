package com.company.commentservice;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    @PostMapping("/create")
    public Comment create(@RequestBody CommentCreateDTO dto) {
        log.info("Creating comment for post with id: {}", dto.postId());
        Comment comment = Comment.builder()
                .message(dto.message())
                .postId(dto.postId())
                .build();
        return commentRepository.save(comment);
    }

    @GetMapping("/get/{postId}")
    public List<Comment> getAllByPostID(@PathVariable Integer postId) throws InterruptedException {
        log.info("Getting all comments by post id: {}", postId);
        return commentRepository.findByPostId(postId);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Integer id) {
        log.info("Getting comment with id: {}", id);
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found: " + id));
    }
}

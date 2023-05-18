package com.company.detailsservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post-details")
public class PostDetailsController {
    private final PostDetailsRepository postDetailsRepository;

    @PostMapping("/create")
    public PostDetail create(@RequestBody PostDetailsCreateDTO dto) {
        log.info("Creating PostDetail for post with id: {}", dto.postId());
        return postDetailsRepository.save(PostDetail.builder()
                .postId(dto.postId())
                .body(dto.body())
                .build());
    }
    @GetMapping("/get/{postId}")
    public PostDetail get(@PathVariable Integer postId) {
        log.info("Getting PostDetail by post id: {}", postId);
        return postDetailsRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("PostDetails not found: %s".formatted(postId)));
    }
}

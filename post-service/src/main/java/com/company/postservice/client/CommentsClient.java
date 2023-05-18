package com.company.postservice.client;

import com.company.postservice.dto.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("${comments.service.baseUrl}")
public interface CommentsClient {
    @GetMapping("/get/{postId}")
    List<Comment> getAllByPostId(@PathVariable Integer postId);
}

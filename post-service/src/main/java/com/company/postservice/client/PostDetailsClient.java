package com.company.postservice.client;

import com.company.postservice.dto.PostDetail;
import com.company.postservice.dto.PostDetailsCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${post.details.service.baseUrl}")
public interface PostDetailsClient {

    @PostMapping("/create")
    PostDetail create(@RequestBody PostDetailsCreateDTO dto);

    @GetMapping("/get/{postId}")
    Object getDetail(@PathVariable Integer postId);
}

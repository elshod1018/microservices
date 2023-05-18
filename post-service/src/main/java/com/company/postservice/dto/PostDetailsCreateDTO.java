package com.company.postservice.dto;

import lombok.Builder;

@Builder
public record PostDetailsCreateDTO(Integer postId, String body) {
}


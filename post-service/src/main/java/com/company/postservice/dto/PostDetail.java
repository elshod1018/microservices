package com.company.postservice.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record PostDetail(Integer id, String title, String summary, Object body, List<Comment> comments) {
}

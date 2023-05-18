package com.company.commentservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CommentCreateDTO(@NotBlank String message, @Positive Integer postId) {
}

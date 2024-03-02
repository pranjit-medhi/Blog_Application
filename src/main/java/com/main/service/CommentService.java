package com.main.service;

import com.main.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Long postId);
    CommentDto updateComment(CommentDto commentDto, Long commentId);
    CommentDto getCommentById(Long commentId);
    List<CommentDto> getAllComment();
    void deleteComment(Long commentId);
}

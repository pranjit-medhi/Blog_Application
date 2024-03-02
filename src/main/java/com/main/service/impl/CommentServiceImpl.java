package com.main.service.impl;

import com.main.entity.Comment;
import com.main.entity.Post;
import com.main.exception.ResourceNotFoundException;
import com.main.payloads.CommentDto;
import com.main.repository.CommentRepository;
import com.main.repository.PostRepository;
import com.main.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllComment() {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        commentRepository.delete(comment);

    }
}

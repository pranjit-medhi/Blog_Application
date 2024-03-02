package com.main.controllers;

import com.main.entity.Comment;
import com.main.payloads.CommentDto;
import com.main.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long postId)
    {
        return  new ResponseEntity<>();

    }
   **/

@PostMapping("/post/{postId}/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId)
    {
        CommentDto newComment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity createComment(@PathVariable Long commentId)
    {
       commentService.deleteComment(commentId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    }


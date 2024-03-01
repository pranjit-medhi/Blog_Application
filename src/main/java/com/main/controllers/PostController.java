package com.main.controllers;

import com.main.entity.Post;
import com.main.payloads.PostDto;
import com.main.payloads.PostResponse;
import com.main.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "10", required = false) Integer pageNumber,
                                            @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize ,
                                            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)

    {
        PostResponse allPost = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    ResponseEntity <PostDto> getPostById(@PathVariable Long postId)
    {
        PostDto postById = postService.getPostById(postId);
        return  new ResponseEntity<>(postById, HttpStatus.OK);
    }
    @PostMapping("/user/{userId}/category/{categoryId}")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId,@PathVariable Long categoryId )
    {
        PostDto savedPostDto = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Long userId)
    {
        List<PostDto> allPostByUser = postService.getAllPostByUser(userId);
    return  new ResponseEntity<>(allPostByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Long categoryId)
    {
        List<PostDto> allPostByCategory = postService.getAllPostByCategory(categoryId);
        return  new ResponseEntity<>(allPostByCategory, HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keyword}")
    ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword)
    {
        List<PostDto> postDtos = postService.searchPost(keyword);
        return  new ResponseEntity<>(postDtos, HttpStatus.OK);
    }



}

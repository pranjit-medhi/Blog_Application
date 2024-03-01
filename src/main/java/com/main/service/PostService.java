package com.main.service;

import com.main.payloads.PostDto;
import com.main.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);
    PostDto updatePost(PostDto postDto, Long postId);
    PostDto getPostById(Long postId);
    PostResponse  getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    List<PostDto> getAllPostByCategory(Long categoryId);
    List<PostDto> getAllPostByUser(Long userId);

    List<PostDto> searchPost (String  keyword);

    void deletePost(Long postId);
}

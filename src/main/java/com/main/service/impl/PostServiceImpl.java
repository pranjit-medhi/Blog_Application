package com.main.service.impl;

import com.main.entity.Category;
import com.main.entity.Post;
import com.main.entity.User;
import com.main.exception.ResourceNotFoundException;
import com.main.payloads.PostDto;
import com.main.payloads.PostResponse;
import com.main.repository.CategoryRepository;
import com.main.repository.PostRepository;
import com.main.repository.UserRepository;
import com.main.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private  final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto,  Long userId, Long categoryId) {
        Post post = modelMapper.map(postDto, Post.class);
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category", "id", categoryId));
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );
        post.setCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post.setUpdatedDate(new Date());

        Post savePost = postRepository.save(post);
        return modelMapper.map(savePost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        return null;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort =( sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);


        Page<Post> pagePost = postRepository.findAll(pageRequest);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> collect = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return  postResponse;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category", "id", categoryId));
        List<Post> allPostsByCategory = postRepository.findAllByCategory(category);
        List<PostDto> postDtos = allPostsByCategory.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("category", "id", userId));
        List<Post> allPostsByUser = postRepository.findAllByUser(user);
        List<PostDto> postDtos = allPostsByUser.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> postsByTitleContaining = postRepository.findByPostTitleContaining(keyword);
        List<PostDto> postDtos = postsByTitleContaining.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));
        postRepository.delete(post);

    }
}

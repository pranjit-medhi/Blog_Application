package com.main.repository;

import com.main.entity.Category;
import com.main.entity.Post;
import com.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);
    List<Post> findByTitleContaining(String title);

}

package com.main.entity;

import com.main.payloads.CategoryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String postTitle;
    private String postBody;
    private String imageName;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;


}

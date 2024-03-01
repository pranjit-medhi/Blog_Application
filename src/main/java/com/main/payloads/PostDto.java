package com.main.payloads;

import com.main.entity.Category;
import com.main.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class PostDto {
    private String title;
    private Date addedDate;
    private String content;
    private Category category;
    private User user;
}

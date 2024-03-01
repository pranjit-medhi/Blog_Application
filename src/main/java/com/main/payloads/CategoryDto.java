package com.main.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {
    private Long categoryId;
    private String categoryTitle;
    private String categoryDescription;
}

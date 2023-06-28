package com.api.BlogApplication.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int category_id;
    private String categoryTitle;
    private String categoryDescription;
}

package com.api.BlogApplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(length = 100)
    @NotBlank
    @Size(min = 4,message = "Minimum size of category title is 4 char")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10,message = "Minimum size of category description is 10 char")
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> post = new ArrayList<>();


}

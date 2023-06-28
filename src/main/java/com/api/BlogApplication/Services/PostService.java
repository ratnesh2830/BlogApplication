package com.api.BlogApplication.Services;

import com.api.BlogApplication.Entities.Post;
import com.api.BlogApplication.Payloads.PostDto;
import com.api.BlogApplication.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(Post post, int userId, int categoryId);
    PostDto updatePost(Post post, int postId);
    PostDto getSinglePost(int postId);
    PostResponse getAllPost(int pageNumber,int pageSize,String sortField,String sortDir);
    void delete(int postId);
    List<PostDto> getPostByCategory(int categoryId);
    List<PostDto> getPostByUser(int userId);

    //to search particular post or collection of posts
    List<PostDto> searchPost(String keyword);
}

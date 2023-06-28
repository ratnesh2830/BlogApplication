package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Entities.Category;
import com.api.BlogApplication.Entities.Post;
import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Exceptions.ResourceNotFoundException;
import com.api.BlogApplication.Payloads.PostDto;
import com.api.BlogApplication.Payloads.PostResponse;
import com.api.BlogApplication.Repositories.CategoryRepo;
import com.api.BlogApplication.Repositories.PostRepo;
import com.api.BlogApplication.Repositories.UserRepo;
import com.api.BlogApplication.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImple implements PostService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public PostDto createPost(Post post, int userId, int categoryId)
    {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        post.setImageName("default.png");

        Post savedPost = postRepo.save(post);
        PostDto postDto = postToPostDto(savedPost);

      return postDto;
    }

    @Override
    public PostDto updatePost(Post post, int postId)
    {
        Post post1 = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));

        post1.setContent(post.getContent());
        post1.setTitle(post.getTitle());
        post1.setImageName(post.getImageName());
        Post updatedPost = postRepo.save(post1);

        PostDto postDto = postToPostDto(updatedPost);

        return postDto;
    }

    @Override
    public PostDto getSinglePost(int postId)
    {
        Post post =  postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        PostDto postDto = postToPostDto(post);
        return postDto;
    }

    @Override
    public PostResponse getAllPost(int pageNumber,int pageSize,String sortField,String sortDir)
    {
        Sort sort=null;

        if(sortDir.equalsIgnoreCase("descending"))
        {
            //Sort.by() will give you an Object of Sort
            sort = Sort.by(sortField).descending(); //it will sort the record on your given field and in ascending order
        }
        else
        {
            sort=Sort.by(sortField); //no need to give ascending because by default it will automatically sort the record on your given field in ascending order
        }

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page = postRepo.findAll(p);

        List<Post> posts = page.getContent();

        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts)
        {
            PostDto postDto = postToPostDto(post);
            postDtos.add(postDto);
        }

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setLastPage(page.isLast());
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalRecords(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());

        return postResponse;
    }

    @Override
    public void delete(int postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();

        for(Post post:posts)
        {
            PostDto postDto = postToPostDto(post);
            postDtos.add(postDto);
        }

        return  postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int userId)
    {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = new ArrayList<>();

        for(Post post:posts)
        {
            PostDto postDto = postToPostDto(post);
            postDtos.add(postDto);
        }

        return  postDtos;
    }


    @Override
    public List<PostDto> searchPost(String keyword)
    {
        List<Post> posts = postRepo.findByTitleContaining(keyword);

        List<PostDto> postDtos = new ArrayList<>();
        for(Post post:posts)
        {
            PostDto postDto = postToPostDto(post);
            postDtos.add(postDto);
        }

             return postDtos;
    }


    public  PostDto postToPostDto(Post post)
    {
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }
}

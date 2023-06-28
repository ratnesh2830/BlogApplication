package com.api.BlogApplication.Controllers;

import com.api.BlogApplication.Entities.Post;
import com.api.BlogApplication.Payloads.PostDto;
import com.api.BlogApplication.Payloads.PostResponse;
import com.api.BlogApplication.Services.FileService;
import com.api.BlogApplication.Services.PostService;
import javax.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${project.image}")
    String path;


    //creating post
    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody Post post,@PathVariable int userId,@PathVariable int categoryId)
    {
        PostDto postDto = postService.createPost(post,userId,categoryId);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }


    //updating a single post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody Post post,@PathVariable int postId)
    {
        PostDto postDto = postService.updatePost(post,postId);
        return  ResponseEntity.ok(postDto);
    }

    //getting a single post
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable int postId)
    {
        PostDto postDto = postService.getSinglePost(postId);
        return ResponseEntity.ok(postDto);
    }


    //getting all post
    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber" , defaultValue = "0") int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                                    @RequestParam(value = "sortField",defaultValue = "postId") String sortField,
                                                   @RequestParam(value = "sortDir",defaultValue = "Ascending",required = false)String sortDir) //by default required field is set as ->"true"
    {
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortField,sortDir);
        return ResponseEntity.ok(postResponse);
    }


    //getting post by category
    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId)
    {
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return ResponseEntity.ok().body(posts);
    }


    //getting post by user
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId)
    {
        List<PostDto> posts = postService.getPostByUser(userId);
        return ResponseEntity.ok(posts);
    }

    //deleting a single post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId)
    {
        postService.delete(postId);
        return  ResponseEntity.ok().body("Post successfully deleted");
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword)
    {
        List<PostDto> posts = postService.searchPost(keyword);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile multipartFile,@PathVariable int postId) throws IOException
    {
              PostDto postDto = postService.getSinglePost(postId);

              String imageName =  fileService.uploadImage(path,multipartFile,postId);

              postDto.setImageName(imageName);
              Post post = modelMapper.map(postDto,Post.class);
              PostDto updatedPostDto = postService.updatePost(post,postId);

              return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping("/post/image/{imageName}")
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resource = fileService.getImage(path,imageName);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}

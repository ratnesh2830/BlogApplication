package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Entities.Comment;
import com.api.BlogApplication.Entities.Post;
import com.api.BlogApplication.Exceptions.ResourceNotFoundException;
import com.api.BlogApplication.Payloads.CommentDto;
import com.api.BlogApplication.Repositories.CommentRepo;
import com.api.BlogApplication.Repositories.PostRepo;
import com.api.BlogApplication.Services.CommentService;
import com.api.BlogApplication.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImple implements CommentService
{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(Comment comment, int postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        post.getComments().add(comment);
        comment.setPost(post);
        Comment comment1 = commentRepo.save(comment);

        CommentDto commentDto = modelMapper.map(comment1,CommentDto.class);

        return commentDto;
    }

    @Override
    public void delete(int commentId)
    {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        Post post = comment.getPost();
        post.getComments().remove(comment);
        postRepo.save(post);
        commentRepo.delete(comment);
    }
}

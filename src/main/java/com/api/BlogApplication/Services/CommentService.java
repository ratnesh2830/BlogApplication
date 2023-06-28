package com.api.BlogApplication.Services;

import com.api.BlogApplication.Entities.Comment;
import com.api.BlogApplication.Payloads.CommentDto;

public interface CommentService
{
    CommentDto createComment(Comment comment,int postId);
    void delete(int commentId);
}

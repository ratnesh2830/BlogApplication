package com.api.BlogApplication.Repositories;

import com.api.BlogApplication.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer>
{

}

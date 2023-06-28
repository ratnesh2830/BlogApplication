package com.api.BlogApplication.Repositories;

import com.api.BlogApplication.Entities.Category;
import com.api.BlogApplication.Entities.Post;
import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer>
{
        List<Post> findByCategory(Category category);
        List<Post> findByUser(User user);
        List<Post> findByTitleContaining(String keyword);
}


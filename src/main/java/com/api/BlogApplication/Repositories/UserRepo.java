package com.api.BlogApplication.Repositories;

import com.api.BlogApplication.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Integer>
{
    User findByEmail(String userName);
}

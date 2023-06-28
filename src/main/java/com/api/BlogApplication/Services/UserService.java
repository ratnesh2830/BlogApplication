package com.api.BlogApplication.Services;

import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Payloads.UserDto;

import java.util.List;

public interface UserService {

   UserDto createUser(User user);
   UserDto updateUser(User user,int userId);
   UserDto getUserById(int userId);
   List<UserDto> getAllUsers();
   void delete(int userId);

}

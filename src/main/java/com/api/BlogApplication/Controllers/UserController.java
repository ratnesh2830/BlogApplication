package com.api.BlogApplication.Controllers;

import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Payloads.UserDto;
import com.api.BlogApplication.Services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user)
    {

       UserDto userDto =  userService.createUser(user);

       return  ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody User user,@PathVariable("userId") int id)
    {
        UserDto userDto = userService.updateUser(user,id);

        return  ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") int id)
    {
        UserDto userDto = userService.getUserById(id);
        return  ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getSingleUser()
    {
        List<UserDto> users= userService.getAllUsers();
        return  ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") int id)
    {
          userService.delete(id);

          return  ResponseEntity.ok().body("User Successfully Deleted");
    }
}

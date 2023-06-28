package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Entities.Role;
import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Exceptions.ResourceNotFoundException;
import com.api.BlogApplication.Payloads.UserDto;
import com.api.BlogApplication.Repositories.RoleRepo;
import com.api.BlogApplication.Repositories.UserRepo;
import com.api.BlogApplication.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImple implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(User user)
    {
        Role role = roleRepo.findById(501).get();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        User savedUser = userRepo.save(user);

        UserDto userDto= this.userToUserDto(savedUser);


        return userDto;
    }

    @Override
    public UserDto updateUser(User user, int userId)
    {
        User user1=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

//        Optional<User> optional=userRepo.findById(userId);
//                if(optional.isEmpty())
//                    throw new ResourceNotFoundException(userId);
//
//        User user1 = optional.get();


        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user1);

        UserDto userDto= userToUserDto(savedUser);

        return userDto;
    }

    @Override
    public UserDto getUserById(int userId)
    {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        UserDto userDto = userToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        Iterable<User> users = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = userToUserDto(user);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    public void delete(int userId)
    {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepo.delete(user);
    }

    public UserDto userToUserDto(User user)
    {
        UserDto userDto = modelMapper.map(user,UserDto.class);



//        userDto.setId(user.getUser_id());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }
}

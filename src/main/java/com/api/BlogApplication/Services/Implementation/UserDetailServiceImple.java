package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImple implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        User user = userRepo.findByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found: " + username);
        }


        return user;
    }
}

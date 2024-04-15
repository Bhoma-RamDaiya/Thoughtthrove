package com.ThoughtTrove.securityPak;

import com.ThoughtTrove.Entity.User;
import com.ThoughtTrove.Exceptions.ResourceNotFoundException;
import com.ThoughtTrove.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
  private   UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundException("User" , "username"+username , 0));
        //this is for temprary i should have to make the new Exception for it
        return user;
    }
}

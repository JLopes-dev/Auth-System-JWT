package com.auth.services;

import com.auth.DTOs.PasswordDTO;
import com.auth.DTOs.UserDTO;
import com.auth.models.User;
import com.auth.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public UserDTO verifyUserWithJwt(HttpServletRequest request, PasswordDTO passwordDTO) {
        String token = jwtService.getHeader(request);
        if (token != null){
            String subject = jwtService.verifyTokenJwt(token);
            User user = (User) loadUserByUsername(subject);
            String passwordHash = new BCryptPasswordEncoder().encode(passwordDTO.newPassword());
            user.updatePassword(passwordHash);
            return new UserDTO(user.getUsername(), user.getPassword());
        }
        return null;
    }

    public User getUserByTokenJwt(HttpServletRequest request){
        String header = jwtService.getHeader(request);
        String username = jwtService.verifyTokenJwt(header);
        return (User) repository.findByUsername(username);
    }
}

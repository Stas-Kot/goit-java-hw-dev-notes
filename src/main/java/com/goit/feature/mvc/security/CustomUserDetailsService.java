package com.goit.feature.mvc.security;


import com.goit.feature.mvc.user.User;
import com.goit.feature.mvc.user.UserDetailsImpl;
import com.goit.feature.mvc.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return UserDetailsImpl.build(user);
    }
}

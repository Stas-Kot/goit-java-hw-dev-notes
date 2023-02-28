package com.goit.feature.mvc.user;

import com.goit.feature.exceptions.NotValidPasswordException;
import com.goit.feature.exceptions.NotValidUserNameException;
import com.goit.feature.exceptions.UserAlreadyExistException;
import com.goit.feature.mvc.user.roles.EUserRole;
import com.goit.feature.mvc.user.views.EViewType;
import com.goit.feature.utils.Validation;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final Validation validation;
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public void printAllUsers(){
        System.out.println("repository.findAll() = " + repository.findAll());
    }

    public synchronized User save(User user) throws UserAlreadyExistException {
        if (user.getUsername() == null || !validation.isValidName(user.getUsername())) {
            throw new NotValidUserNameException("Not valid username");
        }

        if (user.getPassword() == null || !validation.isValidPassword(user.getPassword())) {
            throw new NotValidPasswordException("Not valid password");
        }

        if (isUserExists(user.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: "
                    + user.getUsername());
        }

        user.setRole(EUserRole.ROLE_USER);
        user.setViewType(EViewType.LIST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    private boolean isUserExists(String username) {
        return repository.findByUsername(username) != null;
    }


    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id=" + id + " not found"));
    }

    public void setViewType(String username, String viewType) {
        User user = repository.findByUsername(username);
        user.setViewType(EViewType.valueOf(viewType));
        repository.save(user);
    }
}

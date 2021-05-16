package io.medicorum.auth.services;

import io.medicorum.auth.exceptions.EmailTakenException;
import io.medicorum.auth.exceptions.ResourceNotFoundException;
import io.medicorum.auth.exceptions.UsernameTakenException;
import io.medicorum.auth.models.Role;
import io.medicorum.auth.models.User;
import io.medicorum.auth.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    //private UserEventSender userEventSender;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder
                       ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        //this.userEventSender = userEventSender;
    }

    public List<User> findAll() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        log.info("retrieving user {}", username);
        return userRepository.findByUsername(username);
    }

    public List<User> findByUsernameIn(List<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    public User registerUser(User user) {
        log.info("registering user {}", user.getUsername());

        if(userRepository.existsByUsername(user.getUsername())) {
            log.warn("username {} has already been taken.", user.getUsername());

            throw new UsernameTakenException(
                    String.format("username %s has already been taken", user.getUsername()));
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} has already been taken.", user.getEmail());

            throw new EmailTakenException(
                    String.format("email %s has already been taken", user.getEmail()));
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAssignedRoles(new HashSet<>());
        user.getAssignedRoles().add(Role.USER);

        User savedUser = userRepository.save(user);
        //userEventSender.sendUserCreated(savedUser);

        return savedUser;
    }

    public User updateProfilePicture(String uri, String id) {
        log.info("update profile picture {} for user {}", uri, id);

        return userRepository
                .findById(id)
                .map(user -> {
                    String oldProfilePic = user.getUserProfile().getProfilePictureUrl();
                    user.getUserProfile().setProfilePictureUrl(uri);
                    User savedUser = userRepository.save(user);

                    //userEventSender.sendUserUpdated(savedUser, oldProfilePic);

                    return savedUser;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("user id %s not found", id)));
    }
}

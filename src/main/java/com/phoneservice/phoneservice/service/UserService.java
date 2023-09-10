package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.entity.UserRole;
import com.phoneservice.phoneservice.exception.UserAlreadyExistException;
import com.phoneservice.phoneservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void newClient(User user) throws UserAlreadyExistException {
        if(emailExists(user.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        user.setUserRole(UserRole.CLIENT);
        userRepository.save(user);

    }

    public boolean emailExists(String email) {
        return userRepository.findClientByEmail(email) != null;
    }

    public User findByEmail(String email) {
        return userRepository.findClientByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findClientById(Long id){
        return userRepository.findClientById(id);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

}

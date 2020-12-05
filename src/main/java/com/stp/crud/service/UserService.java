package com.stp.crud.service;

import com.stp.crud.model.User;
import com.stp.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRepr userRepr) {
        User user = new User();
        user.setName(userRepr.getName());
        user.setRole(userRepr.getRole());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> customSelect(){
        return userRepository.findAllByCustomQuery();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void updUserName(String userName, Long userId){
        userRepository.updUser(userName, userId);
    }
}

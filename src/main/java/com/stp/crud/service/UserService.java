package com.stp.crud.service;

import com.stp.crud.model.User;
import com.stp.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

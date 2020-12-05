package com.stp.crud.config;

import com.stp.crud.model.User;
import com.stp.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Collections;

@Service("CustomUserDetailsService")
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserAuthService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = repository.findByNameCustomQuery(name);
        if(user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return SecurityUser.getUser(user);

//        return repository.findByName(name)
//                .map(user -> new User(
//                        user.getName(),
//                        user.getPassword(),
//                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
//                ))
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}

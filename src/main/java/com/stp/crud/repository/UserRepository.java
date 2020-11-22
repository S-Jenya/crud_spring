package com.stp.crud.repository;

import com.stp.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.name =: name where u.id_user =: id_user")
    void updUser(@Param("name") String userName, @Param("id_user") Long userId);

}

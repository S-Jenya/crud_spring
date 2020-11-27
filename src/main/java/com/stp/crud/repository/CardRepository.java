package com.stp.crud.repository;

import com.stp.crud.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select user.cards from User user where user.id_user = ?1")
    List<Card> findCardByUserIdCustomQuery(Long userId);


    @Query("select card from Card card where card.user.id_user = ?1")
    List<Card> findTwoCustomQuery(Long userId);

//    @Query("select i from Institution i left join i.cards")
//    List<Card> findTwoCustomQuery(Long userId);


//    @Query("select t from Test t join User u where u.username = :username")
//    List<Test> findAllByUsername(@Param("username")String username);

}

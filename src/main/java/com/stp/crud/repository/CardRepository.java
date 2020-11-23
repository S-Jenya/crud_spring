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


}

package com.giomodiogo.repository;

import com.giomodiogo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> findByStatus(String status);
}

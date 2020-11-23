package com.giomodiogo.repository;

import com.giomodiogo.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

    List<Deck> findByNameIgnoreCaseContaining(String name);
}

package com.giomodiogo.service;

import com.giomodiogo.model.Deck;
import com.giomodiogo.repository.DeckRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeckService {

    private final DeckRepository deckRepository;

    public List<Deck> list() {
        return deckRepository.findAll();
    }

    public List<Deck> findByName(String name) {
        return deckRepository.findByNameIgnoreCaseContaining(name);
    }

    public Optional<Deck> getById(final Integer id) {
        return deckRepository.findById(id);
    }

    public void delete(final Deck deck) {
        deckRepository.delete(deck);
    }

    public Deck save(final Deck deck) {
        return deckRepository.save(deck);

    }
}

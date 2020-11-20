package com.giomodiogo.service;

import com.giomodiogo.model.Deck;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DeckService {

    private static List<Deck> decks = Stream.of(
            Deck.builder().id(1).name("English").build(),
            Deck.builder().id(2).name("French").build()
    ).collect(Collectors.toList());

    public List<Deck> list() {
        return decks;
    }

    public Optional<Deck> getById(final Integer id) {
        return decks.stream().filter(deck -> deck.getId() == id).findFirst();
    }

    public void delete(final Integer id) {
        decks.removeIf(deck -> deck.getId() == id);
    }

    public Deck save(final Deck deck) {
        Integer id = decks.size() + 1;
        deck.setId(id);
        decks.add(deck);
        return deck;
    }
}

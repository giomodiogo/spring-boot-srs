package com.giomodiogo.controller;

import com.giomodiogo.model.Deck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("decks")
@RequiredArgsConstructor
public class DeckController {

    @GetMapping
    public ResponseEntity<List<Deck>> list() {
        return ResponseEntity.ok(
                Stream.of(
                        Deck.builder().id(1).name("English").build(),
                        Deck.builder().id(2).name("French").build()
                ).collect(Collectors.toList()));
    }
}

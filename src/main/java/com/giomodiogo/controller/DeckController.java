package com.giomodiogo.controller;

import com.giomodiogo.model.Deck;
import com.giomodiogo.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService service;

    // TODO PUT

    @GetMapping
    public ResponseEntity<List<Deck>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return service.getById(id).map(deck -> ResponseEntity.ok(deck)).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Deck> save(@RequestBody @Valid Deck deck) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.save(deck).getId()).toUri();

        // Headers -> Location -> http://localhost:8080/decks/3
        return ResponseEntity.created(location).build();
    }
}

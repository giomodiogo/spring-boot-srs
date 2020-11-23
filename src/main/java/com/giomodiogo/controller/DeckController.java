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

@RestController
@RequestMapping("decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService service;

    @GetMapping
    public ResponseEntity<List<Deck>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return service.getById(id).map(deck -> ResponseEntity.ok(deck)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<List<Deck>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return service.getById(id).map(deck -> {
            service.delete(deck);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody @Valid Deck deck) {
        return service.getById(id).map(deckFound -> {
            deck.setId(id);
            service.save(deck);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Deck> save(@RequestBody @Valid Deck deck) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.save(deck).getId()).toUri();

        // Headers -> Location -> http://localhost:8080/decks/3
        return ResponseEntity.created(location).build();
    }
}

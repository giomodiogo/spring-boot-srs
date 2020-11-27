package com.giomodiogo.controller;

import com.giomodiogo.model.Card;
import com.giomodiogo.service.CardService;
import com.giomodiogo.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;
    private final DeckService deckService;

    @GetMapping
    public ResponseEntity<List<Card>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return service.getById(id).map(card -> ResponseEntity.ok(card)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/status/{status}")
    public ResponseEntity<List<Card>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.findAllByStatus(status));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return service.getById(id).map(card -> {
            service.delete(card);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody @Valid Card card) {
        return service.getById(id).map(deckFound -> {
            card.setId(id);
            service.save(card);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody @Valid Card card) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.save(card).getId()).toUri();

        // Headers -> Location -> http://localhost:8080/cards/3
        return ResponseEntity.created(location).build();
    }
}

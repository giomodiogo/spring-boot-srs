package com.giomodiogo.service;

import com.giomodiogo.model.Card;
import com.giomodiogo.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> list() {
        return cardRepository.findAll();
    }

    public List<Card> findAllByStatus(String status) {
        return cardRepository.findByStatus(status);
    }

    public Optional<Card> getById(final Integer id) {
        return cardRepository.findById(id);
    }

    public void delete(final Card card) {
        cardRepository.delete(card);
    }

    public Card save(final Card card) {
        return cardRepository.save(card);

    }
}

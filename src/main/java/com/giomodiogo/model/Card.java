package com.giomodiogo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Lob
    private String front;

    @NotNull
    @NotEmpty
    @Lob
    private String back;

    //TODO Enum column
    @NotNull
    @NotEmpty
    @Column(length = 10)
    private String status;

    @ManyToOne(targetEntity = Deck.class)
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;
}
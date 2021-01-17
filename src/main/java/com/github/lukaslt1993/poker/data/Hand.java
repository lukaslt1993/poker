package com.github.lukaslt1993.poker.data;

import java.util.Set;

public class Hand {

    private Set<Card> cards;

    public Hand(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }
}

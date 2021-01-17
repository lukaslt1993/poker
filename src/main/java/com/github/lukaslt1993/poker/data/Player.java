package com.github.lukaslt1993.poker.data;

import com.github.lukaslt1993.poker.util.rank.RankWithDuplicates;

import java.util.List;
import java.util.Map;

public class Player {

    private Hand hand;
    private Map<Integer, List<List<Card>>> duplicates;

    public Player(Hand hand) {
        setHand(hand);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        duplicates = RankWithDuplicates.computeDuplicatesMap(hand);
    }

    public Hand getHand() {
        return hand;
    }

    public Map<Integer, List<List<Card>>> getDuplicates() {
        return duplicates;
    }
}

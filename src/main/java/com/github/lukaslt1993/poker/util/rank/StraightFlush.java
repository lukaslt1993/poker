package com.github.lukaslt1993.poker.util.rank;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;

import java.util.ArrayList;
import java.util.Collection;

public class StraightFlush extends BaseRank {

    public StraightFlush(Hand hand) {
        super(hand);
    }

    @Override
    public Collection<Card> getMatch() {
        Collection<Card> straight = new Straight(hand).getMatch();
        return straight.isEmpty() || new Flush(hand).getMatch().isEmpty() ? new ArrayList<>() : straight;
    }

}

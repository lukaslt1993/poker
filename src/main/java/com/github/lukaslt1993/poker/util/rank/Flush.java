package com.github.lukaslt1993.poker.util.rank;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Flush extends BaseRank {

    public Flush(Hand hand) {
        super(hand);
    }

    @Override
    public Collection<Card> getMatch() {
        Iterator<Card> it = hand.getCards().iterator();
        char suite = it.next().getSuite();

        for (int i = 0; i < 4; i++) {
            if (it.next().getSuite() != suite) {
                return new ArrayList<>();
            }
        }

        return hand.getCards();
    }

}

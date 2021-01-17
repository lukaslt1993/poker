package com.github.lukaslt1993.poker.util.rank;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Straight extends BaseRank {

    public Straight(Hand hand) {
        super(hand);
    }

    @Override
    public Collection<Card> getMatch() {
        Iterator<Card> it = hand.getCards().iterator();
        int weight = it.next().getWeight();

        for (int i = 0; i < 4; i++) {
            if (it.next().getWeight() != ++weight) {
                return new ArrayList<>();
            }
        }

        return hand.getCards();
    }

}

package com.github.lukaslt1993.poker.util.rank;

import com.github.lukaslt1993.poker.data.Hand;

public abstract class BaseRank implements Rank {

    protected Hand hand;

    public BaseRank(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}

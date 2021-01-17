package com.github.lukaslt1993.poker.util;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;
import com.github.lukaslt1993.poker.data.Player;
import com.github.lukaslt1993.poker.util.rank.BaseRank;
import com.github.lukaslt1993.poker.util.rank.Flush;
import com.github.lukaslt1993.poker.util.rank.Rank;
import com.github.lukaslt1993.poker.util.rank.RankWithDuplicates;
import com.github.lukaslt1993.poker.util.rank.Straight;
import com.github.lukaslt1993.poker.util.rank.StraightFlush;

import java.util.Collection;
import java.util.stream.Collectors;

public class WinnerCalculator {

    private Player player1;
    private Player player2;

    private Rank[] ranks = new Rank[]{
            new StraightFlush(null), new RankWithDuplicates(null, new int[]{4}),
            new RankWithDuplicates(null, new int[]{2, 3}), new Flush(null),
            new Straight(null), new RankWithDuplicates(null, new int[]{3}),
            new RankWithDuplicates(null, new int[]{2, 2}),
            new RankWithDuplicates(null, new int[]{2})
    };

    public WinnerCalculator(Player player1, Player player2) {
        setPlayers(player1, player2);
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getWinner() {
        for (Rank rank : ranks) {
            Collection<Card> player1Match = getCards(rank, player1);
            Collection<Card> player2Match = getCards(rank, player2);

            if (!player1Match.isEmpty() && player2Match.isEmpty()) {
                return 1;
            }

            if (player1Match.isEmpty() && !player2Match.isEmpty()) {
                return 2;
            }

            if (player1Match.isEmpty() && player2Match.isEmpty()) {
                continue;
            }

            int winner = getPlayerHavingMaxCard(player1Match, player2Match);

            if (winner != 0) {
                return winner;
            }

            winner = getPlayerHavingMaxCard(
                    getNonRankCards(player1.getHand(), player1Match), getNonRankCards(player2.getHand(), player2Match));

            if (winner != 0) {
                return winner;
            }
        }

        return getPlayerHavingMaxCard(player1.getHand().getCards(), player2.getHand().getCards());
    }

    private Collection<Card> getCards(Rank rank, Player player) {
        if (rank instanceof BaseRank) {
            ((BaseRank) rank).setHand(player.getHand());
            return rank.getMatch();
        }

        ((RankWithDuplicates) rank).setDuplicatesMap(player.getDuplicates());
        return rank.getMatch();
    }

    private int getMaxCard(Collection<Card> cards) {
        Card[] arr = new Card[cards.size()];
        return cards.toArray(arr)[cards.size() - 1].getWeight();
    }

    private int getWinner(int weight1, int weight2) {
        if (weight1 > weight2) {
            return 1;
        } else if (weight2 > weight1) {
            return 2;
        } else {
            return 0;
        }
    }

    private int getPlayerHavingMaxCard(Collection<Card> player1Cards, Collection<Card> player2Cards) {
        return getWinner(getMaxCard(player1Cards), getMaxCard(player2Cards));
    }

    private Collection<Card> getNonRankCards(Hand hand, Collection<Card> rank) {
        return hand.getCards().stream().filter(
                card -> !rank.contains(card)).collect(Collectors.toList());
    }

}

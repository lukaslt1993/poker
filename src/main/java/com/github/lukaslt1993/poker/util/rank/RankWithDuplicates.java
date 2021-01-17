package com.github.lukaslt1993.poker.util.rank;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RankWithDuplicates implements Rank {

    private Map<Integer, List<List<Card>>> duplicatesMap;
    private int[] duplicates;

    public RankWithDuplicates(Map<Integer, List<List<Card>>> duplicatesMap, int[] duplicates) {
        this.duplicates = duplicates;
        this.duplicatesMap = duplicatesMap;
    }

    public int[] getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(int[] duplicates) {
        this.duplicates = duplicates;
    }

    public Map<Integer, List<List<Card>>> getDuplicatesMap() {
        return duplicatesMap;
    }

    public void setDuplicatesMap(Map<Integer, List<List<Card>>> duplicatesMap) {
        this.duplicatesMap = duplicatesMap;
    }

    @Override
    public Collection<Card> getMatch() {
        return joinDuplicatesMapToList();
    }

    public static Map<Integer, List<List<Card>>> computeDuplicatesMap(Hand hand) {
        Map<Integer, List<Card>> rankMap = new LinkedHashMap<>();

        hand.getCards().forEach(card -> {

            if (rankMap.containsKey(card.getWeight())) {
                rankMap.get(card.getWeight()).add(card);
            } else {
                rankMap.put(card.getWeight(), new ArrayList<>(){{this.add(card);}});
            }

        });

        Map<Integer, List<List<Card>>> result = new HashMap<>();

        rankMap.entrySet().forEach(entry -> {

            List<Card> cards = entry.getValue();

            if (cards.size() > 1) {
                if (result.containsKey(cards.size())) {
                    result.get(cards.size()).add(cards);
                } else {
                    result.put(cards.size(), new ArrayList<>(){{this.add(cards);}});
                }
            }

        });

        return result;
    }

    private Collection<Card> joinDuplicatesMapToList() {
        Set<Card> result = new TreeSet<>();

        for (int duplicateCount : duplicates) {
            List<List<Card>> duplicatesList = duplicatesMap.get(duplicateCount);

            if (duplicatesList == null || duplicatesList.size() != duplicates.length) {
                return new ArrayList<>();
            }

            for (List<Card> cards : duplicatesList) {
                result.addAll(cards);
            }

        }

        return result;
    }

}

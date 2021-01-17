package com.github.lukaslt1993.poker.util;

import com.github.lukaslt1993.poker.data.Card;
import com.github.lukaslt1993.poker.data.Hand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InputReader {

    public List<Set<Hand>> read(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Set<Hand>> result = new ArrayList<>();

        for (String line : lines) {
            Set<Hand> hands = new LinkedHashSet<>();
            String[] cards = line.split(" ");
            int i = 0;

            while (i < cards.length) {
                Hand hand = new Hand(new TreeSet<>());

                do {
                    hand.add(parseCard(cards[i++]));
                } while (i % 5 != 0);

                hands.add(hand);
            }

            result.add(hands);
        }

        return result;
    }

    private Card parseCard(String card) {
        int weight;
        char firstSymbol = card.charAt(0);
        char suite = card.charAt(1);

        switch (firstSymbol) {
            case 'A' :
                weight = 14;
                break;
            case 'K' :
                weight = 13;
                break;
            case 'Q' :
                weight = 12;
                break;
            case 'J' :
                weight = 11;
                break;
            case 'T' :
                weight = 10;
                break;
            default :
                weight = Character.getNumericValue(firstSymbol);
        }

        return new Card(weight, suite);
    }

}

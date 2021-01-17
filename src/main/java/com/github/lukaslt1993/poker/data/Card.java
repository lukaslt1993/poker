package com.github.lukaslt1993.poker.data;

public class Card implements Comparable<Card> {

    private int weight;
    private char suite;

    public Card(int weight, char suite) {
        this.weight = weight;
        this.suite = suite;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public char getSuite() {
        return suite;
    }

    public void setSuite(char suite) {
        this.suite = suite;
    }

    @Override
    public int compareTo(Card otherCard) {
        int result = Integer.compare(weight, otherCard.getWeight());
        return result == 0 ? suite - otherCard.getSuite() : result;
    }
}

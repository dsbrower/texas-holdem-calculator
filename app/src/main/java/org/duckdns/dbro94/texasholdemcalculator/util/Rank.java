package org.duckdns.dbro94.texasholdemcalculator.util;

public enum Rank {
    STRAIGHT_FLUSH(9),
    FOUR_OF_A_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    PAIR(2),
    HIGH_CARD(1);

    private int value;

    Rank(int value) {
        this.value = value;
    }

    public int getRankValue() {
        return value;
    }
}

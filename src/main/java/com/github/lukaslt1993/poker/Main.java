package com.github.lukaslt1993.poker;

import com.github.lukaslt1993.poker.data.Hand;
import com.github.lukaslt1993.poker.data.Player;
import com.github.lukaslt1993.poker.util.InputReader;
import com.github.lukaslt1993.poker.util.WinnerCalculator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static int getWins(Path path) throws IOException {
        List<Set<Hand>> handsList = new InputReader().read(path);
        AtomicInteger wins = new AtomicInteger();
        WinnerCalculator calc = new WinnerCalculator(null, null);

        handsList.forEach(set -> {

            Iterator<Hand> it = set.iterator();
            Player player1 = new Player(it.next());
            Player player2 = new Player(it.next());
            calc.setPlayers(player1, player2);

            if (calc.getWinner() == 1) {
                wins.getAndIncrement();
            }

        });

        return wins.get();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/poker.txt");
        System.out.println(getWins(path));
    }

}

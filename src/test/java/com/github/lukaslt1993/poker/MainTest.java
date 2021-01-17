package com.github.lukaslt1993.poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class MainTest {

    @Test
    void shouldReturn3() throws IOException {
        Assertions.assertEquals(Main.getWins(Paths.get("src/test/resources/poker.txt")), 3);
    }

}

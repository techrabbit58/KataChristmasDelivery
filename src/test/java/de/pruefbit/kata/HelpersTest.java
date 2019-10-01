package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static de.pruefbit.kata.Helpers.*;
import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    void mustBePositive_throws_on_zero_or_negative() {
        assertThrows(IllegalArgumentException.class, () -> mustBePositive(0));
        assertThrows(IllegalArgumentException.class, () -> mustBePositive(-1));
    }

    @Test
    void mayNotBeNegative_throws_on_negative() {
        assertThrows(IllegalArgumentException.class, () -> mustNotBeNegative(-1));
    }

    @Test
    void mustNotBeEmpty_throws_on_empty_list() {
        List<String> stringList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> mustNotBeEmpty(stringList));
    }
}
package de.pruefbit.kata;

import java.util.*;
import java.util.stream.Collectors;

import static de.pruefbit.kata.Helpers.mustNotBeNegative;

class ToyMachine {
    private final Map<String, Integer> families = new HashMap<>();

    Present givePresent() {
        return new Present(null);
    }
}

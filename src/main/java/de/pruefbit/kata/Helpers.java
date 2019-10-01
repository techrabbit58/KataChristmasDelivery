package de.pruefbit.kata;

import java.util.List;

class Helpers {

    static int mustBePositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("number of team members must be positive");
        }
        return n;
    }

    static int mayNotBeNegative(int n) {
        if (n < 0) {
            throw new RuntimeException("number may not be negative");
        }
        return n;
    }

    static List<String> mustNotBeEmpty(List<String> stringList) {
        if (stringList.isEmpty()) {
            throw new RuntimeException("list of strings must not be empty");
        }
        return stringList;
    }
}

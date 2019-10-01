package de.pruefbit.kata;

//import java.util.List;

class Helpers {

    static int mustBePositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("number must be positive");
        }
        return n;
    }

    static int mustNotBeNegative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number may not be negative");
        }
        return n;
    }

//    static List<String> mustNotBeEmpty(List<String> stringList) {
//        if (stringList.isEmpty()) {
//            throw new IllegalArgumentException("list of strings must not be empty");
//        }
//        return stringList;
//    }
}

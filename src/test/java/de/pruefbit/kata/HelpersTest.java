package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.pruefbit.kata.Helpers.*;
import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    void has_private_default_constructor() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<Helpers> helpers = Helpers.class.getDeclaredConstructor();
            boolean access = helpers.isAccessible();
            helpers.setAccessible(true);
            helpers.newInstance();
            helpers.setAccessible(access);
        });
    }

    @Test
    void mustBePositive_throws_on_zero_or_negative() {
        assertThrows(IllegalArgumentException.class, () -> mustBePositive(0));
        assertThrows(IllegalArgumentException.class, () -> mustBePositive(-1));
    }

    @Test
    void mustBePositive_returns_argument_if_positive() {
        final int POSITIVE = 42;
        assertEquals(POSITIVE, mustBePositive(POSITIVE));
    }

    @Test
    void mustNotBeNegative_throws_on_negative() {
        assertThrows(IllegalArgumentException.class, () -> mustNotBeNegative(-1));
    }

    @Test
    void mustNotBeNegative_returns_argument_if_not_negative() {
        final int ZERO = 0;
        final int POSITIVE = 42;
        assertEquals(ZERO, mustNotBeNegative(ZERO));
        assertEquals(POSITIVE, mustNotBeNegative(POSITIVE));
    }

    @Test
    void mustNotBeEmpty_throws_on_empty_list() {
        List<String> stringList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> mustNotBeEmpty(stringList));
    }

    @Test
    void mustNotBeEmpty_does_not_throw_if_nonempty_list() {
        List<String> stringList = new ArrayList<>();
        stringList.add("foo");
        assertDoesNotThrow(() -> mustNotBeEmpty(stringList));
        assertEquals(1, stringList.size());
    }

    @Test
    void mustNotBeEmpty_returns_argument_if_nonempty_list() {
        List<String> stringList = Arrays.asList("foo", "bar", "baz");
        assertEquals(stringList, mustNotBeEmpty(stringList));
    }

    @Test
    void mustNotBeEmpty_throws_on_empty_string() {
        String s = "";
        assertThrows(IllegalArgumentException.class, () -> mustNotBeEmpty(s));
    }

    @Test
    void mustNotBeEmpty_does_not_throw_if_nonempty_string() {
        String s = "foo";
        assertDoesNotThrow(() -> mustNotBeEmpty(s));
    }

    @Test
    void mustNotBeEmpty_returns_argument_if_nonempty_string() {
        String s = "foo";
        assertEquals(s, mustNotBeEmpty(s));
    }
}
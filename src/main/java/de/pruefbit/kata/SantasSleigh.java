package de.pruefbit.kata;

import java.util.Objects;

interface SantasSleigh {

    default void pack(Present present) {
        Objects.requireNonNull(present);
    }
}

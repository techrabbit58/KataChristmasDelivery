package de.pruefbit.kata;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Queue;

class Sleigh implements SantasSleigh {
    private final Queue<String> cargoList;

    Sleigh(Queue<String> cargoList) {
        this.cargoList = cargoList;
    }

    @Override
    public void pack(Present present) {
        cargoList.add(ZonedDateTime.now().toString() + " got " + Objects.requireNonNull(present).toString());
    }
}

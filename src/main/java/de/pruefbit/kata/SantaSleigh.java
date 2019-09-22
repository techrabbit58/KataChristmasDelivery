package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.List;

public final class SantaSleigh implements SantasSleigh {

    static private final SantaSleigh theSleigh = new SantaSleigh();

    private final List<Present> cargo = new ArrayList<>();

    private SantaSleigh() {
    }

    static synchronized SantaSleigh getSleigh() {
        return theSleigh;
    }

    long cargoSize() {
        return cargo.size();
    }

    @Override
    public void pack(Present present) {
        cargo.add(present);
    }
}

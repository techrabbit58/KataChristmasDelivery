package de.pruefbit.kata;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Elves always ask for a new present, do carry it to its destination,
 * drop it, and then call back for a job.
 * In case they receive a "null" present from their "loader", they
 * stop working immediately.
 * An elf fed with a "null" present will never call back.
 */
class Elf implements Runnable {
    static private final int WALK_TIME = 50; // in millis

    private final Consumer<Present> dropper;
    private final Supplier<Present> loader;
    private final Consumer<Elf> callback;

    Elf(Supplier<Present> loader, Consumer<Present> dropper, Consumer<Elf> callback) {
        this.loader = Objects.requireNonNull(loader);
        this.dropper = Objects.requireNonNull(dropper);
        this.callback = Objects.requireNonNull(callback);
    }

    @Override
    public void run() {
        Present present = loader.get();
        if (present != null) {
            walk();
            dropper.accept(present);
            walk();
            callback.accept(this);
        }
    }

    private void walk() {
        try {
            Thread.sleep(WALK_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + this.hashCode();
    }
}

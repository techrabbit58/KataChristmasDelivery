package de.pruefbit.kata;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Elf implements Runnable {

    static private final int MILLIS_TO_WALK = 10;

    private final Consumer<Present> dropper;
    private final Supplier<Present> loader;
    private final Runnable callback;

    Elf(Supplier<Present> loader, Consumer<Present> dropper, Runnable callback) {
        this.loader = Objects.requireNonNull(loader);
        this.dropper = Objects.requireNonNull(dropper);
        this.callback = Objects.requireNonNull(callback);
    }

    @Override
    public void run() {
        Present present = loader.get();
        walk();
        dropper.accept(present);
        walk();
        callback.run();
    }

    private void walk() {
        try {
            Thread.sleep(MILLIS_TO_WALK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package de.pruefbit.kata;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Elf implements Runnable {

    static private final int MILLIS_TO_WALK = 50;

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
        Present present = Objects.requireNonNull(loader.get(), "elf did not get a present");
        System.out.println(this + " carries " + present);
        walk();
        dropper.accept(present);
        walk();
        callback.accept(this);
        System.out.println(this + " is now idle");
    }

    private void walk() {
        try {
            Thread.sleep(MILLIS_TO_WALK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

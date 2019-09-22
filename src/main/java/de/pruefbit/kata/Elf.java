package de.pruefbit.kata;

import java.util.function.Consumer;
import java.util.function.Supplier;

class Elf implements Runnable {

    private final Consumer<Present> dropper;
    private final Supplier<Present> loader;

    Elf(Supplier<Present> loader, Consumer<Present> dropper) {
        this.loader = loader;
        this.dropper = dropper;
    }

    @Override
    public void run() {
        Present present = loader.get();
        walk();
        dropper.accept(present);
        walk();
        callback();
    }

    private void callback() {
        System.out.println("Elf: I'm available to transport another present.");
    }

    private void walk() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

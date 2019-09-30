package de.pruefbit.kata;

class ToyMachine {

    private final int numPresentsPerFamily;

    ToyMachine() {
        numPresentsPerFamily = 0;
    }

    ToyMachine(int numPresentsPerFamily) {
        this.numPresentsPerFamily = numPresentsPerFamily;
    }

    Present givePresent() {
        Present present;
        if (numPresentsPerFamily > 0) {
            present = new Present("Miller");
        }
        else {
            present = new Present();
        }
        return present;
    }
}

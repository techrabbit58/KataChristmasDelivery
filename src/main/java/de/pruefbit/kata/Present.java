package de.pruefbit.kata;

/**
 * A present is an opaque thing, that were produced by a toy machine.
 * So it will probably contain a toy. Who knows?!
 */
class Present {
    private final String family;

    Present() {
        family = null;
    }

    Present(String family) {
        this.family = family;
    }

    String getFamily() {
        return family;
    }

    @Override
    public String toString() {
        return "Present{id=" + hashCode() + ", family='" + family + "\'}";
    }
}

package de.pruefbit.kata;

/**
 * A present is an opaque thing, that were produced by a toy machine.
 * So it will probably contain a toy. Who knows?!
 */
class Present {
    private final String family;
    private final String origin;
    private String transporter = null;

    Present() {
        family = null;
        origin = null;
    }

    Present(String family) {
        this.family = family;
        this.origin = null;
    }

    Present(String family, String origin) {
        this.family = family;
        this.origin = origin;
    }

    String getFamily() {
        return family;
    }

    void setTransporter(String transporter) {
        this.transporter = String.valueOf(transporter);
    }

    @Override
    public String toString() {
        return "Present{" +
                "id=" + hashCode() +
                ", origin='" + origin + '\'' +
                ", transporter='" + transporter + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}

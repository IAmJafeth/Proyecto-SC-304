package proyecto.sc.pkg304;

public enum TipoCaja {
    Rapida("Rápida"),
    Preferencial("Preferencial"),
    Regular("Regular");

    private final String displayName;

    TipoCaja(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

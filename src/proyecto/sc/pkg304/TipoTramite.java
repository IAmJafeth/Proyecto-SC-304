/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package proyecto.sc.pkg304;

/**
 *
 * @author jgarr
 */
public enum TipoTramite {
    DEPOSITOS("Depósitos"),
    RETIROS("Retiros"),
    CAMBIO_DIVISAS("Cambio de Divisas");

    private final String displayName;

    TipoTramite(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

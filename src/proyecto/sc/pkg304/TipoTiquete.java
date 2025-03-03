/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package proyecto.sc.pkg304;

/**
 *
 * @author jgarr
 */
public enum TipoTickete {
    // Enum utilizado como atributo en la clase Tiquete
    P("Preferencial"),
    A("Un Solo Trámite"),
    B("Dos o más trámites");
    
    private final String displayName;

    TipoTickete(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}

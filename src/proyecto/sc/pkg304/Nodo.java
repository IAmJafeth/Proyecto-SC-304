/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

import java.io.Serializable;

/**
 *
 * @author jafeth.garro
 */
public class Nodo implements Serializable{
    private Tiquete tiqete;
    private Nodo sig;

    public Nodo(Tiquete tiquete) {
        this.tiqete = tiquete;
    }

    public Tiquete geTiquete() {
        return tiqete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiqete = tiquete;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return "Nodo{" + "tiquete=" + tiqete + "}";
    }
    
    
}

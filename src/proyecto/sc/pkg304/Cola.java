/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

/**
 *
 * @author jafeth.garro
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public void encola(Tiquete tiquete) {
        Nodo nuevo = new Nodo(tiquete);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSig(nuevo);
            fin = nuevo;
        }
    }

    public Tiquete atiende() throws Exception{
        if (frente == null) { 
            throw new Exception("La cola está vacia");
        }
        Tiquete tiquete = frente.geTiquete();
        frente = frente.getSig();
        if (frente == null) { 
            fin = null;
        }
        return tiquete;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = frente;
        while (actual != null) {
            sb.append(actual.geTiquete().getNombre()).append(" -> ");
            actual = actual.getSig();
        }
        sb.append("null");
        return sb.toString();
    }
}
 
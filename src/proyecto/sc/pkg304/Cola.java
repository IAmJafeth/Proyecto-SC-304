package proyecto.sc.pkg304;

import java.io.Serializable;

/**
 *
 * @author jafeth.garro
 */
public class Cola implements Serializable{
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public boolean isEmpty() {
        return frente == null;
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
    
    public void imprimirDetalles() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            Nodo actual = frente;
            while (actual != null) {
                System.out.println(actual.geTiquete());
                actual = actual.getSig();
            }
        }
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
 
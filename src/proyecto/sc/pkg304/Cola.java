package proyecto.sc.pkg304;

/**
 *
 * @author jafeth.garro
 */
public class Cola{
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public Nodo getFrente() {
        return frente;
    }

    public void encola(Tiquete tiquete) {
        Nodo nuevo = new Nodo(tiquete);
        if (fin == null) {
            frente = nuevo;
        } else {
            fin.setSig(nuevo);
        }
        fin = nuevo;
    }

    public Tiquete atiende() throws Exception{
        if (frente == null) { 
            throw new Exception("La cola está vacia");
        }
        Tiquete tiquete = frente.getTiquete();
        frente = frente.getSig();
        if (frente == null) { 
            fin = null;
        }
        return tiquete;
    }
    
    public String imprimirDetalles() {
        String detalles = "";
        if (isEmpty()) {
            return "La cola está vacía.";
        } else {
            Nodo actual = frente;
            while (actual != null) {
                detalles += actual.getTiquete().getDetalles() + "\n";
                actual = actual.getSig();
            }
        }
        return detalles;
    }

    int size() {
        int contador = 0;
        Nodo actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.getSig();
        }
        return contador;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = frente;
        while (actual != null) {
            sb.append(actual.getTiquete().getNombre()).append(" -> ");
            actual = actual.getSig();
        }
        sb.append("null");
        return sb.toString();
    }
}
 
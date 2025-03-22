package proyecto.sc.pkg304;

public class Caja {
    private Cola cola;
    private Tiquete tiqueteActual;
    private String nombre;
    private TipoCaja tipoCaja;

    public Caja(String nombre, TipoCaja tipoCaja) {
        this.nombre = nombre;
        this.tipoCaja = tipoCaja;
        this.cola = new Cola();
    }

    public boolean isOcupada() {
        return tiqueteActual != null;
    }

    public void encola(Tiquete tiquete) {
        if (!isOcupada()) {
            tiqueteActual = tiquete;
        } else {
            cola.encola(tiquete);
        }
    }

    public int getTiquetesEnCola() {
        return cola.size();
    }

    public Tiquete atiende() throws Exception {
        if (tiqueteActual == null) {
            throw new Exception("No hay tiquetes por atender.");
        }

        Tiquete tiquete = tiqueteActual;
        if (cola.isEmpty()) {
            tiqueteActual = null;
        } else {
            tiqueteActual = cola.atiende();
        }
        tiquete.setHoraAtencion();
        return tiquete;
    }

    public String imprimirDetalles() {
        // crear un string agradable con los detalles de la caja
        String detalles = "Caja: " + nombre + " (" + tipoCaja + ")\n";
        if (isOcupada()) {
            detalles += "Atendiendo a:\n" + tiqueteActual.getDetalles() + "\n";
        } else {
            detalles += "Sin tiquetes en espera.\n";
        }

        if (!cola.isEmpty()) {
            detalles += "Tiquetes en cola:\n";
            Nodo actual = cola.getFrente();
            while (actual != null) {
                detalles += actual.getTiquete().getDetalles() + "\n";
                actual = actual.getSig();
            }
        }

        return detalles;
    }


    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    public Tiquete getTiqueteActual() {
        return tiqueteActual;
    }

    public void setTiqueteActual(Tiquete tiqueteActual) {
        this.tiqueteActual = tiqueteActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCaja getTipoCaja() {
        return tipoCaja;
    }

    public void setTipoCaja(TipoCaja tipoCaja) {
        this.tipoCaja = tipoCaja;
    }

    @Override
    public String toString() {
        return  "Caja: " + nombre + " (" + tipoCaja + ")\n";
    }
}

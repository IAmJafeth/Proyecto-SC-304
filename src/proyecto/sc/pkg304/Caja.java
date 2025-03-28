package proyecto.sc.pkg304;

public class Caja {
    private Tiquete tiqueteActual;
    private String nombre;
    private TipoCaja tipoCaja;

    public Caja(String nombre, TipoCaja tipoCaja) {
        this.nombre = nombre;
        this.tipoCaja = tipoCaja;
        tiqueteActual = null;
    }

    public boolean isOcupada() {
        return tiqueteActual != null;
    }

    public String imprimirDetalles() {
        String detalles = "Caja: " + this + "\n";
        if (isOcupada()) {
            detalles += "Atendiendo a: " + tiqueteActual.getNombre() + "\n";
            detalles += tiqueteActual.getDetalles() + "\n";
        } else {
            detalles += "No está atendiendo ningún tiquete.\n";
        }
        return detalles;
    }

    public Tiquete getTiqueteActual() {
        return tiqueteActual;
    }

    public void setTiqueteActual(Tiquete tiqueteActual) {
        if (tiqueteActual != null) {
            tiqueteActual.setCaja(this.nombre);
        }
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
        return  nombre + " (" + tipoCaja + ")";
    }
}

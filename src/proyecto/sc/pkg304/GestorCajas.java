package proyecto.sc.pkg304;

import java.util.Arrays;

public class GestorCajas {
    private Caja[] cajas;

    public GestorCajas(int cajasGenerales, int cajasRapidas, int cajasPreferenciales) {
        cajas = new Caja[cajasGenerales + cajasRapidas + cajasPreferenciales];

        for (int i = 0; i < cajasGenerales; i++) {
            cajas[i] = new Caja("Caja " + (i + 1), TipoCaja.Regular);
        }

        for (int i = cajasGenerales; i < cajasGenerales + cajasRapidas; i++) {
            cajas[i] = new Caja("Caja " + (i + 1), TipoCaja.Rapida);
        }

        for (int i = cajasGenerales + cajasRapidas; i < cajasGenerales + cajasRapidas + cajasPreferenciales; i++) {
            cajas[i] = new Caja("Caja " + (i + 1), TipoCaja.Preferencial);
        }
        System.out.println(Arrays.toString(cajas));
    }
    
    public Caja[] getCajas() {
        return cajas;
    }
    
    public int getCantidadCajasRapidas() {
        int contador = 0;
        for (Caja caja : cajas) {
            if (caja.getTipoCaja() == TipoCaja.Rapida) {
                contador++;
            }
        }
        return contador;
    }
    
    public int getCantidadCajasPreferenciales() {
        int contador = 0;
        for (Caja caja : cajas) {
            if (caja.getTipoCaja() == TipoCaja.Preferencial) {
                contador++;
            }
        }
        return contador;
    }

    public String imprimirDetalles() {
        String detalles = "";
        for (Caja caja : cajas) {
            detalles += caja.imprimirDetalles() + "\n";
        }
        return detalles;
    }

    public void encola(Tiquete tiquete) {
        Caja cajaMenosOcupada = cajas[0];
        for (Caja caja : cajas) {
            if (caja.getTiquetesEnCola() < cajaMenosOcupada.getTiquetesEnCola()) {
                cajaMenosOcupada = caja;
            }
        }

        cajaMenosOcupada.encola(tiquete);
        System.out.println("Encolando tiquete: " + tiquete + " en caja " + cajaMenosOcupada.getNombre() + " - Tiquetes en cola: " + cajaMenosOcupada.getTiquetesEnCola());
    }

    public void atiende() {
        for (Caja caja : cajas) {
            if (!caja.isOcupada() && !caja.getCola().isEmpty()) {
                try {
                    Tiquete tiquete = caja.atiende();
                    System.out.println("Atendiendo tiquete: " + tiquete + " en caja " + caja.getNombre());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void atiende(int cajaIndex) {
        if (cajaIndex < 0 || cajaIndex >= cajas.length) {
            System.out.println("No existe la caja con índice " + cajaIndex);
            return;
        }

        Caja caja = cajas[cajaIndex];
        if (!caja.isOcupada() && !caja.getCola().isEmpty()) {
            try {
                Tiquete tiquete = caja.atiende();
                System.out.println("Atendiendo tiquete: " + tiquete);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

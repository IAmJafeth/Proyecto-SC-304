package proyecto.sc.pkg304;

import javax.swing.*;
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

    public int getCantidadCajasRegulares() {
        int contador = 0;
        for (Caja caja : cajas) {
            if (caja.getTipoCaja() == TipoCaja.Regular) {
                contador++;
            }
        }
        return contador;
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
        Caja cajaSeleccionada;
        if (tiquete.getTipo() == TipoTiquete.P) {
            cajaSeleccionada = getCajaConMenosPersonas(TipoCaja.Preferencial);
        } else if (tiquete.getTipo() == TipoTiquete.A) {
            cajaSeleccionada = getCajaConMenosPersonas(TipoCaja.Rapida);
        } else {
            cajaSeleccionada = getCajaConMenosPersonas(TipoCaja.Regular);
        }

        if (cajaSeleccionada == null) {
            System.out.println("No hay cajas disponibles para el tiquete " + tiquete);
            return;
        }

        cajaSeleccionada.encola(tiquete);

        JOptionPane.showMessageDialog(null, "Tiquete creado:\n" + tiquete.getDetalles() + "\n"
                + (cajaSeleccionada.isOcupada() ? "Personas por delante: " + cajaSeleccionada.getTiquetesEnCola()  : "Tiquete siendo atendido inmediatamente, no hay personas por delante.")
                + "\nCaja asignada: " + cajaSeleccionada + "\n"
        );
    }

    public Caja getCajaConMenosPersonas(TipoCaja tipo) {
        Caja cajaConMenosPersonas = null;
        int menorCantidad = Integer.MAX_VALUE;
        for (Caja caja : cajas) {
           if (caja.getTipoCaja() == tipo && caja.getCola().size() < menorCantidad) {
               if (!caja.isOcupada()) {
                   return caja;
               }
               cajaConMenosPersonas = caja;
               menorCantidad = caja.getCola().size();
            }
        }
        return cajaConMenosPersonas;
    }


    public void atiende() {
        String mensaje = "";
        for (Caja caja : cajas) {
            try {
                Tiquete tiquete = caja.atiende();
                mensaje += caja + " Atendiendo tiquete: \n" + tiquete.getDetalles() + "\n\n";
            } catch (Exception e) {
                mensaje += e.getMessage() + "\n\n";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
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

package proyecto.sc.pkg304;

import javax.swing.*;
import java.time.LocalDateTime;

public class GestorCajas {
    private Caja[] cajas;
    Cola cola;

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

        cola = new Cola();
    }

    public String getCola(){
        return cola.toString();
    }

    public String getDetallesCola(){
        return cola.imprimirDetalles();
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

    public String[] getNombresCajas() {
        String[] nombres = new String[cajas.length];
        for (int i = 0; i < cajas.length; i++) {
            nombres[i] = cajas[i].toString();
        }
        return nombres;
    }

    public String imprimirDetalles() {
        String detalles = "";
        detalles += "Tiquetes en cola: " + cola.size() + "\n";
        detalles += "Cola: " + (cola.isEmpty() ? "Vacía" : cola) + "\n\n";
        detalles += "Detalles de las cajas:\n";
        for (Caja caja : cajas) {
            detalles += caja.imprimirDetalles() + "\n";
        }
        return detalles;
    }

    public void encola(Tiquete tiquete) {
        Caja cajaSeleccionada;
        if (tiquete.getTipo() == TipoTiquete.P) {
            cajaSeleccionada = getCajaDisponible(TipoCaja.Preferencial);
        } else if (tiquete.getTipo() == TipoTiquete.A) {
            cajaSeleccionada = getCajaDisponible(TipoCaja.Rapida);
        } else {
            cajaSeleccionada = getCajaDisponible(TipoCaja.Regular);
        }

        if (cajaSeleccionada == null) {
            cola.encola(tiquete);
            JOptionPane.showMessageDialog(null,
                    "No hay cajas disponibles. El tiquete se ha agregado a la cola." +
                            "\nPersonas por delante: " + (cola.size() - 1) +
                            "\n\n" + tiquete.getDetalles());
            return;
        }

        cajaSeleccionada.setTiqueteActual(tiquete);
        JOptionPane.showMessageDialog(null,
                "El tiquete se ha asignado a la " + cajaSeleccionada +
                "\n\n" + tiquete.getDetalles());

    }

    private Caja getCajaDisponible(TipoCaja tipoCaja) {
        for (Caja caja : cajas) {
            if (caja.getTipoCaja() == tipoCaja && !caja.isOcupada()) {
                return caja;
            }
        }
        return null;
    }

    public Caja getCajaPorNombre(String nombreCaja){
        for (Caja caja : cajas) {
            if (nombreCaja.equals(caja.toString())) {
                return caja;
            }
        }
        return null;
    }


    public Tiquete atiende(String nombreCaja) {

        Caja cajaSeleccionada = getCajaPorNombre(nombreCaja);
        if (cajaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Caja no encontrada.");
            return null;
        }

        String mensaje;
        Tiquete tiqueteAtendido = null;
        Tiquete tiqueteSiguiente;

        if (cajaSeleccionada.isOcupada()) {
            tiqueteAtendido = cajaSeleccionada.getTiqueteActual();
            tiqueteAtendido.setHoraAtencion(LocalDateTime.now());
            mensaje = "Se ha atendido a " + tiqueteAtendido.getNombre() + "\n" + tiqueteAtendido.getDetalles();

            AdministradorArchivo.guardarTransaccion(tiqueteAtendido, cajaSeleccionada);

            try {
                tiqueteSiguiente = cola.atiende();
                cajaSeleccionada.setTiqueteActual(tiqueteSiguiente);
                mensaje += "\n" + "\nAhora se atiende a: " + tiqueteSiguiente.getNombre() +  "\n" + tiqueteSiguiente.getDetalles();
            } catch (Exception e) {
                mensaje += "\n" + "No hay más tiquetes en la cola.";
                cajaSeleccionada.setTiqueteActual(null);
            }

        } else {
            mensaje = "La caja " + cajaSeleccionada.getNombre() + " no está ocupada.";
        }

        JOptionPane.showMessageDialog(null, mensaje);
        return tiqueteAtendido;


    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;

public class InterfazUsuario {

    private GestorCajas gestorCajas;

    public InterfazUsuario(GestorCajas gestorCajas) {
        this.gestorCajas = gestorCajas;
    }

    public static GestorCajas configuracionInicial(){
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de atención de trámites.\n"
                + "Por favor, configure la cantidad de cajas generales , preferenciales y rápidas.");
        int cantidadCajasGenerales;
        int cantidadCajasRapidas;
        int cantidadCajasPreferenciales;

        while (true) {
            String cantidadCajasGeneralesStr = JOptionPane.showInputDialog("Ingrese la cantidad de cajas generales:");
            if (cantidadCajasGeneralesStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null; // Cancelar
            }
            try {
                cantidadCajasGenerales = Integer.parseInt(cantidadCajasGeneralesStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La cantidad de cajas generales debe ser un número entero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        while (true) {
            String cantidadCajasRapidasStr = JOptionPane.showInputDialog("Ingrese la cantidad de cajas rápidas:");
            if (cantidadCajasRapidasStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null; // Cancelar
            }
            try {
                cantidadCajasRapidas = Integer.parseInt(cantidadCajasRapidasStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La cantidad de cajas rápidas debe ser un número entero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        while (true) {
            String cantidadCajasPreferencialesStr = JOptionPane.showInputDialog("Ingrese la cantidad de cajas preferenciales:");
            if (cantidadCajasPreferencialesStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return null; // Cancelar
            }
            try {
                cantidadCajasPreferenciales = Integer.parseInt(cantidadCajasPreferencialesStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La cantidad de cajas preferenciales debe ser un número entero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        GestorCajas gestorCajas = new GestorCajas(cantidadCajasGenerales, cantidadCajasRapidas, cantidadCajasPreferenciales);
        JOptionPane.showMessageDialog(null, "Cajas Configuradas." +
                "\n" + "Cajas Generales: " + cantidadCajasGenerales +
                "\n" + "Cajas Rápidas: " + cantidadCajasRapidas +
                "\n" + "Cajas Preferenciales: " + cantidadCajasPreferenciales +
                "\n\n" + "Detalles de las cajas: \n" + gestorCajas.imprimirDetalles()
        );
        return gestorCajas;
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = { "Agregar Tiquete", "Atender Tiquetes", "Mostrar Cajas", "Mostrar Cambio Dolar","Generar Reporte", "Salir" };
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    agregarTiquete();
                    break;
                case 1:
                    atenderTiquetes();
                    break;
                case 2:
                    mostrarCola();
                    break;
                case 3:
                    mostrarCambioDolar();
                    break;
                case 4:
                    generarReporte();
                    break;
                case 5:
                    return; // Salir
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void generarReporte(){
        String reporte = AdministradorReportes.generarReporte();
        JOptionPane.showMessageDialog(null, reporte, "Reporte de Transacciones", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarTiquete() {
        String nombre;

        while (true) {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
            if (nombre == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return; // Cancelar
            } else if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }

            break;
        }

        int id;
        while (true) {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
            if (idStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return; // Cancelar
            }
            try {
                id = Integer.parseInt(idStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        int edad;
        while (true) {
            String edadStr = JOptionPane.showInputDialog("Ingrese la edad del cliente:");
            if (edadStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return; // Cancelar
            }
            try {
                edad = Integer.parseInt(edadStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        TipoTramite[] tramites = TipoTramite.values();
        String[] tramiteDisplayNames = new String[tramites.length];
        for (int i = 0; i < tramites.length; i++) {
            tramiteDisplayNames[i] = tramites[i].getDisplayName();
        }
        String tramiteSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de trámite:",
                "Tipo de Trámite", JOptionPane.QUESTION_MESSAGE, null, tramiteDisplayNames, tramiteDisplayNames[0]);
        TipoTramite tramite = null;
        for (TipoTramite t : tramites) {
            if (t.getDisplayName().equals(tramiteSeleccionado)) {
                tramite = t;
                break;
            }
        }

        TipoTiquete[] tipos = TipoTiquete.values();
        String[] tipoDisplayNames = new String[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
            tipoDisplayNames[i] = tipos[i].getDisplayName();
        }
        String tipoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de tiquete:",
                "Tipo de Tiquete", JOptionPane.QUESTION_MESSAGE, null, tipoDisplayNames, tipoDisplayNames[0]);
        TipoTiquete tipo = null;
        for (TipoTiquete t : tipos) {
            if (t.getDisplayName().equals(tipoSeleccionado)) {
                tipo = t;
                break;
            }
        }

        Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipo);
        gestorCajas.encola(tiquete);

    }

    private void atenderTiquetes() {
        // crea una interfaz que muestre un dropdown con las el nombre de las cajas disponibles y seleccione la caja que se desea atender, se mostrara el dropdown con el nombre de la caja y el tipo de caja
        String[] cajas = gestorCajas.getNombresCajas();
        String cajaSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione la caja a atender:", "Cajas Disponibles",
                JOptionPane.QUESTION_MESSAGE, null, cajas, cajas[0]);
        if (cajaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            return; // Cancelar
        }
        gestorCajas.atiende(cajaSeleccionada);



    }

    private void mostrarCambioDolar() {
        JOptionPane.showMessageDialog(null, "El tipo de cambio actual es: " + WebScrapping.cambiodolar() + " colones por dólar.");
    }

    private void mostrarCola() {
        JOptionPane.showMessageDialog(null, gestorCajas.imprimirDetalles());
    }

    public GestorCajas getGestorCajas() {
        return gestorCajas;
    }
}
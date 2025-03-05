/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;

public class InterfazUsuario {

    private Cola cola;

    public InterfazUsuario(Cola cola) {
        this.cola = cola;
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = { "Agregar Tiquete", "Atender Tiquete", "Mostrar Cola", "Salir" };
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    agregarTiquete();
                    break;
                case 1:
                    atenderTiquete();
                    break;
                case 2:
                    mostrarCola();
                    break;
                case 3:
                    return; // Salir del menú
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        cola.encola(tiquete);

        // Mostrar detalles del tiquete
        String mensaje = "Tiquete creado:\n" + tiquete.getDetalles() + "\n\n";
        mensaje += "Caja asignada: Caja " + (tipo == TipoTiquete.P ? "1 (Preferencial)" : "2 (General)") + "\n";
        mensaje += "Personas por delante: " + contarPersonasPorDelante(tiquete);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void atenderTiquete() {
        try {
            Tiquete tiqueteAtendido = cola.atiende();
            tiqueteAtendido.setHoraAtencion(LocalDateTime.now());
            String mensaje = "Atendiendo a:\n" + tiqueteAtendido.getDetalles();
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarCola() {
        if (cola.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cola está vacía.");
        } else {
            StringBuilder mensaje = new StringBuilder("Estado actual de la cola: " + cola + "\n");
            Nodo actual = cola.getFrente();
            while (actual != null) {
                mensaje.append("--------------------\n");
                mensaje.append(actual.geTiquete().getDetalles()).append("\n");
                actual = actual.getSig();
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private int contarPersonasPorDelante(Tiquete tiquete) {
        int count = 0;
        Nodo actual = cola.getFrente();
        while (actual != null && !actual.geTiquete().equals(tiquete)) {
            count++;
            actual = actual.getSig();
        }
        return count;
    }
}
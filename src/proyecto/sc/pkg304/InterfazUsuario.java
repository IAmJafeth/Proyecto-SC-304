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
            String[] opciones = {"Agregar Tiquete", "Atender Tiquete", "Mostrar Cola", "Salir"};
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
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));
        TipoTramite tramite = (TipoTramite) JOptionPane.showInputDialog(null, "Seleccione el tipo de trámite:",
                "Tipo de Trámite", JOptionPane.QUESTION_MESSAGE, null, TipoTramite.values(), TipoTramite.DEPOSITOS);
        TipoTiquete tipo = (TipoTiquete) JOptionPane.showInputDialog(null, "Seleccione el tipo de tiquete:",
                "Tipo de Tiquete", JOptionPane.QUESTION_MESSAGE, null, TipoTiquete.values(), TipoTiquete.A);

        Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipo);
        cola.encola(tiquete);

        // Mostrar detalles del tiquete
        String mensaje = "Tiquete creado:\n" + tiquete.toString() + "\n\n";
        mensaje += "Caja asignada: Caja " + (tipo == TipoTiquete.P ? "1 (Preferencial)" : "2 (General)") + "\n";
        mensaje += "Personas por delante: " + contarPersonasPorDelante(tiquete);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void atenderTiquete() {
        try {
            Tiquete tiqueteAtendido = cola.atiende();
            tiqueteAtendido.setHoraAtencion(LocalDateTime.now());
            String mensaje = "Atendiendo a:\n" + tiqueteAtendido.toString();
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarCola() {
        if (cola.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cola está vacía.");
        } else {
            StringBuilder mensaje = new StringBuilder("Estado actual de la cola:\n");
            Nodo actual = cola.getFrente();
            while (actual != null) {
                mensaje.append(actual.geTiquete().toString()).append("\n");
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
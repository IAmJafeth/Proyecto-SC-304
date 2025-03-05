/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.sc.pkg304;

import javax.swing.JOptionPane;

/**
 *
 * @author jgarr
 */
public class ProyectoSC304 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String tipoCambio = WebScrapping.cambiodolar();
        Cola cola = AdministradorArchivo.cargarCola();
        JOptionPane.showMessageDialog(null, "Cambio de dolar a colones al día de hoy: " + tipoCambio);

        // Crear la interfaz de usuario y mostrar el menú
        InterfazUsuario interfaz = new InterfazUsuario(cola);
        interfaz.mostrarMenu();

        // Guardar la cola en el archivo antes de salir
        AdministradorArchivo.guardarCola(cola);
        JOptionPane.showMessageDialog(null, "Cola guardada.");
    }
    
}



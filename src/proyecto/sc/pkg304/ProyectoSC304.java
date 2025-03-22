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
        GestorCajas gestorCajas = AdministradorArchivo.cargarGestorCajas();
        if (gestorCajas == null) {
            gestorCajas = InterfazUsuario.configuracionInicial();
        }
        if (gestorCajas != null) {
            InterfazUsuario interfazUsuario = new InterfazUsuario(gestorCajas);
        }

    }
    
}



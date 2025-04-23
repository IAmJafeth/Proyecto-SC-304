/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.sc.pkg304;

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
        InterfazUsuario interfazUsuario;

        if (gestorCajas == null) {
            gestorCajas = InterfazUsuario.configuracionInicial();
        }


        interfazUsuario = new InterfazUsuario(gestorCajas);

        System.out.println(gestorCajas.imprimirDetalles());
        interfazUsuario.mostrarMenu();

        AdministradorArchivo.guardarGestorCajas(interfazUsuario.getGestorCajas());



    }

}



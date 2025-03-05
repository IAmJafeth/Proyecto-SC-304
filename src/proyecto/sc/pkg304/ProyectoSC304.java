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
        Cola cola = AdministradorArchivo.cargarCola();
        cola.imprimirDetalles();
        System.out.println("Cola cargada: " + cola);
        
        cola.encola(new Tiquete("Juan Perez", 1, 30, TipoTramite.DEPOSITOS, TipoTiquete.A));
        cola.encola(new Tiquete("Maria Lopez", 2, 45, TipoTramite.RETIROS, TipoTiquete.B));
        cola.encola(new Tiquete("Carlos Jimenez", 3, 50, TipoTramite.CAMBIO_DIVISAS, TipoTiquete.P));
        
        System.out.println("Cola después de agregar tiquetes: " + cola);
        cola.imprimirDetalles();
        try {
            System.out.println("Atendiendo: " + cola.atiende().getNombre());
            System.out.println("Atendiendo: " + cola.atiende().getNombre());
        } catch (Exception e) {
            System.err.println("Error al atender un tiquete: " + e.getMessage());
        }
        
        System.out.println("Cola después de atender tiquetes: " + cola);
        cola.imprimirDetalles();
        AdministradorArchivo.guardarCola(cola);
    }
    
}

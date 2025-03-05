/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jgarr
 */
public class AdministradorArchivo {
    private static final String FILE_NAME = "cola_tiquetes.ser";
    
    public static void guardarCola(Cola cola) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(cola);
            System.out.println("Cola guardada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la cola: " + e.getMessage());
        }
    }
    
    public static Cola cargarCola() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new Cola();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Cola) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la cola: " + e.getMessage());
            return new Cola();
        }
    }
}

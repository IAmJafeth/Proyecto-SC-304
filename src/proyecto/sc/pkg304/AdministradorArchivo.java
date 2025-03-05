/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jgarr
 */
public class AdministradorArchivo {
    private static final String FILE_NAME = "cola_tiquetes.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    public static void guardarCola(Cola cola) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            Nodo actual = cola.getFrente();
            while (actual != null) {
                Tiquete t = actual.geTiquete();
                writer.write(t.getNombre() + "," + t.getId() + "," + t.getEdad() + "," +
                        t.getHoraCreación().format(FORMATTER) + "," +
                        (t.isCompleted() ? t.getHoraAtencion().format(FORMATTER) : "No atendido") + "," +
                        t.getTramite().name() + "," + t.getTipo().name());
                writer.newLine();
                actual = actual.getSig();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    public static Cola cargarCola() {
        Cola cola = new Cola();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return cola;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nombre = data[0];
                int id = Integer.parseInt(data[1]);
                int edad = Integer.parseInt(data[2]);
                LocalDateTime horaCreacion = LocalDateTime.parse(data[3], FORMATTER);
                LocalDateTime horaAtencion = data[4].equals("No atendido") ? LocalDateTime.MIN : LocalDateTime.parse(data[4], FORMATTER);
                TipoTramite tramite = TipoTramite.valueOf(data[5]);
                TipoTiquete tipo = TipoTiquete.valueOf(data[6]);
                
                Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipo);
                tiquete.setHoraCreación(horaCreacion);
                tiquete.setHoraAtencion(horaAtencion);
                cola.encola(tiquete);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return cola;
    }
}

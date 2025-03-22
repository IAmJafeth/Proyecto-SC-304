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

    public static void guardarGestorCajas(GestorCajas gestorCajas){
        try {
            FileWriter file = new FileWriter("gestorCajas.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println(gestorCajas.getCajas().length);
            writer.println(gestorCajas.getCantidadCajasRapidas());
            writer.println(gestorCajas.getCantidadCajasPreferenciales());
            for (Caja caja : gestorCajas.getCajas()) {
                writer.println(caja.getNombre());
                writer.println(caja.getCola().size());
                writer.println(caja.getTipoCaja());
                Nodo nodo = caja.getCola().getFrente();
                while (nodo != null) {
                    Tiquete tiquete = nodo.getTiquete();
                    writer.println(tiquete.getNombre());
                    writer.println(tiquete.getId());
                    writer.println(tiquete.getEdad());
                    writer.println(tiquete.getHoraCreacion().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    writer.println(tiquete.getHoraAtencion().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    writer.println(tiquete.getTramite().name());
                    writer.println(tiquete.getTipo().name());
                    nodo = nodo.getSig();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el gestor de cajas.");
        }
    }

    public static GestorCajas cargarGestorCajas(){
        try {
            FileReader file = new FileReader("gestorCajas.txt");
            BufferedReader reader = new BufferedReader(file);
            int cantidadCajas = Integer.parseInt(reader.readLine());
            int cajasRapidas = Integer.parseInt(reader.readLine());
            int cajasPreferenciales = Integer.parseInt(reader.readLine());
            GestorCajas gestorCajas = new GestorCajas(cantidadCajas, cajasRapidas, cajasPreferenciales);
            for (Caja caja : gestorCajas.getCajas()) {
                int cantidadTiquetes = Integer.parseInt(reader.readLine());
                for (int i = 0; i < cantidadTiquetes; i++) {
                    String nombre = reader.readLine();
                    int id = Integer.parseInt(reader.readLine());
                    int edad = Integer.parseInt(reader.readLine());
                    LocalDateTime horaCreacion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    LocalDateTime horaAtencion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    TipoTramite tramite = TipoTramite.valueOf(reader.readLine());
                    TipoTiquete tipo = TipoTiquete.valueOf(reader.readLine());
                    Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion);
                    caja.encola(tiquete);
                }
            }
            reader.close();
            return gestorCajas;
        } catch (IOException e) {
            System.out.println("Error al cargar el gestor de cajas.");
            return null;
        }
    }
}

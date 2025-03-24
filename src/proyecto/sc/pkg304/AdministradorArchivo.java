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
            writer.println(gestorCajas.getCantidadCajasRegulares());
            writer.println(gestorCajas.getCantidadCajasRapidas());
            writer.println(gestorCajas.getCantidadCajasPreferenciales());
            for (Caja caja : gestorCajas.getCajas()) {
                guardarCaja(caja, writer);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el gestor de cajas.");
        }
    }

    private static void guardarTiquete(Tiquete tiquete, Writer writer){
        try {
            writer.write(tiquete.getNombre() + "\n");
            writer.write(tiquete.getId() + "\n");
            writer.write(tiquete.getEdad() + "\n");
            writer.write(tiquete.getHoraCreacion().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
            writer.write(tiquete.getHoraAtencion().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
            writer.write(tiquete.getTramite().name() + "\n");
            writer.write(tiquete.getTipo().name() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el tiquete.");
        }
    }

    private static void guardarCaja(Caja caja, Writer writer){
        try {
            if (caja.isOcupada()){
                writer.write(caja.getCola().size() + 1 + "\n");
                guardarTiquete(caja.getTiqueteActual(), writer);
            } else {
                writer.write(caja.getCola().size() + "\n");
            }
            Nodo nodo = caja.getCola().getFrente();
            while (nodo != null) {
                Tiquete tiquete = nodo.getTiquete();
                guardarTiquete(tiquete, writer);
                nodo = nodo.getSig();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la caja.");
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
                System.out.println("Caja" + caja);
                int cantidadTiquetes = Integer.parseInt(reader.readLine());
                System.out.println("Cantidad de tiquetes" + cantidadTiquetes);
                for (int i = 0; i < cantidadTiquetes; i++) {
                    String nombre = reader.readLine();
                    int id = Integer.parseInt(reader.readLine());
                    int edad = Integer.parseInt(reader.readLine());
                    LocalDateTime horaCreacion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    LocalDateTime horaAtencion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    TipoTramite tramite = TipoTramite.valueOf(reader.readLine());
                    TipoTiquete tipo = TipoTiquete.valueOf(reader.readLine());
                    Tiquete tiquete =  new Tiquete(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion);
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

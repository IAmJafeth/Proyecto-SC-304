/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;
import java.time.Duration;

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
            writer.println(gestorCajas.cola.size());
            Nodo nodo = gestorCajas.cola.getFrente();
            while (nodo != null) {
                guardarTiquete(nodo.getTiquete(), writer);
                nodo = nodo.getSig();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el gestor de cajas." + e);
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
            writer.write(tiquete.getCaja() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el tiquete." + e);
        }
    }

    private static void guardarCaja(Caja caja, Writer writer){
        try {
            if (caja.isOcupada()){
                writer.write(caja + "Ocupada\n");
                guardarTiquete(caja.getTiqueteActual(), writer);
            } else {
                writer.write( caja + "Caja vacía\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la caja." + e);
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
                if (!reader.readLine().endsWith("Caja vacía")) {
                    String nombre = reader.readLine();
                    int id = Integer.parseInt(reader.readLine());
                    int edad = Integer.parseInt(reader.readLine());
                    LocalDateTime horaCreacion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    LocalDateTime horaAtencion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    TipoTramite tramite = TipoTramite.valueOf(reader.readLine());
                    TipoTiquete tipo = TipoTiquete.valueOf(reader.readLine());
                    String cajaNombre = reader.readLine();
                    Tiquete tiquete =  new Tiquete(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion, cajaNombre);
                    caja.setTiqueteActual(tiquete);
                }
            }
            int cantidadTiquetes = Integer.parseInt(reader.readLine());
            for (int i = 0; i < cantidadTiquetes; i++) {
                String nombre = reader.readLine();
                int id = Integer.parseInt(reader.readLine());
                int edad = Integer.parseInt(reader.readLine());
                LocalDateTime horaCreacion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime horaAtencion = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                TipoTramite tramite = TipoTramite.valueOf(reader.readLine());
                TipoTiquete tipo = TipoTiquete.valueOf(reader.readLine());
                String cajaNombre = reader.readLine();
                Tiquete tiquete =  new Tiquete(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion, cajaNombre);
                gestorCajas.cola.encola(tiquete);
            }
            reader.close();
            return gestorCajas;
        } catch (IOException e) {
            System.out.println("Error al cargar el gestor de cajas.:" + e);
            return null;
        }
    }

    public static void guardarTransaccion(Tiquete tiquete, Caja caja) {
        try {
            FileWriter file = new FileWriter("transacciones.txt", true);
            PrintWriter writer = new PrintWriter(file);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Duration tiempoEspera = Duration.between(tiquete.getHoraCreacion(), tiquete.getHoraAtencion());

            writer.println("Cliente: " + tiquete.getNombre());
            writer.println("ID: " + tiquete.getId());
            writer.println("Edad: " + tiquete.getEdad());
            writer.println("Tipo de tiquete: " + tiquete.getTipo());
            writer.println("Trámite: " + tiquete.getTramite());
            writer.println("Hora de creación: " + tiquete.getHoraCreacion().format(formatter));
            writer.println("Hora de atención: " + tiquete.getHoraAtencion().format(formatter));
            writer.println("Tiempo de espera: " + tiempoEspera.toMinutes() + " minutos, " +
                    (tiempoEspera.toSeconds() % 60) + " segundos");
            writer.println("Atendido en: " + caja.getNombre() + " (" + caja.getTipoCaja() + ")");
            writer.println();

            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la transacción: " + e.getMessage());
        }
    }
}

package proyecto.sc.pkg304;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AdministradorReportes {

    public static String generarReporte() {
        try {
            // Estructuras para almacenar datos
            Map<String, Integer> clientesPorCaja = new HashMap<>();
            Map<String, Duration> tiempoTotalPorCaja = new HashMap<>();
            Map<String, Integer> tiquetesPorCaja = new HashMap<>();
            int totalClientes = 0;
            Duration tiempoTotal = Duration.ZERO;

            // Leer el archivo
            Map<String, Object> datos = procesarTransacciones();

            if (datos == null) {
                return "No se pudo generar el reporte. El archivo de transacciones no existe o está vacío.";
            }

            clientesPorCaja = (Map<String, Integer>) datos.get("clientesPorCaja");
            tiempoTotalPorCaja = (Map<String, Duration>) datos.get("tiempoTotalPorCaja");
            tiquetesPorCaja = (Map<String, Integer>) datos.get("tiquetesPorCaja");
            totalClientes = (Integer) datos.get("totalClientes");
            tiempoTotal = (Duration) datos.get("tiempoTotal");

            // Encontrar la caja con más clientes
            String cajaConMasClientes = encontrarCajaConMasClientes(clientesPorCaja);

            // Encontrar la caja con mejor tiempo promedio
            String cajaMejorTiempo = encontrarCajaMejorTiempo(tiempoTotalPorCaja, tiquetesPorCaja);

            // Construir el reporte
            StringBuilder reporte = new StringBuilder();
            reporte.append("REPORTE DE TRANSACCIONES\n");
            reporte.append("=======================\n\n");
            reporte.append("1. Caja con mayor cantidad de clientes: ").append(cajaConMasClientes)
                    .append(" (").append(clientesPorCaja.get(cajaConMasClientes)).append(" clientes)\n");
            reporte.append("2. Total de clientes atendidos: ").append(totalClientes).append("\n");

            // Reportar tiempos promedios por caja
            reporte.append("3. Caja con mejor tiempo promedio de atención: ").append(cajaMejorTiempo);
            if (cajaMejorTiempo != null && tiquetesPorCaja.get(cajaMejorTiempo) > 0) {
                Duration tiempoPromedioCaja = tiempoTotalPorCaja.get(cajaMejorTiempo)
                        .dividedBy(tiquetesPorCaja.get(cajaMejorTiempo));
                reporte.append(" (").append(formatearDuracion(tiempoPromedioCaja)).append(" por cliente)\n");
            } else {
                reporte.append(" (sin datos suficientes)\n");
            }

            // Calcular tiempo promedio general
            Duration tiempoPromedioGeneral = (totalClientes > 0) ?
                    tiempoTotal.dividedBy(totalClientes) : Duration.ZERO;

            reporte.append("4. Tiempo promedio de atención general: ")
                    .append(formatearDuracion(tiempoPromedioGeneral)).append("\n\n");

            // Detalle por caja
            reporte.append("DETALLE POR CAJA\n");
            reporte.append("----------------\n");
            for (String caja : clientesPorCaja.keySet()) {
                int clientesCaja = clientesPorCaja.get(caja);
                Duration tiempoPromedioCaja = (clientesCaja > 0) ?
                        tiempoTotalPorCaja.get(caja).dividedBy(clientesCaja) : Duration.ZERO;

                reporte.append(caja).append(": ")
                        .append(clientesCaja).append(" clientes, ")
                        .append(formatearDuracion(tiempoPromedioCaja)).append(" promedio\n");
            }

            return reporte.toString();

        } catch (Exception e) {
            return "Error al generar el reporte: " + e.getMessage();
        }
    }


    private static Map<String, Object> procesarTransacciones() {
        try {
            Map<String, Integer> clientesPorCaja = new HashMap<>();
            Map<String, Duration> tiempoTotalPorCaja = new HashMap<>();
            Map<String, Integer> tiquetesPorCaja = new HashMap<>();
            int totalClientes = 0;
            Duration tiempoTotal = Duration.ZERO;

            BufferedReader reader = new BufferedReader(new FileReader("transacciones.txt"));
            String linea;
            String cajaActual = null;
            LocalDateTime horaCreacion = null;
            LocalDateTime horaAtencion = null;
            Duration tiempoEspera = null;

            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Hora de creación: ")) {
                    String horaCreacionStr = linea.substring("Hora de creación: ".length());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    horaCreacion = LocalDateTime.parse(horaCreacionStr, formatter);
                } else if (linea.startsWith("Hora de atención: ")) {
                    String horaAtencionStr = linea.substring("Hora de atención: ".length());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    horaAtencion = LocalDateTime.parse(horaAtencionStr, formatter);
                } else if (linea.startsWith("Tiempo de espera: ")) {
                    // Para compatibilidad con ambos formatos de archivo (antiguo y nuevo)
                    if (horaCreacion != null && horaAtencion != null) {
                        tiempoEspera = Duration.between(horaCreacion, horaAtencion);
                    }
                } else if (linea.startsWith("Atendido en: ")) {
                    cajaActual = linea.substring("Atendido en: ".length());

                    // Actualizar contadores
                    clientesPorCaja.put(cajaActual, clientesPorCaja.getOrDefault(cajaActual, 0) + 1);
                    tiquetesPorCaja.put(cajaActual, tiquetesPorCaja.getOrDefault(cajaActual, 0) + 1);
                    totalClientes++;

                    // Calcular tiempo de atención
                    if (tiempoEspera == null && horaCreacion != null && horaAtencion != null) {
                        tiempoEspera = Duration.between(horaCreacion, horaAtencion);
                    }

                    if (tiempoEspera != null) {
                        tiempoTotal = tiempoTotal.plus(tiempoEspera);
                        tiempoTotalPorCaja.merge(cajaActual, tiempoEspera, Duration::plus);
                        tiempoEspera = null;
                    }
                } else if (linea.trim().isEmpty()) {
                    // Reset para la siguiente transacción
                    cajaActual = null;
                    horaCreacion = null;
                    horaAtencion = null;
                    tiempoEspera = null;
                }
            }

            reader.close();

            // Guardar datos en un mapa
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("clientesPorCaja", clientesPorCaja);
            resultado.put("tiempoTotalPorCaja", tiempoTotalPorCaja);
            resultado.put("tiquetesPorCaja", tiquetesPorCaja);
            resultado.put("totalClientes", totalClientes);
            resultado.put("tiempoTotal", tiempoTotal);

            return resultado;

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de transacciones: " + e.getMessage());
            return null;
        }
    }


    private static String encontrarCajaConMasClientes(Map<String, Integer> clientesPorCaja) {
        String cajaMaxima = null;
        int maxClientes = 0;

        for (Map.Entry<String, Integer> entry : clientesPorCaja.entrySet()) {
            if (entry.getValue() > maxClientes) {
                maxClientes = entry.getValue();
                cajaMaxima = entry.getKey();
            }
        }

        return cajaMaxima;
    }


    private static String encontrarCajaMejorTiempo(Map<String, Duration> tiempoTotalPorCaja,
                                                   Map<String, Integer> tiquetesPorCaja) {
        String cajaMejor = null;
        double mejorTiempo = Double.MAX_VALUE;

        for (String caja : tiempoTotalPorCaja.keySet()) {
            int tiquetes = tiquetesPorCaja.get(caja);
            if (tiquetes > 0) {
                double promedio = tiempoTotalPorCaja.get(caja).toSeconds() / (double) tiquetes;
                if (promedio < mejorTiempo) {
                    mejorTiempo = promedio;
                    cajaMejor = caja;
                }
            }
        }

        return cajaMejor;
    }


    private static String formatearDuracion(Duration duracion) {
        long minutos = duracion.toMinutes();
        long segundos = duracion.minusMinutes(minutos).getSeconds();
        return minutos + " minutos, " + segundos + " segundos";
    }
}
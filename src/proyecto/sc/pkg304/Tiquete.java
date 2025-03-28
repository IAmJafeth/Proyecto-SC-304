package proyecto.sc.pkg304;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tiquete {
    private String nombre;
    private int id;
    private int edad;
    private LocalDateTime horaCreacion;
    private LocalDateTime horaAtencion;
    private TipoTramite tramite;
    private TipoTiquete tipo;
    private String caja;

    public Tiquete(String nombre, int id, int edad, TipoTramite tramite, TipoTiquete tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;

        this.horaCreacion = LocalDateTime.now();
        this.horaAtencion = LocalDateTime.MIN; // Esto representa que la fecha y hora no se ha asignado.
        this.caja = null;
    }

    public Tiquete(String nombre, int id, int edad, TipoTramite tramite, TipoTiquete tipo, LocalDateTime horaCreacion, LocalDateTime horaAtencion, String caja) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
        this.horaCreacion = horaCreacion;
        this.horaAtencion = horaAtencion;
        this.caja = caja;
    }

    public boolean isCompleted() {
        return horaAtencion != LocalDateTime.MIN;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDateTime getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(LocalDateTime horaCreación) {
        this.horaCreacion = horaCreación;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(){
        this.horaAtencion = LocalDateTime.now();
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public TipoTramite getTramite() {
        return tramite;
    }

    public void setTramite(TipoTramite tramite) {
        this.tramite = tramite;
    }

    public TipoTiquete getTipo() {
        return tipo;
    }

    public void setTipo(TipoTiquete tipo) {
        this.tipo = tipo;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String nombreCaja) {
        this.caja = nombreCaja;
    }

    public String getDetalles() {
        return "Nombre: " + nombre + "\n" +
                "ID: " + id + "\n" +
                "Edad: " + edad + "\n" +
                "Hora de creación: " + horaCreacion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +
                "Hora de atención: " + (horaAtencion.equals(LocalDateTime.MIN) ? "No atendido" : horaAtencion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))) + "\n" +
                "Trámite: " + tramite.getDisplayName() + "\n" +
                "Tipo: " + tipo.getDisplayName();
    }

    @Override
    public String toString() {
        return "Tiquete{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", edad=" + edad +
                ", horaCreacion=" + horaCreacion +
                ", horaAtencion=" + (horaAtencion.equals(LocalDateTime.MIN) ? "No atendido" : horaAtencion) +
                ", tramite=" + tramite.getDisplayName() +
                ", tipo=" + tipo.getDisplayName() +
                '}';
    }
}

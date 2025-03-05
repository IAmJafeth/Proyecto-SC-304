package proyecto.sc.pkg304;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tiquete {
    private String nombre;
    private int id;
    private int edad;
    private LocalDateTime horaCreacion;
    private LocalDateTime horaAtencion;
    private TipoTramite tramite;
    private TipoTiquete tipo;

    public Tiquete(String nombre, int id, int edad, TipoTramite tramite, TipoTiquete tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;

        this.horaCreacion = LocalDateTime.now();
        this.horaAtencion = LocalDateTime.MIN; // Esto representa que la fecha y hora no se ha asignado.
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

    public LocalDateTime getHoraCreación() {
        return horaCreacion;
    }

    public void setHoraCreación(LocalDateTime horaCreación) {
        this.horaCreacion = horaCreación;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
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

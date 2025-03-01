package proyecto.sc.pkg304;

import java.time.LocalDateTime;


public class Tickete {
    private String nombre;
    private int id;
    private int edad;
    private LocalDateTime horaCreación;
    private LocalDateTime horaAtencion;
    private TipoTramite tramite;
    private TipoTickete tipo;

    public Tickete(String nombre, int id, int edad, TipoTramite tramite, TipoTickete tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
        
        this.horaCreación = LocalDateTime.now();
        this.horaAtencion = LocalDateTime.MIN; // Esto representa que la fecha y hora no se ha asignado.
    }
    
    public boolean isCompleted(){
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
        return horaCreación;
    }

    public void setHoraCreación(LocalDateTime horaCreación) {
        this.horaCreación = horaCreación;
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

    public TipoTickete getTipo() {
        return tipo;
    }

    public void setTipo(TipoTickete tipo) {
        this.tipo = tipo;
    }
           
}

package proyecto.sc.pkg304;

import java.util.Date;

public class Tickete {
    private String nombre;
    private int id;
    private int edad;
    private Date horaCreación;
    private Date horaAtencion;
    private TipoTramite tramite;
    private TipoTickete tipo;

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

    public Date getHoraCreación() {
        return horaCreación;
    }

    public void setHoraCreación(Date horaCreación) {
        this.horaCreación = horaCreación;
    }

    public Date getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Date horaAtencion) {
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

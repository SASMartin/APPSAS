package utec.edu.uy.appsas.model;

import java.util.Date;

public class Docente extends Persona {
    private Date fechaEgreso;
    private Date fechaIngreso;


    public Docente(String nombre, String telefono, String documento, String apellido, Date fechaNac, String correo, Pais pais, Date fechaEgreso, Date fechaIngreso) {
        super(nombre, telefono, documento, apellido, fechaNac, correo, pais);
        this.fechaEgreso = fechaEgreso;
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }
    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
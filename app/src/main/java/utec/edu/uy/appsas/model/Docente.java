package utec.edu.uy.appsas.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by usuario on 09-jun-17.
 */

public class Docente extends Persona {

    private String  id ;
    private Date fechaEgreso;
    private Date fechaIngreso;


    public Docente(String nombre, String telefono, String documento, String apellido, Date fechaNac, String correo, Pais pais, Date fechaEgreso, Date fechaIngreso) {
        super(nombre, telefono, documento, apellido, fechaNac, correo, pais);
        this.id =UUID.randomUUID().toString();
        this.fechaEgreso = fechaEgreso;
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
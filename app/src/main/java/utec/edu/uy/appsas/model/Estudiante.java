package utec.edu.uy.appsas.model;

import java.util.Date;


public class Estudiante extends Persona {
    private Date fechaPrimerMat;

    public Estudiante(String nombre, String telefono, String documento, String apellido, Date fechaNac, String correo, Pais pais ,Date fechaPrimerMat) {
        super(nombre, telefono, documento, apellido, fechaNac, correo, pais);
        this.fechaPrimerMat=fechaPrimerMat;
    }

    public Date getFechaPrimerMat() {
        return fechaPrimerMat;
    }

    public void setFechaPrimerMat(Date fechaPrimerMat) {
        this.fechaPrimerMat = fechaPrimerMat;
    }
}

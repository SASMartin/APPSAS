package utec.edu.uy.appsas.model;

import java.util.Date;

public class Persona {
    private String nombre ;
    private String telefono ;
    private String documento ;
    private String apellido;
    private Date fechaNac ;
    private String correo ;
    private Pais pais;

    public Persona(String nombre, String telefono, String documento, String apellido, Date fechaNac, String correo, Pais pais) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.documento = documento;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Date getFechaNac() {
        return fechaNac;
    }
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Pais getPais() {
        return pais;
    }
    public void setPais(Pais pais) {
        this.pais = pais;
    }

}

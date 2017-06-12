package utec.edu.uy.appsas.model;

public class Docente {
    private String mNombre​;
    private String mDocumento;
    private String mPais​;

    public Docente(String nombre, String documento, String pais) {
        this.mNombre​ = nombre;
        this.mDocumento = documento;
        this.mPais​ = pais;
    }

    public String getmNombre() {
        return mNombre​;
    }
    public void setmNombre(String mNombre) {
        this.mNombre​ = mNombre;
    }
    public String getmDocumento() {
        return mDocumento;
    }
    public void setmDocumento(String mDocumento) {
        this.mDocumento = mDocumento;
    }
    public String getmPais() {
        return mPais​;
    }
    public void setmPais(String mPais) {
        this.mPais​ = mPais;
    }

    @Override
    public String toString() {
        return "Docente{" +
                ", mNombre='" + mNombre​ + '\'' +
                ", mDocumento='" + mDocumento + '\'' +
                ", mPais=" + mPais​ +
                '}';
    }
}

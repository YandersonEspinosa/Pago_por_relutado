package madels;

public class UebModel {
        private int ueb_id;
        private String nombre;
        private String tipo;

    public UebModel() {
    }

    public int getUeb_id() {
        return ueb_id;
    }

    public void setUeb_id(int ueb_id) {
        this.ueb_id = ueb_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;

    }

    @Override
    public String toString() {
        return "ID" + ueb_id + " -Nombre: " + nombre + " -Tipo: " + tipo;
    }public String [] toArray() {
        return new String[]{String.valueOf(ueb_id), nombre, tipo};
    }
}



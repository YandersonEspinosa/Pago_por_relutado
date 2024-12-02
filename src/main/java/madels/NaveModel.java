package madels;

public class NaveModel {
    private int nave_id;
    private int ueb_id;
    private String nombre_ueb;
    private String tipo_ueb;

    public NaveModel() {
    }

    public NaveModel(int ueb_id) {
        this.ueb_id = ueb_id;
    }

    public NaveModel(int ueb_id,String nombre_ueb, String tipo_ueb) {
        this.ueb_id = ueb_id;
        this.nombre_ueb = nombre_ueb;
        this.tipo_ueb = tipo_ueb;

    }


    public int getNave_id() {
        return nave_id;
    }

    public void setNave_id(int nave_id) {
        this.nave_id = nave_id;
    }

    public int getUeb_id() {
        return ueb_id;
    }

    public void setUeb_id(int ueb_id) {
        this.ueb_id = ueb_id;
    }

    public String getNombre_ueb() {
        return nombre_ueb;
    }

    public void setNombre_ueb(String nombre_ueb) {
        this.nombre_ueb = nombre_ueb;
    }

    public String getTipo_ueb() {
        return tipo_ueb;
    }

    public void setTipo_ueb(String tipo_ueb) {
        this.tipo_ueb = tipo_ueb;
    }
    public String [] toArray() {
        return new String [] {String.valueOf(nave_id),
                String.valueOf(ueb_id),
                nombre_ueb,
                tipo_ueb};
    }
}

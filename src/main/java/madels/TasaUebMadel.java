package madels;

public class TasaUebMadel {
    private int tasa_ueb_id;
    private int ueb_id;
    private int tasa_id;
    private String nombre_ueb;
    private String tipo_ueb;
    private Double porcentaje_tasa;
    private String plan_tasa;

    public TasaUebMadel() {
    }

    public TasaUebMadel(int ueb_id, int tasa_id) {
        this.ueb_id = ueb_id;
        this.tasa_id = tasa_id;
    }

    public TasaUebMadel(int ueb_id, int tasa_id, String nombre_ueb,
                        String tipo_ueb, Double porcentaje_tasa, String plan_tasa) {
        this.ueb_id = ueb_id;
        this.tasa_id = tasa_id;
        this.nombre_ueb = nombre_ueb;
        this.tipo_ueb = tipo_ueb;
        this.porcentaje_tasa = porcentaje_tasa;
        this.plan_tasa = plan_tasa;
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

    public Double getPorcentaje_tasa() {
        return porcentaje_tasa;
    }

    public void setPorcentaje_tasa(Double porcentaje_tasa) {
        this.porcentaje_tasa = porcentaje_tasa;
    }

    public String getPlan_tasa() {
        return plan_tasa;
    }

    public void setPlan_tasa(String plan_tasa) {
        this.plan_tasa = plan_tasa;
    }

    public int getTasa_ueb_id() {
        return tasa_ueb_id;
    }
    public void setTasa_ueb_id(int tasa_ueb_id) {
        this.tasa_ueb_id = tasa_ueb_id;
    }

    public int getUeb_id() {
        return ueb_id;
    }

    public void setUeb_id(int ueb_id) {
        this.ueb_id = ueb_id;
    }

    public int getTasa_id() {
        return tasa_id;
    }

    public void setTasa_id(int tasa_id) {
        this.tasa_id = tasa_id;
    }
    public String []getArray(){
        return new String[]{String.valueOf(ueb_id),
                            nombre_ueb,
                            tipo_ueb,
                          String.valueOf(tasa_id),
                          String.valueOf(porcentaje_tasa),
                          plan_tasa};
    }
}

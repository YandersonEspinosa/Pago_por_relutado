package madels;

public class NaveraModel {
    private int navera_id;
    private String navera_nombre;
    private Double sueldo_fijo;

    public NaveraModel() {
    }

    public int getNavera_id() {
        return navera_id;
    }

    public void setNavera_id(int navera_id) {
        this.navera_id = navera_id;
    }

    public String getNavera_nombre() {
        return navera_nombre;
    }

    public void setNavera_nombre(String navera_nombre) {
        this.navera_nombre = navera_nombre;
    }

    public Double getSueldo_fijo() {
        return sueldo_fijo;
    }

    public void setSueldo_fijo(Double sueldo_fijo) {
        this.sueldo_fijo = sueldo_fijo;
    }

    @Override
    public String toString() {
        return "ID:" + navera_id + " - Nombre:" + navera_nombre + " - Sueldo:" + sueldo_fijo;
    }
    public String[]toArray() {
        return new String[]{String.valueOf(navera_id),navera_nombre,String.valueOf(sueldo_fijo)};
    }
}

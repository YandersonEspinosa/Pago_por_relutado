package madels;

public class TasaModel {
    private int tasa_id;
    private double porcentaje;
    private String plan;

    public TasaModel() {
    }

    public int getTasa_id() {
        return tasa_id;
    }

    public void setTasa_id(int tasa_id) {
        this.tasa_id = tasa_id;
    }

    public int getPorcentaje() {
        return (int) porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getPlan() {
        return plan;
     }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "ID " + tasa_id + " -Porcentaje " + porcentaje + " -Plan " + plan;
    }
    public String [] toArray() {
        return new String[]{String.valueOf(tasa_id), String.valueOf(porcentaje), plan};
    }
}

package controllers;

import madels.TasaDAO;
import madels.TasaModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class TasaCotroller {
    private TasaDAO tasaDAO;

    public TasaCotroller(TasaDAO tasaDAO) {

        this.tasaDAO = tasaDAO;
    }
    public boolean createTasa(TasaModel tasa) {

        return tasaDAO.createTasa(tasa);
    }
    public ArrayList<TasaModel> getTasaList() {

        return tasaDAO.getTasaList("plan");
    }
    public ArrayList<TasaModel> getTasaByPlan(String plan) {
        return tasaDAO.getTasasByPlan(plan);

    }
    public boolean deleteTasa(int tasaId) throws SQLException {
        return tasaDAO.deleteTasa(tasaId);
    }
}


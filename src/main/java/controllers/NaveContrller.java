package controllers;

import madels.NaveDAO;
import madels.NaveModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class NaveContrller {
    private NaveDAO naveDAO;

    public NaveContrller(NaveDAO naveDAO) {
        this.naveDAO = naveDAO;
    }

    public boolean createNave(NaveModel nave) throws SQLException {
        return naveDAO.createNave(nave);
    }
    public ArrayList<NaveModel> getNaveList(){
        return naveDAO.getNaveList();
    }
    public ArrayList<NaveModel> getNaveListUEB(String nombre){
        return naveDAO.getNaveListUEB(nombre);
    }
}

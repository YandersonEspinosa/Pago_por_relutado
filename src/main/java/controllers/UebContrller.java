package controllers;

import madels.UebDAO;
import madels.UebModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class UebContrller {
    private UebDAO uebDAO;

    public UebContrller(UebDAO uebDAO) {

        this.uebDAO = uebDAO;
    }

    public boolean createUEB(UebModel ueb){
        return uebDAO.createUEB(ueb);
    }
    public ArrayList<UebModel> getUebList(){
        return uebDAO.getUebList();
    }
    public ArrayList<UebModel> getUebByProposito(String proposito){
        return uebDAO.getUebByProposito(proposito);
    }
    public boolean deleteUEB(int ueb_id) throws SQLException {
        return uebDAO.deleteUEB(ueb_id);
    }
}

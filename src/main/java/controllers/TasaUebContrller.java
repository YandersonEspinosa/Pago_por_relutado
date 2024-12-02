package controllers;

import madels.TasaUebDAO;
import madels.TasaUebMadel;

import java.util.ArrayList;

public class TasaUebContrller {
    private TasaUebDAO tasaUebDAO;

    public TasaUebContrller(TasaUebDAO tasaUebDAO) {
        this.tasaUebDAO = tasaUebDAO;
    }

    public boolean createTasaUeb(TasaUebMadel tasaUeb){
        return tasaUebDAO.createTasaUeb(tasaUeb);
    }
    public ArrayList<TasaUebMadel> getTasaUebList(){
        return tasaUebDAO.getTasaUebList();
    }
    public ArrayList<TasaUebMadel> getTasaUebByUEB(int ueb_id){
        return tasaUebDAO.getTasaUebByUEB(ueb_id);
    }
}

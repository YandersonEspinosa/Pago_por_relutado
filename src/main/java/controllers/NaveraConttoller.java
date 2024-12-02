package controllers;

import madels.NavaraDAO;
import madels.NaveraModel;

import java.util.ArrayList;

public class NaveraConttoller {

    private NavaraDAO navaraDAO;

    public NaveraConttoller(NavaraDAO navaraDAO) {
        this.navaraDAO = navaraDAO;
    }

    public boolean createNavera(NaveraModel navera){
        return navaraDAO.createNavera(navera);
    }
    public ArrayList<NaveraModel> getNaveraList(){
        return navaraDAO.getNaveraList();
    }
    public ArrayList<NaveraModel> getNaveraListByNombre(String nombre){
        return navaraDAO.getNaveraListByNombre(nombre);
    }
}

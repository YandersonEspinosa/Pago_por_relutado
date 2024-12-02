package views;


import controllers.*;
import madels.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JTextField tx_nombre_ueb;
    private JTextField tx_proposito;
    private JButton crearUEBButton;
    private JComboBox cb_ueb;
    private JTable tb_uebs;
    private JTextField tx_proposito_buscar;
    private JButton BUSCARUEBButton;
    private JButton ELIMINARUEBButton;
    private JButton ELIMINAUEBButton;
    private JTextField tx_id_ueb;
    private JTextField tx_porcentaje;
    private JComboBox cb_tasa;
    private JButton ELIMINARTASAButton;
    private JTable tb_tasa;
    private JTextField tx_id_tasa;
    private JButton ELIMINARTASAIDButton;
    private JTextField tx_bucar_plan;
    private JButton BUSCARTASAButton;
    private JButton CREARTASAButton;
    private JComboBox cb_tasas;
    private JComboBox cb_uebs;
    private JButton TASA_UEBButton;
    private JTable tb_tasa_ueb;
    private JButton bt_consultat_tasa_ueb;
    private JComboBox cb_uebs2;
    private JComboBox cb_nave_ueb_nombre;
    private JTable tb_nave;
    private JButton bt_crear_nave;
    private JButton buscar_nave;
    private JTextField tx_nombre_navero;
    private JTable tb_navera;
    private JTextField txUeb;
    private JButton bt_crear_navera;
    private JTextField tx_sueldo_fijo;
    private JComboBox cb_naveras;
    private JScrollBar scrollBar1;
    private JTextField tx_buscar_navera;
    private JButton bt_bucar_navera;

    private UebContrller ubContrller;
    private TasaCotroller tasaCotroller;
    private TasaUebContrller tasaUebContrller;
    private NaveContrller naveContrller;
    private NaveraConttoller naveraConttoller;
    public MainWindow() {
        setLocationRelativeTo(null);
        setSize(3000,3000);
        add(mainPanel);
        ubContrller = new UebContrller(new UebDAO());
        tasaCotroller = new TasaCotroller(new TasaDAO());
        tasaUebContrller = new TasaUebContrller(new TasaUebDAO());
        naveContrller = new NaveContrller(new NaveDAO());
        naveraConttoller = new NaveraConttoller(new NavaraDAO());

        cargarUEBs();
        cargaTasas();
        consultaTasaUeb();
        cargarNaves();
        cargarNaveras();

        crearUEBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUEB();
            }
        });
        BUSCARUEBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findUeb();
            }
        });
        ELIMINARUEBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUEB();
            }
        });
        ELIMINAUEBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delteUEB2();
            }
        });
        CREARTASAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTasa();
            }
        });
        BUSCARTASAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findTasa();
            }
        });
        ELIMINARTASAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTasa();
            }
        });
        ELIMINARTASAIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTasa2();
            }
        });

        TASA_UEBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTasaUeb();
            }
        });
        bt_consultat_tasa_ueb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              getTasaUebByUeb();
            }
        });
        bt_crear_nave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNave();
            }
        });
        buscar_nave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              cargarNavesFiltro();
            }
        });
        bt_crear_navera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNavera();
            }
        });
        bt_bucar_navera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findNavera();
            }
        });
    }
    private void findNavera(){
      var lstaNaveras =  naveraConttoller.getNaveraListByNombre(tx_buscar_navera.getText());
      cargarTablaNavra(lstaNaveras);
    }

    private void cargarNaveras(){
        var naveraList =naveraConttoller.getNaveraList();
        cargarComboNaveras(naveraList);
        cargarTablaNavra(naveraList);
    }
    private void cargarTablaNavra(ArrayList<NaveraModel> naveraList){
        String[] encabezado = new String[]{"ID", "NOMBRE", "SUELDO FIJO"};
        var modelo = new DefaultTableModel(null, encabezado);
        for (var navera: naveraList) {
            modelo.addRow(navera.toArray());
        }
        tb_navera.setModel(modelo);
    }
    private void  cargarComboNaveras(ArrayList<NaveraModel> naveraList){
        var modelo = new DefaultComboBoxModel<NaveraModel>();
        for (var navera: naveraList){
            modelo.addElement(navera);
        }
        cb_naveras.setModel(modelo);
    }
    private void createNavera(){
        var navera = new NaveraModel();
        navera.setNavera_nombre(tx_nombre_navero.getText());
        navera.setSueldo_fijo(Double.valueOf(tx_sueldo_fijo.getText()));
        var crrateOK = naveraConttoller.createNavera(navera);
        if (crrateOK){
            JOptionPane.showMessageDialog(null, "Navera Creada");
        }else {
            JOptionPane.showMessageDialog(null, "Error al crear Navera " );
        }
    }

    private void cargarNavesFiltro(){
        UebModel ueb = (UebModel) cb_nave_ueb_nombre.getSelectedItem();
        var listaNaves = naveContrller.getNaveListUEB(ueb.getNombre());
        cargarTablaNaves(listaNaves);
    }
    private void cargarNaves(){
        var navesList =naveContrller.getNaveList();
        cargarTablaNaves(navesList);
    }
    private void cargarTablaNaves(ArrayList<NaveModel> navesList){
       String[] encabezado= new String[]{"NAVE-ID", "UEB-ID","UEB-NOMBRE","UEB-PROPÓSITO" };
        var modelo = new DefaultTableModel(null,encabezado);
        for (var nave : navesList) {
         modelo.addRow(nave.toArray());
        }
        tb_nave.setModel(modelo);

    }
    private void crearNave(){
        var nave = new NaveModel();
        UebModel ueb = (UebModel) cb_nave_ueb_nombre.getSelectedItem();
        nave.setUeb_id(ueb.getUeb_id());
        cargarNaves();
        try {
           boolean creteOk = naveContrller.createNave(nave);
           if (creteOk) {
               JOptionPane.showMessageDialog(null, "Nave Creada");
           }else{
               JOptionPane.showMessageDialog(null, "Error al crear nave");
           }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                JOptionPane.showMessageDialog(null,"La nave no puede ser creada, ya que ya pertece a una UEB");
            }else {
                JOptionPane.showMessageDialog(null,"Error al eliminar en la base de datos");
            }
        }
    }

    private void getTasaUebByUeb(){
        UebModel ueb = (UebModel) cb_uebs2.getSelectedItem();
        var tasaUebList = tasaUebContrller.getTasaUebByUEB(ueb.getUeb_id());
        cargarTablaTasasUeb(tasaUebList);
    }

    private void consultaTasaUeb(){
        var tasaUeb =tasaUebContrller.getTasaUebList();
        cargarTablaTasasUeb(tasaUeb);
    }

    private void cargarTablaTasasUeb(ArrayList<TasaUebMadel> tasaUebList){
        String [] encabezado = new String[]{"UEB-ID","NOMBRE","PRPÓSITO", "TASA-ID", "PORCENTAJE","PLAN"};
        var modelo = new DefaultTableModel(null,encabezado);
        for (var tasaUeb : tasaUebList){
            modelo.addRow(tasaUeb.getArray());
        }
        tb_tasa_ueb.setModel(modelo);
    }
    private  void  createTasaUeb(){
        UebModel ueb = (UebModel) cb_uebs.getSelectedItem();
        TasaModel tasa = (TasaModel) cb_tasas.getSelectedItem();
        var tasaUeb =new TasaUebMadel(ueb.getUeb_id(),tasa.getTasa_id());
        var tasaUebOk =tasaUebContrller.createTasaUeb(tasaUeb);
        if (tasaUebOk){
            JOptionPane.showMessageDialog(null,"Tasa de la UEB creada");
            consultaTasaUeb();
        }else {
            JOptionPane.showMessageDialog(null,"Error al crear la Tasa de la UEB" );
        }
    }
    private void deleteTasa2(){
        int number;
        if (tx_id_tasa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Indica un ID");
            return;
        }else {
            try{
                 number = Integer.parseInt(tx_id_tasa.getText());
            }catch (NumberFormatException error){
                JOptionPane.showMessageDialog(null, "Debe indicar un número");
                return;
            }
            int result = JOptionPane.showConfirmDialog(null,"¿Ralmente desea eliminar la tasa?");
            if (result == 0) {
                try {
                    var deleteOk = tasaCotroller.deleteTasa(number);
                    if (deleteOk) {
                        JOptionPane.showMessageDialog(null,"Tasa eliminada");
                        cargaTasas();
                    }else {
                        JOptionPane.showMessageDialog(null,"El ID no existe");
                    }
                } catch (SQLException e) {
                   if (e.getSQLState().equals("23503")) {
                       JOptionPane.showMessageDialog(null,"La tasa no puede ser eliminada, ya que tiene relcion con unu UEB");
                    }else {
                       JOptionPane.showMessageDialog(null,"Error al eliminar en la base de datos");
                   }
                }

            }
        }
    }

    private void deleteTasa(){
        TasaModel tasa = (TasaModel) cb_tasa.getSelectedItem();
        int result = JOptionPane.showConfirmDialog(null,"¿Ralmente desea eliminar la tasa?");
        if (result == 0) {
            try {
                var deleteOk = tasaCotroller.deleteTasa(tasa.getTasa_id());
                if (deleteOk) {
                    JOptionPane.showMessageDialog(null,"Tasa eliminada");
                    cargaTasas();
                }else {
                    JOptionPane.showMessageDialog(null,"El ID no existe");
                }
            } catch (SQLException e) {
                if (e.getSQLState().equals("23503")) {
                    JOptionPane.showMessageDialog(null,"La tasa no puede ser eliminada, ya que tiene relcion co un UEB");
                }else {
                    JOptionPane.showMessageDialog(null,"Error al eliminar en la base de datos");
                }
            }

        }
    }

    private void findTasa(){
        var listaTasa = tasaCotroller.getTasaByPlan(tx_bucar_plan.getText());
        cargarTablaTasas(listaTasa);
    }

    private void createTasa(){
        var tasa = new TasaModel();
        tasa.setPorcentaje(Double.parseDouble(tx_porcentaje.getText()));
        var createOh = tasaCotroller.createTasa(tasa);
        if (createOh){
            JOptionPane.showMessageDialog(mainPanel, "Tasa creada con exito");
            cargaTasas();
        }else {
            JOptionPane.showMessageDialog(mainPanel, "Error al crear la tasa" );
        }
    }
    private void cargaTasas(){
        var listaTasas = tasaCotroller.getTasaList();
        cargaComboTasas(listaTasas);
        cargarTablaTasas(listaTasas);
    }
    private void  cargaComboTasas(ArrayList<TasaModel> listaTasas){
        var modelo = new DefaultComboBoxModel<TasaModel>();
        for (var tasa : listaTasas){
            modelo.addElement(tasa);
        }
        cb_tasa.setModel(modelo);
        cb_tasas.setModel(modelo);
     }
    private void cargarTablaTasas(ArrayList<TasaModel> listaTasas){
        String[] encabezados = new String[]{"ID", "PORCENTAJE", "PLAN"};
        var modelo = new DefaultTableModel(null,encabezados);
        for (var tasa : listaTasas){
            modelo.addRow(tasa.toArray());
        }
        tb_tasa.setModel(modelo);
    }
    private void findUeb(){
        var listaUebs = ubContrller.getUebByProposito(tx_proposito_buscar.getText());
        cargarTablaUebs(listaUebs);
    }
    private void delteUEB2(){
        int number;
        if (tx_id_ueb.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Indica un ID");
            return;
        }else{
            try {
                number = Integer.parseInt(tx_id_ueb.getText());
            }catch (NumberFormatException  err){
                JOptionPane.showMessageDialog(null, "Debes ndica un número");
                return;
            }
            int result = JOptionPane.showConfirmDialog(null, "¿Realmente desea eleminar?");
            if(result == 0) {
                try {
                    var deleteOk = ubContrller.deleteUEB(number);
                    if(deleteOk) {
                        JOptionPane.showMessageDialog(null, "La UEB se eliminado correctamente");
                        cargarUEBs();
                    }else {
                        JOptionPane.showMessageDialog(null, "No se encontro el UEB");
                    }
                } catch (SQLException e) {
                    if (e.getSQLState().equals("23503")){
                        JOptionPane.showMessageDialog(null, "La UEB no pede eliminarse, ya que posee Tasas o Naves en la base de datos");
                    }else {
                        JOptionPane.showMessageDialog(null, "El UEB no se puede eliminar");
                    }

                }

            }
        }
    }

    private void deleteUEB(){
        UebModel ueb = (UebModel) cb_ueb.getSelectedItem();
        int result = JOptionPane.showConfirmDialog(null, "¿Realmente desea eleminar?");
        if(result == 0) {
            try {
                var deleteOk = ubContrller.deleteUEB(ueb.getUeb_id());
                if(deleteOk) {
                    JOptionPane.showMessageDialog(null, "La UEB se eliminado correctamente");
                    cargarUEBs();
                }else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar la UEB");
                }
            } catch (SQLException e) {
                if (e.getSQLState().equals("23503")){
                    JOptionPane.showMessageDialog(null, "La UEB no pede eliminarse, ya que posee Tasas o Naves en la base de datos");
                }else {
                    JOptionPane.showMessageDialog(null, "El UEB no se puede eliminar");
                }
            }

        }
    }

    private void createUEB(){
        var ueb = new UebModel();
        ueb.setNombre(tx_nombre_ueb.getText());
        ueb.setTipo(tx_proposito.getText());
        var createOK = ubContrller.createUEB(ueb);
        if (createOK) {
            JOptionPane.showMessageDialog(mainPanel, "UEB CREADO CORRECTAMENTE");
            cargarUEBs();
        }else {
            JOptionPane.showMessageDialog(mainPanel, "ERROR AL CREAR UEB") ;
        }
    }

    private void cargarUEBs(){
        var uebList = ubContrller.getUebList();
        cargarComboUEBs(uebList);
        cargarTablaUebs(uebList);
    }
    private void cargarComboUEBs(ArrayList<UebModel> uebList){
        var modelo = new DefaultComboBoxModel<UebModel>();
        for (var ueb : uebList){
            modelo.addElement(ueb);
        }
        cb_ueb.setModel(modelo);
        cb_uebs.setModel(modelo);
        cb_uebs2.setModel(modelo);
        cb_nave_ueb_nombre.setModel(modelo);
    }


    private void cargarTablaUebs(ArrayList<UebModel> uebList){
        String[] encabezados = new String[]{"ID","NOMBRE","PROPÓSITO"};
        var modelo = new DefaultTableModel(null,encabezados);
        for (var ueb : uebList){
            modelo.addRow(ueb.toArray());
        }
        tb_uebs.setModel(modelo);
    }


    public static void main(String[] args) {

        Font font = new Font("Calibri", Font.PLAIN, 12);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Slider.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("List.font", font);
        UIManager.put("Button.font", font);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {new MainWindow().setVisible(true);}
        });

    }

}

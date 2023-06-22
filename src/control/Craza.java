/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Manimal;
import model.Mraza;
import vista.Vraza;

/**
 *
 * @author hp
 */
public class Craza implements ActionListener {
    
    Mraza mraza;
    Vraza vraza;
    
    public Craza() {
        this.mraza = new Mraza();
        this.vraza = new Vraza();
        setEvent();
        this.vraza.actualizar();
        this.vraza.setVisible(true);
    }
    
    public void setEvent() {
        this.vraza.btguardar.setActionCommand("REGISTRAR");
        this.vraza.btguardar.addActionListener(this);
        this.vraza.btmodificar.setActionCommand("MODIFICAR");
        this.vraza.btmodificar.addActionListener(this);
        this.vraza.bteliminar.setActionCommand("ELIMINAR");
        this.vraza.bteliminar.addActionListener(this);
        this.vraza.btfoto.setActionCommand("FOTO");
        this.vraza.btfoto.addActionListener(this);
        
        this.vraza.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla(vraza.jTable1.getSelectedRow());
            }
        });
    }
    
    private void clickEnGrilla(Integer fila) {
        this.vraza.getData(fila);
        this.vraza.btguardar.setEnabled(true);
        this.vraza.btmodificar.setEnabled(true);
        this.vraza.btguardar.setText("nuevo");
    }
    
    public enum AccionMVC {
        REGISTRAR, MODIFICAR, BUSCAR, ELIMINAR, FOTO
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Craza c = new Craza();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String tamanio = String.valueOf(vraza.cbtamanio.getSelectedItem());
        String pelo = String.valueOf(vraza.cbpelo.getSelectedItem());
        String tejido = String.valueOf(vraza.cbtejido.getSelectedItem());
        Manimal manimal = new Manimal();
        manimal = (Manimal) vraza.cbanimal.getSelectedItem();
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case REGISTRAR:
                if (vraza.txtnombre.getText().equals("") || vraza.txtpeso.getText().equals("")) {
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if (vraza.btguardar.getText().equals("nuevo")) {
                    vraza.actualizar();
                    vraza.btguardar.setText("registrar");
                    vraza.btmodificar.setEnabled(false);
                    vraza.bteliminar.setEnabled(false);
                    break;
                }
                this.mraza.setNombre(this.vraza.txtnombre.getText());
                this.mraza.setDescripcion(this.vraza.txtdesc.getText());
                this.mraza.setTamanio(tamanio);
                this.mraza.setPelo(pelo);
                this.mraza.setPeso(Integer.parseInt(this.vraza.txtpeso.getText().toString()));
                this.mraza.setTejido(tejido);
                this.mraza.setIdAnimal(manimal.getId());
                this.mraza.registrar();
                this.vraza.actualizar();
                break;
            case MODIFICAR:
                break;
        }
    }
    
}

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
import model.Mmascota;
import vista.Vatencion;

/**
 *
 * @author hp
 */
public class Catencion implements ActionListener {

    /**
     * @param args the command line arguments
     */
    Mmascota mmascota;
    Vatencion vatencion;

    public Catencion() {
        this.mmascota = new Mmascota();
        this.vatencion = new Vatencion();
        setEvent();
        this.vatencion.actualizar();
        this.vatencion.setVisible(true);
    }

    public void setEvent() {
        this.vatencion.btguardar.setActionCommand("REGISTRAR");
        this.vatencion.btguardar.addActionListener(this);
        this.vatencion.btmodificar.setActionCommand("MODIFICAR");
        this.vatencion.btmodificar.addActionListener(this);
        this.vatencion.bteliminar.setActionCommand("ELIMINAR");
        this.vatencion.bteliminar.addActionListener(this);

        this.vatencion.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla(vatencion.jTable1.getSelectedRow());
            }
        });
    }

    private void clickEnGrilla(Integer fila) {
        this.vatencion.getData(fila);
        this.vatencion.btguardar.setEnabled(true);
        this.vatencion.btmodificar.setEnabled(true);
        this.vatencion.btguardar.setText("nuevo");
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Catencion c = new Catencion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

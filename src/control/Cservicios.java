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
import model.Mdetservicio;
import model.Mservicio;
import vista.Vservicios;

/**
 *
 * @author hp
 */
public class Cservicios implements ActionListener {

    /**
     * @param args the command line arguments
     */
    Mdetservicio mdetservicio;
    Mservicio mservicio;
    Manimal manimal;
    Vservicios vservicio;

    public Cservicios() {
        this.mdetservicio = new Mdetservicio();
        this.mservicio = new Mservicio();
        this.manimal = new Manimal();
        this.vservicio=new Vservicios();
        setEvent();
        this.vservicio.actualizar();
        this.vservicio.setVisible(true);
    }

    public void setEvent() {
        this.vservicio.btregistrar.setActionCommand("REGISTRAR");
        this.vservicio.btregistrar.addActionListener(this);
        this.vservicio.btmodificar.setActionCommand("MODIFICAR");
        this.vservicio.btmodificar.addActionListener(this);
        this.vservicio.bteliminar.setActionCommand("ELIMINAR");
        this.vservicio.bteliminar.addActionListener(this);

        this.vservicio.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla(vservicio.jTable1.getSelectedRow());
            }
        });
    }

    private void clickEnGrilla(Integer fila) {
        this.vservicio.getData(fila);
        this.vservicio.btregistrar.setEnabled(true);
        this.vservicio.btmodificar.setEnabled(true);
        this.vservicio.btregistrar.setText("nuevo");
    }

    public enum AccionMVC {
        REGISTRAR, MODIFICAR, ELIMINAR
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Cservicios c = new Cservicios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idservicio = ((Mservicio) vservicio.cbservicio.getSelectedItem()).getId();
        int idanimal = ((Manimal) vservicio.cbanimal.getSelectedItem()).getId();
        String tamanio = String.valueOf(vservicio.cbtamanio.getSelectedItem());
        String genero = String.valueOf(vservicio.cbgenero.getSelectedItem());
        String pelo = String.valueOf(vservicio.cbpelo.getSelectedItem());
        double peso = Double.parseDouble(vservicio.txtpeso.getText().toString());
        String tejido = String.valueOf(vservicio.cbtejido.getSelectedItem());
        double precio = Double.parseDouble(vservicio.txtPrecio.getText().toString());
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case REGISTRAR:
                if (vservicio.txtPrecio.getText().equals("")) {
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if (vservicio.btregistrar.getText().equals("nuevo")) {
                    vservicio.actualizar();
                    vservicio.btregistrar.setText("registrar");
                    vservicio.btmodificar.setEnabled(false);
                    vservicio.bteliminar.setEnabled(false);
                    break;
                }
                this.mdetservicio.setIdservicio(idservicio);
                this.mdetservicio.setIdanimal(idanimal);
                this.mdetservicio.setTamanio(tamanio);
                this.mdetservicio.setGenero(genero);
                this.mdetservicio.setPelo(pelo);
                this.mdetservicio.setPeso(peso);
                this.mdetservicio.setTejido(tejido);
                this.mdetservicio.setPrecio(precio);
                this.mdetservicio.registrar();
                this.vservicio.actualizar();
                break;
            case MODIFICAR:
                break;
        }
    }

}

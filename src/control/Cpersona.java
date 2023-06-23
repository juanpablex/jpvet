/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Mestado;
import model.Mpersona;
import vista.Vpersona;

/**
 *
 * @author ColaVirtual
 */
public class Cpersona implements ActionListener {

    public Cpersona() {
        this.mpersona = new Mpersona();
        this.vpersona = new Vpersona(mpersona);
        setEvent();
        this.vpersona.actualizar();
        this.vpersona.setVisible(true);
    }
    
    public void setEvent() {
        this.vpersona.btRegistrar.setActionCommand("REGISTRAR");
        this.vpersona.btRegistrar.addActionListener(this);
        this.vpersona.btModificar.setActionCommand("MODIFICAR");
        this.vpersona.btModificar.addActionListener(this);
        this.vpersona.btBuscar.setActionCommand("BUSCAR");
        this.vpersona.btBuscar.addActionListener(this);
        this.vpersona.btEliminar.setActionCommand("ELIMINAR");
        this.vpersona.btEliminar.addActionListener(this);
        this.vpersona.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (vpersona.jTable1.getSelectedRow() >= 0) {
                    clickEnGrilla(vpersona.jTable1.getSelectedRow());
                }
            }
        });
        this.vpersona.cbestado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //vpersona.setEstado();
            }
        });
        this.vpersona.cbsexo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
//        this.vpersona.chsexo.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 boolean sw=vpersona.chsexo.isSelected();
//                 if(sw)
//                     vpersona.setQBESexo(true);
//                 else
//                     vpersona.setQBESexo(false);
//             }
//         });
//        this.vpersona.chestado.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 boolean sw= vpersona.chestado.isSelected();
//                 if(sw)
//                     vpersona.setQBEEstado(true);
//                 else
//                     vpersona.setQBEEstado(false);
//                 
//             }
//         });
    }

    private void clickEnGrilla(Integer fila) {
        //aqui usar el patron estado
        this.vpersona.getData(fila);
        this.vpersona.txtci.setEnabled(false);
        this.vpersona.btEliminar.setEnabled(true);
        this.vpersona.btModificar.setEnabled(true);
        this.vpersona.btRegistrar.setText("nuevo");
        
    }
    Mpersona mpersona;
    Vpersona vpersona;

    public enum AccionMVC {
        REGISTRAR, MODIFICAR, BUSCAR, ELIMINAR,
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Mestado g = (Mestado) vpersona.cbestado.getSelectedItem();
        String g2 = String.valueOf(vpersona.cbsexo.getSelectedItem());
        
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case REGISTRAR:
                if (vpersona.txtci.getText().equals("") || vpersona.txtnombres.getText().equals("")) {
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if (vpersona.btRegistrar.getText().equals("nuevo")) {
                    vpersona.btRegistrar.setText("registrar");
                    vpersona.btModificar.setEnabled(false);
                    vpersona.btEliminar.setEnabled(false);
                    vpersona.txtci.setEnabled(true);
                    vpersona.actualizar();
                    
                    break;
                }
                this.mpersona.setCi(this.vpersona.txtci.getText());
                this.mpersona.setNombres(this.vpersona.txtnombres.getText());
                this.mpersona.setSexo(g2);
                this.mpersona.setIdestado(g.getId());
                this.mpersona.setDireccion(this.vpersona.txtdireccion.getText());
                this.mpersona.setTelefonos(this.vpersona.txttelefonos.getText());
                this.mpersona.setEmail(this.vpersona.txtCorreo.getText());
                long d = new Date().getTime();
                
                this.mpersona.setFecing(new Timestamp(new Date().getYear(),
                        new Date().getMonth(), new Date().getDay(),
                        new Date().getHours(), new Date().getMinutes(),
                        new Date().getSeconds(), 0));
                this.mpersona.setLatitud("");
                this.mpersona.setLongitud("");
                this.mpersona.registrar();
                this.vpersona.actualizar();
                break;
            
            case MODIFICAR:
                this.mpersona.setCi(this.vpersona.txtci.getText());
                this.mpersona.setNombres(this.vpersona.txtnombres.getText());
                this.mpersona.setSexo(g2);
                this.mpersona.setIdestado(g.getId());
                this.mpersona.setDireccion(this.vpersona.txtdireccion.getText());
                this.mpersona.setTelefonos(this.vpersona.txttelefonos.getText());
                this.mpersona.setEmail(this.vpersona.txtCorreo.getText());
                this.mpersona.setLatitud("");
                this.mpersona.setLongitud("");
                this.mpersona.modificar();
                this.vpersona.actualizar();
                this.vpersona.txtci.setEnabled(true);
                this.vpersona.btModificar.setEnabled(false);
                this.vpersona.btEliminar.setEnabled(false);
                this.vpersona.btRegistrar.setEnabled(true);
                
                break;
            
            case BUSCAR:
                if (this.vpersona.txtci.getText().equals("")) {
                    this.mpersona.setCi("-1");
                } else {
                    this.mpersona.setCi(this.vpersona.txtci.getText());
                }
                this.mpersona.setNombres(this.vpersona.txtnombres.getText());
                this.mpersona.setSexo(g2);
                this.mpersona.setIdestado(g.getId());
                this.vpersona.getQBE();
                this.vpersona.getQBESexo();
                this.vpersona.getQBEEstado();
                //ArrayList<Mpersona>lista= this.mpersona.buscar();
                //this.vpersona.actualizarBusqueda(lista);
                this.vpersona.txtci.setEnabled(true);
                this.vpersona.btModificar.setEnabled(false);
                this.vpersona.btEliminar.setEnabled(false);
                this.vpersona.btRegistrar.setEnabled(true);
                break;
            case ELIMINAR:
                this.mpersona.eliminar();
                this.vpersona.actualizar();
                this.vpersona.btModificar.setEnabled(false);
                this.vpersona.btEliminar.setEnabled(false);
                this.vpersona.btRegistrar.setEnabled(true);
                this.vpersona.txtci.setEnabled(true);
                break;
        }
    }

    public static void main(String[] args) {
        Cpersona p = new Cpersona();
    }
}

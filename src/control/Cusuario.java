/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Mgrupo;
import model.Musuario;
import vista.Vusuario;

/**
 *
 * @author ColaVirtual
 */
public class Cusuario implements ActionListener{
    Musuario musuario;
    Vusuario vusuario;
    public Cusuario(){
        this.musuario=new Musuario();
        this.vusuario=new Vusuario(musuario);
        setEvent();
        this.vusuario.actualizar();
        this.vusuario.setVisible(true);
    }
    public void setEvent(){
        this.vusuario.btRegistrar.setActionCommand("REGISTRAR");
        this.vusuario.btRegistrar.addActionListener(this);
        this.vusuario.btModificar.setActionCommand("MODIFICAR");
        this.vusuario.btModificar.addActionListener(this);
        this.vusuario.btBuscar.setActionCommand("BUSCAR");
        this.vusuario.btBuscar.addActionListener(this);
        this.vusuario.btEliminar.setActionCommand("ELIMINAR");
        this.vusuario.btEliminar.addActionListener(this);
        
        this.vusuario.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla2(vusuario.jTable1.getSelectedRow());
            }
        });
        this.vusuario.jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla(vusuario.jTable2.getSelectedRow());
            }
        });
        this.vusuario.cbGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vusuario.setGrupo();
            }
        });
    }
     private void clickEnGrilla(Integer fila) {
         //aqui usar el patron estado
        this.vusuario.getData(fila);
        this.vusuario.txtNick.setEnabled(false);
        this.vusuario.btEliminar.setEnabled(true);
        this.vusuario.btModificar.setEnabled(true);
        this.vusuario.btRegistrar.setText("nuevo");
        
    }
     private void clickEnGrilla2(Integer fila) {
         //aqui usar el patron estado
        this.vusuario.getData2(fila);
        this.vusuario.txtpersona.setEnabled(false);
        this.vusuario.btEliminar.setEnabled(true);
        this.vusuario.btModificar.setEnabled(true);
       // this.vmascota.btRegistrar.setText("nuevo");
        
    }
    public static void main(String[]args){
        Cusuario p=new Cusuario();
    }
    public enum AccionMVC{
        REGISTRAR,MODIFICAR,BUSCAR,ELIMINAR
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Mgrupo g=(Mgrupo)vusuario.cbGrupo.getSelectedItem();
        String g2=String.valueOf(vusuario.cbGrupo.getSelectedItem());
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case REGISTRAR:
                if(vusuario.txtNick.getText().equals("")||vusuario.txtpersona.getText().equals("")){
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if(vusuario.btRegistrar.getText().equals("nuevo")){
                    vusuario.actualizar();
                    vusuario.btRegistrar.setText("registrar");
                    vusuario.btModificar.setEnabled(false);
                    vusuario.btEliminar.setEnabled(false);
                    vusuario.txtNick.setEnabled(true);
                    
                    break;
                }
                this.musuario.setNick(this.vusuario.txtNick.getText());
                this.musuario.setPass("123");
                Date d=new Date();
                d=this.vusuario.jCalendar1.getCalendar().getTime();
                this.musuario.setFecini(new Timestamp(System.currentTimeMillis()));
                this.musuario.setFecfin(new Timestamp(d.getYear(),d.getMonth(),
                d.getDate(),d.getHours(),d.getMinutes(),d.getSeconds(),0));
                this.musuario.setIdgrupo(g.getId());
                this.musuario.setIdpersona(this.vusuario.txtpersona.getText().toString().trim());
                this.musuario.setIdestado(1);
                this.musuario.registrar();
                this.vusuario.actualizar();
                break;
            case MODIFICAR:
                //this.musuario.setNick(g2);
                break;
            case BUSCAR:
                break;
            case ELIMINAR:
                break;
        }
    }
}

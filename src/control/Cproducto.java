/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Mproducto;
import model.Mtipoproducto;
import vista.Vproducto;

/**
 *
 * @author ColaVirtual
 */
public class Cproducto implements ActionListener{
    Mproducto mproducto;
    Vproducto vproducto;
    public Cproducto(){
        this.mproducto=new Mproducto();
        this.vproducto=new Vproducto(mproducto);
        setEvent();
        this.vproducto.actualizar();
        this.vproducto.setVisible(true);
    }
    public void setEvent(){
        this.vproducto.btRegistrar.setActionCommand("REGISTRAR");
        this.vproducto.btRegistrar.addActionListener(this);
        this.vproducto.btModificar.setActionCommand("MODIFICAR");
        this.vproducto.btModificar.addActionListener(this);
        this.vproducto.btBuscar.setActionCommand("BUSCAR");
        this.vproducto.btBuscar.addActionListener(this);
        this.vproducto.btEliminar.setActionCommand("ELIMINAR");
        this.vproducto.btEliminar.addActionListener(this);
        this.vproducto.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(vproducto.jTable1.getSelectedRow()>=0){
                    clickEnGrilla(vproducto.jTable1.getSelectedRow()
);                }
            }
        });
        this.vproducto.cbtipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                vproducto.txtnombre.setText("");
                vproducto.txtid.setText("");
                vproducto.txtcod.setText("");
                vproducto.txtdesc.setText("");
                vproducto.txtprecio.setText("");
            }
        });
        this.vproducto.cbtipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vproducto.setTipo();
            }
        });
    }
   private void clickEnGrilla(Integer fila) {
         //aqui usar el patron estado
        this.vproducto.getData(fila);
        this.vproducto.btEliminar.setEnabled(true);
        this.vproducto.btModificar.setEnabled(true);
        this.vproducto.btRegistrar.setText("nuevo");
        
    }
   public enum AccionMVC{
        REGISTRAR,MODIFICAR,BUSCAR,ELIMINAR,
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Mtipoproducto g=(Mtipoproducto)vproducto.cbtipo.getSelectedItem();
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case REGISTRAR:
                if(vproducto.txtnombre.getText().equals("")){
                    System.out.println("No se insertó, debe poner un nombre");
                    break;
                }
                if(vproducto.btRegistrar.getText().equals("nuevo")){
                    vproducto.btRegistrar.setText("registrar");
                    vproducto.btModificar.setEnabled(false);
                    vproducto.btEliminar.setEnabled(false);
                    vproducto.actualizar();
                    break;
                }
                this.mproducto.setCodigo(this.vproducto.generarCodigo(g));
                this.mproducto.setNombre(this.vproducto.txtnombre.getText().toString().trim());
                this.mproducto.setDescripcion(this.vproducto.txtdesc.getText().toString().trim());
                this.mproducto.setPrecio(Integer.parseInt(this.vproducto.txtprecio.getText().toString().trim()));
                this.mproducto.setIdtipo(g.getId());
                this.mproducto.registrar();
                this.vproducto.actualizar();
            case MODIFICAR:
                this.mproducto.setCodigo(this.vproducto.txtcod.getText().toString().trim());
                this.mproducto.setNombre(this.vproducto.txtnombre.getText().toString().trim());
                this.mproducto.setDescripcion(this.vproducto.txtdesc.getText().toString().trim());
                this.mproducto.setPrecio(Double.parseDouble(this.vproducto.txtprecio.getText().toString().trim()));
                this.mproducto.setIdtipo(g.getId());
                this.mproducto.modificar();
                this.vproducto.actualizar();
                this.vproducto.btModificar.setEnabled(false);
                this.vproducto.btEliminar.setEnabled(false);
                this.vproducto.btRegistrar.setEnabled(true);
                break;
                
        }
    }
     public static void main(String[]args){
        Cproducto p=new Cproducto();
    }
}

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
import model.Manimal;
import model.Mestado;
import model.Mmascota;
import model.Mraza;
import vista.Vmascota;

/**
 *
 * @author ColaVirtual
 */
public class Cmascota implements ActionListener{
    Mmascota mmascota;
    Vmascota vmascota;
    
    public Cmascota(){
        this.mmascota=new Mmascota();
        this.vmascota=new Vmascota(mmascota);
        setEvent();
        this.vmascota.actualizar();
        this.vmascota.setVisible(true);
        
    }
    public void setEvent(){
         this.vmascota.btRegistrar.setActionCommand("REGISTRAR");
        this.vmascota.btRegistrar.addActionListener(this);
        this.vmascota.btModificar.setActionCommand("MODIFICAR");
        this.vmascota.btModificar.addActionListener(this);
        this.vmascota.btBuscar.setActionCommand("BUSCAR");
        this.vmascota.btBuscar.addActionListener(this);
        this.vmascota.btEliminar.setActionCommand("ELIMINAR");
        this.vmascota.btEliminar.addActionListener(this);
        this.vmascota.btFoto.setActionCommand("FOTO");
        this.vmascota.btFoto.addActionListener(this);
        this.vmascota.btLibreta.setActionCommand("LIBRETA");
        this.vmascota.btLibreta.addActionListener(this);
        
        
        this.vmascota.jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 clickEnGrilla(vmascota.jTable2.getSelectedRow());
             }
         });
        this.vmascota.jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 clickEnGrilla2(vmascota.jTable1.getSelectedRow());
             }
         });
        this.vmascota.cbestado.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 vmascota.setEstado();
             }
         });
        this.vmascota.cbanimal.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                vmascota.setAnimal();
                vmascota.setRaza(vmascota.manimal.getId());
             }
         });
        
        this.vmascota.cbraza.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 vmascota.setRaza();
             }
         });
       
       
        
        
    }
    private void agregarModulos(){
        
    }
     private void clickEnGrilla(Integer fila) {
         //aqui usar el patron estado
        this.vmascota.getData(fila);
        this.vmascota.btEliminar.setEnabled(true);
        this.vmascota.btModificar.setEnabled(true);
        this.vmascota.btRegistrar.setText("nuevo");
        
    }
      private void clickEnGrilla2(Integer fila) {
         //aqui usar el patron estado
        this.vmascota.getData2(fila);
        this.vmascota.txtpersona.setEnabled(false);
        this.vmascota.btEliminar.setEnabled(true);
        this.vmascota.btModificar.setEnabled(true);
       // this.vmascota.btRegistrar.setText("nuevo");
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cmascota c=new Cmascota();
    }
public enum AccionMVC{
        REGISTRAR,MODIFICAR,BUSCAR,ELIMINAR,FOTO,LIBRETA
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      Mestado g = (Mestado)vmascota.cbestado.getSelectedItem();
          String g2=String.valueOf(vmascota.cbgenero.getSelectedItem());  
          Manimal manimal=new Manimal();
          Manimal g3=(Manimal)vmascota.cbanimal.getSelectedItem();  
          Mraza g4=(Mraza)(vmascota.cbraza.getSelectedItem());  
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case REGISTRAR:
                if(vmascota.txtpersona.getText().equals("")||vmascota.txtnombre.getText().equals("")){
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if(vmascota.btRegistrar.getText().equals("nuevo")){
                    vmascota.actualizar();
                    vmascota.btRegistrar.setText("registrar");
                    vmascota.btModificar.setEnabled(false);
                    vmascota.btEliminar.setEnabled(false);
                    break;
                }
                this.mmascota.setNombre(this.vmascota.txtnombre.getText());
                this.mmascota.setDescripcion(this.vmascota.txtDescripcion.getText().toString().trim());
                this.mmascota.setGenero(g2);
                Date d= new Date();
                d=this.vmascota.jCalendar1.getCalendar().getTime();
                this.mmascota.setFechanac(new Timestamp(d.getYear(), d.getMonth(),
                        d.getDate(), d.getHours(),d.getMinutes(),d.getSeconds(), 0));
                d=new Date();
                this.mmascota.setFechareg(new Timestamp(d.getYear(), 
                        d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(),
                        d.getSeconds(),0));
                this.mmascota.setIdpersona(this.vmascota.txtpersona.getText().toString().trim());
                this.mmascota.setIdestado(g.getId());
                this.mmascota.setIdanimal(g3.getId());
                this.mmascota.setIdraza(g4.getId());
                this.mmascota.registrar();
                this.vmascota.actualizar();
                break;

            case MODIFICAR:
                 Date d2= new Date();
                d2=this.vmascota.jCalendar1.getCalendar().getTime();
               this.mmascota.setId(Integer.parseInt(this.vmascota.txtid.getText()));
                this.mmascota.setNombre(this.vmascota.txtnombre.getText());
                this.mmascota.setGenero(g2);
                this.mmascota.setDescripcion(this.vmascota.txtDescripcion.getText());
                this.mmascota.setIdestado(g.getId());
                this.mmascota.setIdraza(g4.getId());
                this.mmascota.setIdanimal(g3.getId());
                this.mmascota.setFechanac(new Timestamp(d2.getYear(), d2.getMonth(),
                        d2.getDate(), d2.getHours(),d2.getMinutes(),d2.getSeconds(), 0));
                this.mmascota.setFechareg(this.mmascota.getFechareg());
                this.mmascota.setIdpersona(this.vmascota.txtpersona.getText());
                this.mmascota.modificar();
                this.vmascota.actualizar();
                
                this.vmascota.btModificar.setEnabled(false);
                this.vmascota.btEliminar.setEnabled(false);
                this.vmascota.btRegistrar.setEnabled(true);
                
                break;
                
            case BUSCAR:
               /* if(this.vpersona.txtci.getText().equals(""))
                    this.mpersona.setCi("-1");
                else
                    this.mpersona.setCi(this.vpersona.txtci.getText());
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
                this.vpersona.btRegistrar.setEnabled(true);*/
                break;
            case ELIMINAR:
               /* this.mpersona.eliminar();
                this.vpersona.actualizar();
                this.vpersona.btModificar.setEnabled(false);
                this.vpersona.btEliminar.setEnabled(false);
                this.vpersona.btRegistrar.setEnabled(true);
                this.vpersona.txtci.setEnabled(true);*/
                break;
            case FOTO:
                break;
            case LIBRETA:
                break;
        }  
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.Mmodgrupo;
import vista.Vmenu;

/**
 *
 * @author ColaVirtual
 */
public class Cmenu {

    /**
     * @param args the command line arguments
     */
    Mmodgrupo mmodgrupo;
    Vmenu vmenu;

    public Cmenu() {
        this.mmodgrupo = new Mmodgrupo();
        this.vmenu = new Vmenu(mmodgrupo);
        setEvent();
        //this.vmenu.actualizar();
        this.vmenu.setVisible(true);
    }

    public void setEvent() {
        this.vmenu.actualizar();

        for (int i = 0; i < this.vmenu.jMenuBar1.getMenuCount(); i++) {
            JMenu m = this.vmenu.jMenuBar1.getMenu(i);
            switch (m.getText()) {
                case "opciones":
                    cargarOpciones(m);
                    break;
                case "modulos":
                    cargarAcciones(m);
                    break;

            }

        }
    }

    public void cargarOpciones(JMenu m) {
        for (int j = 0; j < m.getItemCount(); j++) {
            String opcion = m.getItem(j).getText();
            m.getItem(j).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (opcion) {
                        case "salir":
                            System.exit(0);
                            break;
                    }
                }
            });
        }
    }

    public void cargarAcciones(JMenu m) {
        for (int j = 0; j < m.getItemCount(); j++) {
            String modulo = m.getItem(j).getText();
            m.getItem(j).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (modulo) {
                        case "PERSONAS":
                            new Cpersona();
                            break;
                        case "MASCOTAS":
                            new Cmascota();
                            break;
                        case "USUARIOS":
                            new Cusuario();
                            break;
                        case "GRUPO":
                            new Cgrupo();
                            break;
                        case "PRODUCTOS":
                            new Cproducto();
                            break;
                        case "RAZA":
                            new Craza();
                            break;
                        case "SERVICIOS":
                            new Cservicios();
                            break;
                        case "LOGIN":
                            new Clogin();
                            vmenu.dispose();
                            break;
                        case "SALIR":
                            System.exit(0);
                            break;
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Cmenu c = new Cmenu();
    }

}

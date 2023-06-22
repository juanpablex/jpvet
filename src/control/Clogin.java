/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Musuario;
import model.auxiliar;
import vista.Vlogin;

/**
 *
 * @author ColaVirtual
 */
public class Clogin implements ActionListener {

    Musuario musuario;
    Vlogin vlogin;

    public Clogin() {
        this.musuario=new Musuario();
        this.vlogin=new Vlogin(musuario);
        auxiliar.nick="invitado";
        setEvent();
        this.vlogin.actualizar();
        this.vlogin.setVisible(true);

        
    }

    public void setEvent() {
        this.vlogin.btIngresar.setActionCommand("INGRESAR");
        this.vlogin.btIngresar.addActionListener(this);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Clogin p = new Clogin();

    }

    public enum AccionMVC {
        INGRESAR,
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case INGRESAR:
                this.musuario.setNick(this.vlogin.txtUsuario.getText().toString().trim());
                this.musuario.setPass(this.vlogin.txtPass.getText().toString().trim());
                Musuario login = musuario.ingresar();
                if (login != null) {
                    // this.vlogin.actualizar();
                    String ps = this.vlogin.txtPass.getText().toString().trim();
                    if (!login.getPass().equals(this.vlogin.txtPass.getText().toString().trim())) {
                        this.vlogin.errores('p');
                    } else {
                        auxiliar.nick = login.getNick();
                        this.vlogin.dispose();
                        Cmenu c = new Cmenu();

                    }
                } else {
                    this.vlogin.errores('l');
                }

                break;
        }
    }

}

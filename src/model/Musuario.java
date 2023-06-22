/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ColaVirtual
 */
public class Musuario {
    int id;
    int idestado;
    int idgrupo;
    String idpersona;
    String nick;
    String pass;
    Timestamp fecini;
    Timestamp fecfin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Timestamp getFecini() {
        return fecini;
    }

    public void setFecini(Timestamp fecini) {
        this.fecini = fecini;
    }

    public Timestamp getFecfin() {
        return fecfin;
    }

    public void setFecfin(Timestamp fecfin) {
        this.fecfin = fecfin;
    }
     public int registrar() {
        String sql = "insert into usuario(nick,pass,fecini,fecfin,"
                + "idgrupo,idpersona,idestado)"
                + "values(?,?,?,?,?,?,?);";
        return Bd.getConexion().registrar("usuario", sql, nick,pass,fecini,fecfin,
                idgrupo,idpersona,idestado);
    }

    public int modificar() {
        String sql = "UPDATE usuario SET pass=?, fecini=?,"
                + "fecfin=?,idgrupo=?,idpersona=?,idestado=? WHERE nick=?";
        return Bd.getConexion().modificar(sql,pass,fecini,fecfin,
                idgrupo,idpersona,idestado,nick);
    }
     public int eliminar() {
        String sql = "delete from usuario where nick=?";
        return Bd.getConexion().registrar("usuario", sql, id);
    }
     
      public ArrayList<Musuario> listar() {
        String sql = "select * from usuario";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Musuario> lista = new ArrayList<>();
            while (res.next()) {
                Musuario mcliente = new Musuario();
                mcliente.id = res.getInt("id");
                mcliente.nick = res.getString("nick");
                mcliente.pass = res.getString("pass");
                mcliente.fecini=(Timestamp) res.getObject("fecini");
                mcliente.fecfin=(Timestamp) res.getObject("fecfin");
                mcliente.idgrupo = res.getInt("idgrupo");
                mcliente.idpersona = res.getString("idpersona"); 
                mcliente.idestado=res.getInt("idestado");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Musuario.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }
     /* public ArrayList<Mpersona> buscar() {
        String sql = "select * from persona ";
        int c = 0;
        if (id != -1) {
                sql = sql + " where ci=" + ci; //+" and nombres like '%"+nombres+"'% " +" and sexo='" + sexo + "' and idestado=" + estado + " ";
                c = 1;
            }
            if (!nombres.equals("")) {
                if (c == 0) {
                    sql = sql + " where nombres like '%" + nombres + "%' ";
                    c = 1;
                } else {
                    sql = sql + " and nombres like '%" + nombres + "%' ";
                }
            }
     //   if (QBE) {
            
            if(QBESexo){
                if(c==0){
                    sql=sql+" where sexo='"+sexo+"' ";
                    c=1;
                }else{
                    sql=sql+" and sexo='"+sexo+"' ";
                }
            }
            if(QBEEstado){
                if(c==0){
                    sql=sql+" where idestado="+estado;
                }else{
                    sql=sql+" and idestado="+estado;
                }
            }           
       // }
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mcliente> lista = new ArrayList<>();
            while (res.next()) {
                Mcliente mcliente = new Mcliente();
                mcliente.ci = res.getInt("ci");
                mcliente.nombres = res.getString("nombres");
                mcliente.sexo = res.getString("sexo");
                mcliente.estado = res.getInt("idestado");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mcliente.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }*/
      public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        for (Musuario p : listar()) {
            combo.addElement(p);
        }
        return combo;
    }
     public Musuario ingresar(){
        String sql="select * from usuario where nick='"+nick+"'";
         ResultSet res=Bd.getConexion().listar(sql);
         try {
             ArrayList<Musuario>lista=new ArrayList<>();
             while(res.next()){
                 Musuario musuario=new Musuario();
                 musuario.nick=res.getString("nick");
                 musuario.pass=res.getString("pass");
                 lista.add(musuario);
             }
             return lista.get(0);
         } catch (Exception e) {
             System.err.println("<Musuario.ingresar> "+e.getMessage());
             return null;
         }
        
    }
    @Override
    public String toString() {
        
        return id + " - " + nick + " - " + pass + " - " + fecini
                +" - "+fecfin+" - "+idgrupo+" - "+idpersona
                +" - "+idestado;
    }
}

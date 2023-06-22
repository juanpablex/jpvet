/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ColaVirtual
 */
public class Mpersona {

    private int id;
    private String ci;
    private String nombres;
    private String sexo;
    private int idestado;
    private String direccion;
    private String telefonos;
    private String email;
    private Timestamp fecing;
    private String latitud;
    private String longitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getFecing() {
        return fecing;
    }

    public void setFecing(Timestamp fecing) {
        this.fecing = fecing;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int registrar() {
        String sql = "insert into persona(ci,nombres,sexo,idestado,direccion,"
                + "telefonos,email,fecing,latitud,longitud)"
                + "values(?,?,?,?,?,?,?,?,?,?);";
        return Bd.getConexion().registrar("persona", sql, ci, nombres, sexo,
                idestado, direccion, telefonos, email, fecing, latitud, longitud);
    }

    public int modificar() {
        String sql = "UPDATE persona SET nombres=?, sexo=?, idestado=?,"
                + "direccion=?,telefonos=?,email=?,latitud=?,longitud=? WHERE ci=?";
        return Bd.getConexion().modificar(sql, nombres, sexo, idestado,
                direccion, telefonos, email, latitud, longitud, ci);
    }

    public int eliminar() {
        String sql = "delete from persona where ci=?";
        return Bd.getConexion().registrar("persona", sql, ci);
    }

    public ArrayList<Mpersona> listar() {
        String sql = "select * from persona";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mpersona> lista = new ArrayList<>();
            while (res.next()) {
                Mpersona mcliente = new Mpersona();
                mcliente.ci = res.getString("ci");
                mcliente.nombres = res.getString("nombres");
                mcliente.sexo = res.getString("sexo");
                mcliente.idestado = res.getInt("idestado");
                mcliente.direccion = res.getString("direccion");
                mcliente.telefonos = res.getString("telefonos");
                mcliente.email = res.getString("email");
                mcliente.fecing = (Timestamp) res.getObject("fecing");
                mcliente.latitud = res.getString("latitud");
                mcliente.longitud = res.getString("longitud");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mpersona.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public Mpersona getPersonaXci(String ci) {
        Mpersona g = new Mpersona();
        String sql = "Select ci,nombres from persona WHERE ci= '" + ci + "';";
        try {
            ResultSet rs = Bd.getConexion().getById(sql);
            rs.next();
            g.ci = rs.getString("ci");
            g.nombres = rs.getString("nombres");
            return g;
        } catch (Exception ex) {
            System.err.println("<Mpersona.getPersonaXci> " + ex.getMessage());
            return null;
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
        for (Mpersona p : listar()) {
            combo.addElement(p);
        }
        return combo;
    }

    @Override
    public String toString() {

        if (sexo == null) {
            return ci + " - " + nombres;
        } else {
            return ci + " - " + nombres + " - " + sexo + " - " + idestado
                    + " - " + direccion + " - " + telefonos + " - " + email + " - "
                    + fecing + " - " + latitud + " - " + longitud;
        }
    }

}

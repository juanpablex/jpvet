/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ColaVirtual
 */
public class Mgrupo {
    int id;
    String nombre;
    String descripcion;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void autoCommit(boolean b){
        try {
            Bd.getConexion().setAutoCommit(b);
        } catch (SQLException ex) {
            Logger.getLogger(Mgrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void commit(){
        Bd.getConexion().commit();
    }
    public void rollback(){
        Bd.getConexion().rollback();
    }
    public int registrar(){
        String sql="insert into grupo(nombre,descripcion)values(?,?);";
        int cabecera=-1;
        try {
            cabecera = Bd.getConexion().registrar("grupo", sql, nombre,descripcion);
       } catch (Exception e) {
                Logger.getLogger(Mgrupo.class.getName()).log(Level.SEVERE, null, e);
        }
        return cabecera;
    }   
    public int modificar(){
        String sql="UPDATE grupo set nombre=?, descripcion=? where id=?";
        return Bd.getConexion().modificar(sql, nombre,descripcion,id);
    }
    public int eliminar(){
        String sql="delete from grupo where id=?";
        return Bd.getConexion().registrar("grupo", sql, id);
    }
    public ArrayList<Mgrupo> listar(){
        String sql="select * from grupo";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mgrupo> lista = new ArrayList<>();
            while(res.next()){
                Mgrupo mgrupo=new Mgrupo();
                mgrupo.id=res.getInt("id");
                mgrupo.nombre=res.getString("nombre");
                mgrupo.descripcion=res.getString("descripcion");
                lista.add(mgrupo);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mgrupo.listar> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    public ArrayList<Mgrupo> listar2(){
        String sql="select * from grupo";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mgrupo> lista = new ArrayList<>();
            while(res.next()){
                Mgrupo mgrupo=new Mgrupo();
                mgrupo.id=res.getInt("id");
                mgrupo.nombre=res.getString("nombre");
                lista.add(mgrupo);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mgrupo.listar> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    public DefaultComboBoxModel getCombo(){
        DefaultComboBoxModel combo=new DefaultComboBoxModel();
        for(Mgrupo p: listar2()){
            combo.addElement(p);
        }
        return combo;
    }
    public int posCombo(int idgrupo) {
        int pos = 0;
        for (Mgrupo p : listar2()) {
            if (p.getNombre().equals(nombre)) {
                return pos;
            }
            pos++;
        }
        return pos;
    }
    public Mgrupo getGrupoXid(int id){
         Mgrupo g = new Mgrupo();
        String sql = "Select id,nombre from grupo WHERE id= " + id + ";";
        try {
            ResultSet rs = Bd.getConexion().getById(sql);
            rs.next();
            g.id = rs.getInt("id");
            g.nombre = rs.getString("nombre");
            //g.descripcion = rs.getString("descricion");
            //g.nombreCient = rs.getString("nombreCient");
            return g;
        } catch (Exception ex) {
            System.err.println("<Mgrupo.getgrupoXid> " + ex.getMessage());
            return null;
        }
    }
    @Override
    public String toString(){
        if(descripcion==null)
            return id+" - "+nombre;
        else
            return id+" - "+nombre+" - "+descripcion;
    }
    
}

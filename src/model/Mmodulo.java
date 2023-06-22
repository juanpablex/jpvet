/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ColaVirtual
 */
public class Mmodulo {
    int id;
    String nombre;

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
     public int registrar() {
        String sql = "insert into modulo(id,nombres) values(?,?);";
        return Bd.getConexion().registrar("modulo", sql, id, nombre);
    }

    public int modificar() {
        String sql = "UPDATE modulo SET nombre=? WHERE id=?";
        return Bd.getConexion().modificar(sql, nombre,id);
    }
     public int eliminar() {
        String sql = "delete from modulo where ci=?";
        return Bd.getConexion().registrar("modulo", sql, id);
    }
      public ArrayList<Mmodulo> listar() {
        String sql = "select * from modulo";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mmodulo> lista = new ArrayList<>();
            while (res.next()) {
                Mmodulo mmodulo = new Mmodulo();
                mmodulo.id = res.getInt("id");
                mmodulo.nombre = res.getString("nombre");
                lista.add(mmodulo);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmodulo.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }
       @Override
    public String toString() {
            return id + " - " + nombre;
    }
}

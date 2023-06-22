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
public class Manimal {

    private int id;
    private String nombre;
    private String descripcion;
    private String nombreCient;

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

    public String getNombreCient() {
        return nombreCient;
    }

    public void setNombreCient(String nombreCient) {
        this.nombreCient = nombreCient;
    }

    public ArrayList<Manimal> listar2() {
        String sql = "select id,nombre from animal";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Manimal> lista = new ArrayList<>();
            while (res.next()) {
                Manimal mcliente = new Manimal();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Manimal.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Manimal> listar() {
        String sql = "select * from animal";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Manimal> lista = new ArrayList<>();
            while (res.next()) {
                Manimal mcliente = new Manimal();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                mcliente.descripcion = res.getString("descripcion");
                mcliente.nombreCient = res.getString("nombreCient");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Manimal.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        for (Manimal p : listar2()) {
            combo.addElement(p);
        }
        return combo;
    }

    public Manimal getAnimalXid(int id) {
        Manimal g = new Manimal();
        String sql = "Select id,nombre from animal WHERE id= " + id + ";";
        try {
            ResultSet rs = Bd.getConexion().getById(sql);
            rs.next();
            g.id = rs.getInt("id");
            g.nombre = rs.getString("nombre");
            //g.descripcion = rs.getString("descricion");
            //g.nombreCient = rs.getString("nombreCient");
            return g;
        } catch (Exception ex) {
            System.err.println("<Manimal.getanimalXid> " + ex.getMessage());
            return null;
        }
    }

    public int posCombo() {
        int pos = 0;
        for (Manimal p : listar()) {
            if (p.getNombre().equals(nombre)) {
                return pos;
            }
            pos++;
        }
        return pos;
    }

    @Override
    public String toString() {

        return id + " - " + nombre;
    }
}

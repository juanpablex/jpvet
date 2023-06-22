/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author hp
 */
public class Mservicio {

    private int id;
    private String nombre;

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

    public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        for (Mservicio p : listar()) {
            combo.addElement(p);
        }
        return combo;
    }

    public ArrayList<Mservicio> listar() {
        String sql = "select id,nombre from servicios";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mservicio> lista = new ArrayList<>();
            while (res.next()) {
                Mservicio mcliente = new Mservicio();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mservicio.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}

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
public class Mraza {

    private int id;
    private String nombre;
    private String descripcion;
    private String tamanio;
    private String pelo;
    private int peso;
    private int idAnimal;
    private String tejido;
    private int idimagen;

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

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getPelo() {
        return pelo;
    }

    public void setPelo(String pelo) {
        this.pelo = pelo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTejido() {
        return tejido;
    }

    public void setTejido(String tejido) {
        this.tejido = tejido;
    }

    public int getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(int idimagen) {
        this.idimagen = idimagen;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public DefaultComboBoxModel getCombo(int idanimal) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        for (Mraza p : listar2(idanimal)) {
            combo.addElement(p);
        }
        return combo;
    }

    public ArrayList<Mraza> listar2(int id) {
        String sql = "select id,nombre from raza where idanimal=" + id;
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mraza> lista = new ArrayList<>();
            while (res.next()) {
                Mraza mcliente = new Mraza();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");

                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mraza.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Mraza> listar() {
        String sql = "select * from raza";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mraza> lista = new ArrayList<>();
            while (res.next()) {
                Mraza mcliente = new Mraza();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                mcliente.descripcion = res.getString("descripcion");
                mcliente.tamanio = res.getString("tamano");
                mcliente.pelo = res.getString("pelo");
                mcliente.peso = res.getInt("peso");
                mcliente.tejido = res.getString("tejido");
                mcliente.idimagen = res.getInt("idimagen");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mraza.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public Mraza getRazaXid(int id) {
        Mraza g = new Mraza();
        String sql = "Select * from raza WHERE id= " + id + ";";
        try {
            ResultSet rs = Bd.getConexion().getById(sql);
            rs.next();
            g.id = rs.getInt("id");
            g.nombre = rs.getString("nombre");
            g.tamanio = rs.getString("tamano");
            g.pelo = rs.getString("pelo");
            g.peso = rs.getInt("peso");
            g.tejido = rs.getString("tejido");
            //g.descripcion = rs.getString("descricion");
            //g.idimagen = rs.getInt("idimagen");
            return g;
        } catch (Exception ex) {
            System.err.println("<Mraza.getRazaXid> " + ex.getMessage());
            return null;
        }
    }

    public int registrar() {
        String sql = "insert into raza(nombre,descripcion,tamano,pelo,peso,tejido,idimagen,idanimal)"
                + "values(?,?,?,?,?,?,?,?)";
        return Bd.getConexion().registrar("raza", sql, nombre, descripcion,
                tamanio, pelo, peso, tejido,1, idAnimal);
    }

    public int posCombo(int idanimal) {
        int pos = 0;
        for (Mraza p : listar2(idanimal)) {
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

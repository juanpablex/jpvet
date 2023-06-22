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
public class Mdetservicio {

    private int id;
    private int idservicio;
    private int idanimal;
    private String tamanio;
    private String genero;
    private String pelo;
    private double peso;
    private String tejido;
    private double precio;
    private String servicio;
    private String animal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPelo() {
        return pelo;
    }

    public void setPelo(String pelo) {
        this.pelo = pelo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTejido() {
        return tejido;
    }

    public void setTejido(String tejido) {
        this.tejido = tejido;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public double obtenerPrecio(String tamanio, String genero, String pelo,
            String tejido, double peso, int idanimal, int idservicio) {
        String sql = "";
        double precio = 0;
        switch (idservicio) {
            case 1:
                sql = "select precio from detservicio where "
                        + "idservicio=" + idservicio
                        + " and idanimal=" + idanimal
                        + " and tamanio='" + tamanio
                        + "' and genero='" + genero
                        + "' and pelo='" + pelo
                        + "' and tejido='" + tejido
                        + "' and peso=" + peso;
                break;
            case 2:
                sql = "select precio from detservicio where "
                        + "idservicio=" + idservicio
                        + " and tamanio='" + tamanio + "'";
                break;
            case 3:
                sql = "select precio from detservicio where "
                        + "idservicio=" + idservicio
                        + " and tejido='" + tejido + "'";
                break;
            case 4:
                sql = "select precio from detservicio where "
                        + "idservicio=" + idservicio
                        + " and idanimal=" + idanimal
                        + " and tamanio='" + tamanio
                        + "' and genero='" + genero
                        + "' and peso=" + peso;
                break;
            case 5:
                sql = "select precio from detservicio where "
                        + "idservicio=" + idservicio
                        + " and tamanio='" + tamanio + "'";
                break;
        }
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            //ArrayList<Mdetservicio> lista = new ArrayList<>();
            while (res.next()) {
                //Mdetservicio mdet = new Mdetservicio();
                precio = res.getDouble("precio");

                //lista.add(mdet);
            }

            return precio;
        } catch (Exception e) {
            System.err.println("<Mdetservicio.listar> " + e.getMessage());
            return -1;
        }

    }

    public ArrayList<Mdetservicio> listar() {
        String sql = "select a.nombre as nombanimal,s.nombre as nombserv,d.* from detservicio d,servicios s,animal a "
                + "where d.idservicio=s.id and d.idanimal=a.id";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mdetservicio> lista = new ArrayList<>();
            while (res.next()) {
                Mdetservicio mcliente = new Mdetservicio();
                mcliente.id = res.getInt("id");
                mcliente.servicio = res.getInt("idservicio") + "-" + res.getString("nombserv");
                mcliente.animal = res.getInt("idanimal") + "-" + res.getString("nombanimal");
                mcliente.tamanio = res.getString("tamanio");
                mcliente.genero = res.getString("genero");
                mcliente.pelo = res.getString("pelo");
                mcliente.peso = res.getDouble("peso");
                mcliente.tejido = res.getString("tejido");
                mcliente.precio = res.getDouble("precio");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mdetservicio.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public int registrar() {
        String sql = "insert into detservicio(tamanio,genero,pelo"
                + ",tejido,peso,precio,idservicio,idanimal) values("
                + "?,?,?,?,?,?,?,?)";
        return Bd.getConexion().registrar("detservicio", sql, tamanio,
                genero, pelo, tejido, peso, precio, idservicio, idanimal);
    }

}

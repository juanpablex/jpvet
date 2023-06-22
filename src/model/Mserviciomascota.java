/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author hp
 */
public class Mserviciomascota {
    private int id;
    private int idmascota;
    private String cipersona;
    private int idservicio;
    private String glosa;
    private Timestamp fecha;
    private int idestado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public String getCipersona() {
        return cipersona;
    }

    public void setCipersona(String cipersona) {
        this.cipersona = cipersona;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }
    
    public int registrar() {
        String sql = "insert into serviciomascota(idmascota,idservicio,glosa,fecha,idestado)"
                + "values(?,?,?,?,?);";
        return Bd.getConexion().registrar("serviciomascota", sql, idmascota, idservicio, glosa,
                fecha, idestado);
    }

}

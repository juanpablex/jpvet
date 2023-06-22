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
public class Mmascota {

    private int id;
    private String nombre;
    private String descripcion;
    private String genero;
    private Timestamp fechanac;
    private Timestamp fechareg;
    private String idpersona;
    private int idestado;
    private int idanimal;
    private int idraza;
    private int idimagen;
    private String duenio;
    private String animal;
    private String raza;
    private int edad;
    private double peso;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Timestamp getFechanac() {
        return fechanac;
    }

    public void setFechanac(Timestamp fechanac) {
        this.fechanac = fechanac;
    }

    public Timestamp getFechareg() {
        return fechareg;
    }

    public void setFechareg(Timestamp fechareg) {
        this.fechareg = fechareg;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public int getIdraza() {
        return idraza;
    }

    public void setIdraza(int idraza) {
        this.idraza = idraza;
    }

    public int getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(int idimagen) {
        this.idimagen = idimagen;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int registrar() {
        String sql = "insert into mascota(nombre,descripcion,genero,fechanac,"
                + "fechareg,idpersona,idestado,idanimal,idraza,idimagen)"
                + "values(?,?,?,?,?,?,?,?,?,?);";
        return Bd.getConexion().registrar("mascota", sql, nombre, descripcion, genero,
                fechanac, fechareg, idpersona, idestado, idanimal, idraza, idimagen);
    }

    public int modificar() {
        String sql = "UPDATE mascota SET nombre=?, descripcion=?, genero=?,"
                + "fechanac=?,fechareg=?,idpersona=?,idestado=?,idanimal=?,idraza=? WHERE id=?";
        return Bd.getConexion().modificar(sql, nombre, descripcion, genero, fechanac,
                fechareg, idpersona, idestado, idanimal, idraza, id);
    }

    public int eliminar() {
        String sql = "delete from mascota where id=?";
        return Bd.getConexion().registrar("mascota", sql, id);
    }

    public ArrayList<Mmascota> listar() {
        String sql = "select * from mascota";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mmascota> lista = new ArrayList<>();
            while (res.next()) {
                Mmascota mcliente = new Mmascota();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                mcliente.descripcion = res.getString("descripcion");
                mcliente.genero = res.getString("genero");
                mcliente.fechanac = (Timestamp) res.getObject("fechanac");
                mcliente.fechareg = (Timestamp) res.getObject("fechareg");
                mcliente.idpersona = res.getString("idpersona");
                mcliente.idestado = res.getInt("idestado");
                mcliente.idanimal = res.getInt("idanimal");
                mcliente.idraza = res.getInt("idraza");
                mcliente.idimagen = res.getInt("idimagen");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmascota.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Mmascota> listarAtencion() {
        String sql = "select m.id as idmascota,concat_ws('-',p.ci,p.nombres) as dueño,\n"
                + "m.nombre as mascnomb,m.genero,m.fechanac,timestampdiff(year,m.fechanac,CURDATE()) AS edad,\n"
                + "concat_ws('-',a.id ,a.nombre) as animal,concat_ws('-',r.id ,r.nombre) as raza,r.peso\n"
                + "from mascota m,raza r,animal a,persona p\n"
                + "where m.idraza=r.id and r.idanimal=a.id and m.idpersona=p.ci";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mmascota> lista = new ArrayList<>();
            while (res.next()) {
                Mmascota mcliente = new Mmascota();
                mcliente.id = res.getInt("idmascota");
                mcliente.duenio = res.getString("dueño");
                mcliente.nombre = res.getString("mascnomb");
                mcliente.genero = res.getString("genero");
                mcliente.fechanac = (Timestamp) res.getObject("fechanac");
                mcliente.edad = res.getInt("edad");
                mcliente.animal = res.getString("animal");
                mcliente.raza = res.getString("raza");
                mcliente.peso = res.getDouble("peso");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmascota.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Mmascota> getMascotaXid(int id) {
        Mmascota g = new Mmascota();
        String sql = "Select * from mascota WHERE id= " + id + ";";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mmascota> lista = new ArrayList<>();
            while (res.next()) {
                Mmascota mcliente = new Mmascota();
                mcliente.id = res.getInt("id");
                mcliente.nombre = res.getString("nombre");
                mcliente.descripcion = res.getString("descripcion");
                mcliente.genero = res.getString("genero");
                mcliente.fechanac = (Timestamp) res.getObject("fechanac");
                mcliente.fechareg = (Timestamp) res.getObject("fechareg");
                mcliente.idpersona = res.getString("idpersona");
                mcliente.idestado = res.getInt("idestado");
                mcliente.idanimal = res.getInt("idanimal");
                mcliente.idraza = res.getInt("idraza");
                mcliente.idimagen = res.getInt("idimagen");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmascota.getMascotaXid> " + ex.getMessage());
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
        for (Mmascota p : listar()) {
            combo.addElement(p);
        }
        return combo;
    }

    @Override
    public String toString() {

        return id + " - " + nombre + " - " + descripcion + " - " + genero
                + " - " + fechanac + " - " + fechareg + " - " + idpersona
                + " - " + idestado + " - " + idanimal + " - " + idraza + " - " + idimagen;
    }
}

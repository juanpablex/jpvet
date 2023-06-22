/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ColaVirtual
 */
public class Mproducto {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int idtipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }
    
    public int registrar() {
        String sql = "insert into producto(codigo,nombre,descripcion,precio,idtipo)"
                + "values(?,?,?,?,?);";
        return Bd.getConexion().registrar("producto", sql, codigo, nombre,  
                descripcion,precio,idtipo);
    }

    public int modificar() {
        String sql = "UPDATE producto SET nombre=?,"
                + "descripcion=?,precio=?,idtipo=? WHERE codigo=?";
        return Bd.getConexion().modificar(sql, nombre, descripcion,
                precio,idtipo,codigo);
    }
     public int eliminar() {
        String sql = "delete from producto where codigo=?";
        return Bd.getConexion().registrar("producto", sql, codigo);
    }
    
    public ArrayList<Mproducto>listar2(int id){
        String sql="select id,nombre from producto where idtipo="+id;
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mproducto>lista=new ArrayList<>();
            while(res.next()){
                Mproducto mproducto=new Mproducto();
                mproducto.codigo=res.getString("codigo");
                mproducto.nombre=res.getString("nombre");
                lista.add(mproducto);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mproducto.listar2>"+e.getMessage());
            return new ArrayList<>();
        }
    }
    public ArrayList<Mproducto>listar(){
        String sql="select * from producto";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mproducto>lista=new ArrayList<>();
            while(res.next()){
                Mproducto mproducto=new Mproducto();
                mproducto.id=res.getInt("id");
                mproducto.codigo=res.getString("codigo");
                mproducto.nombre=res.getString("nombre");
                mproducto.descripcion=res.getString("descripcion");
                mproducto.precio=res.getDouble("precio");
                mproducto.idtipo=res.getInt("idtipo");
                lista.add(mproducto);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mproducto.listar2>"+e.getMessage());
            return new ArrayList<>();
        }
    }
    public Mproducto getProductoXcod(String cod){
        Mproducto p=new Mproducto();
        String sql="select cod,nombre from producto where codigo='"+cod+"'";
        try {
            ResultSet rs=Bd.getConexion().getById(sql);
            rs.next();
            p.codigo=rs.getString("codigo");
            p.nombre=rs.getString("nombre");
            return p;
        } catch (Exception e) {
            System.err.println("<Mproducto.getProductoXcod>"+e.getMessage());
            return null;
        }
    }
     public int cantProductoXtipo(int id){
        String sql="select count(*) from producto where idtipo="+id;
        ResultSet res=Bd.getConexion().listar(sql);
        int cant=0;
        try {
            while(res.next()){
                cant++;
            }
            return cant;
        } catch (Exception e) {
            System.err.println("<Mproducto.cantProductoXtipo>"+e.getMessage());
            return 0;
        }
    }
     public int posCombo(int idtipo) {
        int pos = 0;
        for (Mproducto p : listar2(idtipo)) {
            if (p.getNombre().equals(nombre)) {
                return pos;
            }
            pos++;
        }
        return pos;
    }
      public String toString() {
        
        if(descripcion==null)
            return codigo + " - " + nombre;
        else
            return id + " - " + codigo + " - " + nombre + " - " + descripcion
                +" - "+precio+" - "+idtipo;
    }
}

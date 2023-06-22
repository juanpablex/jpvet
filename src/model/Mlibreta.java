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
public class Mlibreta {
    int id;
    int idmascota;
    Timestamp fechareg;
    int idestado;
    double total;

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

    public Timestamp getFechareg() {
        return fechareg;
    }

    public void setFechareg(Timestamp fechareg) {
        this.fechareg = fechareg;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public int registrar(){
        String sql="insert into libreta(id,idmascota,fechareg,idestado,total)";
        try {
            return Bd.getConexion().registrar("libreat", sql, idmascota,fechareg,idestado,total);
        } catch (Exception e) {
            return -1;
        }
    }
    public int modificar(){
        String sql="update libreta set total=? where idmascota=?";
        return Bd.getConexion().modificar(sql, total,idmascota);
    }
    public int eliminar(){
        String sql="delete from libreta where idmascota=?";
        return Bd.getConexion().eliminar(sql, idmascota);
    }
    public ArrayList<Mlibreta>listar(){
        String sql="select * from libreta";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mlibreta>lista=new ArrayList<>();
            while(res.next()){
                Mlibreta mlibreta=new Mlibreta();
                mlibreta.id=res.getInt("id");
                mlibreta.idmascota=res.getInt("idmascota");
                mlibreta.fechareg=(Timestamp)res.getObject("fechareg");
                mlibreta.idestado=res.getInt("idestado");
                mlibreta.total=res.getDouble("total");
                lista.add(mlibreta); 
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mlibreta.listar> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    public ArrayList<String[]>listarXparametros(int idlibreta,String nombremascota,String nombrepersona
            ,String ci){
        String sql="select m.id as idmascota,m.nombre as mascota,a.nombre as animal,r.nombre as raza\n" +
"	,m.fechanac as nac,l.fechareg as reg,e.descripcion\n" +
"	,l.id as libreta,l.total as total,p.ci as ci,p.nombres as persona\n" +
"from mascota m,animal a,raza r,libreta l,persona p,estado e\n" +
"where m.id=l.idmascota and m.idraza=r.id and m.idanimal=a.id\n" +
"  		and  m.idpersona=p.ci and e.id=m.idestado and\n" +
"       (l.id="+idlibreta+" or m.nombre like '%"+nombremascota+"%' "
                + "or e.nombres like '%"+nombrepersona+"%' or e.ci='"+ci+"')";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<String[]>lista=new ArrayList<>();
            while(res.next()){
                String[]l=new String[11];
                l[0]=String.valueOf(res.getInt("m.id"));
                l[1]=res.getString("m.nombre");
                l[2]=res.getString("a.nombre");
                l[3]=res.getString("r.nombre");
                l[4]=String.valueOf((Timestamp)res.getObject("m.fechanac"));
                l[5]=String.valueOf((Timestamp)res.getObject("m.fechareg"));
                l[6]=res.getString("e.descripcion");
                l[7]=String.valueOf(res.getInt("l.id"));
                l[8]=String.valueOf(res.getDouble("l.total"));
                l[9]=res.getString("p.ci");
                l[10]=res.getString("p.nombres");
                /*
                l[]=String.valueOf(res.getInt(""));
                l[]=res.getDouble("");
                l[]=res.getString("");
                l[]=String.valueOf((Timestamp)res.getObject(""));*/
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mlibreta.listarXparametros> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Override
    public String toString(){
        return id+" - "+idmascota+" - "+fechareg+" - "+idestado+" - "+total;
    }
}

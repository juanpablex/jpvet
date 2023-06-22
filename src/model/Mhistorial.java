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
public class Mhistorial {
    int idlibreta;
    String idvacuna;
    int idestado;
    Timestamp fvacunacion;
    Timestamp fvacunado;
    double precio;
    String pagado;

    public int getIdlibreta() {
        return idlibreta;
    }

    public void setIdlibreta(int idlibreta) {
        this.idlibreta = idlibreta;
    }

    public String getIdvacuna() {
        return idvacuna;
    }

    public void setIdvacuna(String idvacuna) {
        this.idvacuna = idvacuna;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public Timestamp getFvacunacion() {
        return fvacunacion;
    }

    public void setFvacunacion(Timestamp fvacunacion) {
        this.fvacunacion = fvacunacion;
    }

    public Timestamp getFvacunado() {
        return fvacunado;
    }

    public void setFvacunado(Timestamp fvacunado) {
        this.fvacunado = fvacunado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }
    public int registrar(){
        String sql="insert into historial(idlibreta,idvacuna,idestado,"
                + "fvacunacion,fvacunado,precio,pagado)";
        try {
            return Bd.getConexion().registrar("historial", sql, idlibreta,
                    idvacuna,idestado,fvacunacion,fvacunado,precio,pagado);
        } catch (Exception e) {
            return -1;
        }
    }
    public int modificar(){
        String sql="update historial set idestado=?,fvacunado=?,precio=?,"
                + "pagado=? where idlibreta=?";
        return Bd.getConexion().modificar(sql, idestado,fvacunado,precio,pagado,idlibreta);
    }
    public int eliminar(){
        String sql="delete from historial where idlibreta=?";
        return Bd.getConexion().eliminar(sql, idlibreta);
    }
    public ArrayList<Mhistorial>listar(){
        String sql="select * from historial";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<Mhistorial>lista=new ArrayList<>();
            while(res.next()){
                Mhistorial mhistorial=new Mhistorial();
                mhistorial.idlibreta=res.getInt("idlibreta");
                mhistorial.idvacuna=res.getString("idvacuna");
                mhistorial.idestado=res.getInt("idestado");
                mhistorial.fvacunacion=(Timestamp)res.getObject("fvacunacion");
                mhistorial.fvacunado=(Timestamp)res.getObject("fvacunado");
                mhistorial.precio=res.getDouble("precio");
                mhistorial.pagado=res.getString("pagado");
                lista.add(mhistorial);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mhistorial.listar> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    public ArrayList<String[]>listarXparametro(int idlibreta,String nombremascota,String nombrepersona
    ,String ci){
        if(nombremascota.equals(""))
            nombremascota="-1";
        if(nombrepersona.equals(""))
            nombrepersona="-1";
        String sql="select m.id as idmascota,m.nombre as mascota,a.nombre as animal,r.nombre as raza\n" +
"	,l.id as libreta,l.total as total,e.ci as ci,e.nombres as persona\n" +
"	,p.nombre as vacuna, h.fvacunacion as vacunar\n" +
"    ,h.fvacunado as vacunado,h.pagado as pagado\n" +
"    , h.precio as precio\n" +
"from mascota m,animal a,raza r,libreta l,historial h, producto p,persona e\n" +
"where m.id=l.idmascota and m.idraza=r.id and m.idanimal=a.id\n" +
"  		and l.id=h.idlibreta and h.idvacuna=p.id and  m.idpersona=e.ci and\n" +
"        (l.id="+idlibreta+" or m.nombre like '%"+nombremascota+"%' "
                + "or e.nombres like '%"+nombrepersona+"%' or e.ci='"+ci+"')";
        ResultSet res=Bd.getConexion().listar(sql);
        try {
            ArrayList<String[]>lista=new ArrayList<>();
            while(res.next()){
                String[]l=new String[13];
                l[0]=String.valueOf(res.getInt("m.id"));
                l[1]=res.getString("m.nombre");
                l[2]=res.getString("a.nombre");
                l[3]=res.getString("r.nombre");
                l[4]=String.valueOf(res.getInt("l.id"));
                l[5]=String.valueOf(res.getDouble("l.total"));
                l[6]=res.getString("e.ci");
                l[7]=res.getString("e.nombres");
                l[8]=res.getString("p.nombre");
                l[9]=String.valueOf((Timestamp)res.getObject("h.fvacunacion"));
                l[10]=String.valueOf((Timestamp)res.getObject("h.fvacunado"));
                l[11]=res.getString("h.pagado");
                l[12]=String.valueOf(res.getDouble("h.precio"));
                lista.add(l);
                /*
                l[]=String.valueOf(res.getInt(""));
                l[]=res.getDouble("");
                l[]=res.getString("");
                l[]=String.valueOf((Timestamp)res.getObject(""));*/
            }
            return lista;
        } catch (Exception e) {
            System.err.println("<Mhitorial.listarXparametro> "+e.getMessage());
            return new ArrayList<>();
        }
    }
    @Override
    public String toString(){
        return  idlibreta+" - " +idvacuna+" - " + idestado
                +" - " +fvacunacion+" - " + fvacunado
                +" - " +precio+" - " + pagado;
    }
}

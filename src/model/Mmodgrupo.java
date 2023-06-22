/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ColaVirtual
 */
public class Mmodgrupo {
    int idmodulo;
    int idgrupo;
    List<Mmodgrupo>Lprivilegios=new LinkedList<>();
    Timestamp fecha;

    public int getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(int idmodulo) {
        this.idmodulo = idmodulo;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
     public int registrar() {
        String sql = "insert into modgrupo(idgrupo,idmodulo,fecha) values(?,?,?);";
         try {
             return Bd.getConexion().registrar("modgrupo", sql, idgrupo, idmodulo,fecha);
         } catch (Exception e) {
             return -1;
         }
        
    }
    public List<Mmodgrupo> getLprivilegios() {
        return Lprivilegios;
    }
     public void setLista(List<Mmodgrupo> l){
        this.Lprivilegios=l;
    }
     public void resetLprivilegios(){
         this.Lprivilegios=new LinkedList<>();
     }
    public void setLprivilegios(Mmodgrupo modgrupo) {
        this.Lprivilegios.add(modgrupo);
    }
    public void removeLprivilegios(int index){
        this.Lprivilegios.remove(index);
    }
    public int modificar() {
        String sql = "UPDATE modgrupo SET nombre=? WHERE idgrupo=? and idmodulo=?";
        return Bd.getConexion().modificar(sql, fecha,idgrupo,idmodulo);
    }
     public int eliminar() {
        String sql = "delete from modgrupo where idgrupo=? and idmodulo=?";
        return Bd.getConexion().registrar("modgrupo", sql, idgrupo,idmodulo);
    }
      public ArrayList<Mmodgrupo> listar() {
        String sql = "select * from modgrupo";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mmodgrupo> lista = new ArrayList<>();
            while (res.next()) {
                Mmodgrupo mmodgrupo = new Mmodgrupo();
                mmodgrupo.idgrupo = res.getInt("idgrupo");
                mmodgrupo.idmodulo = res.getInt("idmodulo");
                mmodgrupo.fecha=(Timestamp) res.getObject("fecha");
                lista.add(mmodgrupo);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmodgrupo.listar> " + ex.getMessage());
            return new ArrayList<>();
        }
    }
      public ArrayList<String[]> listarXusuario(String usuario) {
        String sql = "select u.nick,m.idgrupo,g.nombre,m.idmodulo,o.nombre,p.sexo \n" +
"from modgrupo m,usuario u,grupo g,modulo o,persona p\n" +
"                where u.idgrupo=g.id and g.id=m.idgrupo and p.ci=u.idpersona\n"
                + "and o.id=m.idmodulo and u.nick='"+usuario+"'";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<String[]> lista = new ArrayList<>();
            while (res.next()) {
                String[]l=new String[6];
               l[0]=res.getString("u.nick");
               l[1]=res.getString("m.idgrupo");
               l[2]=res.getString("g.nombre");
               l[3]=res.getString("m.idmodulo");
               l[4]=res.getString("o.nombre");
               l[5]=res.getString("p.sexo");
               lista.add(l);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mmodgrupo.listarXusuario> " + ex.getMessage());
            return new ArrayList<>();
        }
    }
       @Override
    public String toString() {
            return idgrupo + " - " + idmodulo+" - "+fecha;
    }
}

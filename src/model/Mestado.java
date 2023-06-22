/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import patrones.estado.Pestado;
import patrones.estado.PestadoInactivo;

/**
 *
 * @author ColaVirtual
 */
public class Mestado {

    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int registrar() {
        String sql = "insert into estado(id,descripcion)";
        return Bd.getConexion().registrar("estado", sql, id, descripcion);
    }

    public int modificar() {
        String sql = "UPDATE estado SET descripcion=? WHERE id=?";
        return Bd.getConexion().modificar(sql, descripcion);
    }

    public int eliminar() {
        String sql = "delete from estado where id=?";
        return Bd.getConexion().registrar("estado", sql, id);
    }

    public ArrayList<Mestado> listar() {
        String sql = "select * from estado";
        ResultSet res = Bd.getConexion().listar(sql);
        try {
            ArrayList<Mestado> lista = new ArrayList<>();
            while (res.next()) {
                Mestado mcliente = new Mestado();
                mcliente.id = res.getInt("id");
                mcliente.descripcion = res.getString("descripcion");
                lista.add(mcliente);
            }
            return lista;
        } catch (Exception ex) {
            System.err.println("<Mestado.listar> " + ex.getMessage());
            return new ArrayList<>();
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

    public DefaultComboBoxModel getCombo(char t) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();

        for (Mestado p : listar()) {
            if (esEstado(p, t)) {
                combo.addElement(p);
            }
        }
        return combo;
    }

    public boolean esEstado(Mestado e, char t) {
        switch (t) {
            case 'p':
                if (e.descripcion.equals("ACTIVO")
                        || e.descripcion.equals("INACTIVO")
                        || e.descripcion.equals("DEUDOR")) {
                    return true;
                }
                break;
            case 'm':
                if (e.descripcion.equals("HABIL")
                        || e.descripcion.equals("TRATAMIENTO")
                        || e.descripcion.equals("FALLECIDO")) {
                    return true;
                }
                break;
            case 'h':
                if (e.descripcion.equals("PENDIENTE")
                        || e.descripcion.equals("VACUNADO")) {
                    return true;
                }
                break;
                case 'a':
                if (e.descripcion.equals("ACTIVO")
                        ||e.descripcion.equals("INACTIVO")
                        || e.descripcion.equals("DEUDOR")) {
                    return true;
                }
                break;
                case 'i':
                if (e.descripcion.equals("INACTIVO")
                        ||e.descripcion.equals("ACTIVO")) {
                    return true;
                }
                break;
                case 'd':
                if (e.descripcion.equals("DEUDOR")
                        ||e.descripcion.equals("ACTIVO")) {
                    return true;
                }
                break;
                case 'b':
                if (e.descripcion.equals("HABIL")
                        ||e.descripcion.equals("TRATAMIENTO")||
                        e.descripcion.equals("FALLECIDO")) {
                    return true;
                }
                break;
                case 't':
                if (e.descripcion.equals("TRATAMIENTO")
                        ||e.descripcion.equals("HABIL")
                        ||e.descripcion.equals("FALLECIDO")) {
                    return true;
                }
                break;
                case 'f':
                if(e.descripcion.equals("FALLECIDO"))
                    return true;
                break;
        }
        return false;
    }

    public int posCombo() {
        int pos = 0;
        for (Mestado p : listar()) {
            if (p.getDescripcion().equals(descripcion)) {
                return pos;
            }
            pos++;
        }
        return pos;
    }
public int posCombo2(ComboBoxModel cbm) {
        int pos = 0;
        for(int i=0;i<cbm.getSize();i++){
            Mestado e = (Mestado)cbm.getElementAt(i);
            if(e.getDescripcion().equals(descripcion))
                return pos;
            else
                pos++;
        }
        
        return pos;
    }
    public Mestado getEstadoXid(int id) {
        Mestado g = new Mestado();
        String sql = "Select * from estado WHERE id= " + id + ";";
        try {
            ResultSet rs = Bd.getConexion().getById(sql);
            rs.next();
            g.id = rs.getInt("id");
            g.descripcion = rs.getString("descripcion");
            return g;
        } catch (Exception ex) {
            System.err.println("<Mestado.getEstadoXid> " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {

        return id + " - " + descripcion;
    }
}

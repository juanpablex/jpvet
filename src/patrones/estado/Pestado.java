/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patrones.estado;

/**
 *
 * @author ColaVirtual
 */
public class Pestado {
    private PnombreEstado n;
    public Pestado(char t){
        switch(t){
            case 'p':
                 setn(new PestadoActivo());
                break;
            case 'm':
                 setn(new PestadoHabil());
                break;
            case 'h':
                
                break;
        }
       
    }
    public void setn(PnombreEstado n){
        this.n=n;
    }
    public void mostrarTipos(char tipo){
        this.n.mostrarTipos(this, tipo);
    }
}

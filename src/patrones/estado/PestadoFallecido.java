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
public class PestadoFallecido implements PnombreEstado{
    private char t;
    public PestadoFallecido(){
        t='f';
    }
    public void setT(char t){
        this.t=t;
    }
    @Override
    public void mostrarTipos(Pestado e, char tipo) {
       /* switch(t){
            case 'i':*/
              //  e.setn(new PestadoInactivo());
             /*   break;
            case 'd':
                e.setn(new PestadoDeudor());
                break;
        }*/
        
    }
    
}

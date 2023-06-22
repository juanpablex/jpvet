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
public class PestadoDeudor implements PnombreEstado{

public PestadoDeudor(){
    
}
    @Override
    public void mostrarTipos(Pestado e, char tipo) {
        e.setn(new PestadoActivo());
    }
    
}

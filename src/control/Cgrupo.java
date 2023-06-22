/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Bd;
import model.Mgrupo;
import model.Mmodgrupo;
import model.Mmodulo;
import vista.Vgrupo;

/**
 *
 * @author ColaVirtual
 */
public class Cgrupo implements ActionListener {

    Mgrupo mgrupo;
    Mmodulo mmodulo;
    Mmodgrupo mmodgrupo;
    Vgrupo vgrupo;

    public Cgrupo() {
        this.mgrupo = new Mgrupo();
        this.vgrupo = new Vgrupo(mgrupo);
        this.mmodulo = new Mmodulo();
        this.mmodgrupo = new Mmodgrupo();
        setEvent();
        this.vgrupo.actualizar();
        this.vgrupo.setVisible(true);
    }

    public void setEvent() {
        this.vgrupo.btRegistrar.setActionCommand("REGISTRAR");
        this.vgrupo.btRegistrar.addActionListener(this);
        this.vgrupo.btModificar.setActionCommand("MODIFICAR");
        this.vgrupo.btModificar.addActionListener(this);
        this.vgrupo.btBuscar.setActionCommand("BUSCAR");
        this.vgrupo.btBuscar.addActionListener(this);
        this.vgrupo.btEliminar.setActionCommand("ELIMINAR");
        this.vgrupo.btEliminar.addActionListener(this);

        this.vgrupo.jTable1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarFila(vgrupo.jTable1.getSelectedRow());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.vgrupo.jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                clickEnGrilla(vgrupo.jTable2.getSelectedRow());
            }
        });
        this.vgrupo.jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath[]paths=e.getPaths();
                for(int i=0;i<paths.length;i++){
                    if(e.isAddedPath(i))
                         setPrivilegio(e);
                    
                        
                }
               
            }
        });

    }

    private void setPrivilegio(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode
                = (DefaultMutableTreeNode) this.vgrupo.jTree1.getLastSelectedPathComponent();
        Object[] texto = new Object[1];
        if (selectedNode.getUserObject() != null) {
            texto[0] = selectedNode.getUserObject().toString();
            vgrupo.setPrivilegio(texto);
        }
    }

    private void clickEnGrilla(Integer fila) {
        //aqui usar el patron estado
        this.vgrupo.getData(fila);
        this.vgrupo.btEliminar.setEnabled(true);
        this.vgrupo.btModificar.setEnabled(true);
        this.vgrupo.btRegistrar.setText("nuevo");
    }

    private void borrarFila(Integer fila) {
        this.vgrupo.borrarFila(fila);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cgrupo c = new Cgrupo();
    }

    public enum AccionMVC {
        REGISTRAR, MODIFICAR, BUSCAR, ELIMINAR,
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case REGISTRAR:
                if (vgrupo.txtNombre.getText().equals("")
                        || vgrupo.txtDesc.getText().equals("")) {
                    System.out.println("No se insertó, debe llenar los campos");
                    break;
                }
                if (vgrupo.btRegistrar.getText().equals("nuevo")) {
                    vgrupo.btRegistrar.setText("registrar");
                    vgrupo.btModificar.setEnabled(false);
                    vgrupo.btEliminar.setEnabled(false);
                    vgrupo.actualizar();
                    break;
                }
                this.mgrupo.setNombre(this.vgrupo.txtNombre.getText());
                this.mgrupo.setDescripcion(this.vgrupo.txtDesc.getText());
                this.mmodgrupo.setLista(this.vgrupo.getPrivilegios());
                try {
                    mgrupo.autoCommit(false);
                    int cabecera = this.mgrupo.registrar();
                    int detalle = -1;
                    List<Mgrupo> l = mgrupo.listar();
                    int id = l.get(l.size() - 1).getId();
                    List<Mmodgrupo> Lpriv = mmodgrupo.getLprivilegios();
                    for (int i = 0; i < Lpriv.size(); i++) {
                        Mmodgrupo mmodgrupo = new Mmodgrupo();
                        mmodgrupo.setIdgrupo(id);
                        mmodgrupo.setIdmodulo(Lpriv.get(i).getIdmodulo());
                        detalle = mmodgrupo.registrar();
                        if (detalle == -1) {
                            mgrupo.rollback();
                            break;
                        }
                    }
                    if (cabecera != -1 && detalle != -1) {
                        mgrupo.commit();
                    }
                } catch (Exception ex) {
                    mgrupo.rollback();

                }
                
                this.vgrupo.actualizar();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import model.Mgrupo;
import model.Mmodgrupo;
import model.Mmodulo;

/**
 *
 * @author ColaVirtual
 */
public class Vgrupo extends javax.swing.JFrame {
    public Mgrupo mgrupo;
    public Mmodulo mmodulo;
    public Mmodgrupo mmodgrupo;
    public List<Mmodgrupo>Lpriv;
    DefaultTableModel model; 
    DefaultTreeModel  modelt;
    /**
     * Creates new form Vgrupo
     */
    public Vgrupo(Mgrupo m) {
        initComponents();
        this.mgrupo=new Mgrupo();
        this.mmodulo=new Mmodulo();
        this.mmodgrupo=new Mmodgrupo();
        Lpriv=new LinkedList<>();
        this.setTitle("Jp Vet - Grupo");
        nuevoModelo();
        jTree1.setModel(getModel(this.mmodulo.listar()));
        /*jTable1.setModel(model);
        TableColumnModel tc=jTable1.getColumnModel();
        
        tc.getColumn(0).setPreferredWidth(10);
        tc.getColumn(1).setPreferredWidth(250);*/
        //jTable1.setAutoCreateRowSorter(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        setDefaultCloseOperation(Vgrupo.DISPOSE_ON_CLOSE);
        this.setExtendedState(Vgrupo.MAXIMIZED_BOTH);
    }
    private void nuevoModelo(){
        String[]titulos={"id","Privilegios"};
        model=new DefaultTableModel(null,titulos){
           @Override
           public boolean isCellEditable(int row,int column){
               return false;
           }
           
        };
    }
    public void actualizar(){
        txtNombre.setText("");
        txtDesc.setText("");
        nuevoModelo();
        //jTree1.setModel(getModel(this.mmodulo.listar()));
        jTree1.clearSelection();
        jTable1.setModel(model);
        mmodgrupo.resetLprivilegios();
        TableColumnModel tc=jTable1.getColumnModel();
        
        tc.getColumn(0).setPreferredWidth(10);
        tc.getColumn(1).setPreferredWidth(250);
        jTable2.setModel(getModel2(this.mgrupo.listar()));
        TableColumnModel tc2=jTable2.getColumnModel();
        tc2.getColumn(0).setPreferredWidth(10);
        tc2.getColumn(1).setPreferredWidth(50);
        tc2.getColumn(2).setPreferredWidth(600);
        jTable2.setAutoCreateRowSorter(true);
        
    }
    public DefaultTableModel getModel2(ArrayList<Mgrupo> lista){
        String[] titulos = {"id", "nombres", "descripcion"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos){
            @Override
             public boolean isCellEditable(int row, int column) {
             return false;
        }
        };
       
        Object[] fila = new Object[3];
        for (Mgrupo p : lista) {
            fila[0] = p.getId();
            fila[1] = p.getNombre();
            fila[2] = p.getDescripcion();
            //System.out.println(generoM.generoById(p.getGen()));
            modelo.addRow(fila);
        }
        return modelo;
    }
    public void setPrivilegio(Object[] texto){
        String[] datos=texto[0].toString().split("-");
        if(!existePrivilegio(datos[0].toString().trim())){
            model.addRow(datos);
            jTable1.setModel(model);
            int j=jTable1.getRowCount()-1;
            mmodgrupo.setIdmodulo(Integer.parseInt(jTable1.getValueAt(j, 0).toString().trim()));
            Mmodgrupo m=new Mmodgrupo();
            m.setIdmodulo(mmodgrupo.getIdmodulo());
            mmodgrupo.setLprivilegios(m);
            //if(mmodgrupo.getLprivilegios().size()==3)
                //mmodgrupo.setLprivilegios(m);
        }
    }
    public List<Mmodgrupo>getPrivilegios(){
        return mmodgrupo.getLprivilegios();
    }
    private boolean existePrivilegio(String id){
        for(int i=0;i<model.getRowCount();i++){
            if(id.equals(jTable1.getValueAt(i, 0).toString().trim()))
                return true;
        }
        return false;
    }
    private DefaultTreeModel getModel(ArrayList<Mmodulo> lista){
        String[]titulos={"id","nombre"};
        DefaultMutableTreeNode dmtn=new DefaultMutableTreeNode();
        Object[]fila=new Object[2];
        for(Mmodulo p:lista){
            fila[0]=p.getId();
            fila[1]=p.getNombre();
            DefaultMutableTreeNode dmtn2=new DefaultMutableTreeNode(fila[0]+" - "+fila[1]);
            dmtn.add(dmtn2);
        }
        modelt= new DefaultTreeModel(dmtn);
        return modelt;
    }
    public void getData(Integer fila) {
        int id=Integer.parseInt(jTable2.getValueAt(fila, 0).toString());
        String nombre=(String)jTable2.getValueAt(fila, 1);
        String descripcion=(String)jTable2.getValueAt(fila, 2);
        this.mgrupo.setId(id);
        this.mgrupo.setNombre(nombre);
        this.mgrupo.setDescripcion(descripcion);
        txtNombre.setText(mgrupo.getNombre());
        txtDesc.setText(mgrupo.getDescripcion());
        //privilegios
    }
 
    public void borrarFila(Integer fila){
        model.removeRow(fila);
        jTable1.setModel(model);
        mmodgrupo.removeLprivilegios(fila);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btmodificar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        txtNombre = new javax.swing.JTextField();
        btRegistrar = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btmodificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btmodificar.setText("REGISTRAR GRUPO");

        jLabel2.setText("usuario:");

        lbUsuario.setText("invitado");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Privilegios");

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        btRegistrar.setText("registrar");
        btRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistrarActionPerformed(evt);
            }
        });

        btModificar.setText("modificar");
        btModificar.setEnabled(false);

        btBuscar.setText("buscar");

        btEliminar.setText("eliminar");
        btEliminar.setEnabled(false);

        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTree1.setShowsRootHandles(true);
        jScrollPane2.setViewportView(jTree1);

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jScrollPane4.setViewportView(jTable2);

        jLabel6.setText("Grupos");

        jLabel7.setText("Privilegios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(btmodificar)
                                    .addGap(42, 42, 42)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btRegistrar)
                                        .addGap(26, 26, 26)
                                        .addComponent(btModificar)
                                        .addGap(34, 34, 34)
                                        .addComponent(btBuscar)
                                        .addGap(29, 29, 29)
                                        .addComponent(btEliminar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lbUsuario))
                            .addComponent(btmodificar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btRegistrar)
                            .addComponent(btModificar)
                            .addComponent(btBuscar)
                            .addComponent(btEliminar)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void btRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRegistrarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBuscar;
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btModificar;
    public javax.swing.JButton btRegistrar;
    private javax.swing.JLabel btmodificar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    public javax.swing.JTree jTree1;
    public javax.swing.JLabel lbUsuario;
    public javax.swing.JTextArea txtDesc;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

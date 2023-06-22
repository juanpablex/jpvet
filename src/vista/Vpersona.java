/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Mestado;
import model.Mpersona;
import java.sql.Timestamp;
import java.util.List;
import model.auxiliar;
import patrones.estado.Pestado;

/**
 *
 * @author ColaVirtual
 */
public class Vpersona extends javax.swing.JFrame {

    /**
     * Creates new form Vpersona
     */
    public Mpersona mpersona;
    public Mestado mestado;
    private boolean QBE;
    private boolean QBESexo;
    private boolean QBEEstado;
    private Pestado estado;
    
    public Vpersona(Mpersona p) {
        initComponents();
        this.estado = new Pestado('p');
        this.mpersona = p;
        this.mestado = new Mestado();
        cbestado.setModel(mestado.getCombo('p'));
        this.mestado = (Mestado) cbestado.getSelectedItem();
        this.setTitle("Jp Vet - Persona");
        setDefaultCloseOperation(Vpersona.DISPOSE_ON_CLOSE);
        this.setExtendedState(Vpersona.MAXIMIZED_BOTH);
    }
    
    public void actualizar() {
        txtci.setText("");
        txtnombres.setText("");
        txttelefonos.setText("");
        txtCorreo.setText("");
        txtdireccion.setText("");
        jTable1.setModel(getModel(this.mpersona.listar()));
        TableColumnModel tc = jTable1.getColumnModel();
        tc.getColumn(0).setPreferredWidth(10);
        tc.getColumn(1).setPreferredWidth(150);
        tc.getColumn(2).setPreferredWidth(5);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tc.getColumn(2).setCellRenderer(tcr);
        tc.getColumn(3).setPreferredWidth(30);
        tc.getColumn(4).setPreferredWidth(100);
        tc.getColumn(5).setPreferredWidth(15);
        tc.getColumn(6).setPreferredWidth(40);
        tc.getColumn(7).setPreferredWidth(40);
        tc.getColumn(8).setPreferredWidth(20);
        tc.getColumn(9).setPreferredWidth(20);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        estado = new Pestado('p');
        lbUsuario.setText(auxiliar.nick);
        //cbestado.setModel(mestado.getCombo('p'));
    }
    
    public void actualizarBusqueda(ArrayList<Mpersona> mc) {
        txtci.setText("");
        txtnombres.setText("");
        txtdireccion.setText("");
        txttelefonos.setText("");
        txtCorreo.setText("");
        jTable1.setModel(getModel(mc));
        TableColumnModel tc = jTable1.getColumnModel();
        tc.getColumn(0).setPreferredWidth(10);
        tc.getColumn(1).setPreferredWidth(150);
        tc.getColumn(2).setPreferredWidth(5);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tc.getColumn(2).setCellRenderer(tcr);
        tc.getColumn(3).setPreferredWidth(30);
        tc.getColumn(4).setPreferredWidth(100);
        tc.getColumn(5).setPreferredWidth(15);
        tc.getColumn(6).setPreferredWidth(30);
        tc.getColumn(7).setPreferredWidth(15);
        tc.getColumn(8).setPreferredWidth(15);
    }
    
    public void setEstado() {
        this.mestado = (Mestado) cbestado.getSelectedItem();
        this.mpersona.setIdestado(this.mestado.getId());
    }
    
    public void setSexo() {
        this.mpersona.setSexo(cbsexo.getSelectedItem().toString().trim());
    }
    
    public void setQBE(boolean sw) {
        QBE = sw;
    }
    
    public boolean getQBE() {
        return QBE;
    }
    
    public void setQBESexo(boolean sw) {
        QBESexo = sw;
    }
    
    public boolean getQBESexo() {
        return QBESexo;
    }
    
    public void setQBEEstado(boolean sw) {
        QBEEstado = sw;
    }
    
    public boolean getQBEEstado() {
        return QBEEstado;
    }
    
    public DefaultTableModel getModel(ArrayList<Mpersona> lista) {
        String[] titulos = {"ci", "nombres y apellidos", "sexo", "estado",
            "direccion", "telefonos", "email", "fecha ing", "lat", "long"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        Object[] fila = new Object[10];
        for (Mpersona p : lista) {
            fila[0] = p.getCi();
            fila[1] = p.getNombres();
            fila[2] = p.getSexo();
            fila[3] = mestado.getEstadoXid(p.getIdestado());
            fila[4] = p.getDireccion();
            fila[5] = p.getTelefonos();
            fila[6] = p.getEmail();
            fila[7] = p.getFecing();
            fila[8] = p.getLatitud();
            fila[9] = p.getLongitud();
            //System.out.println(generoM.generoById(p.getGen()));
            modelo.addRow(fila);
        }
        return modelo;
    }
    
    public char getTipo(String descripcion) {
        switch (descripcion) {
            case "ACTIVO":
                return 'a';
            
            case "INACTIVO":
                return 'i';
        }
        return 'd';
    }
    
    public void getData(Integer fila) {
        String ci = (String) jTable1.getValueAt(fila, 0);
        String nombres = (String) jTable1.getValueAt(fila, 1);
        String sexo = (String) jTable1.getValueAt(fila, 2);
        Mestado mestado = (Mestado) jTable1.getValueAt(fila, 3);
        char t = getTipo(mestado.getDescripcion());
        estado.mostrarTipos(t);
        cbestado.setModel(mestado.getCombo(t));
        String direccion = (String) jTable1.getValueAt(fila, 4);
        String telefonos = (String) jTable1.getValueAt(fila, 5);
        String email = (String) jTable1.getValueAt(fila, 6);
        Timestamp fecing = (Timestamp) jTable1.getValueAt(fila, 7);
        String latitud = (String) jTable1.getValueAt(fila, 8);
        String longitud = (String) jTable1.getValueAt(fila, 9);
        this.mpersona.setCi(ci);
        this.mpersona.setNombres(nombres);
        this.mpersona.setSexo(sexo);
        this.mpersona.setIdestado(mestado.getId());
        this.mpersona.setDireccion(direccion);
        this.mpersona.setTelefonos(telefonos);
        this.mpersona.setEmail(email);
        this.mpersona.setFecing(fecing);
        this.mpersona.setLatitud(latitud);
        this.mpersona.setLongitud(longitud);
        txtci.setText(String.valueOf(mpersona.getCi()));
        txtnombres.setText(mpersona.getNombres());
        cbsexo.setSelectedItem(sexo);
        //String desc=mestado.getEstadoXid(mpersona.getIdestado()).toString();

        //cbestado.setSelectedItem(mestado.posCombo2(cbestado.getModel()));
        cbestado.setSelectedIndex(mestado.posCombo2(cbestado.getModel()));
        setEstado();
        
        txtdireccion.setText(mpersona.getDireccion());
        txttelefonos.setText(mpersona.getTelefonos());
        txtCorreo.setText(mpersona.getEmail());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txttelefonos = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbsexo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btRegistrar = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("REGISTRAR PERSONA");

        jLabel8.setText("usuario:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        lbUsuario.setText("invitado");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ci:");

        jLabel7.setText("telefonos:");

        txttelefonos.setColumns(20);
        txttelefonos.setRows(5);
        jScrollPane1.setViewportView(txttelefonos);

        jLabel3.setText("nombres:");

        jLabel6.setText("direccion:");

        jLabel5.setText("estado:");

        jLabel4.setText("sexo:");

        cbsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F", " " }));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btRegistrar.setText("registrar");

        btModificar.setText("modificar");
        btModificar.setEnabled(false);

        btBuscar.setText("buscar");

        btEliminar.setText("eliminar");
        btEliminar.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btRegistrar)
                .addGap(27, 27, 27)
                .addComponent(btModificar)
                .addGap(33, 33, 33)
                .addComponent(btBuscar)
                .addGap(28, 28, 28)
                .addComponent(btEliminar))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(btRegistrar)
                .addComponent(btModificar)
                .addComponent(btBuscar)
                .addComponent(btEliminar))
        );

        jLabel9.setText("correo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdireccion))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreo))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 259, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(lbUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
 /*  try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
    //</editor-fold>

    /* Create and display the form */
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vpersona().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBuscar;
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btModificar;
    public javax.swing.JButton btRegistrar;
    public javax.swing.JComboBox<String> cbestado;
    public javax.swing.JComboBox<String> cbsexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel lbUsuario;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtci;
    public javax.swing.JTextField txtdireccion;
    public javax.swing.JTextField txtnombres;
    public javax.swing.JTextArea txttelefonos;
    // End of variables declaration//GEN-END:variables
}

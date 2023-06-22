/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Mestado;
import model.Mgrupo;
import model.Mpersona;
import model.Musuario;
import model.auxiliar;
import patrones.estado.Pestado;

/**
 *
 * @author ColaVirtual
 */
public class Vusuario extends javax.swing.JFrame {

    /**
     * Creates new form Vusuario
     */
    public Musuario musuario;
    public Mgrupo mgrupo;
    public Pestado estado;
    public Mestado mestado;
    public Vusuario(Musuario p) {
        initComponents();
        this.musuario=p;
        this.mgrupo=new Mgrupo();
        cbGrupo.setModel(mgrupo.getCombo());
        this.mgrupo=(Mgrupo)cbGrupo.getSelectedItem();
        this.mestado=new Mestado();
        //cargar combo
        this.setTitle("Jp Vet - Usuario");
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        setDefaultCloseOperation(Vusuario.DISPOSE_ON_CLOSE);
        this.setExtendedState(Vusuario.MAXIMIZED_BOTH);
    }
    
    public void actualizar(){
        txtNick.setText("");
        txtpersona.setText("");
        Mpersona mpersona=new Mpersona();
        jTable1.setModel(getModel2(mpersona.listar()));
        TableColumnModel tc2=jTable1.getColumnModel();
         jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable1.setAutoCreateRowSorter(true);
        jTable2.setModel(getModel(this.musuario.listar()));
        TableColumnModel tc=jTable2.getColumnModel();
        tc.getColumn(0).setPreferredWidth(30);
        tc.getColumn(1).setPreferredWidth(250);
        tc.getColumn(2).setPreferredWidth(80);
        tc.getColumn(3).setPreferredWidth(80);
        tc.getColumn(4).setPreferredWidth(120);
        tc.getColumn(5).setPreferredWidth(70);
        jTable2.setAutoCreateRowSorter(true);
        lbUsuario.setText(auxiliar.nick);
    }
    public void setGrupo(){
        this.mgrupo=(Mgrupo)cbGrupo.getSelectedItem();
        this.mgrupo.setId(this.mgrupo.getId());
    }
private DefaultTableModel getModel2(ArrayList<Mpersona>lista){
        String[] titulos = {"ci", "nombres"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos){
            @Override
             public boolean isCellEditable(int row, int column) {
             return false;
        }
        };
        Object[] fila=new Object[2];
        for(Mpersona p:lista){
            fila[0] = p.getCi();
            fila[1] = p.getNombres();
            modelo.addRow(fila); 
        }
        return modelo;
    }
private DefaultTableModel getModel(ArrayList<Musuario>lista){
        String[] titulos = {"nick","nombres",
            "fecha ini","fecha fin","grupo","estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos){
            @Override
             public boolean isCellEditable(int row, int column) {
             return false;
        }
        };
        Object[] fila=new Object[10];
        for(Musuario p:lista){
           // fila[0] = p.getId();
            fila[0] = p.getNick();
            fila[1]=new Mpersona().getPersonaXci(p.getIdpersona());
            fila[2]=p.getFecini();
            fila[3]=p.getFecfin();
            fila[4]=new Mgrupo().getGrupoXid(p.getIdgrupo());
            fila[5]=mestado.getEstadoXid(p.getIdestado());
            //System.out.println(generoM.generoById(p.getGen()));
            modelo.addRow(fila); 
        }
        return modelo;
    }
public void getData2(Integer fila) {
        if(fila==-1)
            return;
        String ci = (String)jTable1.getValueAt(fila, 0).toString();
        //String nombres = (String) jTable1.getValueAt(fila, 1);
        
        this.musuario.setIdpersona(ci);
        //this.musuario.setNombre(nombres);
      
        txtpersona.setText(String.valueOf(musuario.getIdpersona()));
      
       
        

    }

public void getData(Integer fila) {
        //String ci = (String)jTable1.getValueAt(fila, 0).toString();
        if(fila==-1)
            return;
        String nick=(String)jTable2.getValueAt(fila, 0).toString();
        Mpersona persona = (Mpersona) jTable2.getValueAt(fila, 1);
        Timestamp fecini=(Timestamp)jTable2.getValueAt(fila, 2);
        Timestamp fecfin=(Timestamp)jTable2.getValueAt(fila, 3);
        Mgrupo mgrupo=(Mgrupo)jTable2.getValueAt(fila, 4);
        Mestado mestado = (Mestado) jTable2.getValueAt(fila, 5);
        //char t=getTipo(mestado.getDescripcion());
        //estado.mostrarTipos(t);
        //cbestado.setModel(mestado.getCombo(t));
        //animal manimal = (Manimal) jTable2.getValueAt(fila, 7);
        //Mraza mraza = (Mraza) jTable2.getValueAt(fila, 8);
        //this.musuario.setIdpersona(ci);
        this.musuario.setNick(nick);
        this.musuario.setFecini(fecini);
        this.musuario.setFecfin(fecfin);
        this.musuario.setIdpersona(persona.getCi());
        this.musuario.setIdestado(mestado.getId());
        this.musuario.setIdgrupo(musuario.getIdgrupo());
        
        txtNick.setText(nick);
        txtpersona.setText(String.valueOf(musuario.getIdpersona()));
        
        
        //cbgenero.setSelectedItem(sexo);
        
        cbGrupo.setSelectedIndex(mgrupo.posCombo(mgrupo.getId()));
        //cbestado.setSelectedIndex(mestado.posCombo2(cbestado.getModel()));
        Calendar c=Calendar.getInstance();
       
        c.set(fecfin.getYear()+1900, fecfin.getMonth()
                , fecfin.getDate(),fecfin.getHours()
                ,fecfin.getMinutes(),fecfin.getSeconds());
        jCalendar1.setCalendar(c);
        //cbestado.setSelectedIndex(mestado.posCombo());
       
        

    }
 public char getTipo(String descripcion){
    switch(descripcion){
        case "HABIL":
            return 'b';
            
        case "TRATAMIENTO":
            return 't';
    }
    return 'f';
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtpersona = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbGrupo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        btRegistrar = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel10.setText("persona:");

        txtpersona.setEnabled(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        jLabel5.setText("Usuarios");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("REGISTRAR USUARIO");

        lbUsuario.setText("invitado");

        jLabel7.setText("Usuario:");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Grupo:");

        cbGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel4.setText("Fecha inicio");

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btRegistrar)
                .addGap(18, 18, 18)
                .addComponent(btModificar)
                .addGap(18, 18, 18)
                .addComponent(btBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEliminar))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btRegistrar)
                .addComponent(btModificar)
                .addComponent(btBuscar)
                .addComponent(btEliminar))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(lbUsuario)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel5)))))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBuscar;
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btModificar;
    public javax.swing.JButton btRegistrar;
    public javax.swing.JComboBox<String> cbGrupo;
    public com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    private javax.swing.JLabel lbUsuario;
    public javax.swing.JTextField txtNick;
    public javax.swing.JTextField txtpersona;
    // End of variables declaration//GEN-END:variables
}

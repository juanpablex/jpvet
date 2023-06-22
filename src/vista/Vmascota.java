/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Manimal;
import model.Mestado;
import model.Mmascota;
import model.Mpersona;
import model.Mraza;
import model.auxiliar;
import patrones.estado.Pestado;

/**
 *
 * @author ColaVirtual
 */
public class Vmascota extends javax.swing.JFrame {

    /**
     * Creates new form Vmascota
     */
    public Mmascota mmascota;
    public Mestado mestado;
    public Mraza mraza;
    public Manimal manimal;
    public Pestado estado;
    private boolean QBE;
    private boolean QBEGenero;
    private boolean QBEEstado;
    private boolean QBERaza;
    private boolean QBEAnimal;

    public Vmascota(Mmascota m) {
        initComponents();
        this.estado = new Pestado('m');
        this.mmascota = m;
        this.mestado = new Mestado();
        this.mraza = new Mraza();
        this.manimal = new Manimal();
        cbestado.setModel(mestado.getCombo('m'));
        this.mestado = (Mestado) cbestado.getSelectedItem();
        cbanimal.setModel(manimal.getCombo());
        this.manimal = (Manimal) cbanimal.getSelectedItem();
        cbraza.setModel(mraza.getCombo(manimal.getId()));
        this.mraza = (Mraza) cbraza.getSelectedItem();
        this.setTitle("Jp Vet - Mascota");
        txtid.setVisible(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        setDefaultCloseOperation(Vmenu.DISPOSE_ON_CLOSE);
    }

    public void actualizar() {
        txtnombre.setText("");
        txtDescripcion.setText("");
        txtpersona.setText("");
        txtid.setText("");
        jTable2.setModel(getModel(this.mmascota.listar()));
        TableColumnModel tc = jTable2.getColumnModel();
        tc.getColumn(0).setPreferredWidth(5);
        tc.getColumn(1).setPreferredWidth(30);
        tc.getColumn(2).setPreferredWidth(5);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tc.getColumn(2).setCellRenderer(tcr);
        tc.getColumn(0).setCellRenderer(tcr);
        tc.getColumn(3).setPreferredWidth(30);
        tc.getColumn(4).setPreferredWidth(30);
        tc.getColumn(5).setPreferredWidth(200);
        tc.getColumn(6).setPreferredWidth(80);
        tc.getColumn(7).setPreferredWidth(50);
        tc.getColumn(8).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(9).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(9).setMinWidth(0);
        jTable2.getColumnModel().getColumn(9).setPreferredWidth(0);
        jTable2.setAutoCreateRowSorter(true);
        Mpersona mpersona = new Mpersona();
        Vpersona vpersona = new Vpersona(mpersona);
        jTable1.setModel(getModel2(mpersona.listar()));
        TableColumnModel tc2 = jTable1.getColumnModel();
        //tc2.getColumn(0).setPreferredWidth(10);
        //tc2.getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable1.setAutoCreateRowSorter(true);
        estado = new Pestado('m');
        cbestado.setModel(mestado.getCombo('m'));
        lbUsuario.setText(auxiliar.nick);
        this.setExtendedState(Vmascota.MAXIMIZED_BOTH);
        /*  cbraza.setModel(mraza.getCombo());
        cbanimal.setModel(manimal.getCombo());*/
    }

    public void setAnimal() {
        this.manimal = (Manimal) cbanimal.getSelectedItem();
        this.manimal.setId(this.manimal.getId());
    }

    public void setRaza(int idanimal) {
        cbraza.setModel(mraza.getCombo(idanimal));
        this.mraza = (Mraza) cbraza.getSelectedItem();
        this.mraza.setId(this.mraza.getId());
    }

    public void setRaza() {
        this.mraza = (Mraza) cbraza.getSelectedItem();
        this.mraza.setId(this.mraza.getId());
    }

    public void setEstado() {
        this.mestado = (Mestado) cbestado.getSelectedItem();
        this.mmascota.setIdestado(this.mestado.getId());
    }

    public void setGenero() {
        this.mmascota.setGenero(cbgenero.getSelectedItem().toString());
    }

    public boolean isQBE() {
        return QBE;
    }

    public void setQBE(boolean QBE) {
        this.QBE = QBE;
    }

    public boolean isQBEGenero() {
        return QBEGenero;
    }

    public void setQBEGenero(boolean QBEGenero) {
        this.QBEGenero = QBEGenero;
    }

    public boolean isQBEEstado() {
        return QBEEstado;
    }

    public void setQBEEstado(boolean QBEEstado) {
        this.QBEEstado = QBEEstado;
    }

    public boolean isQBERaza() {
        return QBERaza;
    }

    public void setQBERaza(boolean QBERaza) {
        this.QBERaza = QBERaza;
    }

    public boolean isQBEAnimal() {
        return QBEAnimal;
    }

    public void setQBEAnimal(boolean QBEAnimal) {
        this.QBEAnimal = QBEAnimal;
    }

    private DefaultTableModel getModel(ArrayList<Mmascota> lista) {
        String[] titulos = {"id", "nombre", "genero",
            "fecha nac", "fecha reg", "dueño", "estado", "animal",
            "raza", "descripcion"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] fila = new Object[10];
        for (Mmascota p : lista) {
            fila[0] = p.getId();
            fila[1] = p.getNombre();
            fila[2] = p.getGenero();
            fila[3] = p.getFechanac();
            fila[4] = p.getFechareg();
            fila[5] = new Mpersona().getPersonaXci(p.getIdpersona());
            fila[6] = mestado.getEstadoXid(p.getIdestado());
            fila[7] = new Manimal().getAnimalXid(p.getIdanimal());
            fila[8] = new Mraza().getRazaXid(p.getIdraza());
            fila[9] = p.getDescripcion();
            //System.out.println(generoM.generoById(p.getGen()));
            modelo.addRow(fila);
        }
        return modelo;
    }

    private DefaultTableModel getModel2(ArrayList<Mpersona> lista) {
        String[] titulos = {"ci", "nombres"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] fila = new Object[2];
        for (Mpersona p : lista) {
            fila[0] = p.getCi();
            fila[1] = p.getNombres();
            modelo.addRow(fila);
        }
        return modelo;
    }

    public char getTipo(String descripcion) {
        switch (descripcion) {
            case "HABIL":
                return 'b';

            case "TRATAMIENTO":
                return 't';
        }
        return 'f';
    }

    public void getData2(Integer fila) {
        if (fila == -1) {
            return;
        }
        String ci = (String) jTable1.getValueAt(fila, 0).toString();
        String nombres = (String) jTable1.getValueAt(fila, 1);

        this.mmascota.setIdpersona(ci);
        this.mmascota.setNombre(nombres);

        txtpersona.setText(String.valueOf(mmascota.getIdpersona()));

    }

    public void getData(Integer fila) {
        //String ci = (String)jTable1.getValueAt(fila, 0).toString();
        if (fila == -1) {
            return;
        }
        String id = (String) jTable2.getValueAt(fila, 0).toString();
        String nombres = (String) jTable2.getValueAt(fila, 1);
        String sexo = (String) jTable2.getValueAt(fila, 2);
        Timestamp fecnac = (Timestamp) jTable2.getValueAt(fila, 3);
        Timestamp fecreg = (Timestamp) jTable2.getValueAt(fila, 4);
        Mpersona mpersona = (Mpersona) jTable2.getValueAt(fila, 5);
        Mestado mestado = (Mestado) jTable2.getValueAt(fila, 6);
        String descripcion = (String) jTable2.getValueAt(fila, 9);
        char t = getTipo(mestado.getDescripcion());
        estado.mostrarTipos(t);
        cbestado.setModel(mestado.getCombo(t));
        Manimal manimal = (Manimal) jTable2.getValueAt(fila, 7);
        Mraza mraza = (Mraza) jTable2.getValueAt(fila, 8);
        //this.mmascota.setIdpersona(ci);
        this.mmascota.setNombre(nombres);
        this.mmascota.setGenero(sexo);
        this.mmascota.setFechanac(fecnac);
        this.mmascota.setFechareg(fecreg);
        this.mmascota.setIdpersona(mpersona.getCi());
        this.mmascota.setIdestado(mestado.getId());
        this.mmascota.setIdanimal(manimal.getId());
        this.mmascota.setIdraza(mraza.getId());
        txtid.setText(id);
        txtpersona.setText(String.valueOf(mmascota.getIdpersona()));
        txtnombre.setText(mmascota.getNombre());
        txtDescripcion.setText(descripcion);
        cbgenero.setSelectedItem(sexo);
        cbanimal.setSelectedIndex(manimal.posCombo());
        cbraza.setSelectedIndex(mraza.posCombo(manimal.getId()));
        cbestado.setSelectedIndex(mestado.posCombo2(cbestado.getModel()));
        Calendar c = Calendar.getInstance();

        c.set(fecnac.getYear() + 1900, fecnac.getMonth(),
                 fecnac.getDate(), fecnac.getHours(),
                 fecnac.getMinutes(), fecnac.getSeconds());
        jCalendar1.setCalendar(c);
        //cbestado.setSelectedIndex(mestado.posCombo());

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
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbgenero = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbraza = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbanimal = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtnombre = new javax.swing.JTextField();
        panel1 = new java.awt.Panel();
        txtid = new javax.swing.JTextField();
        btFoto = new javax.swing.JButton();
        btRegistrar = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtpersona = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        lbUsuario = new javax.swing.JLabel();
        btLibreta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("REGISTRAR MASCOTA");

        jLabel2.setText("usuario:");

        jLabel5.setText("fecha nacimiento");

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jLabel7.setText("estado:");

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel4.setText("genero:");

        cbgenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F", " " }));

        jLabel9.setText("raza:");

        jLabel8.setText("animal:");

        cbanimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbanimalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbraza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbanimal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbgenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbgenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbanimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbraza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));

        jLabel3.setText("nombre:");

        jLabel6.setText("descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        panel1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        btFoto.setText("foto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3))
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(txtnombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFoto)
                .addGap(52, 52, 52))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFoto))
        );

        btRegistrar.setText("registrar");

        btModificar.setText("modificar");
        btModificar.setEnabled(false);

        btBuscar.setText("buscar");

        btEliminar.setText("eliminar");
        btEliminar.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(153, 255, 102));

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
        jScrollPane3.setViewportView(jTable2);

        jLabel11.setText("mascotas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jLabel10.setText("personas");

        txtpersona.setEnabled(false);

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

        btLibreta.setText("ver libreta");
        btLibreta.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(128, 128, 128)
                                .addComponent(jLabel10)
                                .addGap(9, 9, 9)
                                .addComponent(txtpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(458, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2)
                                .addGap(19, 19, 19))))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btLibreta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lbUsuario))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtpersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRegistrar)
                    .addComponent(btModificar)
                    .addComponent(btBuscar)
                    .addComponent(btEliminar)
                    .addComponent(btLibreta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbanimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbanimalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbanimalActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBuscar;
    public javax.swing.JButton btEliminar;
    public javax.swing.JButton btFoto;
    public javax.swing.JButton btLibreta;
    public javax.swing.JButton btModificar;
    public javax.swing.JButton btRegistrar;
    public javax.swing.JComboBox<String> cbanimal;
    public javax.swing.JComboBox<String> cbestado;
    public javax.swing.JComboBox<String> cbgenero;
    public javax.swing.JComboBox<String> cbraza;
    public com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    public javax.swing.JLabel lbUsuario;
    public java.awt.Panel panel1;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtid;
    public javax.swing.JTextField txtnombre;
    public javax.swing.JTextField txtpersona;
    // End of variables declaration//GEN-END:variables
}

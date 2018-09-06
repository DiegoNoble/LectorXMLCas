/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.views;

import com.dnsoft.LectoXMLCas.ControllerFX.FXMLController;
import com.dnsoft.LectorXMLCas.Main;
import com.dnsoft.LectorXMLCas.beans.Datos;
import com.dnsoft.LectorXMLCas.beans.Resumen;
import com.dnsoft.LectorXMLCas.beans.ResumenPorMes;
import com.dnsoft.LectorXMLCas.beans.Root;
import com.dnsoft.LectorXMLCas.model.SlotTableModel;
import com.dnsoft.LectorXMLCas.service.XMLCasService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import com.dnsoft.LectorXMLCas.daos.IDatosDAO;
import com.dnsoft.LectorXMLCas.daos.IResumenDAO;
import com.dnsoft.LectorXMLCas.model.ResumenTableModel;
import com.dnsoft.utiles.ExportarDatosExcel;
import com.dnsoft.utiles.MeDateCellRenderer;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class Frame extends java.awt.Frame {

    private static String XML_PEDIDOS = "";
    private List<Datos> listSlot;
    private SlotTableModel slotTableModel;
    private ResumenTableModel resumenTableModel;
    private List<Resumen> listResumen;
    private IDatosDAO datosDAO;

    public Frame() {
        initComponents();
        //setSize(getMaximumSize());

        txtRutaXML.setText("Y://FTPManteo");
        setModel();
        //buscaDatos();

        setVisible(true);

    }

    private void setModel() {

        ((DefaultTableCellRenderer) tblSlot.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        listSlot = new ArrayList<Datos>();
        slotTableModel = new SlotTableModel(listSlot);

        tblSlot.setModel(slotTableModel);

        ((DefaultTableCellRenderer) tblResumen.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        listResumen = new ArrayList<Resumen>();
        resumenTableModel = new ResumenTableModel(listResumen);

        tblResumen.setModel(resumenTableModel);
        tblResumen.getColumn("Fecha").setCellRenderer(new MeDateCellRenderer());
    }

    void agregaItem(Root root) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            listSlot.addAll(root.getListTable());

                            resumen(root.getListTable());

                            Comparator<Datos> cmp = new Comparator<Datos>() {
                                public int compare(Datos r1, Datos r2) {
                                    if (r1.getFecha().after(r2.getFecha())) {
                                        return -1;
                                    }
                                    if (r1.getFecha().before(r2.getFecha())) {
                                        return 1;
                                    }
                                    return 0;
                                }

                            };
                            Collections.sort(listSlot, cmp); // pronto agora sua lista esta ordenada
                            slotTableModel.fireTableDataChanged();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void resumen(List<Datos> listDatos) {

        Integer ruletas = 0;
        Integer videos = 0;
        Double win_ruletas = 0.0;
        Double win_videos = 0.0;
        Double coinIn_ruletas = 0.0;
        Double coinIn_videos = 0.0;
        Double coinIn_videos_promo = 0.0;
        Double coinIn_ruleta_promo = 0.0;
        Resumen r = new Resumen();
        r.setSala(jComboBox1.getSelectedItem().toString());
        for (Datos dato : listDatos) {
            r.setNombre_Archivo(dato.getNombre_Archivo());
            r.setFecha(dato.getFecha());

            if (dato.getTipo_Juego().trim().equals("VRULE") || dato.getTipo_Juego().trim().equals("ROULE") || dato.getTipo_Juego().trim().equals("RBCM")) {
                ruletas = ruletas + 1;
                win_ruletas = win_ruletas + dato.getImporte_Win();
                coinIn_ruletas = coinIn_ruletas + dato.getImporte_Handle();
                coinIn_ruleta_promo = coinIn_ruleta_promo + dato.getImporte_Coinin_Promo();

            } else {
                videos = videos + 1;
                win_videos = win_videos + dato.getImporte_Win();
                coinIn_videos = coinIn_videos + dato.getImporte_Handle();
                coinIn_videos_promo = coinIn_videos_promo + dato.getImporte_Coinin_Promo();

            }
        }

        r.setCantidad_Ruletas(ruletas);
        r.setCantidad_Videos(videos);
        r.setWin_ruleta(win_ruletas);
        r.setWin_video(win_videos);
        r.setCoin_in_ruleta(coinIn_ruletas);
        r.setCoin_in_video(coinIn_videos);
        r.setWin_maquina_ruletas(win_ruletas / ruletas);
        r.setWin_maquina_videos(win_videos / videos);
        r.setGanancia_Total(win_ruletas + win_videos);
        r.setCoin_in_Total(coinIn_ruletas + coinIn_videos);
        r.setWin_maquina_total((win_ruletas + win_videos) / (ruletas + videos));
        r.setImporte_Coinin_Promo_Ruleta(coinIn_ruleta_promo);
        r.setImporte_Coinin_Promo_Video(coinIn_videos_promo);

        r.setRetencion_rodillos(0.0);
        r.setWin_maquina_rodillos(0.0);
        r.setRetencion_rodillos(0.0);
        r.setCantidad_Rodillos(0);
        r.setCoin_in_rodillo(0.0);
        r.setWin_rodillo(0.0);
        Double retencionRuletas = 0.0;
        if (win_ruletas != 0 || coinIn_ruletas != 0) {
            retencionRuletas = (win_ruletas / coinIn_ruletas) * 100;

        }
        r.setRetencion_Ruletas(retencionRuletas);
        Double retencionVideos = 0.0;
        if (win_videos != 0 || coinIn_videos != 0) {
            retencionVideos = (win_videos / coinIn_videos) * 100;
        }

        r.setRetencion_videos(retencionVideos);
        Double retencionTotal = 0.0;
        if (win_videos != 0 || coinIn_videos != 0) {
            retencionTotal = ((win_ruletas + win_videos) / (coinIn_ruletas + coinIn_videos)) * 100;
        }

        r.setRetencion_Total(retencionTotal);

        listResumen.add(r);

        Comparator<Resumen> cmp = new Comparator<Resumen>() {
            public int compare(Resumen r1, Resumen r2) {
                if (r1.getFecha().after(r2.getFecha())) {
                    return -1;
                }
                if (r1.getFecha().before(r2.getFecha())) {
                    return 1;
                }
                return 0;
            }

        };

        Collections.sort(listResumen, cmp); // pronto agora sua lista esta ordenada

        resumenTableModel.fireTableDataChanged();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSlot = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtRutaXML = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnLeerArchivos = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblResumen = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtExportarExcel = new javax.swing.JButton();
        txtExportarExcel1 = new javax.swing.JButton();
        pbar = new javax.swing.JProgressBar();

        setPreferredSize(new java.awt.Dimension(1024, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        tblSlot.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSlot);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane1, gridBagConstraints);

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jButton1, gridBagConstraints);

        txtRutaXML.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(txtRutaXML, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(204, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Casino Rivera", "Sarandi Slots", "Casino Salto" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Seleccione la sala de Juego");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        btnLeerArchivos.setText("Leer archivos XML");
        btnLeerArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerArchivosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnLeerArchivos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel5.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel5, gridBagConstraints);

        tblResumen.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblResumen);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane3, gridBagConstraints);

        txtExportarExcel.setText("Exportar Resumen Excel");
        txtExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExportarExcelActionPerformed(evt);
            }
        });
        jPanel2.add(txtExportarExcel);

        txtExportarExcel1.setText("Exportar Detalle Excel");
        txtExportarExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExportarExcel1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtExportarExcel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pbar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            JFileChooser buscar = new JFileChooser();
            //buscarFoto.setCurrentDirectory(new File("C:/Fotos Socios/"));
            buscar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            buscar.setDialogTitle("Cargar pedido XML");
            buscar.showOpenDialog(this);
            //String foto = "C:/Fotos Socios/" + buscarFoto.getSelectedFile().getName();
            txtRutaXML.setText(buscar.getSelectedFile().getPath());

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "No fue posible cargar la foto" + error);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLeerArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerArchivosActionPerformed
        listResumen.clear();
        listSlot.clear();
        resumenTableModel.fireTableDataChanged();
        slotTableModel.fireTableDataChanged();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    XML_PEDIDOS = txtRutaXML.getText();

                    for (Root root : new XMLCasService().leerXML(txtLog, XML_PEDIDOS)) {
                        //listSlot.addAll(root.getListTable());
                        agregaItem(root);
                    }

                    //tableModel.fireTableDataChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }//GEN-LAST:event_btnLeerArchivosActionPerformed

    private void txtExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExportarExcelActionPerformed
        try {
            ExportarDatosExcel e = new ExportarDatosExcel(tblResumen, "Resumen");
            e.exportarJTableExcel();

        } catch (Exception ex) {
            txtLog.append(ex.getMessage());
            Logger.getLogger(Frame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtExportarExcelActionPerformed

    private void txtExportarExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExportarExcel1ActionPerformed
        try {
            ExportarDatosExcel e = new ExportarDatosExcel(tblSlot, "Detalle");
            e.exportarJTableExcel();

        } catch (Exception ex) {
            txtLog.append(ex.getMessage());
            Logger.getLogger(Frame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtExportarExcel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeerArchivos;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JTable tblResumen;
    private javax.swing.JTable tblSlot;
    private javax.swing.JButton txtExportarExcel;
    private javax.swing.JButton txtExportarExcel1;
    public javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtRutaXML;
    // End of variables declaration//GEN-END:variables

}

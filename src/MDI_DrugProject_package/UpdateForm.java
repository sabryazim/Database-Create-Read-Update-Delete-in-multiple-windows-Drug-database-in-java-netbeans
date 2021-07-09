/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDI_DrugProject_package;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 *
 * @author sabryazim
 */
public class UpdateForm extends javax.swing.JFrame {
            JFileChooser fc;
            File imageFile =null;
    /**
     * Creates new form UpdateForm
     */


    class MyThread implements Runnable{
        Mat frame = new Mat();
        Mat frame1 = new Mat();
        MatOfByte mob = new MatOfByte();
        MatOfByte mob1 = new MatOfByte();
        VideoCapture capture;
        public volatile boolean runnable = false;
        boolean takePhoto = false;
        @Override
        synchronized public void run() {
            while(capture.grab()){
                if(runnable){
                    try {
                        capture.read(frame);
                        Imgproc.resize(frame, frame, new Size(315,315));
                        Imgcodecs.imencode(".jpg", frame, mob);
                        Image img = ImageIO.read(new ByteArrayInputStream(mob.toArray()));
                        ImageIcon icon = new ImageIcon(img);
                        lblImage.setIcon(icon);
                        if(takePhoto){
                            Imgproc.resize(frame, frame1, new Size(169,144));
                            Imgcodecs.imencode(".jpg", frame1, mob1);
                            Path curPath = Paths.get("."+"\\"+"_"+".jpg");
                            System.out.println(curPath.toString());
                            Files.copy(new ByteArrayInputStream(mob1.toArray()), curPath,StandardCopyOption.REPLACE_EXISTING);
                            Image img1 = ImageIO.read(new ByteArrayInputStream(mob1.toArray()));
                            ImageIcon icon1 = new ImageIcon(img1);
                            lblImage1.setIcon(icon1);
                            takePhoto = false;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(UpdateForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    public UpdateForm() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        initComponents();
            try{  

                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();  
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/test","sabryazim","5516");  
                if(con == null){
                    JOptionPane.showMessageDialog(null,"connected failed","failed",JOptionPane.WARNING_MESSAGE);
                }
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from `drug`");

                ResultSetMetaData metadata = rs.getMetaData();
                Vector<String> columnNames = new Vector<>();
                int columnCount = metadata.getColumnCount();
                //System.out.println(columnCount);
                Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>();
                for(int i=1 ;i <= columnCount;i++){
                    columnNames.add(metadata.getColumnName(i));
                }     

                while(rs.next()){
                 Vector<Object> obj = new Vector<Object>();
                 for(int i =1;i<= columnCount;i++){
                     obj.add(rs.getObject(i));
                 }
                 dataVector.add(obj);
                }  
                  DefaultTableModel model = new DefaultTableModel(dataVector,columnNames);
                  jTable1.setModel(model);
                con.close();  
            }catch(Exception e){ 
                JOptionPane.showMessageDialog(this, e);
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        drugSearchF = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        drugNo = new javax.swing.JTextField();
        drugLicense = new javax.swing.JTextField();
        drugDate = new javax.swing.JTextField();
        drugUnit = new javax.swing.JTextField();
        drugCompany2 = new javax.swing.JTextField();
        drugCompany = new javax.swing.JTextField();
        drugPrice = new javax.swing.JTextField();
        drugClassification = new javax.swing.JTextField();
        drugScName = new javax.swing.JTextField();
        drugname = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        btnLoadImage = new javax.swing.JButton();
        btnStartWebcam = new javax.swing.JButton();
        btnStopWebcam = new javax.swing.JButton();
        btnTakePhoto = new javax.swing.JButton();
        lblImage1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Udate drug");
        setLocation(new java.awt.Point(100, 10));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        drugSearchF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        drugSearchF.setToolTipText("enter drug name or sc name or price !!");
        drugSearchF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                drugSearchFKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Trade name : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sc. name : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Classification");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("company");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Company2");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Unit");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Date");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("License");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("No#");

        drugNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugLicense.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugUnit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugCompany2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugCompany.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugClassification.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugScName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        drugname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("Update drug");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblImage.setPreferredSize(new java.awt.Dimension(315, 315));

        btnLoadImage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLoadImage.setText("Load image from PC");
        btnLoadImage.setActionCommand("");
        btnLoadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadImageActionPerformed(evt);
            }
        });

        btnStartWebcam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnStartWebcam.setText("Start WebCam");
        btnStartWebcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartWebcamActionPerformed(evt);
            }
        });

        btnStopWebcam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnStopWebcam.setText("Stop WebCam");

        btnTakePhoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTakePhoto.setText("Take Photo");

        lblImage1.setPreferredSize(new java.awt.Dimension(169, 144));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drugSearchF, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnStartWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(277, 277, 277)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnTakePhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnStopWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(74, 74, 74)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(drugNo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel9))
                                            .addGap(41, 41, 41)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(drugScName, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugCompany2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugDate, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(drugLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(btnLoadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnSearch)
                    .addComponent(drugSearchF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugScName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugCompany2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugLicense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drugNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(34, 34, 34)
                        .addComponent(btnUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(btnTakePhoto)
                                .addGap(18, 18, 18)
                                .addComponent(btnStopWebcam)
                                .addGap(18, 18, 18)
                                .addComponent(btnStartWebcam)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoadImage))
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

            int x = jTable1.getSelectedRow();
            drugname.setText(jTable1.getModel().getValueAt(x, 1).toString());
            drugScName.setText(jTable1.getModel().getValueAt(x, 2).toString());
            if(jTable1.getModel().getValueAt(x, 3) != null){
                byte[] blob = (byte[]) jTable1.getModel().getValueAt(x, 3);
                InputStream in = new ByteArrayInputStream(blob);
                Image img = Toolkit.getDefaultToolkit().createImage(blob);
                lblImage.setIcon(new ImageIcon(img));
            }else {
                lblImage.setIcon(null);
            }
            
            drugClassification.setText(jTable1.getModel().getValueAt(x, 4).toString());
            drugPrice.setText(jTable1.getModel().getValueAt(x, 5).toString());
            drugCompany.setText(jTable1.getModel().getValueAt(x, 6).toString());
            drugCompany2.setText(jTable1.getModel().getValueAt(x, 7).toString());
            drugUnit.setText(jTable1.getModel().getValueAt(x, 8).toString());
            drugDate.setText(jTable1.getModel().getValueAt(x, 9).toString());
            drugLicense.setText(jTable1.getModel().getValueAt(x, 10).toString());
            drugNo.setText(jTable1.getModel().getValueAt(x, 11).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void drugSearchFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_drugSearchFKeyPressed
        if(evt.getKeyCode() == 10){
            try{

                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","sabryazim","5516");
                if(con == null){
                    JOptionPane.showMessageDialog(null,"connected failed","failed",JOptionPane.WARNING_MESSAGE);
                }
                /*
                String sql = "select * from drug WHERE TradeName  REGEXP (?)";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql); */
                String query = "select * from drug WHERE TradeName  REGEXP (?) or ScName REGEXP (?) or Price REGEXP (?)";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,drugSearchF.getText());
                st.setString(2,drugSearchF.getText());
                st.setString(3, drugSearchF.getText());
                ResultSet rs = st.executeQuery();

                ResultSetMetaData metadata = rs.getMetaData();
                Vector<String> columnNames = new Vector<>();
                int columnCount = metadata.getColumnCount();
                //System.out.println(columnCount);
                Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>();
                for(int i=1 ;i <= columnCount;i++){
                    columnNames.add(metadata.getColumnName(i));
                }

                while(rs.next()){
                    Vector<Object> obj = new Vector<Object>();
                    for(int i =1;i<= columnCount;i++){
                        obj.add(rs.getObject(i));
                    }
                    dataVector.add(obj);
                }
                DefaultTableModel model = new DefaultTableModel(dataVector,columnNames);
                jTable1.setModel(model);
                con.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_drugSearchFKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try{

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","sabryazim","5516");
            if(con == null){
                JOptionPane.showMessageDialog(null,"connected failed","failed",JOptionPane.WARNING_MESSAGE);
            }
            /*
            String sql = "select * from drug WHERE TradeName  REGEXP (?)";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql); */
            String query = "select * from drug WHERE TradeName  REGEXP (?) or ScName REGEXP (?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,drugSearchF.getText());
            st.setString(2,drugSearchF.getText());
            ResultSet rs = st.executeQuery();

            ResultSetMetaData metadata = rs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = metadata.getColumnCount();
            //System.out.println(columnCount);
            Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>();
            for(int i=1 ;i <= columnCount;i++){
                columnNames.add(metadata.getColumnName(i));
            }

            while(rs.next()){
                Vector<Object> obj = new Vector<Object>();
                for(int i =1;i<= columnCount;i++){
                    obj.add(rs.getObject(i));
                }
                dataVector.add(obj);
            }
            DefaultTableModel model = new DefaultTableModel(dataVector,columnNames);
            jTable1.setModel(model);
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try{

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","sabryazim","5516");
            if(con == null){
                JOptionPane.showMessageDialog(null,"connected failed","failed",JOptionPane.WARNING_MESSAGE);
            }
            int row = jTable1.getSelectedRow();
            int ID = (int) jTable1.getModel().getValueAt(row, 0);
            String query = "UPDATE drug SET TradeName=?, ScName=?, Image = ?,Classification=?,Price=?, Company=?, Company2=?, Unit=?, Date=?, License=?, No=? WHERE ID=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,drugname.getText().isEmpty()? "": drugname.getText());
            st.setString(2,drugScName.getText().isEmpty() ? "": drugScName.getText() );
            st.setBinaryStream(3,imageFile != null ? new FileInputStream(imageFile) : new FileInputStream(".\\_.jpg"));
            st.setString(4,drugClassification.getText().isEmpty() ? "": drugClassification.getText());
            st.setDouble(5, drugPrice.getText().isEmpty() ? 0.0: Double.parseDouble(drugPrice.getText()) );
            st.setString(6,drugCompany.getText().isEmpty() ? "": drugCompany.getText());
            st.setString(7,drugCompany2.getText().isEmpty() ? "": drugCompany2.getText());
            st.setString(8,drugUnit.getText().isEmpty() ? "": drugUnit.getText());
            st.setString(9,drugDate.getText().isEmpty() ? "": drugDate.getText());
            st.setString(10,drugLicense.getText().isEmpty() ? "": drugLicense.getText());
            st.setLong(11, drugNo.getText().isEmpty() ? 0: Long.parseLong(drugNo.getText()));
            st.setInt(12, ID);
            //ResultSet rs = st.executeQuery();
            int rowUpdated = st.executeUpdate();
            if(rowUpdated > 0 ){
                JOptionPane.showMessageDialog(null, "the drug Updated successfully","note",JOptionPane.WARNING_MESSAGE);
            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new mainForm().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btnLoadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadImageActionPerformed
        // TODO add your handling code here:
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Image","jpeg","jpg","ico","bmp"));
        fc.showOpenDialog(null);
        imageFile = fc.getSelectedFile();
        try {
            Image img = ImageIO.read(imageFile);
            ImageIcon icon = new ImageIcon(img);
            lblImage.setIcon(icon);
            
        } catch (IOException ex) {
            Logger.getLogger(UpdateForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoadImageActionPerformed

    private void btnStartWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartWebcamActionPerformed
        // TODO add your handling code here:

        MyThread runn = new MyThread();
        runn.runnable = true;
        runn.capture = new VideoCapture(0,Videoio.CAP_DSHOW);
        Thread th = new Thread(runn);
        th.start();
        btnStartWebcam.setEnabled(false);
        ActionListener al = (ActionEvent ae) -> {
            runn.runnable = false;
            runn.capture.release();
            lblImage.setIcon(null);
            btnStartWebcam.setEnabled(true);
        };
        ActionListener al1 = (ActionEvent ae) -> {
            runn.takePhoto = true;
        };

        btnStopWebcam.addActionListener(al);
        btnTakePhoto.addActionListener(al1);
    }//GEN-LAST:event_btnStartWebcamActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UpdateForm().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadImage;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStartWebcam;
    private javax.swing.JButton btnStopWebcam;
    private javax.swing.JButton btnTakePhoto;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField drugClassification;
    private javax.swing.JTextField drugCompany;
    private javax.swing.JTextField drugCompany2;
    private javax.swing.JTextField drugDate;
    private javax.swing.JTextField drugLicense;
    private javax.swing.JTextField drugNo;
    private javax.swing.JTextField drugPrice;
    private javax.swing.JTextField drugScName;
    private javax.swing.JTextField drugSearchF;
    private javax.swing.JTextField drugUnit;
    private javax.swing.JTextField drugname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImage1;
    // End of variables declaration//GEN-END:variables
}

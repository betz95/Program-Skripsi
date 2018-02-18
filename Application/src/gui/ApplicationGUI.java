package gui;

import engine.GraphDrawer;
import engine.GraphMaker;
import engine.SVGParser;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Albert - 2014730007
 */
public class ApplicationGUI extends javax.swing.JFrame {
    private JFileChooser fileChooser;
    private SVGParser svgParser;
    private GraphMaker graphMaker;
    private GraphDrawer graphDrawer;
    
    /**
     * Creates new form ApplicationGUI
     */
    public ApplicationGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navPanel = new javax.swing.JPanel();
        homeNav = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filesNav = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        generateProblemNav = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        homeContent = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filesContent = new javax.swing.JPanel();
        filesButton = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        generateProblemContent = new javax.swing.JPanel();
        browseButton = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        navPanel.setBackground(new java.awt.Color(54, 33, 89));
        navPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeNav.setBackground(new java.awt.Color(85, 65, 118));
        homeNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeNavMousePressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Home_15px.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Home");

        javax.swing.GroupLayout homeNavLayout = new javax.swing.GroupLayout(homeNav);
        homeNav.setLayout(homeNavLayout);
        homeNavLayout.setHorizontalGroup(
            homeNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeNavLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        homeNavLayout.setVerticalGroup(
            homeNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeNavLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(homeNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        navPanel.add(homeNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 50));

        filesNav.setBackground(new java.awt.Color(64, 43, 100));
        filesNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                filesNavMousePressed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Folder_15px.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Files");

        javax.swing.GroupLayout filesNavLayout = new javax.swing.GroupLayout(filesNav);
        filesNav.setLayout(filesNavLayout);
        filesNavLayout.setHorizontalGroup(
            filesNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesNavLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        filesNavLayout.setVerticalGroup(
            filesNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesNavLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(filesNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        navPanel.add(filesNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, 50));

        generateProblemNav.setBackground(new java.awt.Color(64, 43, 100));
        generateProblemNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                generateProblemNavMousePressed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Refresh_15px.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Generate Problem");

        javax.swing.GroupLayout generateProblemNavLayout = new javax.swing.GroupLayout(generateProblemNav);
        generateProblemNav.setLayout(generateProblemNavLayout);
        generateProblemNavLayout.setHorizontalGroup(
            generateProblemNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generateProblemNavLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        generateProblemNavLayout.setVerticalGroup(
            generateProblemNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generateProblemNavLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(generateProblemNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        navPanel.add(generateProblemNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, 50));

        contentPanel.setLayout(new java.awt.CardLayout());

        homeContent.setBackground(new java.awt.Color(255, 255, 255));

        titlePanel.setBackground(new java.awt.Color(110, 89, 222));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Connecting Dot Problem Generator");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(400, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout homeContentLayout = new javax.swing.GroupLayout(homeContent);
        homeContent.setLayout(homeContentLayout);
        homeContentLayout.setHorizontalGroup(
            homeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeContentLayout.setVerticalGroup(
            homeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeContentLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(441, Short.MAX_VALUE))
        );

        contentPanel.add(homeContent, "card2");

        filesContent.setBackground(new java.awt.Color(255, 255, 255));

        filesButton.setBackground(new java.awt.Color(54, 33, 89));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Open_20px_1.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Files");

        javax.swing.GroupLayout filesButtonLayout = new javax.swing.GroupLayout(filesButton);
        filesButton.setLayout(filesButtonLayout);
        filesButtonLayout.setHorizontalGroup(
            filesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesButtonLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        filesButtonLayout.setVerticalGroup(
            filesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filesButtonLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(filesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout filesContentLayout = new javax.swing.GroupLayout(filesContent);
        filesContent.setLayout(filesContentLayout);
        filesContentLayout.setHorizontalGroup(
            filesContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesContentLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(filesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(308, Short.MAX_VALUE))
        );
        filesContentLayout.setVerticalGroup(
            filesContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filesContentLayout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .addComponent(filesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        contentPanel.add(filesContent, "card2");

        generateProblemContent.setBackground(new java.awt.Color(255, 255, 255));

        browseButton.setBackground(new java.awt.Color(54, 33, 89));
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                browseButtonMousePressed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Open_20px.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Browse");

        javax.swing.GroupLayout browseButtonLayout = new javax.swing.GroupLayout(browseButton);
        browseButton.setLayout(browseButtonLayout);
        browseButtonLayout.setHorizontalGroup(
            browseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browseButtonLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(45, 45, 45))
        );
        browseButtonLayout.setVerticalGroup(
            browseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, browseButtonLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(browseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout generateProblemContentLayout = new javax.swing.GroupLayout(generateProblemContent);
        generateProblemContent.setLayout(generateProblemContentLayout);
        generateProblemContentLayout.setHorizontalGroup(
            generateProblemContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generateProblemContentLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        generateProblemContentLayout.setVerticalGroup(
            generateProblemContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generateProblemContentLayout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        contentPanel.add(generateProblemContent, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeNavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeNavMousePressed
        setColor(homeNav);
        resetColor(generateProblemNav);
        resetColor(filesNav);
        
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(homeContent);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_homeNavMousePressed

    private void filesNavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filesNavMousePressed
        setColor(filesNav);
        resetColor(homeNav);
        resetColor(generateProblemNav);
        
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(filesContent);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_filesNavMousePressed

    private void generateProblemNavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateProblemNavMousePressed
        setColor(generateProblemNav);
        resetColor(homeNav);
        resetColor(filesNav);
        
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(generateProblemContent);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_generateProblemNavMousePressed

    private void browseButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMousePressed
        fileChooser = new JFileChooser(new File("C:/"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("SVG files", "svg"));
        fileChooser.setDialogTitle("Choose SVG File");
        int feedback = fileChooser.showOpenDialog(this);
        if(feedback == JFileChooser.APPROVE_OPTION){
            svgParser = new SVGParser(fileChooser.getSelectedFile());
            svgParser.parseFile();
            graphMaker = new GraphMaker(svgParser.getElements());
            fileChooser = new JFileChooser(new File("C:/"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("HTML files", "html"));
            fileChooser.setDialogTitle("Save Converted File");
            feedback = fileChooser.showSaveDialog(this);
            if(feedback == JFileChooser.APPROVE_OPTION){
                try{
                    graphDrawer = new GraphDrawer(graphMaker.getResult(), svgParser.getUnprocessedElements(), fileChooser.getSelectedFile(), svgParser.getSvgWidth(), svgParser.getSvgHeight());
                    graphDrawer.draw();
                    JOptionPane.showMessageDialog(this, "Berhasil Membuat Soal");
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_browseButtonMousePressed

    void setColor(JPanel panel){
        panel.setBackground(new Color(85, 65, 118));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(64, 43, 100));
    }
    
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
            java.util.logging.Logger.getLogger(ApplicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ApplicationGUI gui = new ApplicationGUI();
                gui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browseButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel filesButton;
    private javax.swing.JPanel filesContent;
    private javax.swing.JPanel filesNav;
    private javax.swing.JPanel generateProblemContent;
    private javax.swing.JPanel generateProblemNav;
    private javax.swing.JPanel homeContent;
    private javax.swing.JPanel homeNav;
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
    private javax.swing.JPanel navPanel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}

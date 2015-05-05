/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourier.fitting.graphic;

import fourier.fitting.Complex;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author caoyuan9642
 */
public class GraphicFitting extends javax.swing.JFrame {

    private boolean show_image = false;
    private boolean show_grid = false;
    private boolean show_curve = false;

    private ArrayList<Point2D> sample_points = new ArrayList<Point2D>(); // points to get fitting
    private int highlight = -1;
    private int dragging = -1;

    private Complex[] coef = new Complex[]{new Complex(0, 0), new Complex(0, 0), new Complex(0, 0)};
    public static final double SCALE = 100;
    private int n = 1;

    /**
     * Creates new form GraphicFitting
     */
    public GraphicFitting() {
        initComponents();
        level_slider.setValue(2);
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
        canvas = new FourierCanvas();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        level_slider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        coefficients_table = new CustomTable();
        button_calculate = new javax.swing.JButton();
        button_clear = new javax.swing.JButton();
        button_loadImage = new javax.swing.JButton();
        checkbox_show_grid = new javax.swing.JCheckBox();
        checkbox_show_curve = new javax.swing.JCheckBox();
        checkbox_show_image = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fourier ");
        setMinimumSize(new java.awt.Dimension(500, 600));

        canvas.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        canvas.setDoubleBuffered(false);
        canvas.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        level_slider.setMajorTickSpacing(1);
        level_slider.setMaximum(10);
        level_slider.setMinimum(1);
        level_slider.setPaintLabels(true);
        level_slider.setValue(1);
        level_slider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        level_slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                level_sliderStateChanged(evt);
            }
        });

        jLabel1.setText("Level n");

        jLabel2.setText("Coefficients");

        coefficients_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(-1),  new Double(0.0),  new Double(0.0), ""},
                { new Integer(0),  new Double(0.0),  new Double(0.0), ""},
                { new Integer(1),  new Double(0.0),  new Double(0.0), null}
            },
            new String [] {
                "n", "Re", "Im", "Complex"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        coefficients_table.setColumnSelectionAllowed(true);
        coefficients_table.setRowHeight(32);
        coefficients_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(coefficients_table);
        coefficients_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (coefficients_table.getColumnModel().getColumnCount() > 0) {
            coefficients_table.getColumnModel().getColumn(3).setResizable(false);
            coefficients_table.getColumnModel().getColumn(3).setPreferredWidth(32);
        }

        button_calculate.setText("Calculate Coefficients");
        button_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_calculateActionPerformed(evt);
            }
        });

        button_clear.setText("Clear Points");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_loadImage.setText("Load Image");
        button_loadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loadImageActionPerformed(evt);
            }
        });

        checkbox_show_grid.setText("Show Grid");
        checkbox_show_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_show_gridActionPerformed(evt);
            }
        });

        checkbox_show_curve.setText("Show Curve");
        checkbox_show_curve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_show_curveActionPerformed(evt);
            }
        });

        checkbox_show_image.setText("Show Image");
        checkbox_show_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_show_imageActionPerformed(evt);
            }
        });

        jCheckBox4.setText("Reserved");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(level_slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(button_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_loadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkbox_show_image)
                                    .addComponent(checkbox_show_grid))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4)
                                    .addComponent(checkbox_show_curve))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button_calculate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(level_slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_clear)
                    .addComponent(button_loadImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(checkbox_show_image))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_show_grid)
                    .addComponent(checkbox_show_curve))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(canvas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkbox_show_gridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_show_gridActionPerformed
        // TODO add your handling code here:
        show_grid = checkbox_show_grid.isSelected();
        repaint();
    }//GEN-LAST:event_checkbox_show_gridActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void checkbox_show_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_show_imageActionPerformed
        // TODO add your handling code here:
        show_image = checkbox_show_image.isSelected();
        repaint();
    }//GEN-LAST:event_checkbox_show_imageActionPerformed

    private void checkbox_show_curveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_show_curveActionPerformed
        // TODO add your handling code here:
        show_curve = checkbox_show_curve.isSelected();
        repaint();
    }//GEN-LAST:event_checkbox_show_curveActionPerformed

    private void level_sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_level_sliderStateChanged
        // TODO add your handling code here:
        n = level_slider.getValue();
        Object model[][] = new Object[2 * n + 1][4];
        for (int i = -n; i <= n; i++) {
            model[i + n][0] = i;
            model[i + n][1] = 0.0;
            model[i + n][2] = 0.0;
            model[i + n][3] = new Complex(0, 0);
        }
        coefficients_table.setModel(new javax.swing.table.DefaultTableModel(
                model,
                new String[]{
                    "n", "Re", "Im", "Complex"
                }
        ));

        coef = new Complex[2 * n + 1];
        for (int i = 0; i < 2 * n + 1; i++) {
            coef[i] = new Complex(0, 0);
        }
    }//GEN-LAST:event_level_sliderStateChanged

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        // TODO add your handling code here:
        sample_points.clear();
        repaint();
    }//GEN-LAST:event_button_clearActionPerformed

    private void button_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_calculateActionPerformed
        // TODO add your handling code here:
        coef[n] = new Complex(0.5, 1);
        coef[n + 1] = new Complex(0.25, 0.1);
        for (int i = 0; i < 2 * n + 1; i++) {
            coefficients_table.setValueAt(coef[i].re(), i, 1);
            coefficients_table.setValueAt(coef[i].im(), i, 2);
            coefficients_table.setValueAt(coef[i], i, 3);
        }
        repaint();
    }//GEN-LAST:event_button_calculateActionPerformed

    private void button_loadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_loadImageActionPerformed
        // TODO add your handling code here:
        ((FourierCanvas)canvas).loadImage();
        if(!show_image){
            checkbox_show_image.setSelected(true);
        }
        repaint();
    }//GEN-LAST:event_button_loadImageActionPerformed

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
            java.util.logging.Logger.getLogger(GraphicFitting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicFitting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicFitting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicFitting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicFitting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_calculate;
    private javax.swing.JButton button_clear;
    private javax.swing.JButton button_loadImage;
    private javax.swing.JPanel canvas;
    private javax.swing.JCheckBox checkbox_show_curve;
    private javax.swing.JCheckBox checkbox_show_grid;
    private javax.swing.JCheckBox checkbox_show_image;
    private javax.swing.JTable coefficients_table;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider level_slider;
    // End of variables declaration//GEN-END:variables

    private class FourierCanvas extends javax.swing.JPanel
            implements MouseListener, MouseMotionListener, ComponentListener {

        private BufferedImage image;
        private AffineTransform trans;
        private double image_scale;
        /**
         * Creates new form FourierCanvas
         */
        public FourierCanvas() {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addComponentListener(this);
            trans = new AffineTransform();
            trans.translate(getWidth() / 2.0, getHeight() / 2.0);
            trans.scale(SCALE, SCALE);
        }

        @Override
        public void paintComponent(Graphics g) {
            //System.out.println("repaint");
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, width, height);

            if (show_image) {
                //g2.drawImage(image, 0, 0, width, height, null);
                Point2D lefttop = new Point2D.Double(0.5*image_scale*image.getWidth(), 0.5*image_scale*image.getHeight());
                System.out.println(lefttop);
                trans.transform(lefttop, lefttop);
                g2.drawImage(image, (int)lefttop.getX(), (int)lefttop.getY(), (int)(image_scale*image.getWidth()), (int)(image_scale*image.getHeight()), this);
            }

            if (show_grid) {
                g2.setColor(Color.yellow);
                for (int x = 0; x <= width / 2.0; x += 50) {
                    g2.draw(new Line2D.Double(width / 2.0 + x, 0, width / 2.0 + x, height));
                    g2.draw(new Line2D.Double(width / 2.0 - x, 0, width / 2.0 - x, height));
                }
                for (int y = 0; y <= height / 2.0; y += 50) {
                    g2.draw(new Line2D.Double(0, height / 2.0 + y, width, height / 2.0 + y));
                    g2.draw(new Line2D.Double(0, height / 2.0 - y, width, height / 2.0 - y));
                }

                g2.setStroke(new BasicStroke(2f));
                g2.draw(new Line2D.Double(width / 2.0, 0, width / 2.0, height));
                g2.draw(new Line2D.Double(0, height / 2.0, width, height / 2.0));

            }

            g2.setStroke(new BasicStroke());
            g2.setColor(Color.red);
            for (int i = 0; i < sample_points.size(); i++) {
                Point2D p = sample_points.get(i);
                Point2D tp = trans.transform(p, null);
                if (highlight == i) {
                    g2.fill(new Ellipse2D.Double(
                            tp.getX() - 4,
                            tp.getY() - 4,
                            8, 8)
                    );
                } else {
                    g2.draw(new Ellipse2D.Double(
                            tp.getX() - 2,
                            tp.getY() - 2,
                            4, 4)
                    );
                }
            }

            g2.setColor(Color.blue);
            //System.out.println(show_curve);
            if (show_curve) {
                ArrayList<Point2D> curve = new ArrayList();
                for (double t = 0; t < 2 * Math.PI; t += Math.PI / 100) {
                    double x = 0, y = 0;
                    for (int i = -n; i <= n; i++) {
                        x += coef[i + n].re() * Math.cos(i * t) - coef[i + n].re() * Math.sin(i * t);
                        y += coef[i + n].im() * Math.cos(i * t) + coef[i + n].re() * Math.sin(i * t);
                    }
                    Point2D tp = trans.transform(new Point2D.Double(x, y), null);
                    //System.out.println(tp.getX() + " " + tp.getY());
                    curve.add(tp);
                }
                for (int i = 0; i < curve.size(); i++) {
                    g2.draw(new Line2D.Double(curve.get(i), curve.get((i + 1) % curve.size())));
                }
            }
        }

        public void loadImage() {
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setCurrentDirectory(new File("."));
            file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = file_chooser.showOpenDialog(GraphicFitting.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    image = ImageIO.read(new File(file_chooser.getSelectedFile().getPath()));
                } catch (IOException ex) {
                    Logger.getLogger(GraphicFitting.class.getName()).log(Level.SEVERE, null, ex);
                }
                image_scale = Math.min(this.getWidth()/image.getWidth(), this.getHeight()/image.getHeight())/SCALE;
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        Point2D press_pos;

        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (highlight != -1) {
                    dragging = highlight;
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) { /*Right click: erase point*/

                if (highlight != -1) {
                    sample_points.remove(highlight);
                    highlight = -1;
                    dragging = -1;
                }
            }
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (dragging == -1) { /*Left click: create point*/
                    /*Create new point*/

                    Point2D newpoint = null;
                    try {
                        newpoint = trans.inverseTransform(e.getPoint(), null);
                    } catch (NoninvertibleTransformException ex) {
                        Logger.getLogger(GraphicFitting.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("clicked at " + newpoint.toString());
                    sample_points.add(newpoint);
                } else {
                    dragging = -1;
                }
            }
            repaint();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            for (int i = 0; i < sample_points.size(); i++) {
                Point2D p = sample_points.get(i);
                if (trans.transform(p, null).distance(e.getPoint()) <= 8) {
                    highlight = i;
                    repaint();
                    return;
                }
            }
            if (highlight != -1) {
                highlight = -1;
                repaint();
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (dragging != -1) {
                try {
                    if (dragging < sample_points.size()) {
                        sample_points.set(dragging, trans.inverseTransform(e.getPoint(), null));
                    }
                } catch (NoninvertibleTransformException ex) {
                    Logger.getLogger(GraphicFitting.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint();
            }
        }

        public void componentResized(ComponentEvent ce) {
            trans.setToIdentity();
            trans.translate(getWidth() / 2.0, getHeight() / 2.0);
            trans.scale(SCALE, SCALE);
        }

        public void componentMoved(ComponentEvent ce) {
        }

        public void componentShown(ComponentEvent ce) {
        }

        public void componentHidden(ComponentEvent ce) {
        }
    }

    private class ComplexIconRenderer extends JComponent implements TableCellRenderer {

        private static final int SIZE = 32;
        private Complex value;

        public ComplexIconRenderer() {
            super();
            value = new Complex(0, 0);
        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawOval(0, 0, SIZE, SIZE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.value = (Complex) value;
            return this;
        }
    }

    private class CustomTable extends JTable {

        private ComplexIconRenderer complex_renderer = new ComplexIconRenderer();

        public CustomTable() {
            super();
        }

        public TableCellRenderer getCellRenderer(int row, int column) {
            if (column == 3) {
                return complex_renderer;
            } else {
                return super.getCellRenderer(row, column);
            }
        }
    }
}

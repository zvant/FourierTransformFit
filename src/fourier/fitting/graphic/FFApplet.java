/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourier.fitting.graphic;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author caoyuan9642
 */
public class FFApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    GraphicFitting frame;

    /**
     *
     */
    public void init() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                // If Nimbus is not available, you can set the GUI to another look and feel.
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                Logger.getLogger(FFApplet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        frame = new GraphicFitting();
        frame.setVisible(true);
        // TODO start asynchronous download of heavy resources
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Application has started in new window.", 0, 20);
    }

    // TODO overwrite start(), stop() and destroy() methods
}

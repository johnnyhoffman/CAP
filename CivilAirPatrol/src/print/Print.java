/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import forms.CommLogView;
import forms.RadioMessageView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Robert
 */
public class Print {
    
public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setSize(800, 1200);
    f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    
    RadioMessageView c = new RadioMessageView();
    
    f.add(c);
    f.setVisible(true);
    //f.setSize(400, 500); // Must set some size, even though it starts
                             // maximized. Else, un-maximizizing the window
                             // may make it ~1 pixel wide
    
    //f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    
    
    saveComponentAsJPEG(c, "testComm.jpg");
}
    
public static void saveComponentAsJPEG(Component myComponent, String filename) {
        
        Dimension size = myComponent.getSize();
        System.out.println(size.toString());
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        try {
            OutputStream out = new FileOutputStream(filename);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(myImage);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
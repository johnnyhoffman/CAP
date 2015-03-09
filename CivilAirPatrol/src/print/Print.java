/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import forms.CommLogView;
import forms.RadioMessageView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Robert
 */
public class Print {
    
	 
		public static void saveComponentAsJPEG(Component myComponent, String filename) {
	        System.out.println("printing: "+filename);
	        //myComponent = myComponent.getComponentAt(2,2);
	        Dimension size = myComponent.getComponentAt(2,2).getComponentAt(2,2).getSize();
	        System.out.println(size.toString());
	        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = myImage.createGraphics();
	        myComponent.getComponentAt(2,2).getComponentAt(2,2).paint(g2);
	        try {
	            OutputStream out = new FileOutputStream(filename);
	            ImageIO.write(myImage, "jpeg", out);
	            out.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	
	}
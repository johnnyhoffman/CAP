/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package print;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Print {

    public static void saveComponentAsJPEG(Component myComponent,
            String filename) {
        System.out.println("printing: " + filename);
        // myComponent = myComponent.getComponentAt(2,2);
        Dimension size = myComponent.getComponentAt(2, 2).getComponentAt(2, 2)
                .getSize();
        System.out.println(size.toString());
        BufferedImage myImage = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.getComponentAt(2, 2).getComponentAt(2, 2).paint(g2);
        try {
            OutputStream out = new FileOutputStream(filename);
            ImageIO.write(myImage, "jpeg", out);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
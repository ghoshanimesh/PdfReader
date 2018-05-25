/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfreader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author jaynam
 */
public class ImageHandler {
    private ArrayList<BufferedImage> list ;
    public void addImage(File file,javax.swing.JPanel imagePanel){
        list = ImageHandler.getImage(file);
        JLabel imageLabel[];
        imageLabel = new JLabel[list.size()];
        imagePanel.removeAll();
        for (int i=0;i<list.size();i++) {
            imageLabel[i] = new JLabel(new ImageIcon(list.get(i))) ;
            imagePanel.add(imageLabel[i]);
            //imagePanel.add(new JLabel("              "));
        }
        imagePanel.validate();
        imagePanel.repaint();
    }
    
    private static ArrayList<BufferedImage> getImage(File file){
        return PDFHandler.extractImage(file.toString());
    }
    
    public void handelButton(javax.swing.JButton[] handler){
        if(list.size() > 1){
                handler[1].setVisible(true);
        }else{
            for(int i=0;i<handler.length;i++)
                handler[i].setVisible(false);
        }
    }
    
    public void handelButton(javax.swing.JButton[] handler , int count ){
        if(count == 0){
            handler[0].setVisible(false);
            handler[1].setVisible(true);
        }else if(count == list.size()-1){
            handler[1].setVisible(false);
            handler[0].setVisible(true);
        }else{
            handler[0].setVisible(true);
            handler[1].setVisible(true);
        }
    }
    
    public static ImageHandler getInstance(){
        return new ImageHandler();
    }
}

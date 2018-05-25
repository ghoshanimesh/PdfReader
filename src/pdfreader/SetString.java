/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfreader;

import javax.swing.JTextArea;
import javax.swing.JLabel;
/**
 *
 * @author jaynam
 */
public class SetString {
    private String text,title;
    SetString(String text,String title){
        this.text = text;
        this.title = title;
    }
    
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setTextOfComponent(JTextArea displayText , JLabel titleLabel){
        displayText.setText(text);
        titleLabel.setText(title);
    }
}

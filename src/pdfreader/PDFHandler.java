/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfreader;

import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author jaynam
 */
public class PDFHandler {

    /*
        @author :-jaynam 
            this is use to read text from pdf and displaying it in text area
        @param :-
            file from where the content would be read
    */

    
    public static String display(File file) throws IOException{
        // load the file into the document 
        PDDocument document = PDDocument.load(file);
        
        //PDFTextStripper have methode to retrieve text from a PDF document
        PDFTextStripper pdfStripper = new PDFTextStripper();
        
        //use to get text of the pdf documents 
        String text = pdfStripper.getText(document);
        return text;
    }
    
    /*
        @author: jaynam
            this methode will check if the file choose is PDF or not
        @param :-
            file :- which will be checked
    */

    /**
     *
     * @param checkFile
     * @return
     * @throws FileNotFoundException
     */
    
    public static boolean isPDF(File checkFile) throws FileNotFoundException{
        Scanner input = new Scanner(new FileReader(checkFile));
        while (input.hasNextLine()) {
            final String checkline = input.nextLine();
            if(checkline.contains("%PDF-")) { 
                return true;
            }  
        }
        return false;
    }
    
    public static ArrayList<BufferedImage> extractImage(String src){

        ArrayList<BufferedImage> imageList;
        imageList = new ArrayList<>();
        try{

            PdfReader pr;
            pr = new PdfReader(src);
            PRStream pst;
            PdfImageObject pio;
            PdfObject po;
            int n=pr.getXrefSize(); //number of objects in pdf document
            for(int i=0;i<n;i++){
                po=pr.getPdfObject(i); //get the object at the index i in the objects collection
                if(po==null || !po.isStream()) //object not found so continue
                    continue;
                pst=(PRStream)po; //cast object to stream
                PdfObject type=pst.get(PdfName.SUBTYPE); //get the object type
                //check if the object is the image type object
                if(type!=null && type.toString().equals(PdfName.IMAGE.toString())){
                    pio=new PdfImageObject(pst); //get the image  
                    //BufferedImage bi=pio.getBufferedImage(); //convert the image to buffered image
                    BufferedImage thumbnail = Thumbnails.of(pio.getBufferedImage()).size(180,220).asBufferedImage();
                    imageList.add(thumbnail);
                    ImageIO.write(thumbnail, "jpg", new File("image"+i+".jpg")); //write the buffered image
                    //to local disk.
                }
            }
        }catch(Exception e){
            return null;
        }
        return imageList;
    }
}
   

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Arco Iris
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDF{
  public PDF() {}
	
  public void crearPDF(String nombre,String producto,String monto,String cantidad) {
    Document document = new Document();
    try{
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombre+".pdf"));
      document.open();
      document.add(new Paragraph("Factura de Pedido #"+nombre));
      document.add(new Paragraph(cantidad+"  X  "+producto));
      document.add(new Paragraph("Total: "+monto));
      document.add(new Paragraph("Gracias por la compra."));
      document.close();
      writer.close();
    } 
    catch(DocumentException e){
      e.printStackTrace();
    } 
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
        
  public static void main(String[] args){
    PDF hola = new PDF();
    hola.crearPDF("joss","huevos", "400", "3");
    /* String userHomeFolder = System.getProperty("user.home");
    System.out.println(userHomeFolder);*/
  }
}
	

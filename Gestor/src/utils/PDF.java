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
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import modelo.Curso;
 
public class PDF{
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
  private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
  private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
  private static String plan;
  public PDF(String pPlan) {
    plan = pPlan;
  }
	
  public void crearPDF(String nombre,ObservableList<Curso> cursos) {
    Document document = new Document();
    
    
    try{
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombre+ ".pdf"));
      document.open();
      /*document.add(new Paragraph("Factura de Pedido #"+nombre));
      document.add(new Paragraph(cantidad+"  X  "+producto));
      document.add(new Paragraph("Total: "+monto));
      document.add(new Paragraph("Gracias por la compra."));*/
      document.add(new Paragraph("Plan de Estudio Numero : " + plan,catFont ));
      document.add(new Paragraph(" "));
      document.add(createTable(cursos));
      
      //addContent(document,cursos);
      
      document.close();
      writer.close();
      System.out.println("paso");
    } 
    catch(DocumentException e){
      e.printStackTrace();
    } 
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
  


    
   private static PdfPTable createTable(ObservableList<Curso> cursos) throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Codigo"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nombre"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Bloque"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Horas Lectivas"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Créditos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

      /* table.addCell("1.0");
      table.addCell("1.1");
      table.addCell("1.2");
      table.addCell("2.1");
      table.addCell("2.2");
      table.addCell("2.3");*/
      for (Curso curso : cursos) {
        table.addCell(curso.getCodigo());
        table.addCell(curso.getNombre());
        table.addCell(curso.getBloque());
        table.addCell(Integer.toString(curso.getCreditos()));
        table.addCell(Integer.toString(curso.getHorasLectivas()));
      }
      return table;

    }
   
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
  
  
  public static void main(String[] args){
    PDF hola = new PDF("plan");
    hola.crearPDF("joss",null);
    /* String userHomeFolder = System.getProperty("user.home");
    System.out.println(userHomeFolder);*/
  }
}
	

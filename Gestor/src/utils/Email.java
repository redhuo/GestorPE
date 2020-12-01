/*
 * Clase encargada de permitir al sistema mandar por correo electrónico los diferentes planes de estudio 
 * que estan registrados en el sistema.
 */
package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email {
  String correo;
  String archivo;
  String numeroPlan;
  
  /**
   * Mètodo cconstructor de la clase Email, recibe como entrada el correo electrònico 
   * de la persona destinaria, la direcciòn deonde esta guardado el archivo y el nùmero del plan
   * que se esta mandando.
   * @param correo
   * @param archivo
   * @param numeroPlan 
   */
  public Email(String correo,String archivo, String numeroPlan)  {
    this.correo = correo;
    this.archivo = archivo;
    this.numeroPlan = numeroPlan;
  }
  /**
   * Método de la clase Email que se encarga de hacer el trabajo de enviar el plan del curso en formato 
   * PDF a la persona destinaria. 
   * Imprimime un mensaje de éxito si fue posible el envío, de lo contrario imprime una excepción.
   * Retorna vacío.
   * @throws MessagingException 
   */
  public void enviar() throws MessagingException {
    String host = "smtp.gmail.com";
    String Password = "rapiexpress1234";
    String from = "rapiexpressprueba@gmail.com";
    String toAddress = this.correo;
    String filename = this.archivo;
    // Get system properties
    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtps.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    Session session = Session.getInstance(props, null);
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO, toAddress);
    message.setSubject("Envio de plan de estudio");
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Plan de Estudio numero: "+ this.numeroPlan);
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);
    messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(filename);
    multipart.addBodyPart(messageBodyPart);
    message.setContent(multipart);
    try {
      Transport tr = session.getTransport("smtps");
      tr.connect(host, from, Password);
      tr.sendMessage(message, message.getAllRecipients());
      System.out.println("Mensaje se ha mandado");
      tr.close();
    } 
    catch (SendFailedException sfe) {
      System.out.println(sfe);
    }
  }
       
    /*public static void main(String args[]) throws MessagingException{
        Email sm = new Email();
        sm.enviar();
    }*/
}
